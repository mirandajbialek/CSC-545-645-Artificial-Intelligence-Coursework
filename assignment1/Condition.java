package mibi.assignment1;

public class Condition {
	private Object key;
	private Object value;

	public Condition(Object key, Object value) {
		if (key == null) {
			throw new IllegalArgumentException("Key cannot be null");
		}
		if (value == null) {
			throw new IllegalArgumentException("Value cannot be null");
		}
		
		this.key = key;
		this.value = value;
	}

	public boolean evaluate(ObjectWithDynamicAttributes p) {
		return value.equals(p.getAttribute(key));
	}

	@Override
	public String toString() {
		return String.valueOf(key) + "==" + value;
	}

	public int hashCode() {
		return toString().hashCode();
	}
}