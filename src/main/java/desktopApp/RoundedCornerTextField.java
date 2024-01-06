package desktopApp;
import javax.swing.JTextField;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class RoundedCornerTextField extends JTextField {

    private int padding = 8; // Adjust the padding value as needed

    public RoundedCornerTextField() {
        super();
        setOpaque(false); // Make the text field transparent
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        // Enable anti-aliasing for smoother graphics
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int arc = 12; // Adjust the arc value for the desired roundness

        // Draw the rounded rectangle for the outer shape
        g2d.setColor(getBackground());
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);

        // Adjust the position to add inside padding
        g2d.translate(padding, 0);

        // Draw the text with inside padding
        super.paintComponent(g2d);

        g2d.dispose();
    }
}
