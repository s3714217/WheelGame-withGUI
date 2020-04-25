package view;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JLabel;


@SuppressWarnings("serial")
public class Pointer extends JLabel{

	private int x;
	private int y;
	private int width;
	private int height;
	
	public Pointer(int x, int y, int wid, int hei) 
	{//pointer coordination
		this.x = x;
		this.y = y;
		this.width = wid;
		this.height = hei;
	}
	
	public void update(int x, int y, int wid, int hei)
	{//update pointer position method 
	
		this.x = x;
		this.y = y;
		this.width = wid;
		this.height = hei;
	}
	
	 public void paintComponent(Graphics g)
	 {
		//pain pointer
		 g.setColor(Color.yellow);
		 g.drawOval(this.x, this.y, this.width, this.height);
		 g.fillOval(x, y, width, height);
		 
	 }
	 
	 
}
