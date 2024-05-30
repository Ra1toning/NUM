import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MsgRotation extends JFrame {
    private JLabel msgLabel;
    private String[] msg = {"Java is fun", "Java is powerful"};
    private int MsgIdx = 0;
    public MsgRotation() {
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        msgLabel = new JLabel(msg[MsgIdx]);
        msgLabel.setHorizontalAlignment(SwingConstants.CENTER);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                rotateMessage();
            }
        });
        getContentPane().add(msgLabel, BorderLayout.CENTER);
    }
    private void rotateMessage() {
        MsgIdx = (MsgIdx + 1) % msg.length;
        msgLabel.setText(msg[MsgIdx]);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MsgRotation app = new MsgRotation();
            app.setVisible(true);
        });
    }
}
