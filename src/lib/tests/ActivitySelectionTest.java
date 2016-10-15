package lib.tests;

import lib.DynamicAlgorithms;
import org.junit.Test;
import lib.Activity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Marcelo on 08/10/2016.
 */
public class ActivitySelectionTest {

    @Test
    public void activitySelectionTest1() throws Exception {
        ArrayList<Activity> activities = new ArrayList<Activity>() {{
            add(new Activity(1, 2));
            add(new Activity(3, 4));
            add(new Activity(0, 6));
            add(new Activity(5, 7));
            add(new Activity(8, 9));
            add(new Activity(5, 9));
        }};

        List<Integer> list = DynamicAlgorithms.activitySelection(activities);

        assertEquals("[0, 1, 3, 4]", list.toString());
    }

    @Test
    public void activitySelectionTest2() throws Exception {
        ArrayList<Activity> activities = new ArrayList<>();

        List<Integer> list = DynamicAlgorithms.activitySelection(activities);

        assertTrue(list.isEmpty());
    }

    @Test
    public void activitySelectionTest3() throws Exception {
        ArrayList<Activity> activities = new ArrayList<Activity>() {{
            add(new Activity(1, 2));
            add(new Activity(80, 90));
            add(new Activity(5, 9));
            add(new Activity(5, 7));

        }};

        List<Integer> list = DynamicAlgorithms.activitySelection(activities);

        assertEquals("[0, 1]", list.toString());
    }

}