package view;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

@SuppressWarnings("serial")
public class Wheel extends JLabel{

	private ImageIcon img1 = new ImageIcon("../WheelGameGUI/img/Basic_roulette_wheel_1024x1024.jpg");
	//initialize and update wheel size after resize()
	public Wheel(JLayeredPane main_panel)
	{
		 setBounds(main_panel.getX() , main_panel.getY(), main_panel.getWidth(), main_panel.getWidth());
		 img1.getImage();
		 setIcon(new ImageIcon(img1.getImage().getScaledInstance(main_panel.getWidth(), main_panel.getWidth(), Image.SCALE_FAST)));
	}
	public void update(int x, int y, int width, int height)
	{
		setBounds(x , y, width, height);
		 img1.getImage();
		 setIcon(new ImageIcon(img1.getImage().getScaledInstance(width,height, Image.SCALE_FAST)));
	}
}
