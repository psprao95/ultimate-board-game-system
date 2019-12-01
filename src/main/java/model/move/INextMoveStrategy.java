package main.java.model.move;



import main.java.gameIO.IGameModel;
import main.java.model.utility.*;

/**
 *	Algorithm to calculate the next move
 *  Strategy Design Pattern
 */
public interface INextMoveStrategy {

	/**
	 * 
	 * @param context : The IModel being used.
	 * @param player : The player whose move is being calculated.
	 * @return : Calculates the next move as a Point (x = column, y = row).
	 */
	public abstract Point getNextMove(IGameModel context, int player);
	
}

