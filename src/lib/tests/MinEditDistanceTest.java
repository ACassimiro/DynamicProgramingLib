package lib.tests;

import lib.DynamicAlgorithms;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MinEditDistanceTest {
	
	@Test
	public void minEditDistanceTest1() throws Exception{
		String a = "azced";
		String b = "abcdef";

		int output = DynamicAlgorithms.minEdit(a, b));

		assertEquals(3, output);
	}

	@Test
	public void minEditDistanceTest2() throws Exception{
		String a = "azced";
		String b = "";

		int output = DynamicAlgorithms.minEdit(a, b));

		assertEquals(5, output);

	}

	@Test
	public void minEditDistanceTest3() throws Exception{
		String a = "";
		String b = "abcdef";

		int output = DynamicAlgorithms.minEdit(a, b));

		assertEquals(6, output);
	}

	@Test
	public void minEditDistanceTest4() throws Exception{
		String a = "book";
		String b = "back";

		int output = DynamicAlgorithms.minEdit(a, b));

		assertEquals(2, output);
	}

	@Test
	public void minEditDistanceTest5() throws Exception{
		String a = "planeta";
		String b = "cometa";

		int output = DynamicAlgorithms.minEdit(a, b));

		assertEquals(2, output);
	}
}