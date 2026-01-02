package mibi.assignment1;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Artificial Intelligence A Modern Approach (3rd Edition): pg 58.<br>
 * <br>
 * Let the world contain just two locations. Each location may or may not
 * contain dirt, and the agent may be in one location or the other. There are 8
 * possible world states, as shown in Figure 3.2. The agent has three possible
 * actions in this version of the vacuum world: <em>Left</em>, <em>Right</em>,
 * and <em>Suck</em>. Assume for the moment, that sucking is 100% effective. The
 * goal is to clean up all the dirt.
 *
 * @author Ravi Mohan
 * @author Ciaran O'Reilly
 * @author Mike Stampone
 * @author Ruediger Lunde
 */
public class VacuumEnvironment extends AbstractEnvironment<VacuumPercept, Action> {
	// Allowable Actions within the Vacuum Environment
	public static final Action ACTION_MOVE_LEFT = new Action("Left");
	public static final Action ACTION_MOVE_RIGHT = new Action("Right");
	public static final Action ACTION_SUCK = new Action("Suck");
	public static final String LOCATION_A = "A";
	public static final String LOCATION_B = "B";
	public static final String LOCATION_C = "C";
	public static final String LOCATION_D = "D";
	public static final String LOCATION_E = "E";
	public static final String LOCATION_F = "F";


    private final List<String> locations;
	protected VacuumEnvironmentState envState;
	protected boolean isDone = false;

	private int XDimension;
	private int YDimension;

	public enum LocationState {
		Clean, Dirty
	}

	/**
	 * Constructs a vacuum environment with two locations A and B, in which dirt is
	 * placed at random.
	 */
	public VacuumEnvironment() {
		this(Util.randomBoolean() ? LocationState.Clean : LocationState.Dirty,
				Util.randomBoolean() ? LocationState.Clean : LocationState.Dirty);
		XDimension = locations.size();
		YDimension = 1;
	}

	/**
	 * Constructs a vacuum environment with two locations A and B, in which dirt is placed as specified.
	 * 
	 * @param locAState
	 *            the initial state of location A, which is either
	 *            <em>Clean</em> or <em>Dirty</em>.
	 * @param locBState
	 *            the initial state of location B, which is either
	 *            <em>Clean</em> or <em>Dirty</em>.
	 */
	public VacuumEnvironment(LocationState locAState, LocationState locBState) {
		this(Arrays.asList(LOCATION_A, LOCATION_B), locAState, locBState);
		XDimension = locations.size();
		YDimension = 1;
	}

	/**
	 * Constructor which allows subclasses to define a vacuum environment with an arbitrary number
	 * of squares. Two-dimensional grid environments can be defined by additionally overriding
	 * {@link #getXDimension()} and {@link #getYDimension()}.
	 */
	protected VacuumEnvironment(List<String> locations, LocationState... locStates) {
		this.locations = locations;
		envState = new VacuumEnvironmentState();
		for (int i = 0; i < locations.size() && i < locStates.length; i++)
			envState.setLocationState(locations.get(i), locStates[i]);
		XDimension = locations.size();
		YDimension = 1;
	}

	//2D constructor.... would still have to implement a forward/back moving vaccuum for this one
	protected VacuumEnvironment(int xDim, int yDim, List<String> locations, LocationState... locStates) {
		this.locations = locations;
		envState = new VacuumEnvironmentState();
		for (int i = 0; i < locations.size() && i < locStates.length; i++)
			envState.setLocationState(locations.get(i), locStates[i]);
		XDimension = xDim;
		YDimension = yDim;
	}

	@Override
	public void addAgent(Agent<? super VacuumPercept, ? extends Action> agent) {
		int idx = new Random().nextInt(locations.size());
		envState.setAgentLocation(agent, locations.get(idx));
		super.addAgent(agent);
	}

	public void addAgent(Agent<? super VacuumPercept, ? extends Action> agent, String location) {
		envState.setAgentLocation(agent, location);
		super.addAgent(agent);
	}

	@Override
	public VacuumPercept getPerceptSeenBy(Agent<?, ?> agent) {
		String loc = envState.getAgentLocation(agent);
		VacuumPercept percept = new VacuumPercept(loc, envState.getLocationState(loc));
		return percept;
	}

	@Override
	public void execute(Agent<?, ?> agent, Action action) {
		String loc = getAgentLocation(agent);
		if (action == ACTION_MOVE_RIGHT) {
			int x = getX(loc);
			if (x < getXDimension())
				envState.setAgentLocation(agent, getLocation(x + 1, getY(loc)));
			updatePerformanceMeasure(agent, -1);
		} else if (action == ACTION_MOVE_LEFT) {
			int x = getX(loc);
			if (x > 1)
				envState.setAgentLocation(agent, getLocation(x - 1, getY(loc)));
			updatePerformanceMeasure(agent, -1);
		} else if (action == ACTION_SUCK) {
			if (envState.getLocationState(loc) == LocationState.Dirty) {
				envState.setLocationState(loc, LocationState.Clean);
				updatePerformanceMeasure(agent, 10);
			}
		}
	}

	@Override
	protected void executeNoOp(Agent<?, ?> agent) {
		// In the Vacuum Environment we consider things done if the agent's act method returns no action.
		System.out.println("Vacuum has finished.");
		isDone = true;
	}

	@Override
	public boolean isDone() {
		return super.isDone() || isDone;
	}

	public List<String> getLocations() {
		return locations;
	}

	public VacuumEnvironmentState getCurrentState() {
		return envState;
	}

	public LocationState getLocationState(String location) {
		return envState.getLocationState(location);
	}

	public String getAgentLocation(Agent<?, ?> agent) {
		return envState.getAgentLocation(agent);
	}

	// Information for grid views...

	public int getXDimension() {
		return XDimension;
	}

	public int getYDimension() {
		return YDimension;
	}

	// 1 means left
	public int getX(String location) {
		return getLocations().indexOf(location) % getXDimension() + 1;
	}

	// 1 means bottom
	public int getY(String location) {
		return getYDimension() - getLocations().indexOf(location) / getXDimension();
	}

	// (1, 1) is bottom left
	public String getLocation(int x, int y) {
		return locations.get((getYDimension() - y) * getXDimension() + x - 1);
	}
}