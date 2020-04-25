package view;

import javax.swing.JFrame;

import model.enumeration.BetType;
import model.interfaces.GameEngine;
import model.interfaces.Player;

import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import controller.PlaceBetListener;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PlaceBetPane {

	private JFrame frame;
	private JComboBox<String>PlayerBox = new JComboBox<String>();
	private JButton Place_button = new JButton("Place Bet");
	private JButton Close = new JButton("Close");
	private JLabel Title = new JLabel("Bet Type:");
	private JLabel Amount_label = new JLabel("Amount:");
	private JLabel BT_label = new JLabel("Bet Type:");
	private JLabel player_label = new JLabel("Player:");
	private JComboBox<String> BetBox = new JComboBox<String>();
	private JTextField amount = new JTextField();
	private PlaceBetListener placebet;
	


	public PlaceBetPane(GameEngine engine, Status_Bar gui) {
		initialize(engine, gui);
		
	}


	private void initialize(GameEngine engine, Status_Bar gui) {
		placebet = new PlaceBetListener(engine,gui,this);
		//initialize JFrame
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);	
		//Set Bound for all component
		amount.setBounds(117, 141, 210, 26);
		PlayerBox.setBounds(117, 60, 210, 27);
		Place_button.setBounds(72, 193, 117, 29);
		Title.setBounds(172, 32, 94, 16);
		Amount_label.setBounds(33, 146, 61, 16);
		BT_label.setBounds(33, 106, 61, 16);
		player_label.setBounds(33, 64, 61, 16);
		BetBox.setBounds(117, 102, 210, 27);
		Close.setBounds(234, 193, 117, 29);
		amount.setBounds(117, 141, 130, 26);
		amount.setColumns(10);
		//Add all component to main frame
		frame.getContentPane().add(PlayerBox);
		frame.getContentPane().add(Place_button);
		frame.getContentPane().add(Close);
		frame.getContentPane().add(Title);
		frame.getContentPane().add(Amount_label);
		frame.getContentPane().add(BT_label);
		frame.getContentPane().add(player_label);
		frame.getContentPane().add(BetBox);
		frame.getContentPane().add(amount);
		

		
		//Creating BetType option list
		String[] BT = new String[3];
		BT[0] = "BLACK";
		BT[1] = "RED";
		BT[2] ="ZEROS";
		for (int x = 0; x < BT.length; x++)
		{
		
		BetBox.addItem(BT[x]);
		}
		
		//Creating Player option list
		String[] Name = new String[gui.getItemCount()];
		for (int x = 0; x < Name.length; x++)
		{	
			Name[x] = ((Player) gui.getItemAt(x)).getPlayerName();
			PlayerBox.addItem(Name[x]);
		}
		
		
		
		//Mouse listener for placing bet 
		Place_button.addMouseListener(placebet);
		
		Close.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Close frame
				frame.setVisible(false);
			}
		});
		
	}
	
	public JFrame getPanel()
	{
		return frame;
	}
	


	public void PlaceBet(GameEngine engine, Status_Bar gui) {
		try {
			int betAmount = Integer.parseInt(amount.getText());
			
			int Bet_index = BetBox.getSelectedIndex();
			Player player = (Player) gui.getItemAt(PlayerBox.getSelectedIndex());
			
			if(betAmount > player.getPoints() || betAmount == 0)
			{
				JOptionPane.showMessageDialog(null,"Invalid bet");	
			}
			else {
			switch (Bet_index){
			//Validating and Set bet
			case 0: engine.placeBet(player, betAmount, BetType.BLACK);JOptionPane.showMessageDialog(null,"Bet placed ");break;
			case 1: engine.placeBet(player, betAmount, BetType.RED);JOptionPane.showMessageDialog(null,"Bet placed ");break;
			case 2: engine.placeBet(player, betAmount, BetType.ZEROS);JOptionPane.showMessageDialog(null,"Bet placed ");break;
			default: JOptionPane.showMessageDialog(null,"Invalid Bet Type ");
			}
			}
		amount.setText("");
			
		}
			catch (NumberFormatException x)
			{
				JOptionPane.showMessageDialog(null,"Invalid amount");
			}
			catch (NullPointerException x)
			{
				JOptionPane.showMessageDialog(null, "Player information cannot be empty");
			}
		
	}
}
