package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import controller.FrameResize;
import controller.SpinListener;
import model.GameEngineImpl;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.Slot;

public class GameEngineGUI {

	private JFrame frame;
	private JToolBar player_bar = new JToolBar();
	private Status_Bar player_status;
	private JLayeredPane main_panel = new JLayeredPane();
	private Wheel wheel;
	private Pointer pointer = new Pointer(0,0,0,0);
	private JButton menu_button = new JButton("MENU");
	private JButton spin_button = new JButton("SPIN");
	private SpinListener spin_listener;
	private FrameResize resize;
	private Slot slot;

	public GameEngineGUI(GameEngine engine) {
		try {
			getGUI(engine);
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	private void getGUI(GameEngine engine ) throws IOException {
		//initialize and add component
		spin_listener = new SpinListener(engine, this);
		resize = new FrameResize(this);
		frame = new JFrame();
		frame.setVisible(true);
	    //frame.setBackground(Color.WHITE);
		frame.setBounds(100, 100, 600, 655);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.getContentPane().setLayout(new BorderLayout(0, 0));
		frame.getContentPane().add(player_bar, BorderLayout.NORTH);
		frame.getContentPane().add(main_panel, BorderLayout.CENTER);
		
		player_status = new Status_Bar(engine);
		player_bar.add(menu_button);
		player_bar.add(spin_button);
		player_bar.add(player_status);
		
		pointer.paint(null);
	    pointer.setBounds(0,0,4000,4000);
	    
	    main_panel.setBounds(0,0,frame.getWidth(), frame.getWidth());
	    wheel = new Wheel(main_panel);
	    main_panel.add(wheel, BorderLayout.CENTER);
	    main_panel.setLayer(wheel, 1);
	    main_panel.add(pointer, BorderLayout.CENTER);
	    main_panel.setLayer(pointer, 2);

		
		
		spin_button.addMouseListener(spin_listener);
		
	    frame.addComponentListener(resize);
	    
	    	//get Menu panel
	    menu_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Menu menu = new Menu(engine, player_status);
				menu.getPanel().setVisible(true);
			}
		});}
	
  //set CurrentSlot for the wheel
	public void setCurrentSlot(Slot slot)
	{
		this.slot = slot;
	}
  //spin the wheel
	public void spinGUI(GameEngine engine)
	{
		 new Thread() {
			   @Override
			   public void run() {
				   //game engine spin method
				   engine.spin(1, 500, 15);
				   
				   //summary panel
				   String info = "";
				   for(Player player : engine.getAllPlayers())
				   {
					   info += "Player: " + player.getPlayerName() + " ---- " + "Current point: " + player.getPoints() +"\n";
						   
				   }
				   
				   String noti = "Game Over! \n" + "The winning slot is: "+slot;
			      JOptionPane.showMessageDialog(null,noti );
			      JOptionPane.showMessageDialog(null, info);
			      JOptionPane.showMessageDialog(null, "Bet reset");
			   } }.start(); 
			   
		
		pointer.setVisible(true);
		spin_button.setVisible(false);
		 menu_button.setVisible(false);

		
		SwingUtilities.invokeLater(new Runnable() {
			
			
			@Override
			public void run() {
				//spinning animation
		
			Timer timer = new Timer(0, new ActionListener() {	 		
		 	int delay = 0;
		 	
	                @Override
	                public void actionPerformed(ActionEvent e) {
	                	int n = slot.getPosition()+1; // get current slot from callbackGUI
	                	int endX, endY; //Slot position
	                	//circular function used to generate slot position
	                	 endX = Math.round(wheel.getX() + wheel.getWidth()/2  + (float)((wheel.getWidth()/2 - wheel.getWidth()*1/40 )*Math.sin( (2*Math.PI/38)*n )));
	     		 	     endY = Math.round(wheel.getY() + wheel.getHeight()/2 - (float)((wheel.getWidth()/2 - wheel.getWidth()*1/40 )*Math.cos( (2*Math.PI/38)*n )));
	     		 	    //pointer moving around the wheel by updating its location
	     		 	     pointer.update(endX -5 , endY -5, main_panel.getWidth()/30 ,main_panel.getWidth()/30);
	            		 frame.repaint(1);
	            		//pointer slowing down 
	            		 ((GameEngineImpl) engine).delay(delay);
	            		 delay += 15;
	            		 if(delay > 500)
	            		 {
	            			 ((Timer)e.getSource()).stop();
	            			 spin_button.setVisible(true);
	            			 menu_button.setVisible(true);
	            		 }
	                }    	                
	            });		
		   timer.start();
		  }} );	
		
	}
  //resize
	public void resize()
	{
		frame.setBounds(frame.getX(), frame.getY(), frame.getWidth(), frame.getWidth() + 55);
 		wheel.update(0, 0, frame.getWidth(), frame.getWidth());
 		pointer.setVisible(false);
	}
	
	
}
