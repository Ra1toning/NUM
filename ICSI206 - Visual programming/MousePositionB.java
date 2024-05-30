import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MousePositionB extends JFrame {
    private JLabel positionLabel;

    public MousePositionB() {
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        positionLabel = new JLabel();
        positionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(positionLabel, BorderLayout.SOUTH);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                positionLabel.setText("Position: (" + x + ", " + y + ")");
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                positionLabel.setText("Position: ");
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MousePositionB app = new MousePositionB();
            app.setVisible(true);
        });
    }
}
