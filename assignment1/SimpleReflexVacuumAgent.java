package mibi.assignment1;

import static mibi.assignment1.VacuumEnvironment.*;
import mibi.assignment1.VacuumEnvironment.LocationState;

import java.util.LinkedHashSet;
import java.util.Set;

public class SimpleReflexVacuumAgent extends Agent<VacuumPercept, Action> {

	private static final String CURRENT_LOCATION = "currentLocation";
	private static final String CURRENT_STATE = "currentState";

	public SimpleReflexVacuumAgent() {
		super(new SimpleReflexAgentProgram<VacuumPercept, Action>(getRuleSet()){		
			@Override
			protected State interpretInput(VacuumPercept percept) {
				State state = new State();
				state.setAttribute(CURRENT_LOCATION, percept.getCurrLocation());
				state.setAttribute(CURRENT_STATE, percept.getCurrState());
				return state;
			}
		});
	}

	private static Set<Rule<Action>> getRuleSet() {
		Set<Rule<Action>> ruleSet = new LinkedHashSet<>();
		// in the form: if 						x		 == 		y, 			then action is z
		ruleSet.add(new Rule<>(new Condition(CURRENT_STATE, LocationState.Dirty), ACTION_SUCK));
		ruleSet.add(new Rule<>(new Condition(CURRENT_LOCATION, LOCATION_A), ACTION_MOVE_RIGHT));
		ruleSet.add(new Rule<>(new Condition(CURRENT_LOCATION, LOCATION_B), ACTION_MOVE_LEFT));
		return ruleSet;
	}
}