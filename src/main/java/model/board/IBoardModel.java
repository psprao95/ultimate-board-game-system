package main.java.model.board;


import main.java.model.modelToView.*;
import main.java.model.move.IBoardLambada;
import main.java.model.move.IBoardStatusVisitor;
import main.java.model.move.ICheckMoveVisitor;
import main.java.model.move.IUndoMove;
import main.java.model.utility.*;

/**
 * 
 * @author 
 * IboardModel interface keeps the rules of game
 * different concrete implementation of this interface
 * represent different type of games 
 */
public interface IBoardModel {


	public abstract Dimension getDimension();
	public abstract IUndoMove makeMove(int r, int c, int plyr, ICheckMoveVisitor cm, IBoardStatusVisitor bs);
	public abstract void reset();
	public abstract int[][] getCells();
	//map(...) -- useful for analyzing the valid moves available for any player.
	public abstract void map(int player, IBoardLambada lambda, Object param);
	//mapAll(...) -- maps an IBoardLambda across all possible board positions, valid or not. This is useful for finding out who is where on the board.
	public abstract void mapAll(int player, IBoardLambada lambda, Object param);
	//playerAt(...) -- looks at a single board location and tells you who, if anyone, is there.
	public abstract int playerAt(int row, int col);
	public abstract Object execute(IBoardStatusVisitor visitor, Object param);
	public abstract boolean isValidMove(int player, int row, int col);
	public abstract void redrawAll(final ICommand command);
	public abstract boolean isSkipPlayer(int player);











}
