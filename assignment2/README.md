# Assignment 2: Graph Search - Goats & Wolves Problem

## Problem Statement

Solve the classic "Goats and Wolves" puzzle using graph search algorithms. The goal is to transport 3 goats and 3 wolves across a river using a boat with limited capacity, ensuring goats are never outnumbered by wolves on either bank (or they get eaten).

**Constraints:**
- Boat capacity: maximum 2 creatures per trip
- Safety constraint: goats ≥ wolves on each bank (or 0 goats/wolves)
- Goal state: All creatures on the right bank

## Approach

Implements **Iterative Deepening Search (IDS)**, which combines the memory efficiency of depth-first search with the optimality guarantees of breadth-first search.

**Algorithm Flow:**
1. Perform depth-limited search at depth 0
2. If no solution found, increment depth limit
3. Repeat until solution is found
4. This ensures shallowest solution is found without maintaining large open lists

## Key Algorithms & Concepts

- **Iterative Deepening Search (IDS)**: Uninformed search strategy
- **Depth-Limited Search**: Search with a maximum depth constraint
- **State Space Exploration**: Generate valid successor states
- **Goal Testing**: Check if current state matches goal state
- **Constraint Validation**: Ensure state transitions are legal

## State Representation

```
State = (goats_left, wolves_left, goats_right, wolves_right, boat_location)
```

Initial: (3, 3, 0, 0, left)
Goal: (0, 0, 3, 3, right)

## How to Run

**Compile:**
```bash
cd assignment2
javac *.java
```

**Run:**
```bash
java Main
```

Or use the Makefile:
```bash
make run
```

## Expected Output

The program will:
1. Display states explored at each depth level
2. Show all valid successor states
3. Print the solution path (sequence of moves)
4. Display the depth at which solution was found

Example output:
```
the goat - wolf problem:

Searching at a depth of 0
current state: (3,3,0,0)
...

Searching at a depth of 2
...

Solution found!!
```

## Key Files

- `Main.java` - IDS implementation and search logic
- `Node.java` - Tree node representing search states
- `State.java` - State representation and movement methods

## Complexity Analysis

- **Time Complexity**: O(b^d) where b is branching factor, d is depth
- **Space Complexity**: O(d) - linear in depth (no open/closed lists)
- **Optimality**: Finds shallowest solution (optimal for uniform-cost domains)

## Learning Outcomes

✓ Understanding of uninformed search strategies
✓ Implementation of iterative deepening search
✓ State space problem modeling
✓ Constraint satisfaction during search
✓ Search tree construction and exploration
