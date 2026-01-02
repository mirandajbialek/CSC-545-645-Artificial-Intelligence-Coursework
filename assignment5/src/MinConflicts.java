

import java.util.HashSet;
import java.util.Set;
import java.util.Random;

public class MinConflicts implements CSPSolver {
    private static Random rand = new Random();
    
    @Override
    public <E> CSPResult<E> solve(CSP<E> csp) {
        return minConflicts(csp, 10000);
    }

    private static <E> CSPResult<E> minConflicts(CSP<E> csp, int maxSteps) {
		//System.out.println("Beginning search with min conflicts");
        Assignment<E> current = createCompleteAssignment(csp);
        
        for (int i = 0; i < maxSteps; i++) {
            if (current.isComplete(csp) && csp.isConsistent(current)) {
                return new CSPResult<>(current, i);
            }
            String var = getRandomConflictedVariable(current, csp);
            E minConflictValue = null;
            int minConflicts = Integer.MAX_VALUE;

            for (E value : csp.domains.get(var)) {
                int conf = conflicts(var, value, current, csp);
                if (conf < minConflicts) {
                    minConflicts = conf;
                    minConflictValue = value;
                }
            }

            current.put(var, minConflictValue);
        }

        return null;
    }

    private static <E> String getRandomConflictedVariable(Assignment<E> current, CSP<E> csp) {
        Set<String> conflictedVariables = new HashSet<>();

        for (Constraint constraint : csp.constraints) {
            if (!constraint.isConsistent(current)) {
                conflictedVariables.addAll(constraint.getScope());
            }
        }
        int index = rand.nextInt(conflictedVariables.size());
        return conflictedVariables.toArray(new String[0])[index];
    }

    private static <E> Assignment<E> createCompleteAssignment(CSP<E> csp) {
        Assignment<E> assignment = new Assignment<>();

        for (String variable : csp.variables) {
            E[] domainValues = csp.domains.get(variable).toArray((E[]) new Object[0]);
            E randomValue = domainValues[rand.nextInt(domainValues.length)];
            assignment.put(variable, randomValue);
        }
		
        return assignment;
    }

    private static <E> int conflicts(String var, E value, Assignment<E> current, CSP<E> csp) {
        int conflictCount = 0;
        E originalValue = current.get(var);
        current.put(var, value);

        for (Constraint constraint : csp.constraints) {
            if (constraint.getScope().contains(var) && !constraint.isConsistent(current)) {
                conflictCount++;
            }
        }

        current.put(var, originalValue); 
        return conflictCount;
    }
}
