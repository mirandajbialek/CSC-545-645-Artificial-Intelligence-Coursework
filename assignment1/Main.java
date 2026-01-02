package mibi.assignment1;

import java.util.Scanner;

public class Main{
    public static void main(String args[]){

        Scanner scanner = new Scanner(System.in);
        String input;
        int numRounds = 0;
        double totalScore = 0;
        double curScore = 0;
        double avgScore = 0;

        System.out.println("Welcome to the vacuum game! type enter for an iteration. type exit to exit.");
        while (true) {
            input = scanner.nextLine();

            if (input.isEmpty()) {
                numRounds++;
                System.out.println("\n\nROUND " + numRounds + ":");
                curScore = vacuumIteration();
                totalScore += curScore;
                avgScore = totalScore/numRounds;

                System.out.println("--------------------------------------------------------");
                System.out.printf("    Round: %d    Performance Score: %.2f  Average: %.2f%n", numRounds, curScore, avgScore);
                System.out.println("--------------------------------------------------------");
                System.out.print("(press enter key for another round, type exit to end) ");
            } else if ("exit".equals(input)) {
                break;
            }
        }
        scanner.close();
    }

    public static double vacuumIteration(){
        Environment<VacuumPercept, Action> env = new VacuumEnvironment();
        EnvironmentListener<Object, Object> view = new EnvironmentView();
		env.addEnvironmentListener(view);
        Agent<VacuumPercept, Action> agent;
		agent = new SimpleReflexVacuumAgent();
        
        env.addAgent(agent);
        
        System.out.println("Starting Environment: " + ((VacuumEnvironment)env).getCurrentState().toString());
        System.out.println("... aka ... "+ ((VacuumEnvironment)env).getCurrentState().toStringSimpler() + "\n");

        //6 iterations because in a two square vacuum environment, the maximum steps it would take to fully clean would be 6
        for (int i = 0; i<6; i++){
            env.step();
            System.out.println("Result: "+ ((VacuumEnvironment)env).getCurrentState().toStringSimpler() + "\n");
        }
		return env.getPerformanceMeasure(agent);
    }
}