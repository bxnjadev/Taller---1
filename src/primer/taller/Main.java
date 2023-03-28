package primer.taller;

import edu.princeton.cs.stdlib.StdDraw;

import java.awt.*;
import java.util.Random;

public class Main {

    private static final Random RANDOM = new Random();

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

        StdDraw.setPenColor(randomColor());
        drawLineAndShow(
                initialX,
                initialY,
                finalX,
                finalY
        );

    }

    public static Color randomColor() {
        return COLORS[RANDOM.nextInt(COLORS.length)];
    }

    public static double randomValue() {
        return MIN_VALUE + (MAX_VALUE - MIN_VALUE) * Math.random();
    }

    public static void drawLineAndShow(double initialX, double  initialY,
                                       double finalX, double finalY) {
        StdDraw.line(initialX, initialY, finalX, finalY);
        StdDraw.show();
    }

}
