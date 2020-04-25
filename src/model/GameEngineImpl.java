package model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import model.enumeration.BetType;
import model.enumeration.Color;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.Slot;
import view.interfaces.GameEngineCallback;

public class GameEngineImpl implements GameEngine{

	protected int initialDelay;
	protected int finalDelay;
	protected int delayIncrement;
	private Slot winningSlot;
	private ArrayDeque<Slot> Slot = new ArrayDeque<Slot>();
	private Map<String, Player> player = new HashMap<String, Player>();
	private Collection<GameEngineCallback> Callback = new ArrayList<GameEngineCallback>();

	public void delay(int delay)
	{
		try {
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void spin(int initialDelay, int finalDelay, int delayIncrement) {
		// get a wheel
		if(Slot.size() < 38)
		{getWheelSlots();}
		
		//generating random starting point
		for(int x = (int)(Math.random()*((38 - 0)+1)) + 0;x >= 0; x--)
		{
			winningSlot = Slot.poll();
			Slot.addLast(winningSlot);
		}

		//generating random stopping point
	while( initialDelay < finalDelay)
	{
			winningSlot = Slot.poll();
			Slot.addLast(winningSlot);
			delay(initialDelay);
			
			for(GameEngineCallback Call: Callback)
			{	
				if (initialDelay < finalDelay - delayIncrement ) {
				Call.nextSlot(winningSlot, this);}
			
				if(initialDelay == finalDelay - delayIncrement)
				{break;}
			}

		initialDelay += delayIncrement;
	}
		

		// calculating the result
		calculateResult(winningSlot);
		for(GameEngineCallback Call: Callback)
		{
			Call.result(winningSlot, this);
		}
		for (Player player : getAllPlayers()){player.resetBet();}
	
	}
	


	@Override
	public void calculateResult(Slot winningSlot) {
		
		//Applying winning condition for BetType
		for (Player player : getAllPlayers())
		{	if (player.getBetType() !=null)
		{
			player.getBetType().applyWinLoss(player, winningSlot);}
		else
		{
			break;
		}
		}
	}

	@Override
	public void addPlayer(Player player) {
		//add player to a harsh map
		this.player.put(player.getPlayerId(), player);
		
	}

	@Override
	public Player getPlayer(String id) {
		//getter method
			return player.get(id);
	}

	@Override
	public boolean removePlayer(Player player) {
		//remove player from harsh map
		return this.player.remove(player.getPlayerId(), player);
	}

	@Override
	public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {
		this.Callback.add(gameEngineCallback);
		//add GameEngineCallback to the Collection
	}

	@Override
	public boolean removeGameEngineCallback(GameEngineCallback gameEngineCallback) {

		return this.Callback.remove(gameEngineCallback);
		//remove GameEngineCallback from the Collection
		//return true if successful and false if failed
	}

	@Override
	public Collection<Player> getAllPlayers() {
		//return an unmodifiabbleCollection
		return Collections.unmodifiableCollection(player.values());
	}

	@Override
	public boolean placeBet(Player player, int bet, BetType betType) {
		if(bet <= player.getPoints() && betType != null)
		{
		player.setBetType(betType);
		return player.setBet(bet);
		}
		//setting Bet value
		else
		{System.out.println("AAAAA");
			return false;
			
		}
		//return true if it is a valid bet and false if not
	}

	@Override
	public Collection<Slot> getWheelSlots() {
	//seeding the slot 
		Slot.add(new SlotImpl(0, Color.GREEN00, 00));
		Slot.add(new SlotImpl(1, Color.RED, 27));
		Slot.add(new SlotImpl(2, Color.BLACK, 10));
		Slot.add(new SlotImpl(3, Color.RED, 25));
		Slot.add(new SlotImpl(4, Color.BLACK, 29));
		Slot.add(new SlotImpl(5, Color.RED, 12));
		Slot.add(new SlotImpl(6, Color.BLACK, 8));
		Slot.add(new SlotImpl(7, Color.RED, 19));
		Slot.add(new SlotImpl(8, Color.BLACK, 31));
		Slot.add(new SlotImpl(9, Color.RED, 18));
		Slot.add(new SlotImpl(10, Color.BLACK, 6));
		Slot.add(new SlotImpl(11, Color.RED, 21));
		Slot.add(new SlotImpl(12, Color.BLACK, 33));
		Slot.add(new SlotImpl(13, Color.RED, 16));
		Slot.add(new SlotImpl(14, Color.BLACK, 4));
		Slot.add(new SlotImpl(15, Color.RED, 23));
		Slot.add(new SlotImpl(16, Color.BLACK, 35));
		Slot.add(new SlotImpl(17, Color.RED, 14));
		Slot.add(new SlotImpl(18, Color.BLACK, 2));
		Slot.add(new SlotImpl(19, Color.GREEN0, 0));
		Slot.add(new SlotImpl(20, Color.BLACK, 28));
		Slot.add(new SlotImpl(21, Color.RED, 9));
		Slot.add(new SlotImpl(22, Color.BLACK, 26));
		Slot.add(new SlotImpl(23, Color.RED, 30));
		Slot.add(new SlotImpl(24, Color.BLACK, 11));
		Slot.add(new SlotImpl(25, Color.RED, 7));
		Slot.add(new SlotImpl(26, Color.BLACK, 20));
		Slot.add(new SlotImpl(27, Color.RED, 32));
		Slot.add(new SlotImpl(28, Color.BLACK, 17));
		Slot.add(new SlotImpl(29, Color.RED, 5));
		Slot.add(new SlotImpl(30, Color.BLACK, 22));
		Slot.add(new SlotImpl(31, Color.RED, 34));
		Slot.add(new SlotImpl(32, Color.BLACK, 15));
		Slot.add(new SlotImpl(33, Color.RED, 3));
		Slot.add(new SlotImpl(34, Color.BLACK, 24));
		Slot.add(new SlotImpl(35, Color.RED, 36));
		Slot.add(new SlotImpl(36, Color.BLACK, 13));
		Slot.add(new SlotImpl(37, Color.RED, 1));
				
		return Slot;
	//returning a collection of Slot
	}
}
