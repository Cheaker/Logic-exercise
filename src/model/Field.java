package model;

import java.util.ArrayList;
import java.util.List;

/**
 * model.Field where the whole exercise happens
 *
 * @author Cheaker on 21.04.2017.
 */
public class Field {

    /**
     * Amount of the balls
     */
    public static final int BALLS_COUNT = 9;

    /**
     * Number of attempts
     */
    private int attempts = 0;

    /**
     * Number of deviated ball
     */
    private int deviatedBall;

    private Scale scale;

    private List<Ball> balls;

    public Field() {
        this.balls = new ArrayList<>();

        this.scale = new Scale();

        for (int i = 1; i <= BALLS_COUNT; i++) {
            balls.add(new Ball(i));
        }

        this.deviatedBall = (int) (Math.random() * BALLS_COUNT + 1);
        getBallByNumber(deviatedBall).setWeight(randomNumber());
    }

    public int getDeviatedBall() {
        return deviatedBall;
    }

    public int getAttempts() {
        return attempts;
    }

    public void increaseAttempt() {
        attempts++;
    }

    public void resetAttempt() {
        attempts = 0;
    }
    /**
    * Getter for scale
    */
    public Scale getScale() {
        return scale;
    }

    public List<Ball> getBalls() {
        return balls;
    }

    /**
     * Method to get random weight for deviated ball
     * Returns randomly 1 or 3
     *
     * @return 1 or 3
     */
    public int randomNumber() {
        return Math.random() - 0.5 < 0 ? 1 : 3;
    }

    public Ball getBallByNumber(int number) {
        for (Ball ball : balls) {
            if (ball.getNumber() == number) {
                return ball;
            }
        }
        return null;
    }
}
