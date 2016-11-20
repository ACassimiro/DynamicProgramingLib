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

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import org.moeaframework.problem.tsplib.DistanceTable;
import org.moeaframework.problem.tsplib.TSPInstance;

abstract class TSPAlgorithm {

    static TSPInstance instance;
	private DistanceTable distanceTable;
	Random rnd = new Random();
    double optimalDistance;

    TSPAlgorithm(String s) {
        try {
            instance = new TSPInstance(new File("./data/tsp/" + s + ".tsp"));

            // Set optinal distance
            instance.addTour(new File("./data/tsp/" + s + ".opt.tour"));
            optimalDistance = instance.getTours().get(0).distance(instance);

    } catch (IOException e) {
            System.out.println("Instance '" + s + "' not found." +
                    "\nInstance file should be at" +
                    "\n    ../data/tsp/<instance_name>.tsp");
            System.exit(1);
        }
        distanceTable = instance.getDistanceTable();
    }

    int getNearestCity(int city, Set<Integer> endpoints) {

        Iterator<Integer> it = endpoints.iterator();
        int nearestCity = 0;
        double minDistance = Double.MAX_VALUE;

        while (it.hasNext()) {
            int i = it.next();
            if (distanceTable.getDistanceBetween(city, i) < minDistance) {
                minDistance = distanceTable.getDistanceBetween(city, i);
                nearestCity = i;
            }
        }

        return nearestCity;
    }

    public double getOptimalDistance() {
        return this.optimalDistance;
    }
}
