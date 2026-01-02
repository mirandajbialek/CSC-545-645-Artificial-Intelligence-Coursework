# Assignment 7: Reinforcement Learning

## Problem Statement

Implement reinforcement learning algorithms to train an agent to make optimal decisions in an environment without explicit programming of correct behaviors. The agent learns through interaction, receiving rewards and penalties.

**Key Characteristics:**
- Agent learns through experience
- No predefined solution or training data
- Reward signals guide learning
- Temporal dependencies between states

## Approach

This assignment explores multiple reinforcement learning algorithms:

### 1. **Q-Learning**
- Off-policy temporal difference learning
- Learns action-value function Q(s, a)
- Updates based on maximum next-state value
- Convergence guarantee with exploration

### 2. **SARSA (State-Action-Reward-State-Action)**
- On-policy temporal difference learning
- Updates based on actual next action taken
- More conservative than Q-learning
- Better for online learning scenarios

### 3. **Policy Gradient Methods**
- Learn policy directly instead of value function
- Gradient ascent on expected return
- Better for continuous action spaces
- Can handle stochastic policies

## Key Algorithms & Concepts

- **Temporal Difference Learning**: Combining Monte Carlo and Dynamic Programming
- **Exploration vs. Exploitation**: ε-greedy strategy balances learning and performance
- **Value Function**: Estimates expected return from each state
- **Bellman Equation**: Recursive formulation of value functions
- **Experience Replay**: Storing and replaying past experiences for stability
- **Neural Networks**: Function approximation for large state spaces

## Q-Learning Update Rule

```
Q(s, a) ← Q(s, a) + α[r + γ·max_a'(Q(s', a')) - Q(s, a)]
```

Where:
- α: Learning rate
- r: Immediate reward
- γ: Discount factor
- s': Next state
- a': Action in next state

## How to Run

**Requirements:**
```bash
pip install jupyter numpy matplotlib
```

**Execute Jupyter Notebook:**
```bash
cd assignment7
jupyter notebook AIHW7Test.ipynb
```

**Run Cells Sequentially:**
- Cell 1: Import libraries and define environment
- Cell 2: Initialize agent and hyperparameters
- Cell 3: Training loop (episodes of interaction)
- Cell 4: Visualization and analysis

## Expected Output

```
Episode 0: Total Reward = 15
Episode 1: Total Reward = 18
Episode 2: Total Reward = 20
...
Episode 999: Total Reward = 198

Learning Curves:
- Reward increasing over time
- Convergence to optimal policy
- Exploration-exploitation trade-off visible
```

## Key Files

- `AIHW7Test.ipynb` - Jupyter notebook with complete implementation
- Contains environment definition, agent, training loop, and visualization

## Hyperparameters

```python
learning_rate = 0.1          # α: How much to update Q-values
discount_factor = 0.99       # γ: Importance of future rewards
epsilon = 0.1                # ε: Exploration probability
episodes = 1000              # Number of training episodes
max_steps = 100              # Max steps per episode
```

## Jupyter Notebook Sections

1. **Environment Setup**
   - Define state space
   - Define action space
   - Define reward function

2. **Agent Implementation**
   - Initialize Q-table or neural network
   - Implement learning algorithms
   - Epsilon-greedy action selection

3. **Training**
   - Episode loop with state reset
   - Experience generation
   - Q-value updates
   - Performance tracking

4. **Evaluation & Visualization**
   - Learning curves (reward vs. episode)
   - Policy visualization
   - Performance metrics
   - Comparison with baselines

## Performance Metrics

- **Cumulative Reward**: Sum of rewards per episode
- **Success Rate**: Percentage of episodes reaching goal
- **Convergence Time**: Episodes needed to stabilize performance
- **Efficiency**: Steps needed to complete task

## Learning Outcomes

✓ Reinforcement learning fundamentals
✓ Q-learning and temporal difference methods
✓ Policy gradient approaches
✓ Exploration-exploitation dilemma
✓ Neural network function approximation
✓ Agent training and evaluation
✓ Interactive learning from environment feedback
