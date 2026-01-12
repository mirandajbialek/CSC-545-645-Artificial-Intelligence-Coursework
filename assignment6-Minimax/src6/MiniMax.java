package src6;

import java.lang.Math;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * A class that implements the MiniMax algorithm.
 */
public class MiniMax {
	private static int numberOfStates; /**< counter to measure the number of iterations / states. */
	private static boolean usePruning;
	
	/**
	 * Start procedure of the MiniMax algorithm.
	 * @param state The state where the MiniMax algorithm starts searching
	 * @param usePruning Whether to use alpha-beta-pruning
	 * @return An optimal action to be taken at this point.
	 */
	public static Action MinimaxDecision(State state, boolean usePruning) {
		MiniMax.usePruning = usePruning;
		numberOfStates = 0;
		float bestUtility = Float.NEGATIVE_INFINITY;
		Action bestAction = null;
		List<Action> bestActions = new ArrayList<>();
	
		for (Action action : state.getActions()) {
			State resultState = state.getResult(action);
			float actionUtility = MinValue(resultState, Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY);
	
			if (actionUtility > bestUtility) {
				bestUtility = actionUtility;
				bestActions.clear();
				bestActions.add(action);
			} else if (actionUtility == bestUtility) {
				bestActions.add(action);
			}
		}
	
		Random rand = new Random();
		bestAction = bestActions.get(rand.nextInt(bestActions.size()));
	
		System.out.println("State space size: " + numberOfStates);
		return bestAction;
	}
	
	/**
	 * @param state The current state to be evaluated
	 * @param alpha The current value for alpha
	 * @param beta The current value for beta
	 * @return The maximum of the utilites invoking MinValue, or the utility of the state if it is a leaf.
	 */
	private static float MaxValue(State state, float alpha, float beta) {
		++numberOfStates;

		if (state.isTerminal()) {
			return state.getUtility();
		}
	
		float v = Float.NEGATIVE_INFINITY;
		for (Action action : state.getActions()) {
			State resultState = state.getResult(action);
			v = Math.max(v, MinValue(resultState, alpha, beta));
			if (usePruning) {
				if (v >= beta) {
					return v;
				}
				alpha = Math.max(alpha, v);
			}
		}
		return v;
	}
	
	/**
	 * @param state The current state to be evaluated
	 * @param alpha The current value for alpha
	 * @param beta The current value for beta
	 * @return The minimum of the utilites invoking MaxValue, or the utility of the state if it is a leaf.
	 */
	private static float MinValue(State state, float alpha, float beta) {
		++numberOfStates;

		 if (state.isTerminal()) {
			return state.getUtility();
		}
	
		float v = Float.POSITIVE_INFINITY;
		for (Action action : state.getActions()) {
			State resultState = state.getResult(action);
			v = Math.min(v, MaxValue(resultState, alpha, beta));
			if (usePruning) {
				if (v <= alpha) {
					return v;
				}
				beta = Math.min(beta, v);
			}
		}
		return v;
	}
}