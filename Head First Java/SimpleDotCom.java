import java.util.ArrayList;

public class SimpleDotCom {
    private ArrayList<Integer> locationCells;
    //int numOfHits = 0;
    // don't need that now
    
    public void setLocationCells(ArrayList<Integer> locs) {
        locationCells = locs;
    }

    public String checkYourself(String stringGuess) {
        int guess = Integer.parseInt(stringGuess);
        String result = "miss";
        int index = locationCells.indexOf(guess);
        if (index >= 0) {
            locationCells.remove(index);
            if (locationCells.isEmpty()) {
                result = "kill";
            } else {
                result = "hit";
            }
        }
        System.out.println(result);
        return result;
    }
}