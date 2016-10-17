package lib.tests;

import lib.DynamicAlgorithms;
import org.junit.Test;


import static org.junit.Assert.*;

public class LongestCommonSubsequenceTest{

	@Test
	public void longestCommonSubsequenceTest1() throws Exception{
		String first_string = "ABCDGH";
		String second_string = "AEDFHR";

		int output = DynamicAlgorithms.lcs(first_string.toCharArray(), second_string.toCharArray());

		assertEquals(3, output);

	}

	@Test
	public void longestCommonSubsequenceTest2() throws Exception{
		String first_string = "AGGTAB";
		String second_string = "GXTXAYB";

		int output = DynamicAlgorithms.lcs(first_string.toCharArray(), second_string.toCharArray());

		assertEquals(4, output);
	}

	@Test
	public void longestCommonSubsequenceTest3() throws Exception{
		String first_string = "ABCDEFG";
		String second_string = "ABCDEFG";

		int output = DynamicAlgorithms.lcs(first_string.toCharArray(), second_string.toCharArray());

		assertEquals(7, output);
	}
}