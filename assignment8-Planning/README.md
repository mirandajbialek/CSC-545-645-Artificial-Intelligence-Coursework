# Assignment 8 – Probabilistic Reasoning & Bayesian Networks

**Course:** CSC545/645 – Artificial Intelligence  
**Semester:** Fall 2025  
**Topics:** Bayesian Reasoning, Naive Bayes, Bayesian Networks, Conditional Independence

---

## Overview

This assignment focuses on **probabilistic reasoning under uncertainty**, a core pillar of modern artificial intelligence. The problems emphasize applying **Bayes' Rule**, reasoning with **priors and evidence**, and modeling real-world systems using **Bayesian networks**.

The work combines conceptual explanations with quantitative reasoning and probabilistic modeling.

---

## Exercise 8.1 – Bayesian Inference & Uncertainty

This section explores reasoning under uncertainty using classic examples from AI literature.

### Key problems include:
- **Witness reliability and base rates** (taxi color problem)
- Distinguishing between *observed evidence* and *latent variables*
- Applying **Bayes' Rule** to compute posterior probabilities
- Understanding why base rates matter even with reliable evidence

**Bayes' Rule:**
```
P(Hypothesis | Evidence) = P(Evidence | Hypothesis) × P(Hypothesis) / P(Evidence)
```

This exercise highlights common human reasoning errors and demonstrates how Bayesian inference corrects them. It emphasizes that even with reliable evidence, prior probabilities (base rates) dramatically influence posterior beliefs.

---

## Exercise 8.2 – Naive Bayes for Text Classification

This section examines **Naive Bayes models** for document classification, a practical application of probabilistic reasoning.

### Topics addressed:
- Constructing a probabilistic model from labeled training data
- Estimating **prior probabilities** P(Class) and **conditional likelihoods** P(Feature | Class)
- Classifying unseen documents using Bayes' theorem
- Critically evaluating the **independence assumption** and its limitations

**Naive Bayes Model:**
```
P(Class | Features) ∝ P(Class) × ∏ P(Feature_i | Class)
```

Despite its simplifying assumption that features are conditionally independent given the class, Naive Bayes demonstrates why this model performs surprisingly well in practice for text classification, spam detection, and sentiment analysis.

---

## Exercise 8.3 – Bayesian Networks for Diagnosis

This section focuses on **Bayesian networks** as a structured representation of joint probability distributions, enabling efficient reasoning in complex domains.

### Tasks include:
- Extending an existing diagnostic network with new Boolean variables
- Defining reasonable **conditional probability tables (CPTs)** for each node
- Comparing full joint distributions vs. factored network representations
- Calculating the number of independent probability parameters
- Explaining **noisy-AND** and **noisy-OR** gates and their applications

**Key Benefits of Bayesian Networks:**
- Encode conditional independence relationships explicitly
- Reduce exponential parameter space through factorization
- Enable efficient inference algorithms (variable elimination, belief propagation)
- Provide interpretable probabilistic models

This exercise demonstrates how conditional independence dramatically reduces model complexity while preserving expressiveness and interpretability.

---

## Core Concepts

### Bayes' Rule and Inference
- Computing posterior probabilities from prior beliefs and observed evidence
- The role of likelihood ratios in updating beliefs
- Why base rates cannot be ignored

### Conditional Independence
- Representing dependencies in Bayesian networks
- D-separation for determining conditional independence
- Markov blanket properties

### Probabilistic Inference
- Exact inference in small networks
- Approximate inference for complex networks
- Decision making under uncertainty

### Parameter Estimation
- Maximum likelihood estimation from data
- Laplace smoothing for handling zero probabilities
- Bayesian approaches to parameter learning

---

## Repository Structure

```text
assignment8/
├── README.md                              # This file
├── assignment8.pdf                        # Written solutions and explanations
├── bayes_calculations.md                  # (Optional) Step-by-step derivations
├── bayesian_network_diagrams.txt          # (Optional) Network visualizations
└── examples/                              # Example problems and solutions
    ├── taxi_problem.txt
    ├── text_classification_example.txt
    └── diagnostic_network.txt
```

---

## Key Learning Outcomes

✓ Understand and apply **Bayes' Rule** for probabilistic inference  
✓ Reason correctly about **base rates** and avoid common biases  
✓ Build and evaluate **Naive Bayes classifiers**  
✓ Design and analyze **Bayesian networks**  
✓ Calculate **conditional probabilities** and **parameter counts**  
✓ Apply **noisy-AND/OR** models for realistic reasoning  
✓ Understand conditional independence and its computational benefits  
✓ Make decisions under uncertainty  

---

## Common Applications

- **Medical Diagnosis**: Patient symptoms → Disease probability
- **Spam Detection**: Email features → Spam vs. Ham classification
- **Risk Assessment**: Observable factors → Risk level prediction
- **Fault Diagnosis**: System observations → Component failure probability
- **Natural Language Processing**: Word sequences → Likely meaning

---

## Important Notes

1. **Conditional Independence**: Bayesian networks are powerful because they exploit conditional independence to reduce complexity. Verify d-separation carefully.

2. **Parameter Estimation**: When learning from data, use appropriate smoothing techniques to handle unseen events.

3. **Inference Complexity**: Exact inference is NP-hard in general Bayesian networks. Know when to use approximation algorithms.

4. **The Independence Assumption**: While Naive Bayes assumes feature independence given the class, it often works well despite this unrealistic assumption due to the robustness of the classification task.

5. **Prior vs. Evidence**: Always consider how prior probabilities influence posterior beliefs—this is a common source of human reasoning errors.
