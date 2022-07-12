public class Simulation {
    public static void main(String[] args) {
        Clock clock = new Clock(70);
        VehicleQueue queue = new VehicleQueue(1.7, 1.2, 10, new VehicleGenerator());

        clock.addObserver(queue);
        clock.run();

        System.out.println("Number of vehicles at the end of time: " + queue.getSize());
        System.out.println("Length of the queue at the end of time: " + queue.getLength());
    }
}
