package mibi.assignment1;

import java.util.Optional;

public class Agent<P, A> implements EnvironmentObject {

	protected AgentProgram<P, A> program;
	private boolean alive = true;

	public Agent() { }

	public Agent(AgentProgram<P, A> program) {
		this.program = program;
	}

	public Optional<A> act(P percept) {
		return (null != program) ? program.thinking(percept) : Optional.empty();
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}
}