import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class StructuredObject extends RenovationObject {
    private Set<RenovationObject> parts = new HashSet<RenovationObject>();

    StructuredObject() {}

    void add(RenovationObject renovationObject) {
        if (renovationObject == null) {
            throw new NullPointerException("renovationProject cannot be null");
        }
        parts.add(renovationObject);
    }

    @Override
    double getPrice() {
        double price = 0;
        for (RenovationObject p : parts) {
            price+=p.getPrice();
        }
        return price;
    }
    
    @Override
    Map<String, Integer> addMaterialRequirements(Map<String, Integer> materials) {
        if (materials == null) {
            throw new NullPointerException("materials must be provided");
        }
        for (String k : materials.keySet()) {
            if (k == null) {
                throw new NullPointerException();
            }
        }
        for (Integer k : materials.values()) {
            if (k == null) {
                throw new NullPointerException();
            }
        }

        Map<String, Integer> out = new HashMap<String, Integer>();
        for (String m : materials.keySet()) {
            out.put(m, materials.get(m));
        }
        for (RenovationObject o : parts) {
            Map<String, Integer> requirements = o.addMaterialRequirements(materials);
            for (String m : requirements.keySet()) {
                if (out.containsKey(m)) {
                    out.put(m, out.get(m) + requirements.get(m));
                } else {
                    out.put(m, requirements.get(m));
                }
            }
        }
        return out;
    }
}
