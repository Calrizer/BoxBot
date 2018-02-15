import java.util.*;

class RepeatingTask extends TimerTask {

    private long currentTick = 0;
    private long seconds = 0;

    // run is a abstract method that defines task performed at scheduled time.
    public void run() {
        currentTick++;
        if (currentTick % 60 == 0){
            seconds++;
        }
    }

    public long getCurrentTick() {
        return currentTick;
    }

    public long getSeconds() {
        return seconds;
    }

}