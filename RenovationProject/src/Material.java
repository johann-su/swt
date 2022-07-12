public abstract class Material {
    private String name;
    private double price;

    Material(String name, double price) {
        if (name == null) {
            throw new NullPointerException("name must be set");
        }
        if (name.equals("")) {
            throw new IllegalArgumentException("name cannot be empty");
        }
        if (price <= 0) {
            throw new IllegalArgumentException("Price must be a positive number");
        }
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPricePerUnit() {
        return price;
    }

    abstract int getMaterialRequirements(Surface surface);
    
    double getPriceOfASurface(Surface surface) {
        if (surface == null) {
            throw new NullPointerException("Surface must be provided");
        }
        return surface.getPrice();
    }
}
