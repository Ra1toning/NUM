import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class PairPoints extends JFrame {
    private List<Point> points = new ArrayList<>();
    private Point closestPoint1;
    private Point closestPoint2;
    public PairPoints() {
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (Point point : points) {
                    int x = (int) point.getX();
                    int y = (int) point.getY();
                    g.drawOval(x - 5, y - 5, 10, 10);
                }

                if (closestPoint1 != null && closestPoint2 != null) {
                    g.setColor(Color.RED);
                    int x1 = (int) closestPoint1.getX();
                    int y1 = (int) closestPoint1.getY();
                    int x2 = (int) closestPoint2.getX();
                    int y2 = (int) closestPoint2.getY();
                    g.fillOval(x1 - 5, y1 - 5, 10, 10);
                    g.fillOval(x2 - 5, y2 - 5, 10, 10);
                    g.drawLine(x1, y1, x2, y2);
                }
            }
        };
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Point newPoint = e.getPoint();
                points.add(newPoint);
                findClosestPair();
                panel.repaint();
            }
        });

        add(panel);
    }
    private void findClosestPair() {
        if (points.size() < 2) {
            closestPoint1 = closestPoint2 = null;
            return;
        }
        double minDistance = Double.MAX_VALUE;
        for (int i = 0; i < points.size() - 1; i++) {
            for (int j = i + 1; j < points.size(); j++) {
                Point p1 = points.get(i);
                Point p2 = points.get(j);
                double distance = p1.distance(p2);
                if (distance < minDistance) {
                    minDistance = distance;
                    closestPoint1 = p1;
                    closestPoint2 = p2;
                }
            }
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PairPoints frame = new PairPoints();
            frame.setVisible(true);
        });
    }
}
