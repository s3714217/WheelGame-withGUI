package controller;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import view.GameEngineGUI;

public class FrameResize implements ComponentListener{
	private GameEngineGUI gui;
	public FrameResize(GameEngineGUI gui)
	{
		this.gui = gui;
	}
	@Override
	public void componentResized(ComponentEvent e) {
		gui.resize();	
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

}
