import java.util.SortedSet;
import java.util.TreeSet;

public class StaffMember implements EnterpriseNode, Comparable<EnterpriseNode> {
    private String name;
    private String job;
    private SortedSet<StaffMember> directSubordinates = new TreeSet<StaffMember>();

    StaffMember(String name, String job) {
        this.name = name;
        this.job = job;
        if (name.equals("") || job.equals("")) {
            throw new IllegalArgumentException("Name/Job cannot be empty");
        }
        if (name == null || job == null) {
            throw new NullPointerException("Name/Job cannot be null");
        }
    }

    String getJob() {
        return this.job;
    }

    boolean addDirectSubordinate(StaffMember subordinate) {
        if (subordinate == null) {
            throw new NullPointerException("Subordinate cannot be null");
        }
        return this.directSubordinates.add(subordinate);
    }

    boolean removeDirectSubordinate(StaffMember subordinate) {
        if (subordinate == null) {
            throw new NullPointerException("Subordinate cannot be null");
        }
        return this.directSubordinates.remove(subordinate);
    }

    SortedSet<StaffMember> getDirectSubordinates() {
        return this.directSubordinates;
    }

    public String toString() {
        return this.name;
    }

    @Override
    public int compareTo(EnterpriseNode o) {
        return this.name.compareTo(o.getName());
    }

    @Override
    public String getName() {
        return this.name;
    }
}
