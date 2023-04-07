package primer.taller;

import edu.princeton.cs.stdlib.StdDraw;

import java.awt.*;

public class Test2 {

    private static final double MIN_VALUE = -1.0;
    private static final double MAX_VALUE = 1.0;
    private static final double LINE_DISTANCE = 000000000.1;

    public static double x, y, xf, yf = 0;

    public static void main(String[] args) {

        StdDraw.setXscale(MIN_VALUE, MAX_VALUE);
        StdDraw.setYscale(MIN_VALUE, MAX_VALUE);

        StdDraw.enableDoubleBuffering();

        double velocityX = 0.0016;
        double velocityY = 0.0016;
        double velocityX1 = 0.0016;
        double velocityY2 = 0.0016;

        x = randomValue();
        y = randomValue();

        xf = x + 0.3;
        yf = y + 0.3;

        while (true) {

            if (Math.abs(x + velocityX) > 1.0 - 0.01) {
                velocityX = -velocityX;
            }

            if (Math.abs(xf + velocityX1) > 1.0 - 0.01) {
                velocityX1 = -velocityX1;
            }


            if (Math.abs(y + velocityY) > 1.0 - 0.01) {
                velocityY = -velocityY;
            }

            if (Math.abs(yf + velocityY2) > 1.0 - 0.01) {
                velocityY2 = -velocityY2;
            }

            x = x + velocityX;
            y = y + velocityY;

            xf = xf + velocityX1;
            yf = yf + velocityY2;


            StdDraw.clear();

            StdDraw.filledCircle(x, y ,0.01);
            StdDraw.filledCircle(xf, yf, 0.01);
            StdDraw.line(x, y, xf, yf);

            generateParallelLines(5);

            StdDraw.show();


        }

    }

    public static double randomValue() {
        return MIN_VALUE + (MAX_VALUE - MIN_VALUE) * Math.random();
    }

    public static void generateParallelLines(int lines) {

        double actualInitialY = y;
        double actualFinalY = yf;

        System.out.println(y);
        System.out.println(yf);

        //Iterate for every line and calculate the distance between a line and the next
        // subtract a distance defined for calculate the other lines

        for (int i = 0; i < lines; i++) {
            actualInitialY = actualInitialY - LINE_DISTANCE;
            actualFinalY = actualFinalY - LINE_DISTANCE;

            //Use the array colors for get the color in those line

            //Check if the color is null for set a random color for those line


            StdDraw.filledCircle(x, actualInitialY, 0.01);
            StdDraw.filledCircle(xf, actualFinalY, 0.01);

            StdDraw.setPenColor(Color.RED);

            StdDraw.line(x, actualInitialY, xf, actualFinalY);

            //Store the last line

            /*lastPositionInitialY = actualInitialY;
            lastPositionFinalY = actualFinalY;*/

        }

       /* lastPositionInitialX = initialX;
        lastPositionFinalX = finalX;
*/
    }

}
