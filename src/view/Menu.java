package view;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import model.interfaces.GameEngine;
import model.interfaces.Player;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu {

	private JFrame frame;
	private JButton add_player = new JButton("Add Player");
	private JButton place_bet = new JButton("Place Bet");
	private JButton remove = new JButton("Remove Player");
	private JButton close_button = new JButton("Close");
	private JLabel Title_label = new JLabel("GAME MENU");
	private AddPlayerPane addPlayer;
	private PlaceBetPane placebet;

	


	public Menu(GameEngine engine, Status_Bar gui) {
		initialize(engine, gui);
	}

	private void initialize(GameEngine engine, Status_Bar gui) {
		//initialize and add components
		frame = new JFrame();
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.getContentPane().add(Title_label);
		frame.getContentPane().add(add_player);
		frame.getContentPane().add(place_bet);
		frame.getContentPane().add(close_button);
		frame.getContentPane().add(remove);
		
		frame.setBounds(100, 100, 450, 300);
		add_player.setBounds(40, 93, 160, 47);
		place_bet.setBounds(40, 157, 160, 47);
		close_button.setBounds(258, 157, 160, 47);
		remove.setBounds(258, 93, 160, 47);
		Title_label.setBounds(188, 46, 77, 16);
		
		
		
		//Listened that switch to addPlayer Pane
		add_player.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				addPlayer = new AddPlayerPane(engine, gui);
				addPlayer.getPanel().setVisible(true);
				frame.setVisible(false);
			}
		});

		//Listened that switch to placeBet Pane
		place_bet.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				placebet = new PlaceBetPane(engine, gui);
				placebet.getPanel().setVisible(true);
				frame.setVisible(false);
			}
		});
		
		
		//Remove Player button
		remove.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			try {
				String[] name = new String[gui.getItemCount()];
				//initialize a list of player
				for (int x= 0; x < name.length; x++)
				{	
					Player player = (Player) gui.getItemAt(x);
					name[x] = player.getPlayerName();
				}
				
			int player_option=	JOptionPane.showOptionDialog(null,"Choose player to remove", "Remove Player", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, name, null);
			int confirm_option = 0;
			//Validating option
			if(player_option >= 0)
			{
				confirm_option=	JOptionPane.showConfirmDialog(null, "Are you sure?", null, JOptionPane.YES_NO_OPTION);
			}
			//Apply remove method
			if(confirm_option == 0)
			{
				Player player = (Player) gui.getItemAt(player_option);
				gui.removeItem(player);
				engine.removePlayer(player);
				JOptionPane.showMessageDialog(null, "Player removed");
			}
			}
			catch (NullPointerException x){
				JOptionPane.showMessageDialog(null, "No selected player");
			}
			}});
		
		//Close button
		close_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			frame.setVisible(false);
			
			}
		});
	}
	public JFrame getPanel()
	{
		return frame;
	}
	

}
