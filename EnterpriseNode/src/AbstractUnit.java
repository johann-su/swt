import java.util.HashSet;
import java.util.Set;

public abstract class AbstractUnit extends AbstractEnterpriseUnit {
    private Set<AbstractEnterpriseUnit> childs = new HashSet<AbstractEnterpriseUnit>();

    AbstractUnit(String name) {
        super(name);
        if (name == null) {
            throw new NullPointerException("Name cannot be null");
        }
        if (name.equals("")) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }

    boolean add(AbstractEnterpriseUnit childNode) {
        if (childNode == null) {
            throw new NullPointerException("Name cannot be null");
        }
        return this.childs.add(childNode);
    }

    boolean remove(AbstractEnterpriseUnit childNode) {
        if (childNode == null) {
            throw new NullPointerException("Name cannot be null");
        }
        return this.childs.remove(childNode);
    }

    Set<AbstractEnterpriseUnit> getChildNodes() {
        return this.childs;
    }
}