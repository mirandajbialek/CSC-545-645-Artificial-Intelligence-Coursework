package mibi.assignment1;

import java.util.LinkedHashMap;
import java.util.Map;

import mibi.assignment1.VacuumEnvironment.LocationState;

/**
 * Represents a state in the Vacuum World
 * 
 * @author Ciaran O'Reilly
 * @author Andrew Brown
 * @author Ruediger Lunde
 */
public class VacuumEnvironmentState implements Cloneable {

	private Map<String, VacuumEnvironment.LocationState> state;
	private Map<Agent<?, ?>, String> agentLocations;

	public VacuumEnvironmentState() {
		state = new LinkedHashMap<>();
		agentLocations = new LinkedHashMap<>();
	}

	public String getAgentLocation(Agent<?, ?> agent) {
		return agentLocations.get(agent);
	}

	public void setAgentLocation(Agent<?, ?> agent, String location) {
		agentLocations.put(agent, location);
	}

	public VacuumEnvironment.LocationState getLocationState(String location) {
		return state.get(location);
	}

	public void setLocationState(String location, VacuumEnvironment.LocationState locState) {
		state.put(location, locState);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && getClass() == obj.getClass()) {
			VacuumEnvironmentState s = (VacuumEnvironmentState) obj;
			return state.equals(s.state) && agentLocations.equals(s.agentLocations);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return 3 * state.hashCode() + 13 * agentLocations.hashCode();
	}

	@Override
	public VacuumEnvironmentState clone() {
		VacuumEnvironmentState result = null;
		try {
			result = (VacuumEnvironmentState) super.clone();
			result.state = new LinkedHashMap<>(state);
			agentLocations = new LinkedHashMap<>(agentLocations);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("{");
		for (Map.Entry<String, VacuumEnvironment.LocationState> entity : state.entrySet()) {
			if (builder.length() > 2) builder.append(", ");
			builder.append(entity.getKey()).append("=").append(entity.getValue());
		}
		// int i = 0;
		// for (Map.Entry<Agent<?, ?>, String> entity : agentLocations.entrySet()) {
		// 	if (builder.length() > 2) builder.append(", ");
		// 	builder.append("Loc").append(++i).append("=").append(entity.getValue());
		// }
		builder.append("}");
		return builder.toString();
	}

	public String toStringSimpler() {
		StringBuilder builder = new StringBuilder("{ ");
		String toAdd = "";
		for (Map.Entry<String, VacuumEnvironment.LocationState> entry : state.entrySet()) {
			toAdd = getLocationState(entry.getKey()) == LocationState.Dirty ? "#" : entry.getKey();
			builder.append(toAdd + " ");
		}
		// int i = 0;
		// for (Map.Entry<Agent<?, ?>, String> entity : agentLocations.entrySet()) {
		// 	if (builder.length() > 2) builder.append(", ");
		// 	builder.append("Loc").append(++i).append("=").append(entity.getValue());
		// }
		builder.append("}");
		return builder.toString();
	}
}