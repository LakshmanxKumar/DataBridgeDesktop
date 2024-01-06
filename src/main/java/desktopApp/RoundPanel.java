package desktopApp;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JPanel;


public class RoundPanel extends JPanel{
	
	private int radius=20;
	public void setRadius(int r) {this.radius=r;}
	 public RoundPanel() {
	        setOpaque(false);}
	 @Override
     protected void paintComponent(Graphics g) {
         Graphics2D g2 = (Graphics2D) g;
         g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

         g2.setColor(getBackground());
         g2.fill(new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, radius, radius));

         super.paintComponent(g);
     }
 

}
