package lib;

/**
 * Created by Marcelo on 14/11/2016.
 */
public class TestInstance {

    private String name;
    private int timeLimit;
    private int testRuns;

    public TestInstance(String name, int timeLimit, int testRuns) {
        this.name = name;
        this.timeLimit = timeLimit;
        this.testRuns = testRuns;
    }

    public String getName() {
        return name;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public int getTestRuns() {
        return testRuns;
    }
}
