package searchProblemGame;

import java.util.ArrayList;
import java.util.Objects;


public class Node implements Comparable<Node>{
    private final ArrayList<Integer> state;
    private final Integer cost;
    private final Node parent;
    private final String operand;


    public Node(ArrayList<Integer> state, Integer cost, Node parent,String operand){
        this.parent = parent;
        this.state = state;
        this.cost = cost;
        this.operand = "T"+"("+operand+")";
    }

    public int getCost(){
        return cost;
    }

    public ArrayList<Integer> getState() {
        return new ArrayList<Integer>(state);
    }

    public Node getParent() {
        return parent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        for (int i=0;i<state.size();i++){
            if(state.get(i) != node.state.get(i)){
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(state);
    }

    public int compareTo(Node node) {
        if (this.cost > node.getCost()){
            return 1;
        }else if (this.cost < node.getCost()){
            return -1;
        }  else{
            return 0;
        }
    }

    public String getOperand() {
        return operand;
    }
}


