package view;

import java.util.logging.Level;
import java.util.logging.Logger;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.Slot;
import view.interfaces.GameEngineCallback;

/**
 * 
 * Skeleton/Partial example implementation of GameEngineCallback showing Java logging behaviour
 * 
 * @author Caspar Ryan
 * @see view.interfaces.GameEngineCallback
 * 
 */
public class GameEngineCallbackImpl implements GameEngineCallback
{
   private static final Logger logger = Logger.getLogger(GameEngineCallback.class.getName());
   
   public GameEngineCallbackImpl()
   {   
	   logger.setLevel(Level.ALL);
   }

   @Override
   public void nextSlot(Slot slot, GameEngine engine)
   {
      
      logger.log(Level.FINE, String.format("Position: %s, Color: %s, Number: %s", slot.getPosition(), slot.getColor(), slot.getNumber()));
    		  
   }

   @Override
   public void result(Slot result, GameEngine engine)
   {
	  String string = String.format("\n  ");
	  logger.log(Level.INFO, String.format("RESULT= %s \n", result.toString()));
      logger.log(Level.INFO, String.format("FINAL PLAYER POINT BALANCES"));
    
      for(Player player : engine.getAllPlayers())
      {
    	  string += player.toString();    			
      }
      logger.log(Level.INFO, string);
   }
}
