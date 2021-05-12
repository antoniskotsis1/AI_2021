import java.util.*;

public class UCS {
    private  int nodesExpanded = 0;
    private final searchProblem sp;
    private Node targetNode;
    private final Set<Node> visitedNodes = new HashSet<>();
    private final PriorityQueue<Node> searchFrontier = new PriorityQueue<>();


    public UCS(searchProblem initState){
        this.sp = initState;
        Node root = new Node(sp.getIntiState(),0,null,0);
        searchFrontier.add(root);
    }

    public void expand(Node node){
        nodesExpanded++;
        for(int i = 2; i <= sp.getIntiState().size(); i++){
            Node newNode = new Node(sp.T(i, node.getState()), sp.getCost()+ node.getCost(),node,i);
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

    public void runUCS(){
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
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        int totalCost = 0;
        while (targetNode.getParent() != null){
            res.add(targetNode.getState());
            totalCost += targetNode.getCost();
            targetNode = targetNode.getParent();
        }
        res.add(targetNode.getState());
        System.out.println("Path to solution: ");
        System.out.println(res);
        System.out.println("Node Expanded:");
        System.out.println(nodesExpanded);
        System.out.println("total cost:");
        System.out.println(totalCost);


    }
}
