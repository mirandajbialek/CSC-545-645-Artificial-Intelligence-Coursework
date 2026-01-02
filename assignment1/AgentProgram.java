package mibi.assignment1;

import java.util.Optional;

public interface AgentProgram<P, A> {

	Optional<A> thinking(P percept);
}