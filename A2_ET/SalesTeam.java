import java.util.*;

class SalesTeam{

    /**
     * Team list (object type LinkedList<String>)
     */
    LinkedList<String> team;

    /**
     * Random object
     */
    Random random = new Random();

    /**
     * Default constructor for SalesTeam
     */
    public SalesTeam(){
        team = new LinkedList<String>();
        team.add("Elijah");
        team.add("Jeremy");
        team.add("Jose");
        team.add("Jason");
        team.add("Linh");
    }

    /**
     * Picks a random sales person for a transaction
     * 
     * @return a random sales person
     */
    public String pickRandom(){
        int num = random.nextInt(team.size());
        return team.get(num);
    }

    /**
     * Prints out the team list in a string
     */
    public void listTeam(){
        ListIterator<String> teamIt = team.listIterator();
        System.out.print("Team: ");
        while(teamIt.hasNext()){
        	System.out.print(teamIt.next() + " ");
        }
    }
    
}