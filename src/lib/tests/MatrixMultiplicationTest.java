package lib.tests;

import lib.DynamicAlgorithms;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MatrixMultiplicationTest {
	@Test
	public void matrixMultiplicationTest1() throws Exception{
		int []array = {10, 20, 30, 40, 30};
		
		int output = DynamicAlgorithms.matrixMultiplication(array);

		assertEquals(30000, output);
	}

	@Test
	public void matrixMultiplicationTest2() throws Exception{
		int []array = {0};
		
		int output = DynamicAlgorithms.matrixMultiplication(array);

		assertEquals(0, output);
	}

	@Test
	public void matrixMultiplicationTest3() throws Exception{
		int []array = {10, 20, 30};
		
		int output = DynamicAlgorithms.matrixMultiplication(array);

		assertEquals(6000, output);
	}

	@Test
	public void matrixMultiplicationTest4() throws Exception{
		int []array = {10, 30, 5, 60};
		
		int output = matrixMultiplication(array);

		assertEquals(4500, output);
	}

	@Test
	public void matrixMultiplicationTest5() throws Exception{
		int []array = {30, 35, 15, 5, 10, 20, 25};
		
		int output = DynamicAlgorithms.matrixMultiplication(array);

		assertEquals(15125, output);
	}
}