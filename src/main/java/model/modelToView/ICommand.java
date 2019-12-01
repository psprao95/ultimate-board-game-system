package main.java.model.modelToView;

/**
 * The command supplied by the view that enables the model to set a token 
 * for a particular player at a given (row, col) location on the screen,
 * clear the display at a (row, col), and to show a particular message on the screen.
 */
public interface ICommand {

	public abstract void setTokenAt(int row, int col, int player);
	public abstract void clearTokenAt(int row, int col);
	public abstract void setMessage(String s);

}
