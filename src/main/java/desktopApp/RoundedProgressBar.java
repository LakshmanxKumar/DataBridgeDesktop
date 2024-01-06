package desktopApp;
import javax.swing.JComponent;
import javax.swing.JProgressBar;
import javax.swing.plaf.basic.BasicProgressBarUI;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;

public class RoundedProgressBar extends JProgressBar {

    public RoundedProgressBar(int x,int y) {
        super(x,y);
        setUI(new RoundedProgressBarUI());
    }

    private class RoundedProgressBarUI extends BasicProgressBarUI {
        @Override
        protected void paintDeterminate(Graphics g, JComponent c) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int width = progressBar.getWidth();
            int height = progressBar.getHeight();
            int arcSize = height; // You can adjust the corner radius as needed

            // Draw the background
            g2d.setColor(progressBar.getBackground());
            g2d.fillRoundRect(0, 0, width, height, arcSize, arcSize);

            // Draw the progress
            double percent = progressBar.getPercentComplete();
            int fillWidth = (int) (width * percent);
            g2d.setColor(progressBar.getForeground());
            g2d.fillRoundRect(0, 0, fillWidth, height, arcSize, arcSize);

            g2d.dispose();
        }
    }
}
