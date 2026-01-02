package mibi.assignment1;

import java.util.*;

public abstract class AbstractEnvironment<P, A> implements Environment<P, A> , Notifier {
	
	protected Set<EnvironmentObject> envObjects = new LinkedHashSet<>(); 
	protected Set<Agent<? super P, ? extends A>> agents = new LinkedHashSet<>();
	protected Map<Agent<?, ?>, Double> performanceMeasures = new LinkedHashMap<>();
	protected Set<EnvironmentListener<? super P, ? super A>> listeners = new LinkedHashSet<>();

	@Override
	public void step() {
		for (Agent<? super P, ? extends A> agent : agents) {
			if (agent.isAlive()) {
				P percept = getPerceptSeenBy(agent);
				Optional<? extends A> anAction = agent.act(percept);
				if (anAction.isPresent()) {
					execute(agent, anAction.get());
					notify(agent, percept, anAction.get());
				} else {
					executeNoOp(agent);
				}
			}
		}
		createExogenousChange();
	}

	@Override
	public boolean isDone() {
		return agents.stream().noneMatch(Agent::isAlive);
	}

	protected void executeNoOp(Agent<?,?> agent) {
	}

	protected void createExogenousChange() {
	}

	public abstract void execute(Agent<?, ?> agent, A action);

	public abstract P getPerceptSeenBy(Agent<?, ?> agent);

	public double getPerformanceMeasure(Agent<?, ?> agent) {
		return performanceMeasures.computeIfAbsent(agent, k -> 0.0);
	}

	protected void updatePerformanceMeasure(Agent<?, ?> forAgent, double addTo) {
		performanceMeasures.put(forAgent, getPerformanceMeasure(forAgent) + addTo);
	}
	
	@Override
	public void notify(String msg) {
		listeners.forEach(listener -> listener.notify(msg));
	}

	protected void notify(Agent<?, ?> agent) {
		listeners.forEach(listener -> listener.agentAdded(agent, this));
	}

	protected void notify(Agent<?, ?> agent, P percept, A action) {
		listeners.forEach(listener -> listener.agentActed(agent, percept, action, this));
	}

	@Override
	public void addAgent(Agent<? super P, ? extends A> agent) {
		agents.add(agent);
		addEnvironmentObject(agent);
		notify(agent);
	}

	@Override
	public void removeAgent(Agent<? super P, ? extends A> agent) {
		agents.remove(agent);
		removeEnvironmentObject(agent);
	}

	@Override
	public List<Agent<?, ?>> getAgents() {
		return new ArrayList<>(agents);
	}

	@Override
	public List<EnvironmentObject> getEnvironmentObjects() {
		return new ArrayList<>(envObjects);
	}

	@Override
	public void addEnvironmentObject(EnvironmentObject eo) {
		envObjects.add(eo);
	}

	@Override
	public void removeEnvironmentObject(EnvironmentObject eo) {
		envObjects.remove(eo);
	}

	@Override
	public void addEnvironmentListener(EnvironmentListener<? super P, ? super A> listener) {
		listeners.add(listener);
	}

	@Override
	public void removeEnvironmentListener(EnvironmentListener<? super P, ? super A> listener) {
		listeners.remove(listener);
	}

}