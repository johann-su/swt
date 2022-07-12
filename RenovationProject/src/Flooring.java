public class Flooring extends Material {
    private double limit = 0.02;
    private double widthOfFlooring;

    Flooring(String name, double price, double width) {
        super(name, price);
        if (width <= 0) {
            throw new IllegalArgumentException("width must be a positive number");
        }
        this.widthOfFlooring = width;
    }

    double getWidth() {
        return this.widthOfFlooring;
    }

    int getMaterialRequirements(Surface surface) {
        if (surface == null) {
            throw new NullPointerException("Surface cannot be null");
        }
        double panels = surface.getArea() / widthOfFlooring;
        
        return (int) (Math.floor(Math.round(panels*100)-Math.round(limit*100))/100)+1;
    }
}
