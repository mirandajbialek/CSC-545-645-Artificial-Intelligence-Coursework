package mibi.assignment1;

import java.util.Optional;
import java.util.Set;

public abstract class SimpleReflexAgentProgram<P, A> implements AgentProgram<P, A> {
	private Set<Rule<A>> rules;

	public SimpleReflexAgentProgram(Set<Rule<A>> rules) {
		this.rules = rules;
	}

	@Override
	public final Optional<A> thinking(P percept) {
		State state = interpretInput(percept);
		Rule<A> rule = ruleMatch(state, rules);
		if (rule != null) {
			return Optional.ofNullable(rule.getAction());
		} else {
			return Optional.empty();
		}
	}

	protected abstract State interpretInput(P p);

	private Rule<A> ruleMatch(ObjectWithDynamicAttributes state, Set<Rule<A>> rules) {
		for (Rule<A> rule : rules) {
			if (rule.evaluate(state)) {
				return rule;
			}
		}
		return null;
	}
}

