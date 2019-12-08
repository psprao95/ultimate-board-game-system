package main.java;


import java.util.List;
import java.util.Scanner;

import main.java.model.GameModel;


import main.java.model.modelToView.ICommand;
import main.java.model.modelToView.IRejectCommand;
import main.java.model.modelToView.IViewManager;

import main.java.model.viewToModel.ITurnManager;
import main.java.model.viewToModel.IViewRequestor;


public class TicTacToe {
	public static void main(String[] args) {
		GameModel game = new GameModel(3,3);
		
		game.setViewManager(new IViewManager()
		{
			public void draw()
			{
				System.out.println("Its a draw");
			}

			public void win(int player)
			{
				System.out.println("Player "+player+" wins");
			}

			public void reset()
			{
				System.out.println("Resetting...");
			}
		}, new ITurnManager() {
			public void takeTurn(IViewRequestor requestor) {
				int row=0;
				int column=0;
				
				Scanner sc = new Scanner(System.in);
				
				try {
				
				
						System.out.println("Enter the row and column square where (separated by spaces) ");
						row = sc.nextInt();
				
						column = sc.nextInt();
						
				}
				catch(Exception e)
				{
					System.out.println("Please enter integer values");
					
				}
			
			

				requestor.setTokenAt(row-1, column-1, new IRejectCommand() {
					public void execute()
					{
						System.out.println("Invalid move");
					}
				});
			}
			
		});
		game.setCommand(new ICommand() {
			
		    public void setTokenAt(int row, int col, int player) {
		        
		        System.out.println("setting player " + player + "  token at ("+ (row+1) + "," + (col+1)+")");
		    }

		    @Override
		    public void clearTokenAt(int row, int col) {
		       
		        System.out.println("token at (" + (row+1) + "," + (col+1) +") was cleared");
		    }

		    @Override
		    public void setMessage(String s) {
		        
		        System.out.println("message was set to " + s);
		    }
		});
		List<Object> players = game.getPlayers();
		game.setPlayers(players.get(0), players.get(1));
	}
}

