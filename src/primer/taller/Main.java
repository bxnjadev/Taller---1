package primer.taller;

import edu.princeton.cs.stdlib.StdDraw;

import java.awt.*;
import java.util.Random;
import java.util.TreeMap;

public class Main {

    private static final Random RANDOM = new Random();

    private static final double MIN_VALUE = -1.0;
    private static final double MAX_VALUE = 1.0;
    private static final Color[] COLORS = {Color.RED, Color.BLUE, Color.ORANGE, Color.DARK_GRAY};
    private static final double LINE_DISTANCE = 0000000000000000.1;

    public static double initialX, initialY, finalX, finalY;
    public static double velocityVectorX, velocityVectorY = 0.008;

    public static void main(String[] args) {
        setScale();
        StdDraw.enableDoubleBuffering();
        generateLineRandom();

        run();
    }

    public static void setScale() {
        StdDraw.setXscale(MIN_VALUE, MAX_VALUE);
        StdDraw.setYscale(MIN_VALUE, MAX_VALUE);
    }

    public static void generateLineRandom() {

        initialX = randomValue();
        initialY = randomValue();

        finalX = randomValue();
        finalY = randomValue();

        selectRandomColor();

        drawLineAndShow(
                initialX,
                initialY,
                finalX,
                finalY
        );

        generateParallelLines(5);

    }

    public static void generateParallelLines(int lines) {

        //double actualInitialX = initialX;
        double actualInitialY = initialY;

        //double actualFinalX = finalX;
        double actualFinalY = finalY;

        for (int i = 0; i < lines; i++) {
            actualInitialY = actualInitialY - LINE_DISTANCE;
            actualFinalY = actualFinalY - LINE_DISTANCE;
            selectRandomColor();


            drawLineAndShow(initialX, actualInitialY, finalX, actualFinalY);
        }

    }

    public static void selectRandomColor() {
        StdDraw.setPenColor(randomColor());
    }

    public static Color randomColor() {
        return COLORS[RANDOM.nextInt(COLORS.length)];
    }

    public static double randomValue() {
        return MIN_VALUE + (MAX_VALUE - MIN_VALUE) * Math.random();
    }

    public static void drawLineAndShow(double initialX, double initialY,
                                       double finalX, double finalY) {

        StdDraw.clear();

        StdDraw.line(initialX, initialY, finalX, finalY);
        StdDraw.show();
    }

    public static void run() {
        while (true) {

            if (Math.abs(initialX + velocityVectorX * initialX) > 1.0) {
                velocityVectorX = -velocityVectorX;
            }

            if (Math.abs(initialY + velocityVectorY * initialY) > 1.0) {
                velocityVectorY = -velocityVectorY;
            }

            initialX = initialX + velocityVectorX * initialX;
            initialY = initialY + velocityVectorY * initialY;

           /* finalX = finalX + velocityVectorX * finalX;
            finalY = finalY + velocityVectorY * finalY;
*/
            drawLineAndShow(initialX, initialY,
                    finalX, finalY);

            //generateParallelLines(5);

            StdDraw.pause(20);

        }
    }

}
