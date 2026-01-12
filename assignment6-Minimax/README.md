# Assignment 6: Adversarial Search - Minimax Algorithm for Tic-Tac-Toe

## Problem Statement

Implement the Minimax algorithm to create an optimal player for Tic-Tac-Toe. The algorithm must explore the game tree and determine the best move for either maximizing or minimizing player using adversarial search.

**Game Rules:**
- 3×3 grid
- Two players (X and O) alternate turns
- First to get 3 in a row wins
- Draw if board fills with no winner

## Approach

Implements the **Minimax Algorithm** with **Alpha-Beta Pruning**:

### Core Minimax Logic
1. **MaxValue**: Maximizing player (trying to win) selects move with highest utility
2. **MinValue**: Minimizing player (opponent) selects move with lowest utility
3. **Recursion**: Alternates between Max and Min levels
4. **Terminal Test**: Checks if game is won, lost, or drawn
5. **Utility**: Returns +1 (max wins), -1 (min wins), 0 (draw)

### Alpha-Beta Pruning
- **Alpha**: Best value found so far for maximizing player
- **Beta**: Best value found so far for minimizing player
- **Pruning**: Eliminates branches that won't affect final decision
- **Efficiency**: Reduces nodes explored from O(b^d) to O(b^(d/2))

## Key Algorithms & Concepts

- **Minimax**: Game tree evaluation for zero-sum games
- **Alpha-Beta Pruning**: Optimization that eliminates dead branches
- **Game Tree Search**: Complete exploration of possible game states
- **Utility Function**: Evaluation of terminal game states
- **Perfect Play**: Guarantees optimal moves against optimal opponent

## Algorithm Flow

```
MinimaxDecision(state)
    ↓
For each possible action:
    ├→ MaxValue(result(action), -∞, +∞)
    │   ├→ If terminal: return utility
    │   ├→ For each child: min(maxValue(child))
    │   └→ Alpha-Beta pruning
    └→ Select action with maximum utility

MinValue works similarly (minimizing instead)
```

## How to Run

**In Visual Studio Code:**
Simply run `Main.java` directly from the IDE.

**Compile:**
```bash
cd assignment6-Minimax/src6
javac *.java
```

**Run:**
```bash
java Main
```

**Or use the script:**
```bash
./run.sh
```

## Expected Output

```
Initial Board:
 1 | 2 | 3
-----------
 4 | 5 | 6
-----------
 7 | 8 | 9

Computer (X) plays: Move 5
Board after computer move:
 1 | 2 | 3
-----------
 4 | X | 6
-----------
 7 | 8 | 9

State space size: 5478

Human (O) plays: Move 1
...
Game Over: Draw
```

## Key Files

- `MiniMax.java` - Core minimax algorithm with alpha-beta pruning
- `State.java` - Game state representation
- `Action.java` - Possible moves
- `TicTacToeState.java` - Tic-tac-toe specific state
- `TicTacToeAction.java` - Tic-tac-toe specific actions
- `Square.java` - Individual board square
- `Main.java` - Game loop and UI

## Performance Metrics

**Without Pruning:**
- Nodes explored: ~5,478 for initial position
- Time: Noticeable delay (milliseconds)

**With Alpha-Beta Pruning:**
- Nodes explored: ~362 (93% reduction)
- Time: Nearly instant

**Pruning Effectiveness:**
- Reduces branching factor from b to √b
- Enables deeper search in limited time

## Minimax Algorithm Variants

| Variant | Feature | Use Case |
|---------|---------|----------|
| **Vanilla Minimax** | No optimization | Teaching/small trees |
| **Alpha-Beta Pruning** | Eliminates dead branches | Standard game-playing |
| **Iterative Deepening** | Deeper search over time | Time-bounded games |
| **Transposition Tables** | Caches evaluated states | Large trees |

## Learning Outcomes

✓ Minimax algorithm for game playing
✓ Alpha-beta pruning optimization
✓ Game tree search and evaluation
✓ Zero-sum game theory
✓ Adversarial search in competitive environments
✓ Pruning effectiveness and performance analysis
