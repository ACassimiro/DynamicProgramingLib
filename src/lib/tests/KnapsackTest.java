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
public class KnapsackTest {

    @Test
    public void knapSack() throws Exception {
        int sizeOfKnapsack = 50;

        List<Item> binaryKnapsack = new ArrayList<>();

        binaryKnapsack.add(new Item(60, 10));
        binaryKnapsack.add(new Item(100, 20));
        binaryKnapsack.add(new Item(120, 30));

        int output = DynamicAlgorithms.knapSack(binaryKnapsack, sizeOfKnapsack);

        assertEquals(220, output);
    }

}