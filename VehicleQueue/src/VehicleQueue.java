import java.util.LinkedList;
import java.util.Queue;

public class VehicleQueue implements ClockObserver {
    private double entryDelay;
    private double exitDelay;
    private int trafficLightRate;
    private boolean greenLight = false;
    private Queue<Vehicle> queue = new LinkedList<Vehicle>();
    private VehicleGenerator generator = new VehicleGenerator();
    private double localEntryDelay;
    private double localExitDelay;

    VehicleQueue(double entryDelay, double exitDelay, int trafficLightRate, VehicleGenerator generator) {
        if (entryDelay <= 0 || exitDelay <= 0 || trafficLightRate <= 0) {
            throw new IllegalArgumentException("numbers must be greater 0");
        }
        if (generator == null) {
            throw new NullPointerException("generator cannot be null");
        }
        this.entryDelay = entryDelay;
        this.exitDelay = exitDelay;
        this.trafficLightRate = trafficLightRate;
        this.generator = generator;
        this.localEntryDelay=0;
        this.localExitDelay=0;
    }

    public void enter() {
        queue.add(this.generator.createVehicle());
    }

    public void leave() {
        if (queue.size() > 0) {
            queue.remove();
        } else {
            System.out.println("Queue was empty");
        }
    }

    public double getLength() {
        double length = 0;
        for (Vehicle v : queue) {
            length+=v.getLength();
        }
        return length;
    }

    public int getSize() {
        return this.queue.size();
    }

    public void tick(int currentTime) {
        if (currentTime < 0) {
            throw new IllegalArgumentException("currentTime must be >0");
        }
        
        
        System.out.println("CurrentTime: " + currentTime);
        System.out.println("Ampel State: " + greenLight);
        System.out.println("Switching state: " + trafficLightRate);
        System.out.println("entryDelay: " + entryDelay);
        System.out.println("exitDelay: " + exitDelay);
        System.out.println("LocalEntryDelay: " + localEntryDelay);
        System.out.println("LocalExitDelay: " + localExitDelay);


        if (this.greenLight == false) {
            this.localExitDelay = currentTime;
        }

        while (Math.round((this.localEntryDelay+this.entryDelay) * 100D)/100D <= currentTime) {
            this.localEntryDelay += this.entryDelay;
            this.enter();
            System.out.println("new Vehicle entering. Size: " + this.queue.size());
        }

        while (Math.round((this.localExitDelay+this.exitDelay) * 100D)/100D <= currentTime && this.greenLight == true) {
            this.localExitDelay += this.exitDelay;
            this.leave();
            System.out.println("new Vehicle leaving. Size: " + this.queue.size());
        }
        
        if (currentTime % this.trafficLightRate == 0) {
            this.greenLight = !this.greenLight;
            System.out.println("Green is changing to: " + this.greenLight);
        }
    }
}
