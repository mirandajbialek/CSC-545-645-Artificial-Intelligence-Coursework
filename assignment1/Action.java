package mibi.assignment1;

public class Action extends ObjectWithDynamicAttributes{
	public static final String ATTRIBUTE_NAME = "name";

	public Action(String name) {
		this.setAttribute(ATTRIBUTE_NAME, name);
	}

	public String getName() {
		return (String) getAttribute(ATTRIBUTE_NAME);
	}

	public String toString() {
		return getName();
	}

}