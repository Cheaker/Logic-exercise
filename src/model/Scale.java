package model;

import model.Ball;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * model.Scale for balls
 *
 * @author Cheaker on 21.04.2017.
 */
public class Scale {
    private State state;

    private List<Ball> leftSide;
    private List<Ball> rightSide;

    public Scale() {
        this.state = State.EQUAL;
        this.leftSide = new ArrayList<>();
        this.rightSide = new ArrayList<>();
    }

    public void checkWeight() {
        int leftWeight = 0;
        int rightWeight = 0;
        for (Ball ball : leftSide) {
            leftWeight += ball.getWeight();
        }
        for (Ball ball : rightSide) {
            rightWeight += ball.getWeight();
        }
        if (leftWeight > rightWeight)
            state = State.TOLEFT;
        else if (rightWeight > leftWeight)
            state = State.TORIGHT;
        else state = State.EQUAL;
        System.out.println("left side " + leftWeight + "  rigth side " + rightWeight);
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public List<Ball> getLeftSide() {
        return leftSide;
    }

    public List<Ball> getRightSide() {
        return rightSide;
    }
}
