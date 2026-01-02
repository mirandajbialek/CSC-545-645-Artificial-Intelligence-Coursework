# Assignment 4: Genetic Algorithm - Map Coloring

## Problem Statement

Solve the map coloring problem using a genetic algorithm. Assign colors to US states on a map such that no two adjacent states have the same color, using the minimum number of colors possible.

**Problem Details:**
- 10 US states with known adjacencies
- Need to find valid coloring (typically 4 colors for any map)
- State connectivity defined by shared borders

## Approach

Implements a **Genetic Algorithm**, a population-based metaheuristic inspired by natural evolution:

**Algorithm Steps:**
1. **Initialization**: Create random population of candidate colorings
2. **Evaluation**: Calculate fitness (number of conflicts)
3. **Selection**: Probabilistically select parents based on fitness
4. **Reproduction**: Combine parent solutions via crossover
5. **Mutation**: Randomly change genes to explore new regions
6. **Iteration**: Repeat until goal found or max iterations reached

## Key Algorithms & Concepts

- **Genetic Algorithm**: Population-based optimization
- **Fitness Function**: Number of state coloring conflicts (violations)
- **Selection**: Fitness-proportionate selection
- **Crossover**: Combine parent solutions
- **Mutation**: Random color changes (1% probability)
- **Termination**: Goal state detection (0 conflicts)

## Genetic Operators

```
Parent 1: [NC=Red, SC=Blue, VA=Red, ...]
Parent 2: [NC=Blue, SC=Red, VA=Blue, ...]
          ↓ Crossover
Child:    [NC=Red, SC=Red, VA=Blue, ...]
          ↓ Mutation (1% chance per gene)
Mutant:   [NC=Green, SC=Red, VA=Blue, ...]
```

## How to Run

**Compile:**
```bash
cd assignment4/src
javac *.java
```

**Run:**
```bash
java Main
```

Or use the provided script:
```bash
./run.sh
```

## Expected Output

```
Beginning map coloring
Found a solution after 245 iterations

North Carolina -> Color 0
South Carolina -> Color 1
Virginia -> Color 2
Tennessee -> Color 0
Kentucky -> Color 1
West Virginia -> Color 3
Georgia -> Color 0
Alabama -> Color 2
Mississippi -> Color 1
Florida -> Color 0
```

## Key Files

- `Main.java` - Main loop, genetic algorithm orchestration
- `Population.java` - Population management and selection
- `Individual.java` - Single solution candidate with fitness evaluation
- `StateMap.java` - Map representation and constraint checking
- `Border.java` - State adjacency relationships

## Configuration Parameters

Located in `Main.java`:
```java
final int populationSize = 300;    // Number of individuals per generation
final int maxIterations = 10000;   // Maximum generations to run
double mutationRate = 0.01;        // Probability of mutation per gene
```

## Performance Characteristics

- **Population Size**: 300 (balance exploration vs. computation)
- **Convergence**: Typically 100-500 generations
- **Mutation Rate**: 1% (low rate for exploitation)
- **Fitness Metric**: Number of adjacent state conflicts

## Learning Outcomes

✓ Genetic algorithm design and implementation
✓ Population-based optimization techniques
✓ Fitness function design for constraint problems
✓ Genetic operator implementation (crossover, mutation)
✓ Parameter tuning for metaheuristics
✓ Graph coloring problem solving
