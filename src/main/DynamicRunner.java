package main;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import lib.tests.*;
/**
 * Runs the JUnit tests for the Dynamic Algorithms.
 * Created by Marcelo on 17/11/2016.
 */
public class DynamicRunner {

    public static void main(String[] args) {

        System.out.println("---------------------------------");
        printResults(JUnitCore.runClasses(ActivitySelectionTest.class), ActivitySelectionTest.class.getName());

        System.out.println("---------------------------------");
        printResults(JUnitCore.runClasses(BoxStackingTest.class), BoxStackingTest.class.getName());

        System.out.println("---------------------------------");
        printResults(JUnitCore.runClasses(CoinChangeTest.class), lib.tests.CoinChangeTest.class.getName());

        System.out.println("---------------------------------");
        printResults(JUnitCore.runClasses(lib.tests.FloydWarshallTest.class), FloydWarshallTest.class.getName());

        System.out.println("---------------------------------");
        printResults(JUnitCore.runClasses(lib.tests.FractionalKnapsackTest.class), FractionalKnapsackTest.class.getName());

        System.out.println("---------------------------------");
        printResults(JUnitCore.runClasses(lib.tests.GraphColoringTest.class), GraphColoringTest.class.getName());

        System.out.println("---------------------------------");
        printResults(JUnitCore.runClasses(lib.tests.KnapsackTest.class), KnapsackTest.class.getName());

        System.out.println("---------------------------------");
        printResults(JUnitCore.runClasses(lib.tests.LongestCommonSubsequenceTest.class), LongestCommonSubsequenceTest.class.getName());

        System.out.println("---------------------------------");
        printResults(JUnitCore.runClasses(lib.tests.MatrixMultiplicationTest.class), MatrixMultiplicationTest.class.getName());

        System.out.println("---------------------------------");
        printResults(JUnitCore.runClasses(lib.tests.MinEditDistanceTest.class), MinEditDistanceTest.class.getName());

        System.out.println("---------------------------------");
        printResults(JUnitCore.runClasses(lib.tests.PrimMinTreeTest.class), PrimMinTreeTest.class.getName());

        System.out.println("---------------------------------");
        printResults(JUnitCore.runClasses(lib.tests.SubsetSumTest.class), SubsetSumTest.class.getName());

        System.out.println("---------------------------------");
        printResults(JUnitCore.runClasses(lib.tests.WordBreakTest.class), WordBreakTest.class.getName());
        System.out.println("---------------------------------");

    }

    private static void printResults(Result result, String testName) {

        System.out.println(testName);
        System.out.println("Number of test cases: " + result.getRunCount());

        System.out.println("Failed tests: " + result.getFailureCount());

        for (Failure failure : result.getFailures())
            System.out.println("    " + failure.toString());

        System.out.println("Time taken: " + result.getRunTime() + "ms");
    }
}
