package desktopApp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;

public class RoundButton extends JButton{
	
	private int radius=10;
	public void setRadius(int r) {this.radius=r;}
	RoundButton(String text){super(text);setContentAreaFilled(false);setOpaque(false);}
	 @Override
     protected void paintComponent(Graphics g) {
	 Graphics2D g2 = (Graphics2D) g;
     g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

     if (getModel().isArmed()) {
         g2.setColor(new Color(145, 177, 255));
     } else {
         g2.setColor(getBackground());
     }

     
     g2.fill(new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, radius, radius));

     super.paintComponent(g);
 }

}
