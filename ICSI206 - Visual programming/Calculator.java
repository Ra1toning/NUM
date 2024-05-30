import java.awt.event.*;
import javax.swing.*;

public class Calculator extends JFrame {
    private JTextField number1Field;
    private JTextField number2Field;
    private JTextField resultField;
    private JButton addButton;
    private JButton subtractButton;
    private JButton multiplyButton;
    private JButton divideButton;

    public Calculator() {

        number1Field = new JTextField(4);
        number2Field = new JTextField(4);
        resultField = new JTextField(10);
        resultField.setEditable(false);

        addButton = new JButton("Add");
        subtractButton = new JButton("Subtract");
        multiplyButton = new JButton("Multiply");
        divideButton = new JButton("Divide");

        JPanel panel1 = new JPanel();
        panel1.add(new JLabel("Number 1:"));
        panel1.add(number1Field);
        panel1.add(new JLabel("Number 2:"));
        panel1.add(number2Field);
        panel1.add(new JLabel("Result:"));
        panel1.add(resultField);

        JPanel panel2 = new JPanel();
        panel2.add(addButton);
        panel2.add(subtractButton);
        panel2.add(multiplyButton);
        panel2.add(divideButton);

        panel1.add(panel2);
        add(panel1);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double number1 = Double.parseDouble(number1Field.getText());
                double number2 = Double.parseDouble(number2Field.getText());
                double result = number1 + number2;

                resultField.setText(String.valueOf(result));
            }
        });

        subtractButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double number1 = Double.parseDouble(number1Field.getText());
                double number2 = Double.parseDouble(number2Field.getText());
                double result = number1 - number2;

                resultField.setText(String.valueOf(result));
            }
        });

        multiplyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double number1 = Double.parseDouble(number1Field.getText());
                double number2 = Double.parseDouble(number2Field.getText());
                double result = number1 * number2;

                resultField.setText(String.valueOf(result));
            }
        });

        divideButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double number1 = Double.parseDouble(number1Field.getText());
                double number2 = Double.parseDouble(number2Field.getText());
                double result = number1 / number2;

                resultField.setText(String.valueOf(result));
            }
        });

        setSize(500, 120);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
