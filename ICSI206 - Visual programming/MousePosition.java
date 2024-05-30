import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MousePosition extends JFrame {
    private JLabel positionLabel;

    public MousePosition() {
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        positionLabel = new JLabel();
        positionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(positionLabel, BorderLayout.SOUTH);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                positionLabel.setText("(" + x + ", " + y + ")");
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MousePosition app = new MousePosition();
            app.setVisible(true);
        });
    }
}
