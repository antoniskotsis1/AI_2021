import java.util.*;


public class CostCalculator {

    public static int calculateUcsCost(SearchProblem sp, Node node){
        return sp.getCost()+ node.getCost();
    }

    public static int calculateAstarCost(SearchProblem sp, Node node){
        return heuristicCost(node.getState())+sp.getCost()+ node.getCost();
    }

    private static int heuristicCost(ArrayList<Integer> state){
        int cost = 0;
        for(int i = 0;i<state.size()-1;i++){
            if(Math.abs(state.get(i)-state.get(i+1))!=1){
                cost++;
            }
        }
        return cost;
    }
}

