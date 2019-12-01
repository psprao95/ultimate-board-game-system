package main.java.model.modelToView;

/**
 * Management interface for the view
 */
public interface IViewManager {

	public abstract void draw();
	public abstract void win(int player);
	/**
	 * Resets the view.
	 */
	public abstract void reset();
}
