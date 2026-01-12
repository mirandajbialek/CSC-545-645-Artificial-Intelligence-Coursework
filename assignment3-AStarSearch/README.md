# Assignment 3: Informed Search - A* and Uniform Cost Search

## Problem Statement

Implement informed search algorithms to find optimal paths in a 2D environment with polygonal obstacles. The agent must navigate from a start position to a goal position while avoiding obstacles.

**Environment:**
- 2D continuous space
- Multiple convex polygon obstacles
- Start and goal positions
- Need to find shortest path

## Approach

Implements two informed search algorithms:

### 1. A* Search
- **Open list management**: Uses priority queue ordered by f(n) = g(n) + h(n)
  - g(n): Actual cost from start to node n
  - h(n): Heuristic estimate from node n to goal
- **Heuristic**: Straight-line distance (admissible and consistent)
- **Visibility graph**: Connect nodes only if line of sight doesn't cross obstacles

### 2. Uniform Cost Search (Dijkstra's)
- Uses actual costs only: f(n) = g(n)
- No heuristic estimation
- Guaranteed to find optimal path
- More expansions than A*

## Key Algorithms & Concepts

- **Informed Search**: Uses domain knowledge via heuristic function
- **Heuristic Design**: Straight-line distance (Euclidean)
- **Admissibility**: h(n) ≤ actual cost to goal
- **Obstacle Avoidance**: Line-of-sight checking with polygon edges
- **Path Optimization**: Finding shortest valid path through environment

## Core Components

```
AStarSearch/UniformCostSearch
    ↓
- search() → returns path from start to goal
- generateFringe() → finds valid next positions
- checkIntersection() → validates no obstacle crossing
- estCostToGoal() → heuristic estimate (A* only)
```

## How to Run

**Compile:**
```bash
cd assignment3-AStarSearch/src
javac *.java
```

**Run:**
```bash
java Main
```

**Generate New Environment:**
```bash
make
./make_env
```

Then refresh `index.html` in your browser to visualize the new environment and search paths.

## Visualization

The program generates HTML output showing:
- 2D environment with polygon obstacles
- Start and goal positions
- Computed search paths overlaid on the environment
- **File**: `index.html` - Open in browser to view results

A pre-generated environment is included; refresh the page after regenerating a new one to see updated visualizations.

## Expected Output

The program will:
1. Load environment with obstacles and start/goal positions
2. Execute A* search or Uniform Cost Search
3. Display solution path coordinates
4. Generate HTML visualization with search paths
5. Print actual path cost

Example:
```
Solution found! Solution path is:
(10.5, 20.3)
(25.1, 35.7)
(50.0, 50.0) [GOAL]
```

## Key Files

- `AStarSearch.java` - A* algorithm implementation
- `UniformCostSearch.java` - Dijkstra's algorithm
- `Search.java` - Interface for search algorithms
- `Environment.java` - World representation with obstacles
- `Polygon.java` - Obstacle representation
- `Vector2D.java` - 2D point representation
- `Node.java` - Search tree node structure
- `index.html` - Visualization of results

## Algorithm Comparison

| Aspect | A* | Uniform Cost |
|--------|-----|--------|
| **Heuristic** | Yes (h(n)) | No |
| **Optimality** | Yes | Yes |
| **Completeness** | Yes | Yes |
| **Nodes Expanded** | Fewer | More |
| **Best For** | Known geometry | General cases |

## Compiler Information

- OpenJDK version 17.0.8.1
- OpenJDK Runtime Environment Temurin-17.0.8.1+1

## Acknowledgments

Framework and inspiration provided by Michael Davis.

## Learning Outcomes

✓ Implementation of informed search algorithms  
✓ Heuristic function design and evaluation  
✓ Geometric path planning  
✓ Obstacle collision detection  
✓ Algorithm efficiency comparison  
✓ Priority queue-based search  
✓ Visualization of search results
