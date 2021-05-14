package searchProblemGame;

import java.util.*;

public class SearchGameInitiator {

    private static boolean checkUsersInput(ArrayList<Integer> initialState,int N){
        return areDistinct(initialState) && haveRightRange(initialState,N);

    }

    private static boolean haveRightRange(ArrayList<Integer> initialState,int N) {
        for(Integer element: initialState){
            if(element > N || element < 0){
                System.out.println("Range of inputs must be from 1 to "+N);
                return false;
            }
        }
        return true;
    }

    private static boolean areDistinct(ArrayList<Integer> initialState) {
        HashSet<Integer> distinctUserInputValues = new HashSet<>(initialState);
        boolean inputIsLegal = initialState.size() == distinctUserInputValues.size();
        if(!inputIsLegal){
            System.out.println("Initial state values must be distinct.");
        }
        return inputIsLegal;
    }

    private static ArrayList<Integer> getInitialState(Scanner inp,int n) {
        while(true){
            ArrayList<Integer> initialState = new ArrayList<>();
            while(n > initialState.size()){
                int element = inp.nextInt();
                initialState.add(element);
            }
            if(checkUsersInput(initialState,n)){
                return initialState;

            }else{
                System.out.println("Illegal input.Type initial state again: ");
            }
        }
    }

    public static void main(String[] args){

        String ans = "y";
        Scanner inp = new Scanner(System.in);
        while(ans.equals("y")){
            System.out.print("Give N: ");
            int n = inp.nextInt();
            System.out.println("Give initial state: ");
            ArrayList<Integer> initialState = getInitialState(inp, n);
            System.out.println("Type <u> to run UCS or <a> to run A* algorithm");
            String algorithm = inp.next();
            ArrayList<Integer> target = new ArrayList<>(initialState);
            Collections.sort(target);
            SearchProblem searchProblem = new SearchProblem(target, initialState);


            if (algorithm.equals("u")){
                System.out.println("Running ucs algorithm!");
                SearchAlgorithm ucs = new SearchAlgorithm(searchProblem, CostCalculator::calculateUcsCost);
                ucs.runAlgorithm();

            }else{
                System.out.println("Running A* algorithm!");
                SearchAlgorithm aStar = new SearchAlgorithm(searchProblem, CostCalculator::calculateAstarCost);
                aStar.runAlgorithm();

            }
            System.out.println("Play again? <y> or <n>");
            ans = inp.next();

        }
    }


}
