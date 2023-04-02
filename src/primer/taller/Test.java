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

        double vx = 0.032;
        double vy = 0.012;

        double radius = 0.05;

        while (true) {

            if (Math.abs(x + vx) > 1.0 || Math.abs(x + vx + 2) > 1.0) {
                vx = -vx;
            }

            if (Math.abs(y + vy) > 1.0 || Math.abs(x + vx + 2) > 1.0) {
                vy = - vy;
            }

            x = x + vx;
            y = y + vy;

            StdDraw.clear();
            StdDraw.setPenColor(Color.RED);

            StdDraw.line(x, y, x + 2, y + 2);

            StdDraw.show();
            StdDraw.pause(20);

        }

    }

}
