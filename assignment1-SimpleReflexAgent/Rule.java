package mibi.assignment1;

public class Rule<A> {
	private Condition condition;
	private A action;

	public Rule(Condition c, A a) {
		assert (null != condition);

		condition = c;
		action = a;
	}

	public boolean evaluate(ObjectWithDynamicAttributes p) {
		return (condition.evaluate(p));
	}

	public A getAction() {
		return action;
	}

	@Override
	public boolean equals(Object o) {
		return o != null && o.getClass() == getClass() && toString().equals(((Rule) o).toString());
	}

	@Override
	public int hashCode() {
		return toString().hashCode();
	}

	@Override
	public String toString() {
		return "if " + condition + " then " + action + ".";
	}
}