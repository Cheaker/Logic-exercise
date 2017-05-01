package view;

import model.Ball;
import model.Field;
import model.State;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

/**
 * Graphic logic
 *
 * @author Cheaker on 21.04.2017.
 */
public class Panel extends JPanel {

    private Field field;

    private Map<Integer, int[]> coordinatesLeftScale;
    private Map<Integer, int[]> coordinatesRightScale;

    private Color ballColor = Color.green;
    private int WIDTH = 800;
    private int HEIGHT = 600;

    public Panel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        requestFocusInWindow(true);
        field = new Field();
        coordinatesRightScale = new HashMap<>();
        coordinatesLeftScale = new HashMap<>();
//        coordinatesRightScale.put(1,new int[]{570, 200});
//        coordinatesRightScale.put(2,new int[]{620, 200});
//        coordinatesRightScale.put(3,new int[]{670, 200});
//        coordinatesRightScale.put(4,new int[]{570, 250});
//        coordinatesRightScale.put(5,new int[]{620, 250});
//        coordinatesRightScale.put(6,new int[]{670, 250});
//        coordinatesRightScale.put(7,new int[]{570, 300});
//        coordinatesRightScale.put(8,new int[]{620, 300});
//        coordinatesRightScale.put(9,new int[]{670, 300});
        for (int i = 1; i <= 9; i++) {
            coordinatesRightScale.put(i, new int[]{520 + 50 * (i % 3), 200 + 50 * ((i - 1) / 3)});
            coordinatesLeftScale.put(i, new int[]{120 + 50 * (i % 3), 200 + 50 * ((i - 1) / 3)});
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(Color.orange);
        g2.fillRect(0, 0, getWidth(), getHeight());

        drawBalls(g2);
        drawScale(g2);

        drawAttempt(g2);
    }

    private void drawBalls(Graphics2D g2) {
        //distance between balls
        int distance = 20;
        for (Ball ball : field.getBalls()) {
            ballDraw(g2, ball, 30 + (ball.getRadius() + distance) * ball.getNumber(), 500);
        }
        int counterRight = 1;
        for (Ball ball : field.getScale().getRightSide()) {
            ballDraw(g2, ball, coordinatesRightScale.get(counterRight)[0], coordinatesRightScale.get(counterRight)[1]);
            counterRight++;
        }
        int counterLeft = 1;
        for (Ball ball : field.getScale().getLeftSide()) {
            ballDraw(g2, ball, coordinatesLeftScale.get(counterLeft)[0], coordinatesLeftScale.get(counterLeft)[1]);
            counterLeft++;
        }
    }

    private void ballDraw(Graphics2D g2, Ball ball, int x, int y) {
        g2.setColor(ballColor);
        ball.setShape(x, y);
        g2.fill(ball.getShape());
        g2.setColor(Color.white);
        g2.setFont(new Font("Arial", Font.BOLD, 14));
        g2.drawString(String.valueOf(ball.getNumber()), (int) ball.getShape().getCenterX() - 3, (int) ball.getShape().getCenterY() + 4);
    }

    private void drawScale(Graphics2D g2) {
        g2.setColor(Color.black);
        g2.fillPolygon(new int[]{350, 450, 400}, new int[]{400, 400, 350}, 3);
        if (field.getScale().getState() == State.EQUAL) g2.drawLine(200, 350, 600, 350);

        if (field.getScale().getState() == State.TORIGHT) g2.drawLine(200, 325, 600, 375);

        if (field.getScale().getState() == State.TOLEFT) g2.drawLine(200, 375, 600, 325);
    }


    private void drawAttempt(Graphics2D g2) {
        g2.setColor(Color.white);
        g2.setFont(new Font("Arial", Font.PLAIN, 20));
        g2.drawString("Attempts: " + String.valueOf(field.getAttempts()), 100, 100);
    }

    public Field getField() {
        return field;
    }
}
