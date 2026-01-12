

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Implements the backtracking search with forward checking. 
 */
public class ForwardCheckingCSPSolver extends BacktrackingCSPSolver {
	
	/**
	 * Implements the actual forward checking. Infers the values to be deleted
	 * from the domains of some variables based on the given variable and value.
	 */
	@Override
	protected <E> Inference<E> inference(CSP<E> csp, Assignment<E> assignment, String var, E value) {
        Inference<E> inference = new Inference<>();
        List<Constraint> relevantConstraints = getConstraintsInvolvingVariable(csp, var);

        for (String neighbor : getUnassignedNeighbors(csp, assignment, var)) {
            List<E> domain = new ArrayList<>(csp.domains.get(neighbor));
            Set<E> removedValues = new HashSet<>();

            for (E neighborValue : csp.domains.get(neighbor)) {
                assignment.put(neighbor, neighborValue);
				boolean consistent = csp.isConsistent(assignment, relevantConstraints);
                if (!consistent) {
                    removedValues.add(neighborValue);
                }
                assignment.remove(neighbor);
            }
            for (E removedValue : removedValues) {
                domain.remove(removedValue);
            }
            if (domain.isEmpty()) {
                return null;
            }
            inference.put(neighbor, removedValues);
        }
        return inference;
	}

	//MY HELPER METHODS

    private <E> List<Constraint> getConstraintsInvolvingVariable(CSP<E> csp, String var) {
        List<Constraint> relevantConstraints = new ArrayList<>();
        for (Constraint constraint : csp.constraints) {
            if (constraint.getScope().contains(var)) {
                relevantConstraints.add(constraint);
            }
        }
        return relevantConstraints;
    }

    private <E> List<String> getUnassignedNeighbors(CSP<E> csp, Assignment<E> assignment, String var) {
        List<String> neighbors = new ArrayList<>();
        for (String neighbor : csp.variables) {
            if (!assignment.containsKey(neighbor) && isNeighbor(var, neighbor, csp.constraints)) {
                neighbors.add(neighbor);
            }
        }
        return neighbors;
    }

    private boolean isNeighbor(String var1, String var2, List<Constraint> constraints) {
        for (Constraint constraint : constraints) {
            List<String> scope = constraint.getScope();
            if (scope.contains(var1) && scope.contains(var2)) {
                return true;
            }
        }
        return false;
    }
}
