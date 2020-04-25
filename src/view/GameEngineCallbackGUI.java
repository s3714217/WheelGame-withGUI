package view;

import model.interfaces.GameEngine;
import model.interfaces.Slot;
import view.interfaces.GameEngineCallback;

public class GameEngineCallbackGUI implements GameEngineCallback{
	
 private GameEngineGUI window;
 

	public GameEngineCallbackGUI(GameEngine engine) {
		
		window = new GameEngineGUI(engine); //initializer
		
	}
	@Override
	public void nextSlot(Slot slot, GameEngine engine) {
		
		window.setCurrentSlot(slot); //update current slot to GUI
	}
	@Override
	public void result(Slot winningSlot, GameEngine engine) {
		window.setCurrentSlot(winningSlot); //update winning slot to GUI
	}

	
	
	
	
}
