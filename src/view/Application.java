package view; /**
 * The application itself with all components
 *
 * @author Cheaker on 21.04.2017.
 */

import controller.Controller;
import model.Ball;
import model.BallComparator;
import model.State;

import javax.swing.*;

import java.awt.*;
import java.util.Iterator;

public class Application {

    private static Panel panel;
    private static JButton checkButton;
    private static JButton hintButton;
    private static JButton unloadLeft;
    private static JButton unloadRight;
    private static JButton resetButton;

    public void createGUI() {
        JFrame frame = new JFrame("CuS");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            //todo set logger see log4j
            e.printStackTrace();
        }

        panel = new Panel();
        panel.setLayout(null);

        resetButton = new JButton("Reset");
        resetButton.setBounds(365, 50, 70, 40);
        resetButton.setFont(new Font("Arial", Font.PLAIN, 14));
        resetButton.addActionListener(e -> {
            panel.getField().getScale().setState(State.EQUAL);
            panel.getField().resetAttempt();

            Iterator<Ball> itR = panel.getField().getScale().getRightSide().iterator();
            while (itR.hasNext()) {
                Ball ball = itR.next();
                panel.getField().getBalls().add(ball);
                itR.remove();
            }

            Iterator<Ball> itL = panel.getField().getScale().getLeftSide().iterator();
            while (itL.hasNext()) {
                Ball ball = itL.next();
                panel.getField().getBalls().add(ball);
                itL.remove();
            }
            panel.getField().getBalls().sort(new BallComparator());

            panel.repaint();
        });
        panel.add(resetButton);

        unloadLeft = new JButton("Unload");
        unloadLeft.setBounds(215, 400, 80, 60);
        unloadLeft.setFont(new Font("Arial", Font.PLAIN, 14));
        unloadLeft.addActionListener(e -> {
            Iterator<Ball> it = panel.getField().getScale().getLeftSide().iterator();
            while (it.hasNext()) {
                Ball ball = it.next();
                panel.getField().getBalls().add(ball);
                it.remove();
            }
            panel.getField().getBalls().sort(new BallComparator());
            panel.repaint();
        });
        panel.add(unloadLeft);

        unloadRight = new JButton("Unload");
        unloadRight.setBounds(500, 400, 80, 60);
        unloadRight.setFont(new Font("Arial", Font.PLAIN, 14));
        unloadRight.addActionListener(e -> {
            Iterator<Ball> it = panel.getField().getScale().getRightSide().iterator();
            while (it.hasNext()) {
                Ball ball = it.next();
                panel.getField().getBalls().add(ball);
                it.remove();
            }
            panel.getField().getBalls().sort(new BallComparator());
            panel.repaint();
        });
        panel.add(unloadRight);

        int deviated = panel.getField().getDeviatedBall();
        String weight = panel.getField().getBallByNumber(deviated).getWeight() == 3 ? "heavier" : "lighter";
        hintButton = new JButton("Hint");
        hintButton.setBounds(700, 100, 70, 50);
        hintButton.setFont(new Font("Arial", Font.PLAIN, 14));
        hintButton.addActionListener(e -> JOptionPane.showMessageDialog(panel, "model.Ball number " + deviated + " is " + weight, "Information", JOptionPane.INFORMATION_MESSAGE));
        panel.add(hintButton);

        checkButton = new JButton("Check");
        checkButton.setFont(new Font("Arial", Font.PLAIN, 20));
        checkButton.setBounds(new Rectangle(325, 130, 150, 50));
        checkButton.addActionListener(e -> {
            panel.getField().getScale().checkWeight();
            panel.getField().increaseAttempt();
            panel.repaint();
        });
        panel.add(checkButton);

        panel.addMouseListener(new Controller(panel));
        frame.getContentPane().add(panel);


        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

}