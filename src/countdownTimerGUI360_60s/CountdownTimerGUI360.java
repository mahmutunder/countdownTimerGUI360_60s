package countdownTimerGUI360_60s;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.*;

public class CountdownTimerGUI360 {

    public static void main(String[] args) {
        new CountdownTimerGUI360();
    }

    public CountdownTimerGUI360() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                }

                JFrame frame = new JFrame("Countdown Timer");
                frame.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
                frame.getRootPane().setBackground(Color.BLUE);
                frame.setUndecorated(false);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(new TestPane());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    public class TestPane extends JPanel {

        private JButton startButton;
        private int angle;
        private Timer timer;
        private double timeRemaining;
        private DecimalFormat decimalFormat;

        public TestPane() {
            decimalFormat = new DecimalFormat("00");
            setLayout(null);

            startButton = new JButton("Start");
            startButton.setBounds(10, 10, 80, 30);
            startButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    angle = 0;
                    timeRemaining = 60;
                    startTimer();
                }
            });
            add(startButton);
        }

        protected void startTimer() {
            timer = new Timer(1000, e -> {
                angle += 6;
                timeRemaining -= 1;
                if (angle >= 360) {
                    timer.stop();
                }
                repaint();
            });
            timer.start();
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(200, 200);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int x = 50;
            int y = 50;
            g2d.setColor(Color.GRAY);
            g2d.fillArc(x, y, 100, 100, 90, 360);
            g2d.setColor(Color.black);
            g2d.fillArc(x, y, 100, 100, 90, 360 - angle);
            g2d.setColor(Color.red);
            g2d.fillArc(x, y, 100, 100, 90, -angle);
            g2d.setColor(Color.white);
            g2d.setFont(new Font("Default", Font.BOLD, 20));
            String time = decimalFormat.format(timeRemaining);
            int width = g2d.getFontMetrics().stringWidth(time);
            g2d.drawString(time, x + 50 - width / 2, y + 50);
            g2d.dispose();
        }
    }

}
