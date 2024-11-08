import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    private RoulettePanel roulettePanel;
    private Timer timer;
    private int angle = 0;

    public Main() {
        roulettePanel = new RoulettePanel();
        add(roulettePanel);

        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (angle < 360) {
                    angle += 1;
                } else {
                    angle = 0; // Reset angle after a full rotation
                }
                roulettePanel.repaint(); // Repaint the panel
            }
        });

        setTitle("Roulette Animation");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        timer.start(); // Start the animation
    }

    private class RoulettePanel extends JPanel {
        private final int NUM_SEGMENTS = 37; // 36 numbers + 0
        private final Color[] COLORS = {
                Color.GREEN,
                Color.BLACK,Color.RED, Color.BLACK, Color.RED, Color.BLACK,
                Color.RED, Color.BLACK, Color.RED, Color.BLACK, Color.RED,
                Color.BLACK, Color.RED, Color.BLACK, Color.RED, Color.BLACK,
                Color.RED, Color.BLACK, Color.RED, Color.BLACK, Color.RED,
                Color.BLACK, Color.RED, Color.BLACK, Color.RED, Color.BLACK,
                Color.RED, Color.BLACK, Color.RED, Color.BLACK, Color.RED,
                Color.BLACK, Color.RED, Color.BLACK, Color.RED, Color.BLACK, Color.RED
        };

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            // Calculate the diameter based on the smaller dimension
            int diameter = Math.min(getWidth(), getHeight());
            int radius = diameter / 2;

            // Apply rotation before drawing the segments
            g2d.rotate(Math.toRadians(angle), getWidth() / 2, getHeight() / 2);

            // Draw the roulette wheel
            for (int i = 0; i < NUM_SEGMENTS; i++) {
                g2d.setColor(COLORS[i]);
                double startAngle = (360.0 / NUM_SEGMENTS) * i;
                double arcAngle = (360.0 / NUM_SEGMENTS);
                g2d.fillArc(getWidth() / 2 - radius, getHeight() / 2 - radius, diameter, diameter, (int) startAngle, (int) arcAngle);
            }

            // Draw the outer arc for additional visual effect (optional)
            g2d.setColor(Color.BLACK); // Set color for outer arc
            g2d.drawArc(getWidth() / 2 - radius, getHeight() / 2 - radius, diameter, diameter, 0, 360);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(800, 800); // Fixed preferred size for square aspect ratio
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}
