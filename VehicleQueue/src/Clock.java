import java.util.ArrayList;
import java.util.List;

public class Clock {
    private int currentTime;
    private int endOfTime;
    private List<ClockObserver> observers = new ArrayList<ClockObserver>();

    Clock(int endOfTime) {
        if (endOfTime <= 0) {
            throw new IllegalArgumentException("endOfTime must be greater than 0");
        }
        this.endOfTime = endOfTime;
    }

    public void addObserver(ClockObserver observer) {
        if (observer == null) {
            throw new NullPointerException("observer cannot be null");
        }
        this.observers.add(observer);
    }

    public void removeObserver(ClockObserver observer) {
        if (observer == null) {
            throw new NullPointerException("observer cannot be null");
        }
        this.observers.remove(observer);
    }

    public int getCurrentTime() {
        return this.currentTime;
    }

    public void run() {
        this.currentTime = 0;
        while(this.currentTime < this.endOfTime) {
            this.tick(this.currentTime);
        }
        System.out.println("The simulation is over");
    }

    private void tick(int currentTime) {
        if (currentTime < 0) {
            throw new IllegalArgumentException("currentTime must be greater than 0");
        }
        this.currentTime++;
        for (ClockObserver o : this.observers) {
            o.tick(this.currentTime);
        }
    }
}