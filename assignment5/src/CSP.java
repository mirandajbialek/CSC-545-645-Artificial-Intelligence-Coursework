import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A class representing a CSP.
 * @param <E> The type of the values of the variables of the csp.
 */
public class CSP<E> {
	public List<String> variables; /**< The set of variables */
	public Map<String, List<E>> domains; /**< A mapping of variables to their domains */
	public List<Constraint> constraints; /**< The constraints that define the csp */
	
	/**
	 * Default constructor creating an empty CSP.
	 */
	public CSP() {
		variables = new ArrayList<String>();
		domains = new HashMap<String, List<E>>();
		constraints = new ArrayList<Constraint>();
	}

	public int totalNumberDomains(){
		int total = 0;
		for (Map.Entry<String, List<E>> e : domains.entrySet()){
			total += e.getValue().size();
		}
		return total;
	}

	//{KY = Blue, RI = Red, .....}

	/**
	 * Checks whether an assignment is consistent with all constraints of this CSP.
	 * @param assignment The assignment to be checked
	 * @return Whether all constraints are fulfilled
	 */

	 //in the context of this problem, return true if no adj states have same color?
	 //"IS LEGAL"
	public boolean isConsistent(Assignment<E> assignment) {
		for (Constraint c : constraints){ //here, constraint list contains border indicies 
			if (!c.isConsistent(assignment)){
				return false;
			}
		}
		return true;
	}

	public boolean isConsistent(Assignment<E> assignment, List<Constraint> givenConstraints) {
		for (Constraint c : givenConstraints) {
			if (!c.isConsistent(assignment)) {
				return false;
			}
		}
		return true;
	}
}
