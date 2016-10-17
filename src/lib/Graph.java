package lib;

public class Graph {

    private double[][] graph;
    private boolean negativeCycle;

    public Graph(int n) {
        this.graph = new double[n][n];
        initGraph();
    }

    private void initGraph() {
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                if (i == j) {
                    graph[i][j] = 0;
                } else {
                    graph[i][j] = Double.POSITIVE_INFINITY;
                }
            }
        }
    }

    public boolean hasNegativeCycle() {
        return this.negativeCycle;
    }

    public void setNegativeCycle(boolean negativeCycle) {
        this.negativeCycle = negativeCycle;
    }

    public double[][] getGraph() {
        return graph;
    }

    public void addEdge(int from, int to, double weight) {
        graph[from][to] = weight;
    }
}