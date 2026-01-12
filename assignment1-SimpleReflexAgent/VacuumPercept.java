package mibi.assignment1;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import mibi.assignment1.VacuumEnvironment.LocationState;

public class VacuumPercept  implements Percept{
	private final String currLocation;
	private final LocationState currState;
	private final Map<String, Object> dynAttributes;

	public VacuumPercept(String currLocation, LocationState state) {
		this.currLocation = currLocation;
		this.currState = state;
		dynAttributes = new LinkedHashMap<>();
	}

	public String getCurrLocation() {
		return currLocation;
	}

	public LocationState getCurrState() {
		return currState;
	}

	public Object getAttribute(String key) {
		return dynAttributes.get(key);
	}

	public void setAttribute(String key, Object value) {
		dynAttributes.put(key, value);
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("[location: ").append(getCurrLocation()).append(", state:").append(getCurrState());
		if (!dynAttributes.isEmpty())
			for (Map.Entry<String, Object> e : dynAttributes.entrySet())
				result.append(", ").append(e.getKey()).append("=").append(e.getValue());
		result.append("]");
		return result.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && getClass() == obj.getClass()) {
			VacuumPercept vp = (VacuumPercept) obj;
			return Objects.equals(currLocation, vp.currLocation)
					&& currState == vp.currState
					&& dynAttributes.equals(vp.dynAttributes);
		}
		return false;
	}
}