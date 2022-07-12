import java.util.SortedSet;
import java.util.TreeSet;

public class Team extends AbstractEnterpriseUnit {
    private StaffMember teamLeader;

    Team(String name, StaffMember teamLeader) {
        super(name);
        if (name == null || teamLeader == null) {
            throw new NullPointerException("Args cannot be null");
        }
        if (name.equals("")) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.teamLeader = teamLeader;
    }

    StaffMember getTeamLeader() {
        return this.teamLeader;
    }

    SortedSet<StaffMember> getTeamMembers() {
        SortedSet<StaffMember> teamMembers = new TreeSet<StaffMember>();
        teamMembers.add(teamLeader);

        StaffMemberIterator it = new StaffMemberIterator(teamLeader.getDirectSubordinates());

        System.out.println("has next: " + teamLeader.getDirectSubordinates());
        
        while(it.hasNext()) {
            System.out.println("has next");
            teamMembers.add(it.next());
        }
        
        return teamMembers;
    }
}
