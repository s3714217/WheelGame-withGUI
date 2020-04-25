package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import model.interfaces.GameEngine;
import view.GameEngineGUI;

public class SpinListener implements MouseListener{
	private GameEngineGUI gui;
	private GameEngine engine;
	
	public SpinListener(GameEngine engine, GameEngineGUI gui )
	{	
		this.engine = engine;
		this.gui = gui;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		gui.spinGUI(engine);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
