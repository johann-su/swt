import java.util.Random;

public class VehicleGenerator {
    private Random radomGenerator;

    VehicleGenerator() {
        this.radomGenerator = new Random();
    }

    public Vehicle createVehicle() {
        int type = radomGenerator.nextInt(3);
        switch (type) {
            case 1:
                return new Bicycle();
            case 2:
                return new Car();
            default:
                return new Bus();
        }
    }
}
