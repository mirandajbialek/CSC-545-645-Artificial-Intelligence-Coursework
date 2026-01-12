# Assignment 10 – Dynamic Bayesian Networks & Temporal Inference

**Course:** CSC545/645 – Artificial Intelligence  
**Semester:** Fall 2025  
**Topics:** Dynamic Bayesian Networks (DBNs), Hidden Markov Models, Filtering, Smoothing

---

## Overview

This assignment focuses on **probabilistic reasoning over time** using **Dynamic Bayesian Networks (DBNs)**. The problems explore how hidden state variables evolve across time steps and how observations can be used to infer latent causes.

The assignment combines **model design**, **exact inference**, and **temporal reasoning**.

---

## Exercise 10.1 – Dynamic Bayesian Network Design

This section models student sleep behavior using a DBN.

### Variables:
- **EnoughSleep (E)** – hidden state variable
- **RedEyes (R)** – observable variable
- **SleepingInClass (S)** – observable variable

### Key tasks include:
- Designing a DBN that captures temporal dependence of sleep quality
- Defining transition probabilities between hidden states
- Specifying emission probabilities for observable symptoms
- Recasting the DBN as a **Hidden Markov Model** by combining observation variables

**DBN Structure Example:**
```
Time t-1          Time t          Time t+1
  E(t-1) ──→      E(t) ──→       E(t+1)
    ↓              ↓               ↓
  R(t-1)         R(t)            R(t+1)
  S(t-1)         S(t)            S(t+1)
```

This exercise demonstrates how DBNs generalize Bayesian networks to temporal domains, where state transitions follow Markov assumptions.

---

## Exercise 10.2 – Filtering & Smoothing

This section applies **exact inference algorithms** to temporal data.

### Topics covered:
- **Forward filtering**: Computing P(State_t | Evidence_1:t)
- **Backward smoothing**: Computing P(State_t | Evidence_1:T)
- **Viterbi algorithm**: Finding most likely hidden state sequence
- **Forward-backward algorithm**: Full smoothed posterior inference

**Filtering Process:**
```
Time 0: Initialize belief state P(E_0)
Time 1: Observe O_1 → Update P(E_1 | O_1)
Time 2: Observe O_2 → Predict P(E_2), then Update P(E_2 | O_2)
...
Time T: Observe O_T → Final filtered estimate P(E_T | O_1:T)
```

**Smoothing vs. Filtering:**
- **Filtering**: Use only past observations (online, real-time)
- **Smoothing**: Use all observations including future ones (offline, after-the-fact)
- **Prediction**: Use only past observations to predict future states

---

## Exercise 10.3 – HMM Applications & Inference

This section applies Hidden Markov Models to practical problems.

### Tasks include:
- Designing HMMs for specific domains
- Implementing forward algorithm for likelihood computation
- Computing filtered posterior beliefs over time
- Analyzing inference complexity and accuracy

**HMM Definition:**
```
States: X_t (hidden)
Observations: E_t (observable)
Transition Model: P(X_t | X_{t-1})
Sensor/Emission Model: P(E_t | X_t)
```

**Key Algorithms:**
1. **Forward Algorithm**: Compute P(E_1:t, X_t)
2. **Backward Algorithm**: Compute P(E_{t+1:T} | X_t)
3. **Viterbi**: Find argmax P(X_1:T | E_1:T)
4. **Forward-Backward**: Compute all smoothed beliefs P(X_t | E_1:T)

---

## Core Concepts

### Dynamic Bayesian Networks
- Extension of Bayesian networks to temporal domains
- Two-time-slice representation: transitions from t to t+1
- Conditional independence given previous time step (Markov assumption)
- Can represent complex temporal relationships

### Hidden Markov Models
- Special case of DBN with single hidden state
- Markov chain for state evolution
- Probabilistic observations (emissions) from states
- Memoryless: P(X_t | X_{t-1}, ..., X_0) = P(X_t | X_{t-1})

### Inference Tasks
- **Filtering**: Belief over current state given past observations
- **Smoothing**: Belief over past states given all observations
- **Prediction**: Belief about future states given past observations
- **Most likely explanation**: Sequence of states explaining observations

