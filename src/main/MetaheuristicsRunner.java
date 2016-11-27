package main;

import lib.Memetic;
import lib.TSPIterativeGreedy;
import lib.TestInstance;

/**
 * Provides a rudimentary way to test the solutions for the TSP problem.
 * Created by Marcelo on 20/11/2016.
 */
public class MetaheuristicsRunner {

    public static void main(String[] args) {

        TestInstance testInstance = cmdLineParser(args);

        if (testInstance == null)
            System.exit(1);

        if (args[0].equals("-m"))
            runMemetic(testInstance);

        if (args[0].equals("-i"))
            runIG(testInstance);

    }

    /**
     * Runs the Memetic Algorithm with the instance chosen.
     * @param testInstance instance of TSPLib
     */
    private static void runMemetic(TestInstance testInstance) {
        System.out.println("---------- " + testInstance.getName() + " ----------");
        Memetic solution = new Memetic();
        for (int i = 0; i < testInstance.getTestRuns(); i++) {

            solution = new Memetic(testInstance.getName());
            solution.run(testInstance.getTimeLimit());

            System.out.println("Distance found for run " + (i+1) + ": " + solution.getDistance());

        }
        System.out.println("Optimal distance: " + solution.getOptimalDistance());
    }

    /**
     * Runs the Iterated Greedy with the instance chosen.
     * @param testInstance instance of TSPLib
     */
    private static void runIG(TestInstance testInstance) {
        System.out.println("---------- " + testInstance.getName() + " ----------");
        TSPIterativeGreedy solution = new TSPIterativeGreedy();
        for (int i = 0; i < testInstance.getTestRuns(); i++) {

            solution.IG(testInstance.getName(), testInstance.getTimeLimit());

            System.out.println("Distance found for run " + (i+1) + ": " + solution.getBestFitness());
        }
    }

    /**
     * Command line parser
     * @param args instance name, timelimit, numeber of test runs
     * @return TestInstance ready to run
     */
    private static TestInstance cmdLineParser(String[] args) {

        switch (args.length) {
            case 3: {
                String name = args[1];
                int timeLimit = Integer.parseInt(args[2]);
                int testRuns = 1;
                return new TestInstance(name, timeLimit, testRuns);
            }
            case 4: {
                String name = args[1];
                int timeLimit = Integer.parseInt(args[2]);
                int testRuns = Integer.parseInt(args[3]);
                return new TestInstance(name, timeLimit, testRuns);
            }
        }

        System.out.println("Usage: <-m | -i> <instance_name time_limit> [test_runs]\n" +
                           "    -m: runs with Memetic Algorithm\n" +
                           "    -i: runs with Iterated Greedy\n" +
                           "\n" +
                           "    instance_name: one of the instances thats in the data/tsp folder" +
                           "\n" +
                           "    [optional] test_runs: number of test runs for the instance");

        return null;
    }
}
