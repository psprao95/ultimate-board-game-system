package main.java.model.viewToModel;

import main.java.model.modelToView.IRejectCommand;

/**
 * This interface is used by the view to tell a HumanPlayer 
 * that the user wishes to place a token at on the board. 
 * The player number is not needed because the HumanPlayer object already knows who it is.
 */
public interface IViewRequestor {

	/**
	 * Requests that a token be placed at the given (row, col) 
	 * and that if the request is rejected, to execute the supplied reject command.
	 * @param row
	 * @param col
	 * @param rejectCommand
	 */
	public abstract void setTokenAt(int row, int col, IRejectCommand rejectCommand);
	

}
