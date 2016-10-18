package lib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * TODO
 * Created by Marcelo on 09/10/2016.
 */
public class DynamicAlgorithms {

    /**
     * Provided with a List of Activity's returns a sublist with the activities
     * that can be done by one single person, one at the time. The activities must be
     * sorted by their finishing time.
     * @param activities List of activities
     * @return List
     */
    public static List<Integer> activitySelection(List<Activity> activities) {

        List<Integer> list = new ArrayList<>();

        if (activities.isEmpty())
            return list;

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

    /**
     * Provided with a list of coins and a change returns how many different ways
     * there is to achieve that change with the list of coins.
     * @param coins array of coin values
     * @param change change required
     * @return number of arrangements of the change
     */
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

    /**
     * Provided with a List of Item's and a knapsack size returns the maximum profit
     * that can be achieved by filling the knapsack. In this case a Item is not
     * discrete, it can be split and not lose it's value (eg.: grains).
     * @param items list of Item's
     * @param capacity size of the knapsack
     * @return maximum profit
     */
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

    /**
     * Provided with a List of Item's and a knapsack size returns the maximum profit
     * that can be achieved by filling the knapsack. In this case an Item is a solid
     * object that can not be split without losing it's value.
     * @param items list of Item's
     * @param capacity size of the knapsack
     * @return maximum profit
     */
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

    /**
     * Given a dictionary of words and a word returns true if it is possible to
     * segment the word into a space-separated sequence of the dictionary.
     * @param dictionary dictionary of words for reference
     * @param str string to be checked
     * @return true if the string can be segmented, false otherwise
     */
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

    /**
     * Given a sequence of matrices, return the minimum number of operations
     * needed to perform the multiplication of these matrices.
     * @param matDim array with the matrices dimensions in the sequence of multiplications
     * @return the minimum number of operations needed to multiply all the matrices
     */
    public static int matrixMultiplication(int[] matDim){
        if (matDim.length < 2)
            return 0;

        int[][] numOper;

        int i, j, k; 

        int n = matDim.length - 1;
        numOper = new int[n][n];

        for (k = 1; k < n; k++) {
            for (i = 0; i < n - k; i++) {
                j = i + k;

                numOper[i][j] = Integer.MAX_VALUE;

                for (int z = i; z < j; z++) {
                    int num = numOper[i][z] + numOper[z+1][j] + matDim[i] * matDim[z+1] * matDim[j+1];
                    if (num < numOper[i][j]) {
                        numOper[i][j] = num;
                    }
                }
            }
        }

        return numOper[0][n-1];
    }

    /**
     * Given two strings str1 and str2, you are able to insert, remove, and replace 
     * characters in str1. Return the minimum number of edits (operations) required
     * to convert ‘str1′ into ‘str2′.
     * @param str1 string sting to be modified
     * @param str2 string that the first parameter must become
     * @return the minimum number of operations needed edit str1 to str2
     */
    public static int minEditDistance(String str1, String str2){
        int[][] tableMin = new int[str1.length() + 1][str2.length() + 1];
        int i, j; 
        int min;

        if(str1.length() < 1)
            return str2.length();
        if(str2.length() < 1)
            return str1.length();

        for(i = 0; i < str1.length(); i++)
            tableMin[i][0] = i;

        for(i = 0; i < str2.length(); i++)
            tableMin[0][i] = i;

        char[] a = str1.toCharArray();
        char[] b = str2.toCharArray();

        for(i = 1; i < str1.length()+1; i++) {
            for(j = 1; j < str2.length()+1; j++) {
                if (a[i-1] == b[j-1]) {
                    tableMin[i][j] = tableMin[i-1][j-1];
                } else { 
                    min = tableMin[i-1][j-1];
                    if (min > tableMin[i-1][j])
                        min = tableMin[i-1][j];

                    if (min > tableMin[i][j-1])
                        min = tableMin[i][j-1];

                    tableMin[i][j] = 1 + min;
                }
            }
        }

        return tableMin[str1.length()][str2.length()];
    }

    /**
     * Given a set of non-negative integers, and a value sum, determine if
     * there is a subset of the given set with sum equal to given sum.
     * @param sum integer value of the desired sum
     * @param set array representing the substet values
     * @return true if a subset with the given sum can be found, false if not.
     */
    public static boolean subsetSum(int sum, int[] set){
        int i, j;

        boolean[][] tabelaSub = new boolean[set.length][sum+1];

        for(i = 0; i < set.length; i++)
            for(j = 0; j < sum+1; j++)
                tabelaSub[i][j] = j == 0;

        for(i = 0; i < set.length; i++) {
            for(j = 1; j < sum+1; j++) {
                if (set[i] == j) {
                    tabelaSub[i][j] = true;
                } else if(i > 0 ) {
                    if (tabelaSub[i-1][j]) {
                        tabelaSub[i][j] = true;
                    } else if (j >= set[i]) {
                        tabelaSub[i][j] = tabelaSub[i-1][j-set[i]];
                    }
                }
            }
        }

        return tabelaSub[set.length-1][sum];
    }

    /**
     * Given a weighted graph, this algorithm uses the Prim's approach to find the
     * minimum spanning tree that can be built with the nodes of the graph.
     * @param graph weighted graph in a bidimensional array format
     * @param vert number of vertices
     * @return an array where each graph vertice is represented by the array index, and the
     * content of the array represents to which node the current node (i) should
     * be connected to in the tree. Since the root is 0, the first value of the array will
     * be -1 (the root has no father).
     */
    public static int[] primMinTree(int[][] graph, int vert) {
        int i, v;

        int tree[] = new int[vert];
 
        int peso[] = new int[vert];

        Boolean aincluir[] = new Boolean[vert]; 
 
        for (i = 0; i < vert; i++){
            peso[i] = Integer.MAX_VALUE;
            aincluir[i] = false;
        }

        peso[0] = 0;    
        tree[0] = -1;
 
        for (int count = 0; count < vert-1; count++){
            int min = Integer.MAX_VALUE;
            int min_index = -1;
             
            for (v = 0; v < vert; v++){
                if (!aincluir[v] && peso[v] < min){
                    min = peso[v];
                    min_index = v;
                }
            }
            
            int u = min_index;

            aincluir[u] = true;
 
            for (v = 0; v < vert; v++){                
                if (graph[u][v]!=0 && !aincluir[v] && graph[u][v] <  peso[v]){
                    tree[v]  = u;
                    peso[v] = graph[u][v];
                }
            }
        }

        return tree;
    }

    /**
     * Given a graph of v vertices, can each node be painted with a different color
     * than its adjacents with a set of n colors ? If yes, return true, else return false.
     * @param graph in a bidimensional array format
     * @param numColours number of colors to be used
     * @param vert number of vertices in the graph
     * @return true if the graph can be colored with n colors without having two adjacent
     * nodes with the same color, false elsewise.
     */
    public static boolean colorGraph(int[][] graph, int numColours, int vert) {
        vert = graph.length;
        
        int[] cores = new int[vert];

        try {
            paint(0, vert, graph, numColours, cores);
        } catch (Exception e) {
            return true;
        }
        return false;
    }
    
    private static void paint(int v, int vert, int[][] graph, int numColours, int[] cores) throws Exception {
        boolean auxFlag;

        if (v == vert)
            throw new Exception("Solucao encontrada");
        
        for (int c = 1; c <= numColours; c++) {
            auxFlag = true;
            for (int i = 0; i < vert; i++)
                if (graph[v][i] == 1 && c == cores[i])
                    auxFlag = false;

            if (auxFlag){
                cores[v] = c;
                paint(v + 1, vert, graph, numColours, cores);
                cores[v] = 0;
            }
        }    
    }

    /**
     * Given a set of n boxes 3d, the user wants to know the maximum height that it can obtain
     * by stacking the n boxes with the restriction that the housing base 2d of the top box must
     * always be less than the base of the box below.
     * @param array input with n boxes
     * @param array containing all rotations
     * @return the maximum height of the stack of boxes
     */

    public static int maxHeight(Box[] input) {

        Box[] allRotation = new Box[input.length * 3];
        rotation(input, allRotation);

        Arrays.sort(allRotation);

        int H[] = new int[allRotation.length];
        int result[] = new int[allRotation.length];

        for (int i = 0; i < H.length; i++) {

            H[i] = allRotation[i].getHeight();
            result[i] = i;
        }

        for (int i = 1; i < H.length; i++) {
            for (int j = 0; j < i; j++) {

                if (allRotation[i].getLength() < allRotation[j].getLength()
                        && allRotation[i].getWidth() < allRotation[j].getWidth()) {
                    if (H[j] + allRotation[i].getHeight() > H[i]) {

                        H[i] = H[j] + allRotation[i].getHeight();
                        result[i] = j;
                    }
                }
            }
        }

        int max = 0;

        for (int i = 0; i < H.length; i++) {
            if (H[i] > max) {
                max = H[i];
            }
        }

        return max;
    }

    private static void rotation(Box[] input, Box[] allRotation){

        int index = 0;
        for (int i = 0; i < input.length; i++) {
            allRotation[index++] = Box.createBox(input[i].getHeight(), input[i].getLength(), input[i].getWidth());
            allRotation[index++] = Box.createBox(input[i].getLength(), input[i].getHeight(), input[i].getWidth());
            allRotation[index++] = Box.createBox(input[i].getWidth(), input[i].getLength(), input[i].getHeight());
        }
    }

    /**
     *Solves the problem of calculating the shortest path between all vertex pairs in a directed graph
     *@param an object of class Graph 
     *@return the graph with the shortest paths
     */

    public static double[][] floydWarshall(Graph graph) {
        double[][] distances = new double[graph.getGraph().length][graph.getGraph()[0].length];
        int n = graph.getGraph().length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < graph.getGraph()[i].length; j++) {
                distances[i][j] = graph.getGraph()[i][j];
            }
        }


        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    distances[i][j] = Math.min(distances[i][j], distances[i][k] + distances[k][j]);
                }
            }

            if (distances[k][k] < 0.0) {
                graph.setNegativeCycle(true);
            }
        }
        return distances;
    }

    /**
     * Given two strings, they are converted into 2 characteres arrays.
     * Results in the greatest value of longest common subsequence between the strings
     * @param array to char first_string
     * @param array to char second_string
     * @return size of the longest common subsequence
     */

    public static int lcs(char first_string[],char second_string[]){

        int table[][] = new int[first_string.length + 1][second_string.length + 1];
        int max = 0;

        for(int i=1; i < table.length; i++){
            for(int j=1; j < table[i].length; j++){

                if(first_string[i-1] == second_string[j-1]) {
                    table[i][j] = table[i - 1][j - 1] + 1;
                } else {
                    table[i][j] = Math.max(table[i][j-1],table[i-1][j]);
                }

                if(table[i][j] > max){
                    max = table[i][j];
                }
            }
        }
        return max;
    }
}


