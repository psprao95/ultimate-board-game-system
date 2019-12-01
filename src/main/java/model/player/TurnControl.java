package main.java.model.player;

// Referenced classes of package model:
//      APlayer

public class TurnControl{
	
	APlayer players;
	boolean skipPlayer;
	private ANextTurn proceedTurn;
	private ANextTurn waitTurn;
	private ANextTurn haltTurn;
	private volatile ANextTurn nextTurn;
	
	
	private abstract class ANextTurn{

		abstract boolean apply();

		private ANextTurn(){
		}

	}

	public TurnControl(APlayer aplayer){
		skipPlayer = false;
		proceedTurn = new ANextTurn() {

			boolean apply(){
				System.out.println("Proceeding to next player...");
				players = players.getNextPlayer();
				if (skipPlayer){
					skipPlayer = false;
					players = players.getNextPlayer();
				}
				//setWait();
				players.takeTurn();
				return true;
			}

		};
		waitTurn = new ANextTurn() {

			boolean apply()
			{
				System.out.println("Waiting....");
				try
				{
					Thread.sleep(5);
				}
				catch (Exception exception)
				{
					System.err.println(exception);
				}
				return true;
			}

		};
		haltTurn = new ANextTurn() {

			boolean apply()
			{
				System.out.println("Halting....");
				return false;
			}

		};
		nextTurn = proceedTurn;
		players = aplayer;
	}

	public void addPlayer(APlayer aplayer)
	{
		players.insertAsRest(aplayer);
		APlayer aplayer1 = players;
		int i = 1;
		do
			System.out.print("player " + i + " = " + aplayer1 + " ");
		while (players != (aplayer1 = aplayer1.getNextPlayer()));
	}

	public void run()
	{
		nextTurn = proceedTurn;
		(new Thread() {

			public void run()
			{
				try
				{
					while (nextTurn.apply()) ;
				}
				catch (Exception exception)
				{
					System.out.println("TurnControl.run() exception: " + exception);
				}
			}

		}).start();
	}

	public synchronized void setProceed()
	{
		nextTurn = proceedTurn;
	}

	public synchronized void setWait()
	{
		nextTurn = waitTurn;
	}

	public synchronized void setHalt()
	{
		nextTurn = haltTurn;
	}

	public synchronized void setSkipPlayer(boolean flag)
	{
		System.out.println("Skip player #" + flag);
		skipPlayer = flag;
	}



}