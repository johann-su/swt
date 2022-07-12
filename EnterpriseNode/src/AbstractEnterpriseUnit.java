public abstract class AbstractEnterpriseUnit implements EnterpriseNode {
    private String name;

    AbstractEnterpriseUnit(String name) {
        if (name == null) {
            throw new NullPointerException("Name cannot be null");
        }
        if (name.equals("")) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
