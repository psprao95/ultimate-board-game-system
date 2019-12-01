package main.java.gameIO;


import java.util.List;

import main.java.model.modelToView.ICommand;
import main.java.model.utility.*;
import main.java.model.viewToModel.IViewRequestor;

/**
 * The running interface of the view.
 */
public interface IView {
	
	/**
	 * @return ICommand : The ICommand object used by the model to display tokens, etc. on the view.
	 */
	public abstract ICommand getCommand();
	
	/**
	 * @param requestor : For use by the view
	 */
	public abstract void setiViewRequestor(IViewRequestor iViewRequestor);
	
	/**
	 * A List of Objects to be used by the view to give a choice of players. 
	 * The view will select two of the elements of the List (possibly the same element twice), 
	 * to be used to determine the players of the game.
	 * @param players
	 */
	public abstract void setPlayers(List<Object> players);
	
	/**
	 * Sets the width (columns) and height (rows) of the displayed board. 
	 * This information should be obtained from the model.
	 * @param size
	 */
	public abstract void setDimension(Dimension size);
	

}
