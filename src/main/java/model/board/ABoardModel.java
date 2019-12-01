package main.java.model.board;

import main.java.gameIO.IGameModel;
import main.java.model.modelToView.ICommand;
import main.java.model.move.*;
import main.java.model.utility.Dimension;


public abstract class ABoardModel implements IBoardModel{
	protected static final int EMPTY = 0;
	protected int[][] cells;
	protected IBoardState state;
	protected int[][] directions;
	protected IGameModel host;

	public ABoardModel(final int n, final int n2, final IGameModel host) {
		this.state = (IBoardState) NonTerminalState.Singleton;
		this.directions = new int[][] { { 1, 0 }, { 0, 1 }, { 1, 1 }, { 1, -1 } };
		this.cells = new int[n][n2];
		this.host = host;
	}

	int valueToPlayer(final int n) {
		return (n + 1) / 2;
	}

	int playerToValue(final int n) {
		return 2 * n - 1;
	}

	public Dimension getDimension() {

		return new Dimension(this.cells[0].length, this.cells.length);
	}

	protected synchronized void chgState(final int n) {
		if (n == -1) {
			this.state = (IBoardState)Player0WinState.Singleton;
		}
		else if (n == 1) {
			this.state = (IBoardState)Player1WinState.Singleton;
		}
		else {
			this.map(n, new IBoardLambada(){

				@Override
				public boolean apply(int i, IBoardModel iboardmodel, Object obj, int j, int k, int l) {
					ABoardModel.this.state = (IBoardState)NonTerminalState.Singleton;
					return false;
				}

				@Override
				public void noApply(int i, IBoardModel iboardmodel, Object obj) {
					ABoardModel.this.state = (IBoardState)DrawState.Singleton;					
				}

			}, null);
		}
	}

	public abstract IUndoMove makeMove(final int p0, final int p1, final int p2, final ICheckMoveVisitor p3, final IBoardStatusVisitor p4);

	public void reset() {
		this.mapAll(0, new IBoardLambada() {

			@Override
			public void noApply(int i, IBoardModel iboardmodel, Object obj) {				
			}

			@Override
			public boolean apply(int i, IBoardModel iboardmodel, Object obj, int j, int k, int l) {
				ABoardModel.this.cells[j][k] = 0;
				return true;
			}
		}, null);
	}

	public int[][] getCells() {
		return this.cells;
	}

	public void undoMove(final int n, final int n2, final IUndoVisitor undoVisitor) {
		throw new UnsupportedOperationException("Method undoMove() not yet implemented.");
	}

	public void map(final int n, final IBoardLambada boardLambda, final Object o) {
		this.state.map(n, boardLambda, o, (IBoardModel)this);
	}

	public void mapAll(final int n, final IBoardLambada boardLambda, final Object o) {
		for (int i = 0; i < this.cells.length; ++i) {
			for (int j = 0; j < this.cells[i].length; ++j) {
				boardLambda.apply(n, (IBoardModel)this, o, i, j, this.valueToPlayer(this.cells[i][j]));
			}
		}
	}

	public int playerAt(final int n, final int n2) {
		return this.cells[n][n2];
	}

	public Object execute(final IBoardStatusVisitor boardStatusVisitor, final Object o) {
		return this.state.execute(boardStatusVisitor, o, (IBoardModel)this);
	}

	public abstract boolean isValidMove(final int p0, final int p1, final int p2);

	public void redrawAll(final ICommand command) {
		this.mapAll(0, new IBoardLambada() {

			@Override
			public void noApply(int i, IBoardModel iboardmodel, Object obj) {
				throw new IllegalStateException("ABoardModel.redrawAll(): noApply() should be unreachable!");
			}

			@Override
			public boolean apply(int i, IBoardModel iboardmodel, Object obj, int j, int k, int l) {
				l = ABoardModel.this.cells[j][k];
				if (l == 0) {
					command.clearTokenAt(j, k);
				}
				else {
					command.setTokenAt(j, k, ABoardModel.this.valueToPlayer(l));
				}
				return true;
			}
		}, null);
	}

	public boolean isSkipPlayer(final int n) {
		final boolean[] array = { false };
		this.map(n, new IBoardLambada(){

			@Override
			public boolean apply(int i, IBoardModel iboardmodel, Object obj, int j, int k, int l) {
				return false;
			}

			@Override
			public void noApply(int i, IBoardModel iboardmodel, Object obj) {
				array[0] = true;
			}

		}, null);
		return array[0];
	}
}