import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AC3 {
	/**
	 * This class represents a single arc for the AC-3 algorithm.
	 */
	public static class Arc {
		private String value1, value2;

		public Arc(String value1, String value2) {
			this.value1 = value1;
			this.value2 = value2;
		}
	}
	
	/**
	 * Implements the AC-3 algorithm to make a csp arc consistent.
	 * @param csp The csp
	 * @return Whether an inconsistency was found (false) or not (true)
	 * @throws Exception
	 */
	public static <E> boolean ac3(CSP<E> csp) throws Exception {
		//System.out.println("Beginning AC-3. Total number domains = " + csp.totalNumberDomains());
		Queue<Arc> queue= new LinkedList<>();

        for (Constraint constraint : csp.constraints) {
            List<String> scope = constraint.getScope();
            if (scope.size() == 2) {
                queue.add(new Arc(scope.get(0), scope.get(1)));
                queue.add(new Arc(scope.get(1), scope.get(0)));
            }
        }

		while (!queue.isEmpty()){
			Arc arc = queue.poll();
			if (revise(csp, arc.value1, arc.value2)) {
				if (csp.domains.get(arc.value1).isEmpty()){
					System.out.println("Ending AC-3. Total number domains = " + csp.totalNumberDomains());
					return false;
				}

				Set<String> neighbors = neighbors(csp, arc.value1);
				neighbors.remove(arc.value2);

				for (String neighbor : neighbors){
					queue.add(new Arc(neighbor, arc.value1));
				}
			}
		}
		//System.out.println("Ending AC-3. Total number domains = " + csp.totalNumberDomains());
		return true;
	}
	
	/**
	 * Implements the revise-routine of the AC-3 algorithm. Effectively iterates
	 * over all domain values of var1 and checks if there is at least 1 possible value
	 * for var2 remaining. If not, removes that value from the domain of var1.
	 * @param csp
	 * @param var1
	 * @param var2
	 * @return
	 */
	private static <E> boolean revise(CSP<E> csp, String var1, String var2) {
		 boolean revised = false; 
		 List<E> toRemove = new ArrayList<>();

		 for (E value1 : csp.domains.get(var1)) {
			boolean consistent = false;
			for (E value2 : csp.domains.get(var2)) {
				Assignment<E> assignment = new Assignment<E>();
				assignment.put(var1, value1);
				assignment.put(var2, value2);

				//check is assignment is legal 
				if (csp.isConsistent(assignment)){
					consistent = true;
					break;
				}
			}
			if (!consistent){
				toRemove.add(value1);
				revised = true;
			}
		 }

		 //remove vals from domain of var1
		 csp.domains.get(var1).removeAll(toRemove);
		 return revised;
	}
	
	/**
	 * Computes the "neighbors" of a variable in a CSP. A variable is
	 * a neighbor if it is coupled to another variable by a constraint.
	 * @param csp The csp
	 * @param var The variable the neighbors of which are to be found.
	 * @return The neighbors of the given variable.
	 */
	private static Set<String> neighbors(CSP<?> csp, String var) {
		Set<String> neighborSet = new HashSet<>();
        for (Constraint constraint : csp.constraints) {
            List<String> scope = constraint.getScope();
            if (scope.contains(var)) {
                neighborSet.addAll(scope);
            }
        }
        neighborSet.remove(var); // remove the variable itself from the neighbors
        return neighborSet;
	}
}
