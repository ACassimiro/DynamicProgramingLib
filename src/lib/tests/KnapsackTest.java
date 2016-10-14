package lib.tests;

import lib.Item;
import lib.DynamicAlgorithms;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Some data sets retrieved from:
 * <a href=https://people.sc.fsu.edu/~jburkardt/datasets/knapsack_01/knapsack_01.html></a>
 * Created by Marcelo on 08/10/2016.
 */
public class KnapsackTest {

    @Test
    public void knapSackTest1() throws Exception {
        int sizeOfKnapsack = 50;

        List<Item> binaryKnapsack = new ArrayList<Item>() {{
            add(new Item(60, 10));
            add(new Item(100, 20));
            add(new Item(120, 30));
        }};

        int output = DynamicAlgorithms.knapSack(binaryKnapsack, sizeOfKnapsack);

        assertEquals(220, output);
    }

    @Test
    public void knapSackTest2() throws Exception {
        int sizeOfKnapsack = 0;

        List<Item> binaryKnapsack = new ArrayList<Item>() {{
            add(new Item(60, 10));
            add(new Item(100, 20));
            add(new Item(120, 30));
        }};

        int output = DynamicAlgorithms.knapSack(binaryKnapsack, sizeOfKnapsack);

        assertEquals(0, output);
    }

    @Test
    public void knapSackTest3() throws Exception {
        int sizeOfKnapsack = 50;

        List<Item> binaryKnapsack = new ArrayList<>();

        int output = DynamicAlgorithms.knapSack(binaryKnapsack, sizeOfKnapsack);

        assertEquals(0, output);
    }

    @Test
    public void knapSackTest4() throws Exception {
        int sizeOfKnapsack = 165;

        List<Item> binaryKnapsack = new ArrayList<Item>() {{
            add(new Item(92, 23));
            add(new Item(57, 31));
            add(new Item(49, 29));
            add(new Item(68, 44));
            add(new Item(60, 53));
            add(new Item(43, 38));
            add(new Item(67, 63));
            add(new Item(84, 85));
            add(new Item(87, 89));
            add(new Item(72, 82));
        }};

        int output = DynamicAlgorithms.knapSack(binaryKnapsack, sizeOfKnapsack);

        assertEquals(309, output);
    }

    @Test
    public void knapSackTest5() throws Exception {
        int sizeOfKnapsack = 165;

        List<Item> binaryKnapsack = new ArrayList<Item>() {{
            add(new Item(24, 12));
            add(new Item(13, 7));
            add(new Item(23, 11));
            add(new Item(15, 8));
            add(new Item(16, 9));
        }};

        int output = DynamicAlgorithms.knapSack(binaryKnapsack, sizeOfKnapsack);

        assertEquals(91, output);
    }

}