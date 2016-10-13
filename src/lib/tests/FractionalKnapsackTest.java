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
    public void fractionalKnapsack() throws Exception {

        int sizeOfKnapsack = 50;

        List<Item> items = new ArrayList<>();

        items.add(new Item(60, 10));
        items.add(new Item(120, 30));
        items.add(new Item(100, 20));

        double output = DynamicAlgorithms.fractionalKnapsack(items, sizeOfKnapsack);

        assertEquals(240, output, 0.1);
    }

}