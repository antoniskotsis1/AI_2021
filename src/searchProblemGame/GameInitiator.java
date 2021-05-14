package searchProblemGame;

import java.util.*;

public class GameInitiator {


    public static void main(String[] args){

        String ans = "y";
        Scanner inp = new Scanner(System.in);
        while(ans.equals("y")){
            System.out.print("Give N: ");
            int n = inp.nextInt();
            System.out.println("Give initial state: ");
            ArrayList<Integer> initialState = new ArrayList<>();
            while(n > initialState.size()){
                int element = inp.nextInt();
                initialState.add(element);
            }
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
