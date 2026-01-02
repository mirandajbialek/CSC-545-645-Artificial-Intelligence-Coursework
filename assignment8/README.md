# Assignment 8: Logic & Planning

## Problem Statement

Implement automated planning and logical reasoning systems to solve planning problems. The agent must reason about preconditions, effects, and action sequences to achieve goals.

**Key Concepts:**
- First-order logic representation of world states
- Action preconditions and postconditions
- Planning as state-space search
- Goal stack decomposition
- Constraint satisfaction in planning

## Approach

This assignment explores classical AI planning techniques:

### 1. **STRIPS Representation**
- State: Set of propositions (facts)
- Actions: Defined by preconditions and effects
- Goals: Conjunctions of desired propositions
- Plans: Sequences of actions achieving goals

### 2. **Forward State-Space Planning**
- Start from initial state
- Apply actions that have satisfied preconditions
- Progress toward goal state
- Search through state space

### 3. **Backward Goal-Stack Planning**
- Start from goal
- Decompose goals into subgoals
- Find actions that satisfy subgoals
- Build plan by working backward

### 4. **Constraint-Based Planning**
- Represent planning as CSP
- Variables: Action ordering and timing
- Constraints: Preconditions, effects, ordering
- Solve with CSP techniques

## Key Algorithms & Concepts

- **STRIPS Planning**: Action representation language
- **State-Space Search**: Planning as search problem
- **Goal Regression**: Working backward from goals
- **Plan Validation**: Checking plan correctness
- **Action Sequencing**: Ordering constraints on actions
- **Partial-Order Planning**: Actions with flexible ordering
- **Constraint Propagation**: Enforcing planning constraints

## Planning Example

```
Initial State: At(A), At(B), Clear(C)
Goal: At(B), At(C)

Action: Move(X, Y)
  Preconditions: At(X), Clear(Y)
  Effects: At(Y), ¬At(X)

Plan:
1. Move(A, C) - preconditions satisfied, effects applied
   New state: At(C), At(B), Clear(A)
2. Move(B, A) - preconditions satisfied
   New state: At(C), At(A), Clear(B)
   Goal achieved!
```

## How to Run

**Compile:**
```bash
cd assignment8
javac *.java
```

**Run:**
```bash
java Main
```

## Expected Output

```
Initial State:
- At(A)
- At(B)
- Clear(C)

Goal: At(B), At(C)

Finding Plan...

Plan Found:
1. Move(A, C)
   Preconditions: At(A), Clear(C) ✓
   Effects: At(C), ¬At(A)
   
2. Move(B, A)
   Preconditions: At(B), Clear(A) ✓
   Effects: At(A), ¬At(B)

Plan Validation: SUCCESS
```

## Key Files

- Implementation files depend on planning approach
- Likely includes:
  - `Action.java` - Action definitions
  - `State.java` - State representation
  - `Goal.java` - Goal definitions
  - `Planner.java` - Planning algorithm
  - `Main.java` - Entry point

## Planning Techniques Comparison

| Technique | Approach | Speed | Flexibility |
|-----------|----------|-------|------------|
| **Forward State-Space** | Start→Goal | Moderate | High |
| **Backward Goal-Stack** | Goal→Start | Moderate | Medium |
| **Partial-Order** | Constraints | Fast | High |
| **Constraint-Based** | CSP formulation | Slow | Very High |

## Complexity Analysis

- **State Space Size**: Exponential in number of propositions
- **Plan Length**: Often polynomial in problem size
- **Search Complexity**: NP-hard in general case
- **Heuristics**: Relaxed problems, delete-all-preconditions

## Learning Outcomes

✓ Automated planning problem formulation
✓ STRIPS action representation
✓ State-space and goal-stack planning
✓ Plan validation and verification
✓ Action preconditions and effects
✓ Constraint-based planning approaches
✓ Logic and reasoning in AI systems
