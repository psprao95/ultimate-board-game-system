package main.java.model.board;

import main.java.gameIO.IGameModel;
import main.java.model.move.IBoardStatusVisitor;
import main.java.model.move.ICheckMoveVisitor;
import main.java.model.move.IUndoMove;

public class TicTacToeBoard extends ABoardModel
{

    public TicTacToeBoard(int nRows, int nCols, IGameModel model) {
		super(3, 3, model);
	}

	public IUndoMove makeMove(int r, int c, int plyr, ICheckMoveVisitor cm, IBoardStatusVisitor bs) {
		if(isValidMove(plyr, r, c)) {
			cm.validMoveCase();
			cells[r][c] = playerToValue(plyr);
			if(wonGame(plyr)) {
				if(plyr == 0) {
					bs.player0WonCase(this, null);
					display();
				}
				else {
					bs.player1WonCase(this, null);
					display();
				}
			}
			else if(tiedGame()) {
				bs.drawCase(this, null);
				display();
			}
			else {
				bs.noWinnerCase(this, null);
				display();
        
			}
		}
		else {
			cm.invalidMoveCase();
			
		}
		
		



		return null;

	}

	private boolean wonGame(int plyr) {
		int player = this.playerToValue(plyr);
		for(int i = 0; i < cells.length; i++) {
			for(int j = 0; j < cells[i].length; j++) {
				if(cells[i][j] == player) {
					if(i+2 < cells.length) {
						if(player == cells[i+1][j] && player == cells[i+2][j]) {
							return true;
						}
						if(j+2 < cells[i].length && player == cells[i+1][j+1] && player == cells[i+2][j+2]) {
							return true;
						}
					}
					if(j+2 < cells[i].length) {
						if(player == cells[i][j+1] && player == cells[i][j+2]) {
							return true;
						}
						if(i-2 >= 0 && player == cells[i-1][j+1] && player == cells[i-2][j+2]) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	private boolean tiedGame() {
		int moves = 0;

		for(int i = 0; i < cells.length; i++) {
			for(int j = 0; j < cells[i].length; j++) {
				if(cells[i][j] != 0) {
					moves++;
				}
			}
		}

		return moves == cells.length * cells[0].length;
	}

	public boolean isValidMove(int player, int row, int col) {
		return row >= 0 && col >= 0 && row < cells.length && col < cells[row].length && this.cells[row][col] == 0;
	}


	public void display()
	{
		System.out.println("Board:");
		System.out.println("_________");
		for(int i=0;i<cells.length;i++)
		{
			for(int j=0;j<cells[0].length;j++)
			{
				System.out.print(cells[i][j]+" ");
			}
			System.out.println("");
		}
		System.out.println("_________");
	}


}
