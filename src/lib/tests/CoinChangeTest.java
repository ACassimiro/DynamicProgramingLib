package lib.tests;

import lib.DynamicAlgorithms;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Marcelo on 08/10/2016.
 */
public class CoinChangeTest {

    @Test
    public void coinChangeTest1() throws Exception {
        int[] coins = {2, 5, 3, 6};
        int change = 10;

        int output = DynamicAlgorithms.coinChange(coins, change);

        assertEquals(5, output);
    }

    @Test
    public void coinChangeTest2() throws Exception {
        int[] coins = {2, 4, 6};
        int change = 8;

        int output = DynamicAlgorithms.coinChange(coins, change);

        assertEquals(4, output);
    }

    @Test
    public void coinChangeTest3() throws Exception {
        int[] coins = {};
        int change = 10; // any number, doesn't matter

        int output = DynamicAlgorithms.coinChange(coins, change);

        assertEquals(0, output);
    }

    @Test
    public void coinChangeTest4() throws Exception {
        int[] coins = {5, 10, 25, 50};
        int change = 0;

        int output = DynamicAlgorithms.coinChange(coins, change);

        assertEquals(1, output);
    }

    @Test
    public void coinChangeTest5() throws Exception {
        int[] coins = {2, 5, 10};
        int change = 15;

        int output = DynamicAlgorithms.coinChange(coins, change);

        assertEquals(3, output);
    }
}