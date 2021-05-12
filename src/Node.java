import java.util.ArrayList;
import java.util.Objects;

public class Node implements Comparable<Node>{
    private  ArrayList<Integer> state;
    private final Integer cost;
    private Node parent;
    private int operant;


    public Node(ArrayList<Integer> state, Integer cost, Node parent,int operant){
        this.parent = parent;
        this.state = state;
        this.cost = cost;
        this.operant = operant;
    }

    public int getCost(){
        return cost;
    }

    public ArrayList<Integer> getState() {
        ArrayList<Integer> newList = new ArrayList<Integer>();
        newList.addAll(state);
        return newList;
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
}


