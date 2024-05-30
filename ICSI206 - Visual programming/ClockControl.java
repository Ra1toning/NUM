import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class ClockControl extends JFrame {
    private StillClock clock = new StillClock();
    private Timer timer;

    public ClockControl() {
        add(clock);

        timer = new Timer(1000, new TimerListener());
        timer.start();

        JButton startButton = new JButton("Start");
        JButton stopButton = new JButton("Stop");

        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startClock();
            }
        });

        stopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                stopClock();
            }
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    private void startClock() {
        if (!timer.isRunning()) {
            timer.start();
        }
    }
    private void stopClock() {
        if (timer.isRunning()) {
            timer.stop();
        }
    }
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            clock.setCurrentTime();
            clock.repaint();
        }
    }
    public static void main(String[] args) {
        JFrame frame = new ClockControl();
        frame.setTitle("ClockControl");
        frame.setSize(300, 300);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
