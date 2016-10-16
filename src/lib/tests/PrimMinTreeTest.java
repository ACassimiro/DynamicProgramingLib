package lib.tests;

import lib.DynamicAlgorithms;
import org.junit.Test;

import static org.junit.Assert.*;


public class PrimMinTreeTest {

    @Test
    public void primMinTreeTest1() throws Exception {
        int vert=5; 

        int grafo[][] = new int[][]{{0, 2, 0, 6, 0}, 
                                    {2, 0, 3, 8, 5},
                                    {0, 3, 0, 0, 7},
                                    {6, 8, 0, 0, 9},
                                    {0, 5, 7, 9, 0}};

        int [] arvore = DynamicAlgorithms.primMinTree(grafo, vert);

        String arestas = "";
        
        for (int i = 1; i<vert ; i++) {
            arestas += Integer.toString(arvore[i]) + "-" + Integer.toString(i) + ";";
        }

        assertEquals("0-1;1-2;0-3;1-4;", arestas);

    }

    @Test
    public void primMinTreeTest2() throws Exception {
        int vert=3; 

        int grafo[][] = new int[][]{{0, 2, 1}, 
                                    {0, 0, 0},
                                    {1, 2, 0}};
    
        int [] arvore = DynamicAlgorithms.primMinTree(grafo, vert);

        String arestas = "";
        
        for (int i = 1; i<vert ; i++) {
            arestas += Integer.toString(arvore[i]) + "-" + Integer.toString(i) + ";";
        }

        //The root points at two nodes
        assertEquals("0-1;0-2;", arestas);

    }

    @Test
    public void primMinTreeTest3() throws Exception {
        int vert=3; 

        int grafo[][] = new int[][]{{0, 1, 2}, 
                                    {1, 0, 1},
                                    {2, 1, 0}};
    
        int [] arvore = DynamicAlgorithms.primMinTree(grafo, vert);

        String arestas = "";
        
        for (int i = 1; i<vert ; i++) {
            arestas += Integer.toString(arvore[i]) + "-" + Integer.toString(i) + ";";
        }

        //Root points to second node, which points to the third node
        assertEquals("0-1;1-2;", arestas);

    }

    @Test
    public void primMinTreeTest4() throws Exception {
        int vert=1; 

        int grafo[][] = new int[][]{{0}}; 
    
        int [] arvore = DynamicAlgorithms.primMinTree(grafo, vert);

        String arestas = "";
        
        for (int i = 1; i<vert ; i++) {
            arestas += Integer.toString(arvore[i]) + "-" + Integer.toString(i) + ";";
        }

        //Root only, no paths
        assertEquals("", arestas);
    }
}