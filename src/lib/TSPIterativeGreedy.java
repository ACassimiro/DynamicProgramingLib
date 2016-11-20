/* Copyright 2016 Aellison Cassimiro
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

import java.io.File;
import java.util.*;

import org.moeaframework.problem.tsplib.Tour;

public class TSPIterativeGreedy {

	private TSPInstance instance;
	private Tour tour;
	private double averageFitness;
	private double bestFitness;
	private Tour bestTour;
	private ArrayList<Tour> tourList;
	private long executionTime;

	public TSPIterativeGreedy(){
		averageFitness = 0;
		executionTime = 0;
		bestFitness = Double.MAX_VALUE;
		tourList = new ArrayList<Tour>();
	}


	public double getBestFitness(){
		return this.bestFitness;

	}
	
	public double getAverageFitness(){
		return this.averageFitness;
	}

	public Tour getBestTour(){
		return this.bestTour;
	}

	public ArrayList<Tour> getTourList(){
		return this.tourList;
	}

	public Tour getTour(){
	    return this.tour;
	}

	public long getExecutionTime(){
		return this.executionTime;
	}

	public double getCurrentFitness(){
		DistanceTable graph = instance.getDistanceTable();
		int []path = tour.toArray();
		double fitness = 0;

		for(int i = 0; i < graph.listNodes().length - 1; i++){
			fitness += graph.getDistanceBetween(path[i], path[i+1]); 
		}
			
		return fitness;
	}

	public int[] createPath(int begin, int end, int destructionFactor, int[] visited){
		int [] path = new int[destructionFactor];
		ArrayList<Integer> pathList = new ArrayList();
		int currentNode, nextNode;
		double lower, distance;

		List<Integer> visitedList = new ArrayList<Integer>(visited.length);

		for (int i=0; i<visited.length; i++){
		    visitedList.add(visited[i]);
		}

		DistanceTable graph = instance.getDistanceTable();

		int []neighbors;

		currentNode = begin;
		while(true){
			neighbors = graph.getNeighborsOf(currentNode);

			if(pathList.size() == destructionFactor){
				break;
			}

			lower = Double.MAX_VALUE;
			nextNode = -1;

			for(int i = 0; i < neighbors.length; i++){
				distance = graph.getDistanceBetween(currentNode, neighbors[i]);
				
				if((lower > distance) && !(visitedList.contains(neighbors[i]))){
					lower = distance;
					nextNode = neighbors[i];
				}
			}

			if(nextNode == -1){
				System.out.println("ERROR: NO PATH FOUND");
				System.exit(0);
			}

			visitedList.add(nextNode);
			pathList.add(nextNode);
			currentNode = nextNode;

		}


		for(int i = 0; i < path.length; i++){
			path[i] = pathList.get(i);
		}

		return path;
	}

	int lastDPoint = -1;
	public Tour rebuildTour(Tour originalTour){
		int []arrTour = originalTour.toArray();
		int destructionFactor;
		int destructionPoint;
		int i;
		int []ruinedArrayTour;
		int []rebuiltPath;
		int currentRandom = 0;

		Random r = new Random();

		destructionFactor = (int) (arrTour.length * 0.3);

		destructionPoint = (int) (arrTour.length * (r.nextInt(7)) * 0.1);
		while(lastDPoint == destructionPoint){
			destructionPoint = (int) (arrTour.length * (r.nextInt(7)) * 0.1);
		}

		lastDPoint = destructionPoint;

		if(destructionPoint == 0){
			destructionPoint++;
		} else if(destructionPoint == arrTour.length){
			destructionPoint--;
		}
		
		ruinedArrayTour = new int[arrTour.length - destructionFactor];

		for(i = 0; i < destructionPoint; i ++){
			ruinedArrayTour[i] = arrTour[i];	
		}

		for(; i < ruinedArrayTour.length; i++){
			ruinedArrayTour[i] = arrTour[i + destructionFactor];
		}

		rebuiltPath = createPath(ruinedArrayTour[destructionPoint - 1], ruinedArrayTour[destructionPoint - 1], destructionFactor,ruinedArrayTour);

		for(i = 0; i < destructionPoint; i++) {
			arrTour[i] = ruinedArrayTour[i]; 
		}

		for(i = 0; i < destructionFactor; i++) {
			arrTour[i + destructionPoint] = rebuiltPath[i]; 
		}

		for(i = destructionPoint + destructionFactor; i < arrTour.length; i++) { 
			arrTour[i] = ruinedArrayTour[i - destructionFactor]; 
		}

		originalTour.fromArray(arrTour);

		return originalTour;
	}



	public void IG(String path, int poolSize){
		double fitness = 0;
		long beginTime, endTime;
		instance = new TSPInstance();

		File instanceFile = new File("../../data/tsp/" + path);

		instance.getDistanceTable();

		try{
			instance.load(instanceFile);
		} catch (Exception e){
			System.out.println("Erro no arquivo");
			System.exit(0);
		}

		DistanceTable grafo = instance.getDistanceTable();
		
		lastDPoint = -1;
		tour = Tour.createRandomTour(instance.getDimension());
		for(int i = 0; i < poolSize; i++){
			beginTime = System.currentTimeMillis();
			tour = rebuildTour(tour);
			endTime = System.currentTimeMillis();

			executionTime += endTime - beginTime; 

			fitness = getCurrentFitness();

			if(fitness < bestFitness){
				bestFitness = fitness;
				bestTour = this.tour;
			}

			tourList.add(this.tour);
			averageFitness += fitness;
		}

		averageFitness = averageFitness/poolSize;

	} 
}
