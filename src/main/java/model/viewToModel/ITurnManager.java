package main.java.model.viewToModel;

/**
 * The interface used to tell the view that the user needs to try a move.
 */
public interface ITurnManager {

	/**
	 * 
	 * @param requestor: The requester used by the view to communicate which move it wishes to try
	 */
	public void takeTurn(IViewRequestor requestor);

}
