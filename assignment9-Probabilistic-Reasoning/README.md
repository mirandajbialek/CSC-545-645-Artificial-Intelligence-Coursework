# Assignment 9 – Bayesian Networks & Variable Elimination

**Course:** CSC545/645 – Artificial Intelligence  
**Semester:** Fall 2025  
**Topics:** Bayesian Networks, Probabilistic Inference, Variable Elimination

---

## Overview

This assignment focuses on **probabilistic inference in Bayesian networks**, with particular emphasis on **network structure**, **conditional probability tables**, and the **variable elimination algorithm**.

The work combines conceptual modeling, exact probabilistic inference, and computational complexity analysis.

---

## Exercise 9.1 – Bayesian Network Modeling

This section models a simplified **nuclear power station alarm system** using a Bayesian network.

### Key tasks include:
- Defining Boolean and multi-valued random variables
- Constructing a Bayesian network that captures causal relationships and sensor failures
- Determining whether the resulting network is a **polytree**
- Building **conditional probability tables (CPTs)** for sensor readings and alarm behavior

**Bayesian Network Structure:**
```
    Nuclear Event
         ↓
    ┌────┴────┐
    ↓         ↓
  Gauge    Alarm
    ↓
  Reading
```

This exercise highlights how causal assumptions and dependency structure affect inference.

---

## Exercise 9.2 – Variable Elimination Algorithm

This section analyzes and applies the **Variable Elimination Algorithm (VEA)** for exact inference.

### Topics covered:
- Step-by-step execution of variable elimination for a conditional probability query
- Verifying correctness against textbook examples
- Counting arithmetic operations and comparing performance with **enumeration**
- Demonstrating why variable elimination avoids redundant computation

**Variable Elimination Example:**
```
Query: P(Event | Reading, Alarm)

Enumeration approach: Sum over all hidden variables
∑_X ∑_Y P(Event, X, Y, Reading, Alarm)

Variable Elimination: Factorize and eliminate systematically
- Join factors involving next variable to eliminate
- Sum out that variable
- Repeat until query variables remain
```

---

## Exercise 9.3 – Complexity Analysis

This section focuses on the **computational complexity** of probabilistic inference.

### Key results include:
- Comparing enumeration vs. variable elimination on chain-structured networks
- Showing exponential complexity for enumeration
- Proving linear-time complexity for variable elimination on **polytrees**, given a valid variable ordering

**Complexity Comparison:**

| Algorithm | Chain Network | General Network |
|-----------|---------------|-----------------|
| **Enumeration** | O(2^n) | O(2^n) |
| **Variable Elimination** | O(n) | O(n · w^k) |

Where n = number of variables, w = domain size, k = treewidth

This demonstrates how exploiting conditional independence yields dramatic efficiency gains.

---

## Core Concepts

### Bayesian Networks
- Directed acyclic graph (DAG) of random variables
- Edges represent direct causal influence
- Local Markov assumption: variables independent of non-descendants given parents
- Compact representation of joint probability distribution

### Conditional Probability Tables (CPTs)
- Quantify conditional dependencies in network
- P(Child | Parents) specified for each variable
- Joint probability factorizes: P(X₁,...,Xₙ) = ∏ P(Xᵢ | Parents(Xᵢ))

### Variable Elimination
**Algorithm:**
1. Choose elimination order for non-query variables
2. For each variable to eliminate:
   - Collect all factors containing that variable
   - Multiply factors together
   - Sum out the variable
   - Add result to remaining factors
3. Multiply remaining factors to get answer

**Time Complexity:** O(n · d^(w+1))
- n = number of variables
- d = domain size per variable
- w = treewidth of network

### Polytrees
- Bayesian networks with at most one undirected path between any two nodes
- Efficient inference possible (linear in network size)
- Can be solved with belief propagation algorithm

---

## Repository Structure

```text
assignment9/
├── README.md
├── AIHW9.pdf                    # Written solutions and derivations
├── bayesian_network_diagrams/   # Network visualizations
│   ├── alarm_network.png
│   └── elimination_example.png
└── examples/                     # Example problems and solutions
    ├── alarm_system.txt
    ├── variable_elimination.txt
    └── complexity_analysis.txt
```

---

## Key Learning Outcomes

✓ Understand Bayesian network structure and semantics  
✓ Build conditional probability tables for domain problems  
✓ Apply variable elimination for exact probabilistic inference  
✓ Analyze computational complexity of inference algorithms  
✓ Exploit conditional independence for efficiency  
✓ Compare exact vs. approximate inference methods  
✓ Design efficient variable elimination orderings  
✓ Model real-world systems using Bayesian networks  

---

## Important Concepts

### D-Separation
- Criterion for determining conditional independence in DAGs
- X ⊥ Y | Z if Z d-separates X and Y
- Rules:
  1. Blocked by evidence on intermediate node (non-collider)
  2. Blocked by lack of evidence on collider
  3. Evidence on descendant of collider affects blocking

### Markov Blanket
- Minimal set of variables shielding a variable from others
- For Xi: Markov_Blanket(Xi) = Parents(Xi) ∪ Children(Xi) ∪ CoParents(Children(Xi))
- Xi ⊥ all other variables | Markov_Blanket(Xi)

### Inference Complexity
- **NP-hard** in general (computing exact probabilities)
- **Polynomial** for polytrees with optimal variable ordering
- Trade-off between network connectivity and inference tractability

---

## Variable Elimination Example

Given network: A → C ← B, C → D

**Query:** P(D | C)

**Method:**
```
P(D | C) = P(C, D) / P(C)

P(C, D) = ∑_A ∑_B P(A) P(B) P(C | A, B) P(D | C)
        = P(D | C) ∑_A ∑_B P(A) P(B) P(C | A, B)
        = P(D | C) P(C)

Therefore: P(D | C) as specified (no inference needed)
```

---

## Common Applications

- **Medical Diagnosis**: Symptoms → Disease → Test Results
- **Spam Detection**: Features → Spam Classification → User Feedback
- **Weather Prediction**: Pressure → Temperature → Cloud Cover → Precipitation
- **Fault Diagnosis**: Observable failures → Component health → System status
- **Recommendation Systems**: User preferences → Item features → Predicted ratings

---

## Notes on Problem-Solving

1. **Network Design**: Start with clear variable definitions and causal relationships
2. **CPT Specification**: Ensure values sum to 1; use domain knowledge
3. **Elimination Order**: Non-optimal orders can increase complexity exponentially
4. **D-Separation**: Master this for understanding independence
5. **Verification**: Always check results against small examples or enumeration

---

## Extensions & Advanced Topics

- **Approximate Inference**: Belief propagation, particle filtering
- **Learning**: Parameter learning from data, structure learning
- **Temporal Models**: Hidden Markov Models (HMMs), Dynamic Bayesian Networks (DBNs)
- **Causal Inference**: Do-calculus, causal graphs beyond observational data
