package main.java.gameIO;

import main.java.model.board.IBoardModel;
import main.java.model.modelToView.ICommand;

/**
 * This interface represents the model for a game in an MVC architecture.
 */

public interface IGameModel {

	void setCommand(final ICommand p0);
    	IBoardModel getBoardModel();
	
}
