package lib;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 * Created by Marcelo on 09/10/2016.
 */
public class DynamicAlgorithms {

    //  TODO Documentation
    public static List<Integer> activitySelection(List<Activity> activities) {

        List<Integer> list = new ArrayList<>();
        list.add(0);

        Activity running = activities.get(0);
        Activity activity;

        for (int i = 1; i < activities.size(); i++) {
            activity = activities.get(i);
            if (activity.getStartTime() >= running.getFinishTime()) {
                list.add(i);
                running = activity;
            }
        }

        return list;
    }

    //  TODO Documentation
    public static int coinChange(int[] coins, int change) {

        if (coins.length <= 0)
            return 0;

        int[][] table = new int[change + 1][coins.length];
        int x, y;

        for (int i = 0; i < coins.length; i++)
            table[0][i] = 1;

        for (int i = 1; i < change + 1; i++) {
            for (int j = 0; j < coins.length; j++) {

                x = (i - coins[j]) >= 0 ? table[i - coins[j]][j] : 0;

                y = j >= 1 ? table[i][j - 1] : 0;

                table[i][j] = x + y;
            }
        }

        return table[change][coins.length - 1];
    }

    //  TODO Documentation
    public static double fractionalKnapsack(List<Item> items, int capacity) {

        items.sort((o1, o2) -> (o2.getValue()/o2.getWeight()) - (o1.getValue()/o1.getWeight()));

        int curWeight = 0;
        double finalValue = 0.0;

        for (Item item: items) {
            if (curWeight + item.getWeight() <= capacity) {
                curWeight += item.getWeight();
                finalValue += item.getValue();
            } else {
                int remain = capacity - curWeight;
                finalValue += item.getValue() * ((double) remain / item.getWeight());
                break;
            }
        }

        return finalValue;
    }

    //  TODO Documentation
    public static int knapSack(List<Item> items, int capacity) {

        int table[][] = new int[items.size()+1][capacity+1];

        for (int i = 0; i <= items.size(); i++) {
            for (int w = 0; w <= capacity; w++) {
                if (i != 0 && w != 0)
                    if (items.get(i-1).getWeight() <= w)
                        table[i][w] = Math.max(items.get(i-1).getValue() + table[i-1][w - items.get(i-1).getWeight()],
                                           table[i-1][w]);
                    else
                        table[i][w] = table[i-1][w];
            }
        }

        return table[items.size()][capacity];
    }

    //  TODO Documentation
    public static boolean wordBreak(List<String> dictionary, String str) {

        if (str.length() == 0)  return true;

        boolean[] table = new boolean[str.length() + 1];

        for (int i = 0; i <= str.length(); i++) {
            if (!table[i] && dictionary.contains(str.substring(0, i)))
                table[i] = true;

            if (table[i]) {
                if (i == str.length())
                    return true;

                for (int j = i + 1; j <= str.length(); j++) {
                    if (!table[j] && dictionary.contains(str.substring(i, j)))
                        table[j] = true;

                    if (j == str.length() && table[j])
                        return true;
                }
            }
        }

        return false;
    }

}
