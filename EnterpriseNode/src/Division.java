public class Division extends AbstractUnit {
    Division(String name) {
        super(name);
        if (name == null) {
            throw new NullPointerException("Name cannot be null");
        }
        if (name.equals("")) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }

    @Override
    boolean add(AbstractEnterpriseUnit childNode) {
        if (childNode.getClass() == Holding.class ||
            childNode.getClass() == Company.class ||
            childNode.getClass() == Division.class
        ) {
            throw new IllegalArgumentException("Cannot add a holding to a holding");
        }
        return super.add(childNode);
    }
}
