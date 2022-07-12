public class Holding extends AbstractUnit {
    Holding(String name) {
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
        if (
            childNode.getClass() == Holding.class || 
            childNode.getClass() == Division.class || 
            childNode.getClass() == Team.class
        ) {
            throw new IllegalArgumentException("Cannot add a holding to a holding");
        }
        return super.add(childNode);
    }
}
