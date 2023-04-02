package primer.taller;

import edu.princeton.cs.stdlib.StdDraw;

import java.awt.*;

public class Test {

    private static final double MIN_VALUE = -1.0;
    private static final double MAX_VALUE = 1.0;

    public static void main(String[] args) {

        StdDraw.setXscale(MIN_VALUE, MAX_VALUE);
        StdDraw.setYscale(MIN_VALUE, MAX_VALUE);

        StdDraw.enableDoubleBuffering();

        double x = MIN_VALUE + (MAX_VALUE - MIN_VALUE) * Math.random();
        double y = MIN_VALUE + (MAX_VALUE - MIN_VALUE) * Math.random();

        double x_2 = MIN_VALUE + (MAX_VALUE - MIN_VALUE) * Math.random();
        double y_2 = MIN_VALUE + (MAX_VALUE - MIN_VALUE) * Math.random();

        StdDraw.setPenColor(Color.RED);

        double vx = 0.032;
        double vy = 0.012;

        double radius = 0.05;

        while (true) {

            if (Math.abs(x + vx) > 1.0 || Math.abs(x + vx + 0.5) > 1.0) {
                vx = -vx;
            }

            if (Math.abs(y + vy) > 1.0 || Math.abs(y + vy + 0.5) > 1.0) {
                vy = - vy;
            }

            x = x + vx;
            y = y + vy;

            StdDraw.clear();
            StdDraw.setPenColor(Color.RED);

            StdDraw.line(x, y, x + 0.5, y + 0.5);

            StdDraw.show();
            StdDraw.pause(20);

        }

    }

}
