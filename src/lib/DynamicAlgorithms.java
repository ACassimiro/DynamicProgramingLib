package lib;

import java.util.ArrayList;
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
     * @param array with the matrices dimensions in the sequence of multiplications
     * @return the minimum number of operations needed to multiply all the matrices
     */
    public static int matrixMultiplication(int[] matDim){
        if(matDim.length < 2)
            return 0;

        int[][]numOper;

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
     * @param the string sting to be modified
     * @param the string that the first parameter must become
     * @return the minimum number of operations needed edit str1 to str2
     */
    public static int minEditDistance(String str1, String str2){
        int tabelaMin[][] = new int[str1.length() + 1][str2.length() + 1];
        int i, j; 
        int menor;

        if(str1.length() < 1)
            return str2.length();
        if(str2.length() < 1)
            return str1.length();

        for(i= 0; i<str1.length(); i++)
            tabelaMin[i][0] = i;

        for(i=0; i<str2.length(); i++)
            tabelaMin[0][i] = i;

        char []a = str1.toCharArray();
        char []b = str2.toCharArray();

        for(i=1; i<str1.length()+1; i++) {
            for(j=1; j<str2.length()+1; j++) {
                if(a[i-1] == b[j-1]){
                    tabelaMin[i][j] = tabelaMin[i-1][j-1];
                } else { 
                    menor = tabelaMin[i-1][j-1];
                    if(menor > tabelaMin[i-1][j])
                        menor = tabelaMin[i-1][j];

                    if(menor > tabelaMin[i][j-1])
                        menor = tabelaMin[i][j-1];

                    tabelaMin[i][j] = 1 + menor;
                }
            }
        }

        return tabelaMin[str1.length()][str2.length()];
    }

    /**
     * Given a set of non-negative integers, and a value sum, determine if
     * there is a subset of the given set with sum equal to given sum.
     * @param the integer value of the desired sum 
     * @param the array representing the substet values
     * @return true if a subset with the given sum can be found, false if not.
     */
    public static boolean SubsetSum(int sum, int[] set){
        int i, j;

        boolean tabelaSub[][] = new boolean[set.length][sum+1];

        for(i=0; i<set.length; i++){
            for(j=0; j<sum+1; j++){
                if(j == 0)
                    tabelaSub[i][j] = true;
                else
                    tabelaSub[i][j] = false;
            }
        }

        for(i = 0; i<set.length; i++){
            for(j = 1; j<sum+1; j++){
                if(set[i] == j){
                    tabelaSub[i][j] = true;
                } else if(i > 0 ) {
                    if (tabelaSub[i-1][j]){
                        tabelaSub[i][j] = true;
                    } else if(j >= set[i]){
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
     * @param the weighted graph in a bidimensional array format 
     * @param the number of vertices
     * @return an array where each graph vertice is represented by the array index, and the
     * content of the array represents to which node the current node (i) should
     * be connected to in the tree. Since the root is 0, the first value of the array will
     * be -1 (the root has no father).
     */
    public static int[] PrimMinTree(int [][] grafo, int vert) {
        int i, v;

        int min = Integer.MAX_VALUE, min_index=-1;

        int arvore[] = new int[vert];
 
        int peso[] = new int [vert];

        Boolean aincluir[] = new Boolean[vert]; 
 
        for (i = 0; i < vert; i++){
            peso[i] = Integer.MAX_VALUE;
            aincluir[i] = false;
        }

        peso[0] = 0;    
        arvore[0] = -1; 
 
        for (int count = 0; count < vert-1; count++){
            min = Integer.MAX_VALUE;
            min_index=-1;
             
            for (v = 0; v < vert; v++){
                if (aincluir[v] == false && peso[v] < min){
                    min = peso[v];
                    min_index = v;
                }
            }
            
            int u = min_index;

            aincluir[u] = true;
 
            for (v = 0; v < vert; v++){                
                if (grafo[u][v]!=0 && aincluir[v] == false && grafo[u][v] <  peso[v]){
                    arvore[v]  = u;
                    peso[v] = grafo[u][v];
                }
            }
        }

        return arvore;
    }

    /**
     * Given a graph of v vertices, can each node be painted with a different color
     * than its adjacents with a set of n colors ? If yes, return true, else return false.
     * @param the graph in a bidimensional array format 
     * @param the number of colors to be used
     * @param the number of vertices in the graph
     * @return true if the graph can be colored with n colors without having two adjacent
     * nodes with the same color, false elsewise.
     */
    public static boolean colorGraph(int[][] g, int numCores, int vert) {
        vert = g.length;
        
        int[] cores = new int[vert];
        int[][] grafoAux = g;
 
        try {
            atribuirCor(0, vert, grafoAux, numCores, cores);
        } catch (Exception e) {
            return true;
        }
        return false;
    }
    
    private static void atribuirCor(int v, int vert, int[][] grafo, int numCores, int[] cores) throws Exception {
        boolean auxFlag = true;
        boolean flagRetorno = false;
        
        if (v == vert)
            throw new Exception("Solucao encontrada");
        
        for (int c = 1; c <= numCores; c++) {
            auxFlag = true;
            for (int i = 0; i < vert; i++)
                if (grafo[v][i] == 1 && c == cores[i])
                    auxFlag = false;

            if (auxFlag){
                cores[v] = c;
                atribuirCor(v + 1, vert, grafo, numCores, cores);
                cores[v] = 0;
            }
        }    
    }

}
