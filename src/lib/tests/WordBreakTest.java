package lib.tests;

import lib.DynamicAlgorithms;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Marcelo on 08/10/2016.
 */
public class WordBreakTest {

    private static List<String> dictionary;

    @BeforeClass
    public static void setUp() {
        dictionary = new ArrayList<String>() {{
            add("mobile");
            add("samsung");
            add("sam");
            add("sung");
            add("man");
            add("mango");
            add("icecream");
            add("and");
            add("go");
            add("i");
            add("like");
            add("ice");
            add("cream");
        }};
    }

    @Test
    public void wordBreakTest1() throws Exception {
        assertTrue(DynamicAlgorithms.wordBreak(dictionary, "ilikesamsung"));
    }

    @Test
    public void wordBreakTest2() throws Exception {
        assertTrue(DynamicAlgorithms.wordBreak(dictionary, "iiiiiiii"));
    }

    @Test
    public void wordBreakTest3() throws Exception {
        assertTrue(DynamicAlgorithms.wordBreak(dictionary, "ilikelikeimangoiii"));
    }

    @Test
    public void wordBreakTest4() throws Exception {
        assertTrue(DynamicAlgorithms.wordBreak(dictionary, ""));
    }

    @Test
    public void wordBreakTest5() throws Exception {
        assertTrue(DynamicAlgorithms.wordBreak(dictionary, "samsungandmango"));
    }

    @Test
    public void wordBreakTest6() throws Exception {
        assertFalse(DynamicAlgorithms.wordBreak(dictionary, "samsungandmangok"));
    }

}