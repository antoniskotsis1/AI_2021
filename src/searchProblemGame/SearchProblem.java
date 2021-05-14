package searchProblemGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class SearchProblem {
    private final ArrayList<Integer> initState;
    private final ArrayList<Integer> target;
    private final int cost = 1;

    public SearchProblem(ArrayList<Integer> target, ArrayList<Integer> initState ){
        this.initState = initState;
        this.target = target;
    }

    public ArrayList<Integer> getTarget() {
        return target;
    }

    public ArrayList<Integer> getInitState() {
        return initState;
    }

    public ArrayList<Integer> T(int k,ArrayList<Integer> currentState) {
        List<Integer> al = currentState.subList(0, k);
        List<Integer> ar = currentState.subList(k, currentState.size());
        Collections.reverse(al);
        ArrayList<Integer> b = new ArrayList<>();
        b.addAll(al);
        b.addAll(ar);
        return b;
    }

     public int getCost(){
        return cost;
     }
}
