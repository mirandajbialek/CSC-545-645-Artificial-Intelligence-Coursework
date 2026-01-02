# Assignment 9: Neural Networks & Deep Learning

## Problem Statement

Implement and train neural networks to solve classification or regression problems. This assignment covers fundamental deep learning concepts including network architecture, forward propagation, backpropagation, and optimization.

**Key Concepts:**
- Multi-layer perceptrons (MLPs)
- Activation functions (ReLU, sigmoid, tanh)
- Forward and backward propagation
- Gradient descent optimization
- Loss functions and metrics

## Approach

This assignment explores neural network fundamentals:

### 1. **Network Architecture**
- Input layer: Features from data
- Hidden layers: Feature transformation
- Output layer: Predictions
- Customizable layer sizes and activation functions

### 2. **Forward Propagation**
- Linear transformation: z = Wx + b
- Activation: a = σ(z)
- Progressive computation through layers
- Vectorized operations for efficiency

### 3. **Backpropagation**
- Compute loss gradient with respect to weights
- Chain rule for multi-layer networks
- Update rule: w ← w - α·∇L
- Efficient computation using dynamic programming

### 4. **Training Optimization**
- Stochastic Gradient Descent (SGD)
- Mini-batch training
- Learning rate scheduling
- Convergence criteria

## Key Algorithms & Concepts

- **Perceptron**: Single neuron with activation function
- **Multi-Layer Perceptron**: Universal function approximator
- **Backpropagation**: Efficient gradient computation
- **Activation Functions**: Non-linearity introduction
  - Sigmoid: σ(z) = 1/(1+e^-z)
  - ReLU: max(0, z)
  - Tanh: (e^z - e^-z)/(e^z + e^-z)
- **Loss Functions**: MSE, Cross-entropy
- **Regularization**: Dropout, weight decay, L1/L2
- **Optimization**: Adam, RMSprop, momentum

## Network Structure

```
Input Layer (n features)
    ↓
Hidden Layer 1 (h1 neurons) → Activation
    ↓
Hidden Layer 2 (h2 neurons) → Activation
    ↓
Hidden Layer N (hN neurons) → Activation
    ↓
Output Layer (m classes) → Softmax/Sigmoid
```

## How to Run

**Requirements:**
```bash
pip install jupyter numpy pandas matplotlib scikit-learn
```

**Execute Jupyter Notebook:**
```bash
cd assignment9
jupyter notebook AIHW9.ipynb
```

**Run Cells in Order:**
1. Data loading and exploration
2. Preprocessing and normalization
3. Network architecture definition
4. Training loop implementation
5. Evaluation and visualization
6. Hyperparameter tuning

## Expected Output

```
Training Neural Network...

Epoch 1/100: Loss = 0.4521, Accuracy = 0.68
Epoch 2/100: Loss = 0.3842, Accuracy = 0.74
...
Epoch 100/100: Loss = 0.1203, Accuracy = 0.96

Test Accuracy: 0.945
Confusion Matrix:
  [945,   3,   2]
  [  2, 952,   1]
  [  1,   2, 947]

Visualizations:
- Training curves (loss, accuracy)
- Confusion matrix heatmap
- Learned feature maps (if applicable)
- Prediction examples
```

## Key Files

- `AIHW9.ipynb` - Complete Jupyter notebook with implementation
- Contains dataset, network code, training, and evaluation

## Hyperparameters

```python
input_size = 784              # Features (e.g., 28×28 images)
hidden_sizes = [128, 64, 32]  # Hidden layer dimensions
output_size = 10              # Number of classes
learning_rate = 0.001         # Gradient descent step size
batch_size = 32               # Samples per update
epochs = 100                  # Training iterations
dropout_rate = 0.2            # Regularization
```

## Notebook Sections

1. **Data Preparation**
   - Load dataset (MNIST, CIFAR-10, etc.)
   - Train/test split
   - Normalization and augmentation

2. **Model Definition**
   - Network architecture
   - Weight initialization
   - Layer specifications

3. **Training**
   - Forward pass
   - Loss computation
   - Backpropagation
   - Parameter updates
   - Validation monitoring

4. **Evaluation**
   - Test set accuracy
   - Confusion matrix
   - Performance metrics
   - Error analysis

5. **Visualization**
   - Training curves
   - Activation distributions
   - Sample predictions
   - Feature representations

## Performance Metrics

| Metric | Description |
|--------|------------|
| **Accuracy** | Percentage correct predictions |
| **Precision** | True positives / (true + false positives) |
| **Recall** | True positives / (true positives + false negatives) |
| **F1-Score** | Harmonic mean of precision and recall |
| **Loss** | Optimization objective value |

## Common Issues & Solutions

| Issue | Solution |
|-------|----------|
| **Overfitting** | Add dropout, L2 regularization, more data |
| **Underfitting** | Increase model capacity, train longer |
| **Vanishing Gradients** | Use ReLU, batch normalization |
| **Exploding Gradients** | Gradient clipping, weight initialization |
| **Slow Convergence** | Increase learning rate, better initialization |

## Learning Outcomes

✓ Neural network architecture and design
✓ Forward and backward propagation
✓ Gradient descent and optimization
✓ Loss functions and regularization
✓ Hyperparameter tuning
✓ Model evaluation and validation
✓ Deep learning best practices
✓ TensorFlow/PyTorch basics (if applicable)
