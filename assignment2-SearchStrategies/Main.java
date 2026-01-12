package mibi.assignment2;
import java.util.ArrayList;

public class Main {

    public static void main(String args[]){
        System.out.println("the goat - wolf problem:");
        State currentState = new State();
        State goalState = new State(0,0,3,3);
        Node root = new Node(currentState);
        
        int maxDepth = 10; //to prevent infinite searching
        Node solution = IDS(root, goalState, maxDepth);
        if (solution == null){
            System.out.println("\nNo solution");
        } else {
            System.out.println("\nSolution found!!");
        }
    }

    public static Node IDS(Node node, State gState, int maxDepth) {
        for (int i = 0; i < maxDepth; i++){
            System.out.println("\n\nSearching at a depth of " + i);
            System.out.println("-------------------------");
            Node result = depthLimitedSearch(node, gState, i);
            if (result != null){
                return result;
            }
        }
        return null;
    }

    public static Node depthLimitedSearch(Node node, State gState, int depthLimit) {
        System.out.print("current state: ");
        node.state.printState();

        if (goalTest(node.state, gState)) {
            return node;
        } 

        if (node.calculateDepth() == depthLimit ) {
            return null;
        }

        ArrayList<State> possibleStates = generateStates(node.state);
        System.out.print("\n\n" + possibleStates.size() + " children:");
        for (State s : possibleStates){
            node.children.add(new Node(s));
            s.printState();
        }
        System.out.println("\n\n");

        //will return node if soln exists, null otherwise
        for (Node child : node.children) {
            return depthLimitedSearch(child, gState, depthLimit);
        }
        return null;
    }

    public static ArrayList<State> generateStates(State cState){
        ArrayList<State> states = new ArrayList<State>();

        //move one goat to right
        State p1 = cState.moveGoatRight();
        if (actionValid(p1)){
            states.add(p1);
        }
        //move one goat one wolf to right
        State p2 = cState.moveGoatRight().moveWolfRight();
        if (actionValid(p2)){
            states.add(p2);
        }
        //move two goat right
        State p3 = cState.moveGoatRight().moveGoatRight();
        if (actionValid(p3)){
            states.add(p3);
        }
        //move one wolf right
        State p4 = cState.moveWolfRight();
        if (actionValid(p4)){
            states.add(p4);
        }
        //move two wolf right
        State p5 = cState.moveWolfRight().moveWolfRight();
        if (actionValid(p5)){
            states.add(p5);
        }
        //move one goat to left
        State p6 = cState.moveGoatLeft();;
        if (actionValid(p6)){
            states.add(p6);
        }
        //move one goat one wolf to left
        State p7 = cState.moveGoatLeft().moveWolfLeft();
        if (actionValid(p7)){
            states.add(p7);
        }
        //move two goat left
        State p8 = cState.moveGoatLeft().moveGoatLeft();
        if (actionValid(p8)){
            states.add(p8);
        }
        //move one wolf left
        State p9 = cState.moveWolfLeft();;
        if (actionValid(p9)){
            states.add(p9);
        }
        //move two wolf left
        State p10 = cState.moveWolfLeft().moveWolfLeft();
        if (actionValid(p10)){
            states.add(p10);
        }
        return states;
    }

    public static boolean goalTest(State cState,State gState){
        return cState.equals(gState);
    }

    public static boolean actionValid(State potentialState){
        Integer goatLeft = potentialState.leftBank.get("goats");
        Integer goatRight = potentialState.rightBank.get("goats");
        Integer wolfLeft = potentialState.leftBank.get("wolves");
        Integer wolfRight = potentialState.rightBank.get("wolves");

        if (goatLeft < 0 || goatRight < 0 || wolfLeft < 0 || wolfRight < 0 || goatLeft > 3 || goatRight > 3 || wolfLeft > 3 || wolfRight > 3)
            return false;

        if ( (goatLeft > 0 && goatLeft<wolfLeft) || (goatRight > 0 && goatRight<wolfRight))
            return false;

        return true;
    }
}
