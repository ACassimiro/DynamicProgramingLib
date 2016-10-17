package lib.tests;

import lib.DynamicAlgorithms;
import org.junit.Test;
import lib.Box;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BoxStackingTest {
	@Test
	public void boxStackingTest1() throws Exception{
		int input[] = {{4, 6, 7}, {1, 2, 3}, {4, 5, 6}, {10, 12, 32}};
		
		int output = DynamicAlgorithms.maxHeight(input);

		assertEquals(60, output);
	}

	@Test
	public void boxStackingTest2() throws Exception{
		int input[] = {{3, 2, 5}, {1, 2, 4}};
		
		int output = DynamicAlgorithms.maxHeight(input);

		assertEquals(11, output);
	}

	@Test
	public void boxStackingTest3() throws Exception{
		int input[] = {{4, 7, 9}, {5, 8, 9}, {11, 20, 40}, {1, 2, 3}};
		
		int output = DynamicAlgorithms.maxHeight(input);

		assertEquals(78, output);
	}

}