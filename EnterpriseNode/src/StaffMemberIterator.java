import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TreeSet;

public class StaffMemberIterator implements EnterpriseNodeIterator<StaffMember> {
    private Set<StaffMember> directSubordinates = new TreeSet<>();
    private Iterator<StaffMember> it;

    StaffMemberIterator(Set<StaffMember> directSubordinates) {
        if (directSubordinates == null) {
            throw new NullPointerException("Arg cannot be null");
        }

        for (StaffMember m : directSubordinates) {
            findSubordinatesRecursively(m);
        }
        it = this.directSubordinates.iterator();
    }

    @Override
    public boolean hasNext() {
        if (it == null){
            return false;
        }
        return it.hasNext();
    }

    @Override
    public StaffMember next() {
        if (it == null || it.hasNext() == false) {
            throw new NoSuchElementException("Cannot return next");
        }
        return it.next();
    }

    private void findSubordinatesRecursively(StaffMember m) {
        this.directSubordinates.add(m);
        for (StaffMember s : m.getDirectSubordinates()) {
            System.out.println("name: " + s.getName());
            findSubordinatesRecursively(s);
        }
    }
}
