# Assignment 1: Simple Reflex Vacuum Agent

## Problem Statement

Implement an intelligent agent that operates in a vacuum world environment. The agent must perceive its current location and the state of that location (clean or dirty), then decide what action to take based on simple if-then rules.

**Environment**: A two-location vacuum world where:
- The agent can be at Location A or Location B
- Each location can be clean or dirty
- The agent must clean dirty locations and navigate between locations

## Approach

This assignment implements the **Simple Reflex Agent** architecture, which uses a rule-based decision-making system:

1. **Perception**: Agent receives percepts containing:
   - Current location (A or B)
   - Current location state (clean or dirty)

2. **Decision Making**: Agent applies a set of condition-action rules:
   - If current location is dirty → Suck
   - If at location A → Move right (to B)
   - If at location B → Move left (to A)

3. **Action Execution**: Agent performs the selected action and receives new percepts

## Key Algorithms & Concepts

- **Simple Reflex Agent Model**: Matches percepts to pre-defined rules
- **Rule-Based Systems**: Uses if-then condition-action pairs
- **Agent-Environment Interaction**: Demonstrates agent program architecture from AIMA
- **State Management**: Custom State class for attribute management

## Architecture

```
VacuumEnvironment (simulates the world)
    ↓
SimpleReflexVacuumAgent (makes decisions)
    ↓
SimpleReflexAgentProgram (implements decision logic)
    ↓
Rule Set (condition-action mappings)
```

## How to Run

**Compile:**
```bash
cd assignment1
javac *.java
```

**Run:**
```bash
java Main
```

Or use the provided Makefile:
```bash
make run
```

## Expected Output

The agent will:
1. Start at a location
2. Perceive whether it's clean or dirty
3. Execute actions based on rules
4. Move between locations and clean as needed
5. Display each action taken and resulting state

## Key Files

- `SimpleReflexVacuumAgent.java` - The agent implementation
- `VacuumEnvironment.java` - Environment simulator
- `SimpleReflexAgentProgram.java` - Decision-making logic
- `Rule.java` - Condition-action rule structure
- `Main.java` - Entry point and simulation

## Learning Outcomes

✓ Understanding of agent architectures
✓ Implementation of rule-based decision systems
✓ Agent-environment interaction patterns
✓ Percept interpretation and action execution

