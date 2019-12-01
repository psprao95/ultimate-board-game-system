/**
 *
 */
package main.java.model;

import java.util.*;

import main.java.gameIO.IModelCombo;
import main.java.model.board.*;
import main.java.model.modelToView.*;
import main.java.model.move.*;
import main.java.model.player.*;
import main.java.model.viewToModel.*;

/**
 * based on design patterns for games : https://www2.cs.duke.edu/courses/cps108/spring04/notes/zungmvc.pdf
 *
 *	Single game management class
 *	represent a concrete game
 */


/**
 * A concrete model of a game.  For descriptions of the methods, see the IModel,
 * IModelManager, and IModelCombo interface documentation.
 * Except for the setPlayers() and getPlayers() methods, see below.
 */
public final class GameModel implements IModelCombo {
	/**
	 * An abstract factory to create APlayers.   Used privately by GameModel.
	 */
	 interface IMakePlayer {
		/**
		 * Instantiates an APlayer object given the player's "player number".
		 * Player number 0 plays first, player number 1 plays second.
		 * @param playerNo The player number for the player to be instantiated.
		 * @return An APlayer object
		 */
		public APlayer create(int playerNo);
	}

	/**
	 * For player management.
	 */
	private TurnControl turnControl;

	/**
	 * Adapter to talk to the view to display/clear a game piece ("token") or
	 * a String message.
	 */
	private ICommand iCommand;

	/**
	 * Adapter to talk to the view to announce winner, draw, or reset.
	 */
	private IViewManager viewManager;

	/**
	 * Adapter to talk to the view to tell that the human player needs to try a
	 * move.
	 */
	private ITurnManager turnManager;

	/**
	 * The invariant, encapsulated rules and behaviors of a game.
	 */
	private IBoardModel boardModel;

	/**
	 * The constructor for the game model.
	 * @param nRows The number of rows in the board.
	 * @param nCols The number of columns in the board.
	 */
	public GameModel(int nRows, int nCols) {
		boardModel = new TicTacToeBoard(nRows,nCols, this);
		 //boardModel = new CheckersBoard(nRows, nCols,this);
	}

	public GameModel(IBoardModel boardModel) {
		this.boardModel = boardModel;
	}

	/**
	 * The facade that does "everything"!
	 * Use anonymous inner class to have access to everything in the outer object.
	 */
	private IRequestor requestor = new IRequestor() {

		@Override
		public void setTokenAt(int row, int col, int player, IRejectCommand rejectCommand) {
			boardModel.makeMove(row, col, player,

					new ICheckMoveVisitor() {

				@Override
				public void validMoveCase() {
					iCommand.setTokenAt(row, col, player);
				}
				@Override
				public void invalidMoveCase() {
					rejectCommand.execute();
				}
			},
					new IBoardStatusVisitor() {

				@Override
				public Object player1WonCase(IBoardModel host, Object param) {
					viewManager.win(1);
					viewManager.reset();
					boardModel.reset();
					return null;
				}

				@Override
				public Object player0WonCase(IBoardModel host, Object param) {
					viewManager.win(0);
					viewManager.reset();
					boardModel.reset();
					return null;
				}

				@Override
				public Object noWinnerCase(IBoardModel host, Object param) {
					return null;
				}

				@Override
				public Object drawCase(IBoardModel host, Object param) {
					viewManager.draw();
					viewManager.reset();
					boardModel.reset();
					return null;
				}
			});
		}
	};

	/**
	 * @param command
	 */
	public void setCommand(ICommand command) {
		iCommand = command;
	}

	public void reset() {
		System.out.println("Resetting");
		boardModel.reset();
		boardModel.redrawAll(iCommand);
		if(turnControl != null) {
			turnControl.setHalt();
		}
	}

	/**
	 * Assumes that the players are IMakePlayer factory objects.
	 * @param player0 an IMakePlayer factory
	 * @param player1 an IMakePlayer factory
	 */
	public void setPlayers(Object player1, Object player2) {
		// Last in/first play
		turnControl = new TurnControl(((IMakePlayer) player2).create(2));
		turnControl.addPlayer(((IMakePlayer) player1).create(1));
		turnControl.run();
	}

	public void setViewManager(IViewManager viewManager, ITurnManager turnManager) {
		this.viewManager = viewManager;
		this.turnManager = turnManager;
	}

	public IBoardModel getBoardModel() {
		return boardModel;
	}


	/**
	 * Returns a list filled with IMakePlayer factory objects.
	 * @return
	 */
	public List<Object> getPlayers() {
		List<Object> playerList = new ArrayList<Object>();

		playerList.add(new IMakePlayer() {
			public APlayer create(int playerNo) {
				return new ComputerPlayer(requestor, playerNo, GameModel.this, new RandomMoveStrategy());
			}
			public String toString() {
				return "Computer RandomMove";
			}
		});

		

		playerList.add(new IMakePlayer() {
			public APlayer create(int playerNo) {
				return new HumanPlayer(requestor, playerNo, turnManager);
			}
			public String toString() {
				return "Human player";
			}
		});

		// TODO: ADD COMPUTER PLAYER WITH OTHER NEXT MOVE STRATEGY

		return playerList;
	}

	public void exit() {
		reset();
	}

    public void skipNextPlayer(boolean isSkip) {
        turnControl.setSkipPlayer(isSkip);
    }

}
