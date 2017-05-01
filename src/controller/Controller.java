package controller;

import model.Ball;
import view.Panel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;

/**
 * Mouse controller
 *
 * @author Cheaker on 21.04.2017.
 */
public class Controller implements MouseListener {

    private Panel panel;

    public Controller(Panel panel) {
        this.panel = panel;

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println(e.getX() + " " + e.getY() + " " + e.getButton());
        handleClick(e.getX(), e.getY(), e.getButton());
        panel.repaint();
    }


    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    private void handleClick(int x, int y, int button) {
        outerBalls(x, y, button);
    }

    private void outerBalls(int x, int y, int button) {
        Iterator<Ball> iter = panel.getField().getBalls().iterator();
        while (iter.hasNext()) {
            Ball ball = iter.next();
            if (ball.getShape().contains(x, y)) {
                if (button == 1) {
                    panel.getField().getScale().getLeftSide().add(ball);
                    iter.remove();
                }
                if (button == 3) {
                    panel.getField().getScale().getRightSide().add(ball);
                    iter.remove();
                }
            }
        }
    }
}