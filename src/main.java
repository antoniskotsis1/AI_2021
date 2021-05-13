import java.util.*;

public class main {


    public static ArrayList<Integer> T(int k,ArrayList<Integer> state) {
        List<Integer> al = state.subList(0, k);
        List<Integer> ar = state.subList(k, state.size());
        Collections.reverse(al);
        ArrayList<Integer> b = new ArrayList<Integer>();
        b.addAll(al);
        b.addAll(ar);
        return b;
    }
    public static void main(String[] args){


//            ArrayList<Integer> state = new ArrayList<>();
//            state.add(1);
//            state.add(4);
//            state.add(2);
//            state.add(3);
//
//            System.out.println(state);
//            System.out.println(T(2,state));
//        }
//
//
//}

        Scanner inp = new Scanner(System.in);
        System.out.print("Give N: ");
        int n = inp.nextInt();
        int count = 0;
        System.out.println("Give inital state: ");
        ArrayList<Integer> initialState = new ArrayList<Integer>();
        while(n > count){
            initialState.add(inp.nextInt());
            count++;

        }
        System.out.println("Type <u> to run UCS or <a> to run A* algorithm");
        String algorithm = inp.next();
        ArrayList<Integer> target = new ArrayList<Integer>();
        target = (ArrayList)initialState.clone();
        Collections.sort(target);
        SearchProblem game = new SearchProblem(target, initialState);


        if (algorithm.equals("u")){
            System.out.println("Running ucs algorithm!");
            SearchAlgorithm ucs = new SearchAlgorithm(game, CostCalculator::calculateUcsCost);
            ucs.runAlgorithm();


        }else{
            System.out.println("Running A* algorithm!");
            SearchAlgorithm aStar = new SearchAlgorithm(game, CostCalculator::calculateAstarCost);
            aStar.runAlgorithm();

        }
    }
}
