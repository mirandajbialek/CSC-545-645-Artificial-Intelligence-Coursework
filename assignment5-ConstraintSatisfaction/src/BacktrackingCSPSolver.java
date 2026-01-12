

import java.util.List;

/**
 * A class implementing the backtracking CSP algorithm.
 */
public class BacktrackingCSPSolver implements CSPSolver {
	private int iterationCount;
	
	/**
	 * Initiates the backtracking search for this CSP.
	 * @return A consistent assignment for this CSP
	 */
	@Override
	public <E> CSPResult<E> solve(CSP<E> csp) {
		iterationCount = 0;
		Assignment<E> finalAssignment = backtrack(csp, new Assignment<E>());
		return new CSPResult<E>(finalAssignment, iterationCount);		
	}
	
	/**
	 * Selects an unassigned variable. For this algorithm, it can be just the first, or a
	 * randomly chosen unassigned variable.
	 * @param assignment The assignment for which to determine an unassigned variable
	 * @return A variable that is not assigned yet.
	 */
	protected <E> String selectUnassignedVariable(CSP<E> csp, Assignment<E> assignment) {
		for (String v : csp.variables){
			if (!assignment.containsKey(v)){
				return v;
			}
		} 
		
		return null;
	}
	
	/**
	 * This method returns the values of the domain of a variable in
	 * a specific order. Can be used to implement e.g. least-constraining-value heuristic.
	 * For this algorithm, it can be in any order, e.g. the arbitrary order in which they are
	 * stored in the csp.
	 * @param variable The variable for which the domain values should be returned
	 * @param assignment The current assignment
	 * @return An ordering of all domain values of the given variable.
	 */
	protected <E> List<E> orderDomainValues(CSP<E> csp, String variable, Assignment<E> assignment) {
		return csp.domains.get(variable); //in context of states, will be arraylist of Integer
	}
	
	/**
	 * This method returns some inference, which is basically a set of domain values that can be safely
	 * deleted from the domains of the csp. To be used to implement e.g. forward-checking heuristic.
	 * If it returns null this means there is some failure and we should back-track.
	 * For this algorithm, it can always return an empty inference.
	 * 
	 * @param assignment The current assignment
	 * @param var The selected variable
	 * @param value The value for the given variable
	 * @return An inference based on the current state of the csp, or null if there is a failure.
	 */
	protected <E> Inference<E> inference(CSP<E> csp, Assignment<E> assignment, String var, E value) {
		return new Inference<E>();
    }

	/**
	 * Actual recursive implementation of the backtracking search. The
	 * implementation can pretty much follow the pseudo-code in the text book.
	 * Basically, the algorithm can be implemented using almost only calls to the other
	 * methods of this and the other classes.
	 * @param assignment The current assignment
	 * @return The updated assignment, or null if there is no valid assignment
	 */
	private <E> Assignment<E> backtrack(CSP<E> csp, Assignment<E> assignment) {
		++iterationCount;
		if (assignment.isComplete(csp)) {
			return assignment; // Return the complete assignment
		}
	
		// Select an unassigned variable
		String var = selectUnassignedVariable(csp, assignment);
	
		// If no unassigned variables are left, return null (failure)
		if (var == null) {
			return null;
		}
	
		// Get the list of domain values in a specific order
		List<E> orderedDomain = orderDomainValues(csp, var, assignment);
	
		for (E value : orderedDomain) { //in context of states, value is an Integer
			Assignment<E> potentialAssignment = assignment.deepCopy();
	
			potentialAssignment.put(var, value);
	
			if (csp.isConsistent(potentialAssignment)) {
				Inference<E> inferences = inference(csp, potentialAssignment, var, value);
	
				// if inferences result in a consistent CSP
				if (inferences != null) {
					inferences.reduceDomain(csp);
	
					// recursively call backtrack on the updated assignment
					Assignment<E> result = backtrack(csp, potentialAssignment);
	
					// restore csp to prev state by reverting inferences
					inferences.restoreDomain(csp);
	
					// if a valid assignment is found in the recursive call, return it
					if (result != null) {
						return result;
					}
				}
			}
		}
	
		// If no valid assignment is found, return null (failure)
		return null;
	}
}
