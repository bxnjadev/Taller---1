package primer.taller;

import edu.princeton.cs.stdlib.StdDraw;

import java.awt.*;

public class Test {

    private static final double MIN_VALUE = -1.0;
    private static final double MAX_VALUE = 1.0;

    public static double velocity = 00.12;
    public static double xfa, x0a, y0, x0, xf, yf = 0;

    public static void generateSpinEffect() {
        while (true) {

            double angleOne = 0;
            double angleTwo = 360;

            while (true) {

                System.out.println("****");
                System.out.println("xf: " + xf);
                System.out.println("yf: " + yf);
                System.out.println("****");


                angleOne = angleOne + 0000.1;
                angleTwo = angleTwo - 0000.1;
                //double angleInRadian = Math.toRadians(angle);

                System.out.println("----------");

                System.out.println("Angle: " + Math.toDegrees(angleOne));
                System.out.println(0.1 * Math.cos(angleOne));
                System.out.println(0.1 * Math.sin(angleOne));
                System.out.println("---------");

                double xf_a = xf + 0.1 * Math.cos(angleOne);
                double yf_a = yf + 0.1 * Math.sin(angleOne);

                double x0_a = x0 + 0.1 * Math.cos(angleTwo);
                double y0_a = y0 + 0.1 * Math.sin(angleTwo);

                StdDraw.clear();
                StdDraw.line(x0_a, y0_a, xf_a, yf_a);

                StdDraw.pause(20);

            }
        }
    }

    public static void main(String[] args) {

        StdDraw.setXscale(MIN_VALUE, MAX_VALUE);
        StdDraw.setYscale(MIN_VALUE, MAX_VALUE);

        double x0 = randomValue();
        double y0 = randomValue();

        double xf = x0 + 0.1;
        double yf = y0 + 0.1;

        StdDraw.line(x0, y0, xf, yf);

        StdDraw.pause(60);

        while (true) {

            if (Math.abs(x0 + velocity) > 1.0 || Math.abs(xf + velocity) > 1.0) {
                velocity = -velocity;
            }

            if (Math.abs(y0 + velocity) > 1.0 || Math.abs(yf + velocity) > 1.0) {
                velocity = -velocity;
            }

            x0 = x0 + velocity;
            y0 = y0 + velocity;

            xf = x0 + 0.5;
            yf = y0 + 0.5;

            StdDraw.clear();
            StdDraw.line(x0, y0, xf, yf);
            StdDraw.show();

        }


    }

    public static double randomValue() {
        return MIN_VALUE + (MAX_VALUE - MIN_VALUE) * Math.random();
    }

}
