package main.java.model.modelToView;

/**
 * A basic command that is executed when a move request has been rejected by the model.
 */
public interface IRejectCommand {

	public abstract void execute();
}
