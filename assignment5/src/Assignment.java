import java.util.HashMap;
import java.util.List;

/**
 * An assignment for the variables (or a subset of them) of a csp. .....so i guess this is like a state or individual?
 *
 * @param <E> The type of the values of the variables.
 */
@SuppressWarnings("serial")
public class Assignment<E> extends HashMap<String, E> {
	
	/**
	 * Check if all variables in the csp are actually contained
	 * in the assignment. Returns whether all variables of the csp have a value assigned.
	 * @param csp The underlying csp
	 * @return Whether the assignment is complete.
	 */
	public boolean isComplete(CSP<E> csp) {
		 List<String> cspVariables = csp.variables;
		 return this.keySet().containsAll(cspVariables);
	}

	public Assignment<E> deepCopy() {
        Assignment<E> copy = new Assignment<E>();
        for (Entry<String, E> entry : this.entrySet()) {
            String keyCopy = new String(entry.getKey());
            E valueCopy;
            
            // Check if the value is clonable
            if (entry.getValue() instanceof Cloneable) {
                try {
                    valueCopy = (E) entry.getValue().getClass().getMethod("clone").invoke(entry.getValue());
                } catch (Exception e) {
                    throw new RuntimeException("Failed to clone value of type " + entry.getValue().getClass().getName(), e);
                }
            } else {

                valueCopy = entry.getValue();
            }
            copy.put(keyCopy, valueCopy);
        }
        return copy;
    }
}
