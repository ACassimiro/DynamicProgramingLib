package lib.tests;

import lib.DynamicAlgorithms;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GraphColoringTest {
	@Test
	public void subsetSumTestTest1() throws Exception{
		int numCor = 3;
 		int vert=5;

        int grafo[][] = new int[][]{{0, 1, 0, 1, 0},
                                    {1, 0, 1, 1, 1},
                                    {0, 1, 0, 0, 1},
                                    {1, 1, 0, 0, 1},
                                    {0, 1, 1, 1, 0},
                                   };
 		
		assertTrue(DynamicAlgorithms.colorGraph(grafo, numCor, vert));
	}


	@Test
	public void subsetSumTestTest2() throws Exception{
 		int vert=5;
		int numCor = 3;

        int grafo[][] = new int[][]{{0, 1, 0, 0, 0, 1, 0, 0, 0, 0},
                                    {1, 0, 1, 0, 0, 0, 1, 0, 0, 0},
                                    {0, 1, 0, 1, 0, 0, 0, 1, 0, 0},
                                    {0, 0, 1, 0, 1, 0, 0, 0, 1, 0},
                                    {1, 0, 0, 1, 0, 0, 0, 0, 0, 1},
                                    {1, 0, 0, 0, 0, 0, 0, 1, 1, 0},
                                    {0, 1, 0, 0, 0, 0, 0, 0, 1, 1},
                                    {0, 0, 1, 0, 0, 1, 0, 0, 0, 1},
                                    {0, 0, 0, 1, 0, 1, 1, 0, 0, 0},
                                    {0, 0, 0, 0, 1, 0, 1, 1, 0, 0}};

 		
		assertTrue(DynamicAlgorithms.colorGraph(grafo, numCor, vert));
	}

	@Test
	public void subsetSumTestTest3() throws Exception{
		int numCor = 1;
 		int vert=5;

        int grafo[][] = new int[][]{{0, 1, 0, 1, 0},
                                    {1, 0, 1, 1, 1},
                                    {0, 1, 0, 0, 1},
                                    {1, 1, 0, 0, 1},
                                    {0, 1, 1, 1, 0},
                                   };
 		
		assertFalse(DynamicAlgorithms.colorGraph(grafo, numCor, vert));
	}
}