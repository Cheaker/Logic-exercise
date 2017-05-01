package model;

import model.Ball;

import java.util.Comparator;

/**
 * Comparator for balls, compares by balls' number
 *
 * @author Cheaker on 21.04.2017.
 */
public class BallComparator implements Comparator<Ball> {
    @Override
    public int compare(Ball o1, Ball o2) {
        return o1.getNumber() - o2.getNumber();
    }
}
