package src;

public class Individual {
	private StateMap map; // the map
	private double fitness; // fitness is cached and only updated on request whenever necessary
	private int[] colors; //represents genom of individual
	/**
	 * Updates the fitness value based on the genom and the map.
	 */
	public void updateFitness() {		
		double adjacentColors = 0;
		for (Border border : map.borders) {
			if (colors[border.index1] == colors[border.index2]) {
				adjacentColors++;
			}
		}

		//subtracting adjacent colors from the maximum fitness value, which would be the total number of borders (going in both directions for simplicity)
		//... thus if no conflicts are found, then the fitness value would be the highest 
		fitness = map.borders.size() - adjacentColors; 
	}
	/**
	 * Default ctor. Creates a (valid) random individual.
	 * @param map The US states map.
	 */
	public Individual(StateMap map) {
		this.map = map;
		int n = map.states.size();
		colors = new int[n];

		for (int i = 0; i < n; ++i){
			colors[i] = (int)(Math.random() * 4); //here, the numbers 0 - 3 represent the 4 colors 
		}
		updateFitness();				
	}

	/**
	 * Reproduces a child randomly from two individuals (see textbook).
	 * @param x The first parent.
	 * @param y The second parent.
	 * @return The child created from the two individuals.
	 */
	public static Individual reproduce(Individual x, Individual y) {
		Individual child = new Individual(x.map);
		int n = x.colors.length;

		int point = (int)(Math.random() * n);
		for (int i = 0; i < point; ++i){
			child.colors[i] = x.colors[i];
		}
		for (int i = point; i < n; ++i){
			child.colors[i] = y.colors[i];
		}

		child.updateFitness();
		return child;
	}

	/**
	 * Returns the current fitness value of the individual.
	 * @return The current fitness value.
	 */
	public double getFitness() {
		return fitness;
	}

	/**
	 * Randomly mutates the individual.
	 */
	public void mutate() {
		int i = (int)(Math.random() * colors.length);
		colors[i] = (int)(Math.random() * 4);
		updateFitness();
	}

	/**
	 * Checks whether the individual represents a valid goal state.
	 * @return Whether the individual represents a valid goal state.
	 */
	public boolean isGoal() {
		return fitness == map.borders.size();
	}

	/**
	 * Prints out the individual to the console.
	 */
	void print() {
		System.out.println("Fitness: " + fitness);
		
		for (int i = 0; i < map.states.size(); i++) {
			String stateName = map.states.get(i);
			int stateColor = colors[i];
			System.out.println(stateName + ": " + stateColor);
		}
	}	
}
