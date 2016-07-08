package lesson1;
import java.util.*;

/**
 * class Team
 */
public class Team {
    protected String teamName;
    private ArrayList<Animal> members = new ArrayList();

    public Team() {
        this.teamName = "Undefined";
    }
    
    public Team(String teamName, Animal[] animals) {
        this.teamName = teamName;
        Collections.addAll(members, animals);
    }

    public String getTeamName() {
        return teamName;
    }

    public void showTeam() {
        System.out.println("\"" + getTeamName() + "\" team members:");
        for (Animal member : members) {
            System.out.println(member.getType() + " " + member.getName());
        }
    }

    public void showMembersWhoPassedDistance() {
        for (Animal member: members) {
           if (member.isOnDistance()){
               System.out.println(member.getType() + " " + member.getName() + " done it");
           }
        }
    }
}