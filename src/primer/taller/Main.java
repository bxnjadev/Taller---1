package primer.taller;

import edu.princeton.cs.stdlib.StdDraw;

import java.awt.*;

public class Main {

    private static final double MIN_VALUE = -1.0;
    private static final double MAX_VALUE = 1.0;
    private static final Color[] COLORS = {Color.RED, Color.BLUE, Color.CYAN, Color.DARK_GRAY}

    public static void main(String[] args) {


    }

    public static void setScale() {
        StdDraw.setXscale(MIN_VALUE, MAX_VALUE);
        StdDraw.setYscale(MIN_VALUE, MAX_VALUE);
    }

    public static void generateLineRandom() {

        double initialX = randomValue();
        double initialY = randomValue();

        double finalX = randomValue();
        double finalY = randomValue();



    }

    public static double randomValue() {
        return MIN_VALUE + (MAX_VALUE - MIN_VALUE) * Math.random();
    }

    public static void drawLineAndShow(int initialX, int initialY,
                                       int finalX, int finalY) {
        StdDraw.line(initialX, initialY, finalX, finalY);
        StdDraw.show();
    }

}
