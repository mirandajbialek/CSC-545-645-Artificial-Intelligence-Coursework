

import java.util.List;
import java.util.ArrayList;

/**
 * Implements the inequality constraint of two variables. 
 */
public class InequalityConstraint implements Constraint {
	private String value1, value2;

	 //in context of map, value1 and value2 are two STRINGS of bordering states
	public InequalityConstraint(String value1, String value2) {
		this.value1 = value1;
		this.value2 = value2;
	}

	@Override
	public List<String> getScope() {
		List<String> scope = new ArrayList<String>();
		scope.add(value1);
		scope.add(value2);
		return scope;
	}

	@Override
	public <E> boolean isConsistent(Assignment<E> assignment) {
		E assignedValue1 = assignment.get(value1); //would be an int representing state's color
		E assignedValue2 = assignment.get(value2);
	
		if (assignedValue1 == null || assignedValue2 == null) {
			return true;
		}
	
		return !assignedValue1.equals(assignedValue2); //in the case of states, the object compared would be Integer 

	}
	
	@Override
	public String toString() {
		return value1 + " != " + value2;
	}
	
	@Override
	public boolean equals(Object o) { 
		if(o == null || !(o instanceof InequalityConstraint)) {
			return false;
		}
		
		InequalityConstraint other = (InequalityConstraint)o;
		return value1.equals(other.value1) && value2.equals(other.value2);
	}
}
