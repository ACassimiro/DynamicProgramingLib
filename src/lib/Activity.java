package lib;

/**
 * Activity for the Activity Selection problem.
 * Created by Marcelo on 07/10/2016.
 */
public class Activity {

    private int startTime;
    private int finishTime;

    public Activity(int startTime, int finishTime) {
        this.startTime = startTime;
        this.finishTime = finishTime;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getFinishTime() {
        return finishTime;
    }

    @Override
    public String toString() {
        return "[" + startTime + ", " + finishTime + "]";
    }
}
