package model;

import java.awt.geom.Ellipse2D;

/**
 * Balls for the field
 *
 * @author Cheaker on 21.04.2017.
 */
public class Ball {
    /**
     * The imagine weight of ball: 2 by default (same as another), 1 for lighter, 3 for heavier
     */
    private int weight = 2;

    private Ellipse2D.Double circle;

    private int number;

    private int radius = 50;

    public Ball(int number) {
        this.number = number;
    }

    public int getRadius() {
        return radius;
    }

    public int getNumber() {
        return number;
    }

    public Ellipse2D.Double getShape() {
        return circle;
    }

    public void setShape(int x, int y) {
        circle = new Ellipse2D.Double(x, y, radius, radius);
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
