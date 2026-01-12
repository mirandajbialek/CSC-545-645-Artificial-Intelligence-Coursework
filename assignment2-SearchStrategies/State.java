package mibi.assignment2;

import java.util.HashMap;
import java.util.Map;

public class State {

    public Map<String, Integer> leftBank;
    public Map<String, Integer> rightBank; 
    
    public State() {
        leftBank= new HashMap<>();
        leftBank.put("goats", 3);
        leftBank.put("wolves", 3);

        rightBank= new HashMap<>();
        rightBank.put("goats", 0);
        rightBank.put("wolves", 0);
    }

    public State(int lg, int lw, int rg, int rw) {
        leftBank= new HashMap<>();
        leftBank.put("goats", lg);
        leftBank.put("wolves", lw);

        rightBank= new HashMap<>();
        rightBank.put("goats", rg);
        rightBank.put("wolves", rw);
    }

    public boolean equals(State otherState){
        return (otherState.leftBank.get("goats").equals(this.leftBank.get("goats"))) && (otherState.rightBank.get("wolves").equals(this.rightBank.get("wolves")));
    }

    public State cloneState(){
        return new State(leftBank.get("goats"), leftBank.get("wolves"), rightBank.get("goats"), rightBank.get("wolves"));
    }

    public State moveGoatLeft(){
        State newState = cloneState();
        Integer newRight = rightBank.get("goats") - 1;
        newState.rightBank.put("goats", newRight);
        Integer newLeft = leftBank.get("goats") + 1;
        newState.leftBank.put("goats", newLeft); 
        return newState;
    }

    public State moveGoatRight(){
        State newState = cloneState();
        Integer newRight = rightBank.get("goats") + 1;
        newState.rightBank.put("goats", newRight);
        Integer newLeft = leftBank.get("goats") - 1;
        newState.leftBank.put("goats", newLeft); 
        return newState;
    }

    public State moveWolfLeft(){
        State newState = cloneState();
        Integer newRight = rightBank.get("wolves") - 1;
        newState.rightBank.put("wolves", newRight);
        Integer newLeft = leftBank.get("wolves") + 1;
        newState.leftBank.put("wolves", newLeft); 
        return newState;
    }

    public State moveWolfRight(){
        State newState = cloneState();
        Integer newRight = rightBank.get("wolves") + 1;
        newState.rightBank.put("wolves", newRight);
        Integer newLeft = leftBank.get("wolves") - 1;
        newState.leftBank.put("wolves", newLeft); 
        return newState;   
    }

    public void printState(){
        System.out.print("\n");
        for (int i=0;i<leftBank.get("goats"); i++){
            System.out.print("G");
        }
        System.out.print(" ");
        for (int i=0;i<leftBank.get("wolves"); i++){
            System.out.print("W");
        }
        System.out.print(" || ");
        for (int i=0;i<rightBank.get("goats"); i++){
            System.out.print("G");
        }
        System.out.print(" ");
        for (int i=0;i<rightBank.get("wolves"); i++){
            System.out.print("W");
        }
    }
}


