import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class tictactoe extends JFrame implements ActionListener {
    private JButton[][] buttons;
    private boolean xTurn = true;

    public tictactoe() {
        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLayout(new GridLayout(3, 3));
        initializeButtons();
    }

    private void initializeButtons() {
        buttons = new JButton[3][3];
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                buttons[x][y] = new JButton("");
                buttons[x][y].setFont(new Font("Arial", Font.BOLD, 40));
                buttons[x][y].setBackground(Color.WHITE);
                buttons[x][y].setFocusPainted(false);
                buttons[x][y].addActionListener(this);
                add(buttons[x][y]);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton buttonClicked = (JButton) e.getSource();

        if (buttonClicked.getText().equals("") && xTurn) {
            buttonClicked.setText("X");
            buttonClicked.setForeground(Color.BLACK);
        } else if (buttonClicked.getText().equals("") && !xTurn) {
            buttonClicked.setText("O");
            buttonClicked.setForeground(Color.BLUE);
        }

        if (checkForWin()) {
            if (xTurn) {
                JOptionPane.showMessageDialog(this, "X ЯЛЛАА!");
            } else {
                JOptionPane.showMessageDialog(this, "O ЯЛЛАА!");
            }
            resetBoard();
        } else if (isBoardFull()) {
            JOptionPane.showMessageDialog(this, "ТЭНЦЛЭЭ!");
            resetBoard();
        } else {
            xTurn = !xTurn;
        }
    }

    private boolean checkForWin() {
        // Мөр, багана, диагональ зэргийг шалгана
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(buttons[i][1].getText()) && buttons[i][1].getText().equals(buttons[i][2].getText()) && !buttons[i][0].getText().equals("")) {
                return true; // Эгнээний хожил
            }
            if (buttons[0][i].getText().equals(buttons[1][i].getText()) && buttons[1][i].getText().equals(buttons[2][i].getText()) && !buttons[0][i].getText().equals("")) {
                return true; // Баганны хожил
            }
        }

        if (buttons[0][0].getText().equals(buttons[1][1].getText()) && buttons[1][1].getText().equals(buttons[2][2].getText()) && !buttons[0][0].getText().equals("")) {
            return true; // Диагональ хожил (зүүн дээдээс баруун доош)
        }

        if (buttons[0][2].getText().equals(buttons[1][1].getText()) && buttons[1][1].getText().equals(buttons[2][0].getText()) && !buttons[0][2].getText().equals("")) {
            return true; // Диагональ хожил (баруун дээдээс зүүн доош)
        }

        return false;
    }

    private boolean isBoardFull() {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if (buttons[x][y].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    private void resetBoard() {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                buttons[x][y].setText("");
                buttons[x][y].setEnabled(true);
            }
        }
        xTurn = true;
    }

    public static void main(String[] args) {
        tictactoe game = new tictactoe();
        game.setVisible(true);
    }
}
