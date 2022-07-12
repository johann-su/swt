public class Paint extends Material {
    private double limit = 0.02;
    private int numberOfCoatings;
    private double squareMetersPerLiter;

    Paint(String name, double price, int numberOfCoatings, double squareMetersPerLiter) {
        super(name, price);
        if (numberOfCoatings <=0 || squareMetersPerLiter <= 0) {
            throw new IllegalArgumentException("numberOfCoatings and squareMetersPerLiter must be positive numbers");
        }
        this.numberOfCoatings = numberOfCoatings;
        this.squareMetersPerLiter = squareMetersPerLiter;
    }

    int getNumberOfCoats() {
        return this.numberOfCoatings;
    }

    double getSquareMetersPerLiter() {
        return this.squareMetersPerLiter;
    }

    int getMaterialRequirements(Surface surface) {
        if (surface == null) {
            throw new NullPointerException("Surface must be provided");
        }

        double liter = surface.getArea() * numberOfCoatings / squareMetersPerLiter;

        return (int) (Math.floor((Math.round(liter*100)-Math.round(limit*100))*2)/100)+1;
    }
}