### Computational Complexity
- **Forward filtering**: O(S² × T) where S = number of states, T = time steps
- **Smoothing**: O(S² × T) with forward-backward algorithm
- **Viterbi**: O(S² × T) with dynamic programming
- Constant time update per timestep (efficient online inference)

---

## Repository Structure

```text
assignment10/
├── README.md
├── Assignment10-3.pdf                 # Written solutions and derivations
├── dbn_models/                        # DBN specifications
│   ├── sleep_dbn.txt
│   ├── weather_hmm.txt
│   └── robot_localization.txt
├── inference_algorithms/              # Algorithm implementations
│   ├── forward_algorithm.py
│   ├── backward_algorithm.py
│   ├── viterbi.py
│   └── forward_backward.py
└── examples/                          # Worked examples and applications
    ├── filtering_example.txt
    ├── smoothing_example.txt
    └── viterbi_example.txt
```

---

## Key Learning Outcomes

✓ Understand temporal structure of probabilistic models  
✓ Design Dynamic Bayesian Networks for temporal domains  
✓ Implement filtering for online belief estimation  
✓ Apply smoothing for offline inference  
✓ Understand Hidden Markov Model structure and inference  
✓ Compute most likely explanation sequences (Viterbi)  
✓ Analyze computational complexity of temporal inference  
✓ Apply DBNs/HMMs to real-world problems  

---

## Common Applications

- **Robot Localization**: Inferring robot position from sensor observations over time
- **Speech Recognition**: Hidden phonemes from observed acoustic signals
- **Weather Forecasting**: Predicting weather states from observations
- **Disease Diagnosis**: Inferring disease progression from symptom observations
- **Stock Market**: Estimating market regime from price observations
- **Motion Tracking**: Tracking object position from noisy measurements

---

## Important Distinctions

### Forward vs. Backward Inference
- **Forward**: P(X_t | E_1:t) - efficient online computation
- **Backward**: P(E_{t+1:T} | X_t) - computed offline in reverse
- **Smoothing**: Combines both for optimal estimate P(X_t | E_1:T)

### Filtering vs. Viterbi
- **Filtering**: Marginal probability P(X_t | E_1:t) for each time step independently
- **Viterbi**: Most likely joint sequence argmax P(X_1:T | E_1:T) considering dependencies
- Different answers: Viterbi doesn't maximize marginal probabilities

### Markov Assumption
- **First-order Markov**: X_t depends only on X_{t-1}
- **k-order Markov**: X_t depends on X_{t-1}, ..., X_{t-k}
- Higher-order models more complex but may be necessary for accurate modeling

---

## Mathematical Framework

### Forward Algorithm Recursion:
```
α_t(x_t) = P(E_t | x_t) × Σ_{x_{t-1}} P(x_t | x_{t-1}) × α_{t-1}(x_{t-1})
```

### Backward Algorithm Recursion:
```
β_t(x_t) = Σ_{x_{t+1}} P(E_{t+1} | x_{t+1}) × P(x_{t+1} | x_t) × β_{t+1}(x_{t+1})
```

### Smoothed Posterior:
```
P(X_t | E_1:T) ∝ α_t(X_t) × β_t(X_t)
```

### Viterbi (most likely path):
```
m_t(x_t) = max_{x_{t-1}} [P(x_t | x_{t-1}) × m_{t-1}(x_{t-1})] × P(E_t | x_t)
```

---

## Notes on Problem-Solving

1. **Model Specification**: Carefully define states, observations, transitions, and emissions
2. **Probability Normalization**: Ensure all CPTs sum to 1 (or handle unnormalized versions)
3. **Numerical Stability**: Use log-probabilities to avoid underflow in long sequences
4. **Boundary Conditions**: Handle t=0 initialization and t=T terminal conditions separately
5. **Complexity Analysis**: Forward-backward is O(S²T), practical for moderate S and T

---

## Extensions & Advanced Topics

- **Particle Filtering**: Approximate inference for non-linear/non-Gaussian systems
- **Kalman Filtering**: Optimal filtering for linear-Gaussian systems
- **Learning**: EM algorithm for parameter learning (Baum-Welch)
- **Decoding**: Viterbi and posterior decoding strategies
- **Temporal Models**: Factor graphs, temporal plates in graphical models
- **Real-time Constraints**: Online algorithms with bounded computation
