package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import model.interfaces.GameEngine;
import view.PlaceBetPane;
import view.Status_Bar;

public class PlaceBetListener implements MouseListener{

	private GameEngine engine; 
	private Status_Bar gui;
	private PlaceBetPane pane;
	
	public PlaceBetListener(GameEngine engine, Status_Bar gui, PlaceBetPane pane)
	{
		this.engine = engine;
		this.gui = gui;
		this.pane = pane;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		pane.PlaceBet(engine, gui);
		
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
