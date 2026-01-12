package src;
import java.util.Vector;

/**
 * Class representing a population of individuals
 */
public class Population extends Vector<Individual> {
	private StateMap map;
		
	/**
	 * Actual standard ctor.
	 * @param map The map.
	 * @param initialSize The initial size of the population.
	 */
	Population(StateMap map, int initialSize) {
		for(int i = 0; i < initialSize; ++i)
		{
			add(new Individual(map));
		}
	}
	
	/**
	 * Standard ctor.
	 * @param map The map.
	 */
	public Population(StateMap map) {
		this(map, 0);
	}
	
	/**
	 * Randomly selects an individual out of the population
	 * proportionally to its fitness.
	 * @return The selected individual.
	 */
	Individual randomSelection() {
	
		double totalFitness = 0;
		for (Individual i : this){
			totalFitness += i.getFitness();
		}

		double randomValue = Math.random() * totalFitness;
		double cumulativeFitness = 0;

		for (Individual i : this){  //this way, individuals with higher fitness value are more likely to be picked
			cumulativeFitness += i.getFitness();
			if (cumulativeFitness >=  randomValue){
				return i;
			}
		}

		//if no fit individual is selected, then an individual will still be returned
		return lastElement();
	}
	
}
