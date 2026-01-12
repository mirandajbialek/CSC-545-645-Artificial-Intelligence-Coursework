# Assignment 5: Constraint Satisfaction Problems (CSP)

## Problem Statement

Solve constraint satisfaction problems using multiple algorithms. The primary application is map coloring, but the framework supports any CSP.

**Problem Components:**
- **Variables**: US states to color
- **Domains**: Colors available for each state
- **Constraints**: Adjacent states must have different colors
- **Goal**: Find valid assignment satisfying all constraints

## Approach

Implements four CSP solving algorithms with varying sophistication:

### 1. **Backtracking Search**
- Core CSP algorithm with recursive search
- Variable and value ordering heuristics
- Inference capability for future pruning
- Backtracks when inconsistency detected

### 2. **Forward Checking**
- Enhanced backtracking with inference
- After assigning a variable, check remaining domains
- Detects dead-ends early
- Removes inconsistent values from future variables

### 3. **AC-3 (Arc Consistency)**
- Advanced constraint propagation
- Removes values from domains inconsistent with neighbors
- Can be applied before search or during search
- Significantly prunes search space

### 4. **Min-Conflicts**
- Local search approach (different from backtracking)
- Starts with complete (but possibly conflicting) assignment
- Iteratively reassigns variables to reduce conflicts
- Finds solutions quickly in many problems
- Note: Does not always find a solution; performance varies with problem size

## Key Algorithms & Concepts

- **CSP Framework**: Variables, domains, constraints, assignments
- **Backtracking**: Core search with chronological backtracking
- **Inference**: Arc consistency and constraint propagation
- **Heuristics**: 
  - Minimum Remaining Values (MRV)
  - Least Constraining Value (LCV)
- **Local Search**: Min-Conflicts hill-climbing approach

## Algorithm Comparison

| Algorithm | Approach | Speed | Memory | Best For |
|-----------|----------|-------|--------|----------|
| **Backtracking** | Systematic | Slow | Low | Small problems |
| **Forward Checking** | Systematic + Inference | Medium | Medium | Medium problems |
| **AC-3** | Systematic + Strong Propagation | Fast | Medium | Hard problems |
| **Min-Conflicts** | Local search | Very Fast | Very Low | Large problems |

## How to Run

**Quick Start with Script:**
```bash
cd assignment5-ConstraintSatisfaction
./run.sh
```

**Manual Compilation & Run:**
```bash
cd assignment5-ConstraintSatisfaction/src
javac *.java
java Main
```

## Problem Configurations

**By Default:**
The program solves for the entire 50-state US map, which is a more challenging CSP.

**Alternative (10-State Map):**
A 10-state Southeastern US map is available and can be uncommented in the code. This smaller problem is useful for:
- Faster testing
- Higher likelihood of Min-Conflicts finding solutions within max iterations
- Quicker grading/demonstration purposes

## Performance Notes

- **Iteration Count**: The `computeAverageTime()` function accepts a parameter for number of iterations. Currently set to 5 for nearly instantaneous execution, making grading convenient.
- **Full Benchmark**: Set iterations to 100 for comprehensive timing analysis (runs within ~1 minute)
- **Min-Conflicts Variability**: Success rate depends on problem size; the 10-state version is much more likely to find solutions within max iterations

## Expected Output

```
Solving CSP using Backtracking...
Solution found!
North Carolina: 0
South Carolina: 1
Virginia: 2
Tennessee: 1
Kentucky: 0
West Virginia: 3
Georgia: 2
Alabama: 0
Mississippi: 1
Florida: 0

Iterations: 245
```

## Key Files

- `CSPSolver.java` - Interface for all solvers
- `BacktrackingCSPSolver.java` - Basic backtracking implementation
- `ForwardCheckingCSPSolver.java` - Backtracking with forward checking
- `AC3.java` - Arc consistency enforcement
- `MinConflicts.java` - Local search solver
- `CSP.java` - Problem representation
- `Assignment.java` - Variable assignments
- `Constraint.java` - Constraint definitions
- `Inference.java` - Domain reduction tracking

## CSP Framework Architecture

```
CSP (Variables, Domains, Constraints)
    ↓
CSPSolver (abstract)
    ├── BacktrackingCSPSolver
    ├── ForwardCheckingCSPSolver
    ├── AC3Solver
    └── MinConflictsSolver
    ↓
CSPResult (Solution + Statistics)
```

## Key Methods

**CSPSolver Interface:**
```java
CSPResult<E> solve(CSP<E> csp)
```

**Customization Points:**
- `selectUnassignedVariable()` - Variable ordering heuristic
- `orderDomainValues()` - Value ordering heuristic
- `inference()` - Constraint propagation strategy

## Performance Metrics

- **Iteration Count**: Number of consistency checks
- **Solution Quality**: All constraints satisfied
- **Efficiency**: AC-3 typically 10-100x faster than basic backtracking

## Learning Outcomes

✓ Constraint satisfaction problem formulation
✓ Multiple CSP solving algorithms
✓ Constraint propagation and arc consistency
✓ Heuristic search strategies (MRV, LCV)
✓ Local search techniques
✓ Algorithm selection for different problem types
✓ Inference and domain reduction techniques
