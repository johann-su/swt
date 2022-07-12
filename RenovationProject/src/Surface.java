import java.util.HashMap;
import java.util.Map;

public class Surface extends RenovationObject {
    private double width;
    private double length;
    private Material selectedMaterial;

    Surface(double width, double length) {
        if (width <= 0 || length <= 0) {
            throw new IllegalArgumentException("width and length must be positive numbers");
        }
        this.length = length;
        this.width = width;
    }

    void setMaterial(Material material) {
        if (material == null) {
            throw new NullPointerException("material cannot be null");
        }
        this.selectedMaterial = material;
    }

    double getArea() {
        return this.width * this.length;
    }

    double getLength() {
        return this.length;
    }

    double getWidth() {
        return this.width;
    }

    double getPrice() {
        System.out.println(this.selectedMaterial.getMaterialRequirements(this));
        return this.selectedMaterial.getPricePerUnit() * this.getArea() / this.selectedMaterial.getMaterialRequirements(this);
    }

    Map<String, Integer> addMaterialRequirements(Map<String, Integer> materials) {
        if (materials == null) {
            throw new NullPointerException("materials cannot be null");
        }
        for (String k : materials.keySet()) {
            if (k==null) {
                throw new NullPointerException();
            }
        }
        for (Integer k : materials.values()) {
            if (k==null) {
                throw new NullPointerException();
            }
        }
        Map<String, Integer> out = new HashMap<String, Integer>();
        for (String m : materials.keySet()) {
            out.put(m, materials.get(m));
        }
        if (out.containsKey(selectedMaterial.getName())) {
            out.put(selectedMaterial.getName(), out.get(selectedMaterial.getName()) + selectedMaterial.getMaterialRequirements(this));
        } else {
            out.put(selectedMaterial.getName(), selectedMaterial.getMaterialRequirements(this));
        }
        return out;
    }
}
