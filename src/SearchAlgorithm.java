import java.util.*;
import java.util.function.BiFunction;

public class SearchAlgorithm {
    private  int nodesExpanded = 0;
    private final SearchProblem sp;
    private Node targetNode;
    private final Set<Node> visitedNodes = new HashSet<>();
    private final PriorityQueue<Node> searchFrontier = new PriorityQueue<>();
    private BiFunction<SearchProblem, Node, Integer> costCalculator;

    public SearchAlgorithm(SearchProblem initState, BiFunction<SearchProblem, Node, Integer> costCalculator) {
        this.sp = initState;
        Node root = new Node(sp.getIntiState(),0,null,"mixas");
        searchFrontier.add(root);
        this.costCalculator = costCalculator;
    }



    public void expand(Node node){
        nodesExpanded++;
        for(int i = 2; i <= sp.getIntiState().size(); i++){
            ArrayList<Integer> stateAfterOperand = sp.T(i, node.getState());
            int heuristicCost = costCalculator.apply(sp,node);
            Node newNode = new Node(stateAfterOperand,heuristicCost,node,Integer.toString(i));
            if(!mustDiscardNode(newNode)){
                searchFrontier.add(newNode);
            }
        }
    }

    // returns true if a node must be discarded
    private boolean mustDiscardNode(Node newNode){
        return searchSFrontier(newNode) || visitedNodes.contains(newNode);
    }



    //returns true if the new node must be discarded
    private boolean searchSFrontier(Node newNode){
        for (Node node : searchFrontier)
            if (node.equals(newNode)) {

                if(newNode.getCost() >= node.getCost()){
                    return true;
                }else{
                    searchFrontier.remove(node);
                    return false;
                }

            }
        return false;
    }

    private boolean isTargetNode(Node newNode) {
        if(newNode.getState().equals(sp.getTarget())){
            targetNode = newNode;
            System.out.println("found solution"+ newNode.getState().toString());
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
        System.out.print("ela mesa");

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


