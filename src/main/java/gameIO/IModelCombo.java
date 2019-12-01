package main.java.gameIO;

import main.java.model.modelToView.IViewManager;
import main.java.model.viewToModel.IModelManager;
import main.java.model.viewToModel.ITurnManager;

/**
 * Combines the running and management interfaces of the model.
 */
public interface IModelCombo extends IGameModel, IModelManager{

	/**
	 * Sets the management interface of the view used by the model.
	 * @param viewManager
	 * @param turnManager
	 */
	public abstract void setViewManager(IViewManager viewManager, ITurnManager turnManager);
	

}
