package searchProblemGame;

import java.util.*;
import java.util.function.BiFunction;

public class SearchAlgorithm {
    private  int nodesExpanded = 0;
    private final SearchProblem sp;
    private Node targetNode;
    private final Set<Node> visitedNodes = new HashSet<>();
    private final PriorityQueue<Node> searchFrontier = new PriorityQueue<>();
    private BiFunction<SearchProblem, Node, Integer> costCalculator;

    public SearchAlgorithm(SearchProblem initProblem, BiFunction<SearchProblem, Node, Integer> costCalculator) {
        this.sp = initProblem;
        Node root = new Node(sp.getInitState(),0,null,"mixas");
        searchFrontier.add(root);
        this.costCalculator = costCalculator;
    }

    public void expand(Node nodeToBeExpanded){
        nodesExpanded++;
        for(int i = 2; i <= sp.getInitState().size(); i++){
            ArrayList<Integer> stateAfterOperand = sp.T(i, nodeToBeExpanded.getState());
            int cost = costCalculator.apply(sp,nodeToBeExpanded);
            Node childNode = new Node(stateAfterOperand,cost,nodeToBeExpanded,Integer.toString(i));
            if(!mustDiscardNode(childNode)){
                searchFrontier.add(childNode);
            }
        }
    }

    // returns true if a node must be discarded
    private boolean mustDiscardNode(Node childNode){
        return existsInSearchFrontier(childNode) || visitedNodes.contains(childNode);
    }

    //returns true if the new node must be discarded
    private boolean existsInSearchFrontier(Node childNode){
        for (Node searchFrontiersNode : searchFrontier)
            if (searchFrontiersNode.equals(childNode)) {
                if(childNode.getCost() >= searchFrontiersNode.getCost()){
                    return true;
                }else{
                    searchFrontier.remove(searchFrontiersNode);
                    return false;
                }
            }
        return false;
    }

    private boolean isTargetNode(Node possibleTargetNode) {
        if(possibleTargetNode.getState().equals(sp.getTarget())){
            targetNode = possibleTargetNode;
            System.out.println("Found solution |->"+ possibleTargetNode.getState().toString());
            return true;
        }
        return false;
    }

    public void runAlgorithm(){
        while(!searchFrontier.isEmpty()) {
            Node nodeToBeExamined = searchFrontier.remove();
            visitedNodes.add(nodeToBeExamined);
            if(isTargetNode(nodeToBeExamined)){
                printPath();
                break;
            }
            expand(nodeToBeExamined);
        }
    }

    public void printPath(){

        ArrayList<ArrayList<Integer>> path = new ArrayList<>();
        ArrayList<String> operands = new ArrayList<>();
        int totalCost = 0;
        while (targetNode.getParent() != null){
            totalCost++;
            path.add(targetNode.getState());
            operands.add(targetNode.getOperand());
            targetNode = targetNode.getParent();
        }
        path.add(targetNode.getState());
        Collections.reverse(path);
        Collections.reverse(operands);
        formattedPathOutput(path,operands);
        System.out.print("Node(s) Expanded: ");
        System.out.println(nodesExpanded);
        System.out.print("Total Cost: ");
        System.out.println(totalCost);
    }

    private void formattedPathOutput(ArrayList<ArrayList<Integer>> path, ArrayList<String> operands) {
        Iterator<String> operand = operands.iterator();
        String finalOutput = "";
        for(ArrayList<Integer> state: path){
            if (operand.hasNext()){
                finalOutput += state.toString() +"--| "+ operand.next()+" |--> ";
            }else{
                finalOutput += state.toString();
            }
        }
        System.out.println("Path to solution: ");
        System.out.println(finalOutput);
    }
}


