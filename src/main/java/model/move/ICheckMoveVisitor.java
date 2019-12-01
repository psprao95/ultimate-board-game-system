package main.java.model.move;

/**
 * This interface encapsulates the commands to follow when 
 * the model/board has determined that a valid or invalid move was requested.
 */
public interface ICheckMoveVisitor {

	public abstract void invalidMoveCase();
	public abstract void validMoveCase();

}
