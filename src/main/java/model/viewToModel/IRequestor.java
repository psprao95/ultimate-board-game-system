package main.java.model.viewToModel;

import main.java.model.modelToView.IRejectCommand;

/**
 * This interface encapsulates the request mechanism used by an APlayer 
 * to try to make a move to a given (row, col) by a given player. 
 * This is the public (client) end of a "Facade" design pattern that hides the internal workings of the model.
 */
public interface IRequestor {
	
	/**
	 * Requests to the model that a given player's token be placed 
	 * on the internal board at (row, col). 
	 * An IRejectCommand is supplied that should be executed if the request is rejected.
	 * 
	 * @param row
	 * @param col
	 * @param player
	 * @param rejectCommand
	 */
	public abstract void setTokenAt(int row, int col, int player, IRejectCommand rejectCommand);

}
