package lib.tests;

import lib.DynamicAlgorithms;
import org.junit.Test;
import lib.Box;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BoxStackingTest {
	@Test
	public void boxStackingTest1() throws Exception {
		Box[] input = { new Box(4, 6, 7),
				new Box(1, 2, 3),
                new Box(4, 5, 6),
                new Box(10, 12, 32)};
		
		int output = DynamicAlgorithms.maxHeight(input);

		assertEquals(60, output);
	}

	@Test
	public void boxStackingTest2() throws Exception {
		Box[] input = {new Box(3, 2, 5),
                new Box(1, 2, 4)};
		
		int output = DynamicAlgorithms.maxHeight(input);

		assertEquals(11, output);
	}

	@Test
	public void boxStackingTest3() throws Exception {
		Box[] input = {
		        new Box(4, 7, 9),
                new Box(5, 8, 9),
                new Box(11, 20, 40),
                new Box(1, 2, 3)};
		
		int output = DynamicAlgorithms.maxHeight(input);

		assertEquals(78, output);
	}

}