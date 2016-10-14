package lib.tests;

import lib.Item;
import lib.DynamicAlgorithms;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Marcelo on 08/10/2016.
 */
public class FractionalKnapsackTest {

    @Test
    public void fractionalKnapsackTest1() throws Exception {

        int sizeOfKnapsack = 50;

        List<Item> items = new ArrayList<>();

        items.add(new Item(60, 10));
        items.add(new Item(120, 30));
        items.add(new Item(100, 20));

        double output = DynamicAlgorithms.fractionalKnapsack(items, sizeOfKnapsack);

        assertEquals(240, output, 0.1);
    }

    @Test
    public void fractionalKnapsackTest2() throws Exception {
        int sizeOfKnapsack = 0;

        List<Item> binaryKnapsack = new ArrayList<Item>() {{
            add(new Item(60, 10));
            add(new Item(100, 20));
            add(new Item(120, 30));
        }};

        double output = DynamicAlgorithms.fractionalKnapsack(binaryKnapsack, sizeOfKnapsack);

        assertEquals(0, output, 0.1);
    }

    @Test
    public void fractionalKnapsackTest3() throws Exception {
        int sizeOfKnapsack = 50;

        List<Item> binaryKnapsack = new ArrayList<>();

        double output = DynamicAlgorithms.fractionalKnapsack(binaryKnapsack, sizeOfKnapsack);

        assertEquals(0, output, 0.1);
    }

    @Test
    public void fractionalKnapsackTest4() throws Exception {
        int sizeOfKnapsack = 165;

        List<Item> binaryKnapsack = new ArrayList<Item>() {{
            add(new Item(57, 31));
            add(new Item(49, 29));
            add(new Item(68, 44));
            add(new Item(43, 38));
            add(new Item(67, 63));
            add(new Item(84, 85));
            add(new Item(87, 89));
            add(new Item(72, 82));
        }};

        double output = DynamicAlgorithms.fractionalKnapsack(binaryKnapsack, sizeOfKnapsack);

        assertEquals(241.5, output, 0.1);
    }

    @Test
    public void fractionalKnapsackTest5() throws Exception {
        int sizeOfKnapsack = 165;

        List<Item> binaryKnapsack = new ArrayList<Item>() {{
            add(new Item(24, 12));
            add(new Item(13, 7));
            add(new Item(15, 8));
            add(new Item(16, 9));
        }};

        double output = DynamicAlgorithms.fractionalKnapsack(binaryKnapsack, sizeOfKnapsack);

        assertEquals(68, output, 0.1);
    }

}