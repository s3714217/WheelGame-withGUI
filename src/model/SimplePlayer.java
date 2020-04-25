package model;

import model.enumeration.BetType;
import model.interfaces.Player;

public class SimplePlayer implements Player
{
	private String playerId;
	private String playerName;
	private int initialPoint;
	private int bet;
	private BetType BT;
	
	public SimplePlayer(String playerId, String playerName, int initialPoints)
	{
		this.playerId = playerId;
		this.initialPoint = initialPoints;
		this.playerName = playerName;
		//constructor for the Player object
	}

	@Override
	public String getPlayerName() {
		
		return playerName;
		//getter method for Player Name
	}
	@Override
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
		//setter method for Player Name
	}
	@Override
	public int getPoints() {
		return initialPoint;
		//getter method for Points
	}
	@Override
	public void setPoints(int points) {
		this.initialPoint = points;
		//setter method for Points
	}
	@Override
	public String getPlayerId() {

		return playerId;
		//getter method for PlayerID
	}
	@Override
	public boolean setBet(int bet) {

		if (bet > initialPoint || bet < 0)
		{
			return false;
		}
		else
		{
		this.bet = bet;
		}
		return true;
		//setter method for Bet
		//return true if a valid bet
	}
	@Override
	public int getBet() {
		return bet;
		//getter method for Player Bet
	}
	@Override
	public void setBetType(BetType betType) {
	if(betType != null)
	{
			this.BT = betType; 
	}
	
		//setter method for Bet Type
		
	}
	@Override
	public BetType getBetType() {
		
		return BT;
		//getter method for Bet Type
	}
	@Override
	public void resetBet() {
		bet = 0;
		//reset bet
	}
	public String toString()
	{
		return String.format("Player ID: %s Player Name: %s Bet: %s BetType: %s Points: %d\n  ",
				 this.playerId, this.playerName, this.getBet(), this.getBetType(), this.initialPoint);
		//to String method
	}
}
