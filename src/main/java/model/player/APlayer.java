package main.java.model.player;

import main.java.model.viewToModel.IRequestor;

public abstract class APlayer {

	private IRequestor iRequestor;
	private int player;
	private APlayer nextPlayer = this;
	
	
	public APlayer(IRequestor irequestor, int i)
	  {
	    nextPlayer = this;
	    iRequestor = irequestor;
	    player = i;
	  }

	  public abstract void takeTurn();

	  public IRequestor getRequestor(){
	    return iRequestor;
	  }

	  public int getPlayer(){
	    return player;
	  }

	  public APlayer getNextPlayer(){
	    return nextPlayer;
	  }

	  private void setNextPlayer(APlayer aplayer){
	    nextPlayer = aplayer;
	  }

	  public void insertAsRest(APlayer aplayer){
	    aplayer.setNextPlayer(getNextPlayer());
	    setNextPlayer(aplayer);
	  }
	
}
