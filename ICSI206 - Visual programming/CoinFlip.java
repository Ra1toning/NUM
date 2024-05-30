import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CoinFlip extends JFrame {
    private final CoinCell[] coinCells;
    public CoinFlip() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));

        coinCells = new CoinCell[9];
        for (int i = 0; i < 9; i++) {
            coinCells[i] = new CoinCell();
            add(coinCells[i]);
        }
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CoinFlip();
        });
    }
    private class CoinCell extends JLabel {
        private boolean isHeadsUp;
        public CoinCell() {
            setText("  H  ");
            setHorizontalAlignment(JLabel.CENTER);
            setOpaque(true);
            isHeadsUp = true;
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    flipCoin();
                }
            });
        }
        public void flipCoin() {
            isHeadsUp = !isHeadsUp;
            setText(isHeadsUp ? "H" : "T");
        }
    }
}
