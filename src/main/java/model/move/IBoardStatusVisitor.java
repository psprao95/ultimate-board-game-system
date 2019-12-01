package main.java.model.move;

import main.java.model.board.IBoardModel;

/**
 * The visitor to an IBoardModel that provides variant behaviors for the different states of the board.
 */
public interface IBoardStatusVisitor {
	
	public abstract Object player0WonCase(IBoardModel host, Object param);
	public abstract Object player1WonCase(IBoardModel host, Object param);
	public abstract Object drawCase(IBoardModel host, Object param);
	public abstract Object noWinnerCase(IBoardModel host, Object param);

}
