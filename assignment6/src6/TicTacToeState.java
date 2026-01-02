package src6;

import java.util.ArrayList;
import java.util.List;
public class TicTacToeState implements State {
	private Square[] field; /**< The field, consisting of nine squares. First three values correspond to first row, and so on. */
	public Square player; /**< The player, either X or O. */
	public Square playerToMove; /**< The player that is about to move. */
	private float utility; /**< The utility value of this state. Can be 0, 1 (won) or -1 (lost).*/

	/**
	 * Updates the utility value.
	 */
	private void updateUtility() {
		/** TODO
		 * The utility value for the TicTacToe game is defined as follows:
		 * - if player has three marks in a row, it is 1
		 * - if the other player has three marks in a row, it is -1
		 * - otherwise it is 0
		 * Note that "three marks in a row" can actually be a row, a column
		 * or a diagonal. So basically, first find out if there are three
		 * identical values in a row, and if so, check whether the marks belong
		 * to player or not. 
		 */
		for (int i = 0; i < 3; i++) {
            // Check rows, columns, and diagonals
            if (checkWin(i * 3, i * 3 + 1, i * 3 + 2) || checkWin(i, i + 3, i + 6)
                    || (i == 0 && checkWin(0, 4, 8)) || (i == 2 && checkWin(2, 4, 6))) {
                return;
            }
        }
        utility = 0;
	}

	private boolean checkWin(int a, int b, int c) {
        if (field[a] == player && field[a] == field[b] && field[b] == field[c]) {
            utility = 1;
            return true;
        } else if (field[a] != Square.EMPTY && field[a] == field[b] && field[b] == field[c]) {
            utility = -1;
            return true;
        }
        return false;
    }
	
	/**
	 * Default constructor.
	 */
	public TicTacToeState() {
		field = new Square[9];
		for(int i = 0; i < 9; ++i) {
			field[i] = Square.EMPTY;
		}
		player = Square.X;
		playerToMove = Square.X;
		utility = 0;
	}
	
	@Override
	public List<Action> getActions() {
		/** TODO
		 * For the TicTacToe game, there is one valid action
		 * for each empty square. The action would then consist
		 * of the position of the empty square and the "color" of
		 * the player to move.
		 */
        List<Action> actions = new ArrayList<>();
        for (int i = 0; i < field.length; i++) {
            if (field[i] == Square.EMPTY) {
                actions.add(new TicTacToeAction(playerToMove, i));
            }
        }
        return actions;
	}

	@Override
	public float getUtility() {
		return utility;
	}

	@Override
	public State getResult(Action action) {
		/** TODO
		 * Create a new state and copy all the contents of the current state
		 * to the new one (in particular the field and the player). The
		 * player to move must be switched. Then incorporate the action into
		 * the field of the new state. Finally, compute the utility of the new
		 * state using updateUtility().
		 */
        TicTacToeState newState = new TicTacToeState();
        for (int i = 0; i < field.length; i++) {
            newState.field[i] = this.field[i];
        }
        newState.player = this.player;
        newState.playerToMove = (this.playerToMove == Square.X) ? Square.O : Square.X;

        TicTacToeAction tttAction = (TicTacToeAction) action;
        newState.field[tttAction.position] = tttAction.player;

        newState.updateUtility();
        return newState;
	}

	@Override
	public boolean isTerminal() {
		/** TODO
		 * Hint: the utility value has specific values if one of
		 * the players has won, which is a terminal state. However,
		 * you will also have to check for terminal states in which
		 * no player has won, which can not be inferred immediately
		 * from the utility value.
		 */
        if (Math.abs(utility) == 1) {
            return true;
        }
        for (Square s : field) {
            if (s == Square.EMPTY) {
                return false;  // Not terminal since there are empty squares
            }
        }
        return true; // Draw condition, board is full.
	}

	@Override
	public void print() {
		String s = "" + field[0] + "|" + field[1] + "|" + field[2] + "\n";
		s += "-+-+-\n";
		s += field[3] + "|" + field[4] + "|" + field[5] + "\n";
		s += "-+-+-\n";
		s += field[6] + "|" + field[7] + "|" + field[8] + "\n";
		System.out.println(s);
	}
}