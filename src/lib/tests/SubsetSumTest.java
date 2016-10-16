package lib.tests;

import lib.DynamicAlgorithms;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SubsetSumTest {
	@Test
	public void subsetSumTestTest1() throws Exception{
		int sum = 11;
		int []set = {2,3,7,8,10};

		boolean output = DynamicAlgorithms.SubsetSum(sum, set);

		assertTrue(output);
	}

	@Test
	public void subsetSumTestTest2() throws Exception{
		int sum = 55;
		int []set = {30, 40, 10, 15, 10, 60, 54};

		boolean output = DynamicAlgorithms.SubsetSum(sum, set);

		assertTrue(output);
	}

	@Test
	public void subsetSumTestTest3() throws Exception{
		int sum = 9;
		int set[] = {3, 34, 4, 12, 5, 2};
		
		boolean output = DynamicAlgorithms.SubsetSum(sum, set);

		assertTrue(output);
	}

	@Test
	public void subsetSumTestTest4() throws Exception{
		int sum = 0;
		int []set = {1};
		
		boolean output = DynamicAlgorithms.SubsetSum(sum, set);

		assertTrue(output);
	}

	@Test
	public void subsetSumTestTest5() throws Exception{
		int sum = 80;
		int []set = {0};

		boolean output = DynamicAlgorithms.SubsetSum(sum, set);

		assertFalse(output);
	}
	
}