# Assignment 7: Quantifying Uncertainty

## Problem Statement

Develop understanding of **probabilistic reasoning and uncertainty quantification** through practical simulations. This assignment focuses on using probability distributions, sampling methods, and statistical analysis to understand uncertain outcomes in real-world scenarios.

**Key Concepts:**
- Probability distributions and random events
- Monte Carlo simulation methods
- Statistical measures (mean, median, variance)
- Empirical vs. theoretical probability
- Uncertainty quantification in complex systems

## Assignment Overview

### Problem: Slot Machine Simulation

Implement and analyze a slot machine game to explore:
1. **Probability of outcomes** - Three reels with different symbols
2. **Expected value** - Long-term profitability of playing
3. **Empirical statistics** - Computing mean and median over many trials
4. **Stochastic processes** - Random sequences and outcomes

### Game Rules

**Symbols:** bar, bell, lemon, cherry (each with equal probability 1/4)

**Payout Structure:**
- **Cherry matches (1, 2, or 3):** Win number of matching cherries
- **Three identical bars:** Win $20
- **Three identical bells:** Win $15
- **Three identical lemons:** Win $5

**Gameplay:**
- Start with $10
- Each spin costs $1
- Continue until money runs out
- Track number of spins until bankruptcy

## Implementation

### `trial()` Function
Simulates a single game session:
- Initializes player with $10
- Loops through spins:
  - Randomly selects 3 symbols (each reel independent)
  - Determines matching pattern (1, 2, or 3 equals)
  - Calculates payout based on symbol type and matches
  - Updates money balance
  - Counts total spins
- Returns number of spins before running out of money

### `test(trials)` Function
Runs Monte Carlo simulation:
- Executes `trial()` function `trials` times
- Collects results (spin counts)
- Computes **mean** - average spins across trials
- Computes **median** - middle value of sorted results
- Prints statistics for analysis

## Statistical Analysis

### Running the Simulation

```python
test(1000)  # Run 1000 trials
```

**Expected Output:**
```
1000 trials, mean=XX.XX, median=YY
```

### Key Questions (From Short Answer)

The assignment explores:
1. **Theoretical vs. Empirical Probability** - How do simulated results compare to mathematical expectations?
2. **Law of Large Numbers** - Does mean stabilize with more trials?
3. **Expected Value** - Is the slot machine fair or biased?
4. **Distribution of Outcomes** - What does the histogram of results look like?
5. **Statistical Measures** - When should you use mean vs. median?

## How to Run

**Requirements:**
```bash
python 3.x (no additional packages needed for basic implementation)
# For enhanced visualization:
pip install jupyter numpy matplotlib
```

**Execute Jupyter Notebook:**
```bash
cd assignment7-Quantifying-Uncertainty
jupyter notebook AIHW7Test.ipynb
```

**Run Individual Cells:**
1. Cell 1: Define `trial()` function (slot machine simulation)
2. Cell 2: Define `test()` function (statistical analysis)
3. Cell 3: Execute `test(1000)` to run simulation
4. Additional cells: Extend analysis with visualizations or comparisons

## Key Concepts

### Monte Carlo Simulation
- Generate many random samples
- Use samples to estimate probabilities and expected values
- More trials → more accurate estimates

### Statistical Measures
- **Mean:** Average outcome (sensitive to outliers)
- **Median:** Middle value (robust to outliers)
- **Variance/Std Dev:** Measure of spread in outcomes

### Probability Distributions
- **Discrete uniform:** Each symbol equally likely (1/4)
- **Empirical distribution:** Actual results from simulation

### Uncertainty Quantification
- Represent outcomes as probability distributions
- Estimate confidence intervals from samples
- Use statistics to make inferences about unknown true values

## Short Answer Topics

This assignment covers written questions on:
1. Calculating theoretical probabilities
2. Interpreting simulation results
3. Understanding the relationship between sample size and accuracy
4. Comparing different statistical measures
5. Making predictions about long-run behavior
6. Identifying bias in games of chance

## Learning Outcomes

✓ Monte Carlo simulation techniques
✓ Statistical analysis (mean, median, distributions)
✓ Probability and expected value calculations
✓ Empirical reasoning about uncertain outcomes
✓ Understanding randomness and stochastic processes
✓ Quantifying and reasoning under uncertainty
✓ Interpreting simulation results

## Extensions & Analysis

Potential enhancements to explore:
- Plot histogram of spin counts
- Compare mean vs. median (why different?)
- Analyze payout fairness (expected value < $10?)
- Experiment with different rule variations
- Run 10,000 trials to see convergence
- Calculate confidence intervals

## Files Included

- `AIHW7Test.ipynb` - Jupyter notebook with implementation
- `assignment7-short-answer.pdf` - Theoretical questions and analysis prompts
- `README.md` - This file
