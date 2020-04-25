package client;

import java.awt.EventQueue;
import model.GameEngineImpl;
import model.SimplePlayer;
import model.enumeration.BetType;
import model.interfaces.Player;
import view.GameEngineCallbackGUI;
import view.GameEngineCallbackImpl;

public class GUI_test {


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameEngineImpl engine = new GameEngineImpl();
					//example players
					engine.addPlayer(new SimplePlayer("4", "King Spin", 1000));
					engine.addPlayer(new SimplePlayer("5", "Crazy Gambler", 1000));
					engine.addPlayer(new SimplePlayer("6", "Luck Master", 1000));
					
					int enumOrdinal = 0;
				      for (Player player : engine.getAllPlayers())
				      {
				        engine.addPlayer(player);
				        engine.placeBet(player, 100, BetType.values()[enumOrdinal++ % BetType
				            .values().length]);
				      }
				      	GameEngineCallbackGUI callbackGUI = new GameEngineCallbackGUI(engine);
						GameEngineCallbackImpl callback = new GameEngineCallbackImpl();
						engine.addGameEngineCallback(callbackGUI);
						engine.addGameEngineCallback(callback);
						
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


}
