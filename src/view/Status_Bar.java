package view;

import javax.swing.JComboBox;

import model.interfaces.GameEngine;
import model.interfaces.Player;

@SuppressWarnings({ "rawtypes", "serial" })
public class Status_Bar extends JComboBox {
	
//add all player from game engine
	@SuppressWarnings("unchecked")
	public Status_Bar(GameEngine engine)
	{
		for(Player players : engine.getAllPlayers())
		{			
			addItem(players);	
		}
	}
	@SuppressWarnings("unchecked")
	public void AddItem(Player player)
	{
		addItem(player);
	}
	@SuppressWarnings("unchecked")
	public void updateStatus(GameEngine engine)
	{	
		removeAllItems();
		for(Player players : engine.getAllPlayers())
		{			
			addItem(players);	
		}
	}
}
