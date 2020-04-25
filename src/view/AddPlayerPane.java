package view;

import javax.swing.JFrame;
import javax.swing.JTextField;

import controller.AddPlayerListener;
import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class AddPlayerPane extends JFrame {

	private JFrame frame;
	private JTextField Name_text;
	private JTextField ID_text;
	private JTextField Point_text;
	private JLabel Name_label = new JLabel("Player Name:");
	private JLabel ID_label = new JLabel("ID:");
	private JLabel Point_label = new JLabel("Point:");
	private JLabel Title = new JLabel("REGISTER PLAYER");
	private JButton Registe_button = new JButton("Register");
	private JButton Close_button = new JButton("Close");
	private AddPlayerListener addPlayer;
	

	public AddPlayerPane(GameEngine engine, Status_Bar gui) {
		initialize(engine, gui);
	}

	private void initialize(GameEngine engine, Status_Bar gui) {
		//initialize and add components
		addPlayer = new AddPlayerListener(engine, gui, this);
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
	//	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
	
		Point_text = new JTextField();
		ID_text = new JTextField();
		Name_text = new JTextField();
		Name_text.setBounds(191, 77, 130, 26);
		ID_text.setBounds(191, 115, 130, 26);
		Point_text.setBounds(191, 153, 130, 26);
		Name_label.setBounds(81, 82, 98, 16);
		Point_label.setBounds(81, 158, 61, 16);
		Title.setBounds(170, 33, 117, 16);
		Registe_button.setBounds(58, 205, 117, 29);
		Close_button.setBounds(227, 205, 117, 29);
		ID_label.setBounds(81, 120, 61, 16);
		Name_text.setColumns(10);
		ID_text.setColumns(10);
		Point_text.setColumns(10);
		
		frame.getContentPane().add(Name_text);
		frame.getContentPane().add(ID_text);
		frame.getContentPane().add(Name_label);
		frame.getContentPane().add(ID_label);
		frame.getContentPane().add(Point_label);
		frame.getContentPane().add(Title);
		frame.getContentPane().add(Point_text);
		frame.getContentPane().add(Registe_button);
		frame.getContentPane().add(Close_button);


		Registe_button.addMouseListener(addPlayer);
		
		
		
		Close_button.addMouseListener(new MouseAdapter() {
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
	public void AddPlayer(GameEngine engine, Status_Bar gui)
	{
		try {
			String name = Name_text.getText();
			String id = ID_text.getText();
			int point = Integer.parseInt(Point_text.getText());
			boolean valid = true;
						
			if(name == null || id == null)
			{
				JOptionPane.showMessageDialog(null, "Your Information cannot be empty");
			}
			else if(name.length() < 3)
			{
				JOptionPane.showMessageDialog(null, "Your Name should contain more than 3 character");
			}
			else
			{	
				
					for (int n = 0; n < gui.getItemCount(); n++)
					{
						Player player = (Player) gui.getItemAt(n);
						//looping through players and validating
						if (player.getPlayerName().toLowerCase().equals(name.toLowerCase()) == false && player.getPlayerId().equals(id) == false)
						{	
							valid = true;
						}
						else 
						{	
							valid = false;break;
							
						}
						
					}
					
					if(valid == false) {
						JOptionPane.showMessageDialog(null, "Player's name or ID already exist! Please add a different player");
						
					}
					else {
						JOptionPane.showMessageDialog(null, "Player added");
						engine.addPlayer(new SimplePlayer(id, name, point));
						gui.updateStatus(engine);
					}
					
			
			}

			Name_text.setText("");
			ID_text.setText("");
			Point_text.setText("");
			
		}//exception handling
		catch (NumberFormatException x)
		{
			JOptionPane.showMessageDialog(null, "Invalid initial point");
			Point_text.setText("");
		}
		catch (NullPointerException x)
		{
			JOptionPane.showMessageDialog(null, "Player information cannot be empty");
		}
		
	}
}
