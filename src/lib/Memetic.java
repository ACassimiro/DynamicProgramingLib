/* Copyright 2016 Marcelo Aguiar
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to
 * deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or
 * sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 * IN THE SOFTWARE.
 */

package lib;

import java.util.*;

import org.moeaframework.problem.tsplib.TSP2OptHeuristic;
import org.moeaframework.problem.tsplib.Tour;

public class Memetic extends TSPAlgorithm {

    private int poolSize;
    private Candidate[] candidates;

    public Memetic(String s, int poolSize) {
        super(s);
        this.poolSize = poolSize;
        this.candidates = new Candidate[poolSize];
    }

    public Candidate executeAlgorithm(int timeLimit) {

        int timeLimitInSeconds = 1000 * timeLimit;
        long time = System.currentTimeMillis();

        initializePopulation();

        Candidate bestSolutionFound = getCandidateWithBestFitness();

        while (System.currentTimeMillis() - time <= timeLimitInSeconds) {

            recombinePopulation();
            mutatePopulation();
            improvePopulation();

            double bestFitnessOfCurrentIteration = getCandidateWithBestFitness().getFitness();

            if (bestFitnessOfCurrentIteration < bestSolutionFound.getFitness())
                bestSolutionFound = getCandidateWithBestFitness();
        }

        return bestSolutionFound;
    }

    private void initializePopulation() {
        for (int i = 0; i < poolSize; i++) {
            Tour tour = Tour.createRandomTour(instance.getDimension());

            this.candidates[i] = new Candidate(tour, instance);
        }
    }

    private void recombinePopulation() {
        LinkedList<Integer> toReplace = new LinkedList<>();
        LinkedList<Integer> toRecombine = new LinkedList<>();

        double best = getCandidateWithBestFitness().getFitness();

        double avg = Arrays.stream(candidates)
                .mapToDouble(Candidate::getFitness)
                .average()
                .getAsDouble();

        for (int i = 0; i < poolSize; i++) {
            double d = candidates[i].getFitness();

            double p_survive = 0.5 * (avg - d) / (avg - best) + 0.5;
            double p_recombine = (avg - d) / (avg - best);

            if (Math.random() > p_survive)
                toReplace.add(i);
            if (Math.random() < p_recombine)
                toRecombine.add(i);
        }

        // Treatment of rare case where almost all solutions are chosen to be replaced
        if (toReplace.size() > 0.9 * poolSize)
            for (int i = 0; i < 0.4 * poolSize; i++)
                toReplace.remove();


        // Treatment of rare case where almost no solutions are chosen to be recombined
        if (toRecombine.size() < 0.2 * poolSize)
            for (int i = 0; i < 0.2 * poolSize; i++) {
                int k = rnd.nextInt(poolSize);
                if (!toRecombine.contains(k))
                    toRecombine.add(k);
            }

        int n = toRecombine.size();
        for (int i: toReplace) {
            int indexOfParent1 = toRecombine.get(rnd.nextInt(n));
            int indexOfParent2 = toRecombine.get(rnd.nextInt(n));

            while (indexOfParent1 == indexOfParent2)
                indexOfParent2 = toRecombine.get(rnd.nextInt(n));

            Tour parent1 = candidates[indexOfParent1].getTour();
            Tour parent2 = candidates[indexOfParent2].getTour();

            Tour offspring = dpx(parent1,parent2);

            candidates[i] = new Candidate(offspring, instance);
        }
    }

    private void mutatePopulation() {
        for (int i = 0; i < poolSize; i++) {
            //perform a randomly selected 4-change with probability 0.1, assumes size of TSP > 8
            if (Math.random() < 0.2) {
                candidates[i].getTour().fourChange();
                candidates[i].setFitness(instance);
            }
        }
    }

    private void improvePopulation() {
        TSP2OptHeuristic heuristic = new TSP2OptHeuristic(instance);
        for (int i = 0; i < poolSize; i++) {
            heuristic.apply(candidates[i].getTour());
            candidates[i].setFitness(instance);
        }
    }

    private Candidate getCandidateWithBestFitness() {
        return Arrays.stream(candidates)
                .min((o1, o2) -> Double.compare(o1.getFitness(), o2.getFitness()))
                .get();
    }

    private Tour dpx(Tour t1, Tour t2) {
        int[] parent1 = t1.toArray();
        int[] parent2 = t2.toArray();
        int n = parent1.length;
        int[][] neighbours = new int[n][2];

        for (int i = 0; i < n; i++) {
            neighbours[parent2[i] - 1][0] = parent2[Math.abs(i - 1) % n];
            neighbours[parent2[i] - 1][1] = parent2[Math.abs(i + 1) % n];
        }

        // Divide into fragments (the list contains the indices of the end of a fragment, endpoints)
        List<ArrayList<Integer>> fragments = new ArrayList<>();
        int[] fragmentNumberOfCity = new int[n];
        Set<Integer> endpoints = new HashSet<>();
        int fragmentIndex = -1;
        int k = 0;

        while (k < n) {
            fragmentIndex++;
            ArrayList<Integer> fragment = new ArrayList<>();
            fragment.add(parent1[k]);
            fragmentNumberOfCity[parent1[k] - 1] = fragmentIndex;
            endpoints.add(parent1[k]);
            k++;
            while ((k < n) && (
                            parent1[k % n] == neighbours[parent1[k - 1] - 1][0]
                         || parent1[k % n] == neighbours[parent1[k - 1] - 1][1]
                        )
                    ) {
                fragment.add(parent1[k]);
                fragmentNumberOfCity[parent1[k]-1] = fragmentIndex;
                k++;
            }
            fragments.add(fragment);
            endpoints.add(parent1[k - 1]);
        }

        // recombine(creates child tour)
        ArrayList<Integer> child = new ArrayList<>();
        fragmentIndex = 0;

        for (int i = 0; i < fragments.size(); i++) {
            ArrayList<Integer> fragment = fragments.get(fragmentIndex);
            child.addAll(fragment);
            endpoints.remove(fragment.get(0));
            int end = fragment.get(fragment.size() - 1);
            endpoints.remove(end);
            // Reconnect to the nearest city (by determining the new fragmentIndex)

            if (endpoints.isEmpty())
                break;
            fragmentIndex = fragmentNumberOfCity[getNearestCity(end, endpoints)-1];
        }

        int[] arr = child.stream()
                         .mapToInt(Integer::intValue)
                         .toArray();

        return Tour.createTour(arr);
    }

}
