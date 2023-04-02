package primer.taller;

import edu.princeton.cs.stdlib.StdDraw;

import java.awt.*;
import java.util.Random;

/**
 * This program try to simulate a screen protector
 *
 * @author Benjamín Miranda 21544970-k
 */

public class Main {

    private static final Random RANDOM = new Random();

    private static final double MIN_VALUE = -1.0;
    private static final double MAX_VALUE = 1.0;

    private static final Color DEFAULT_COLOR = Color.BLACK;
    private static final Color[] COLORS = {Color.RED, Color.BLUE, Color.ORANGE, Color.DARK_GRAY};
    private static final Color[] COLORS_ASSIGNED = new Color[6];

    private static int counter_colors = 0;

    private static final double LINE_DISTANCE = 0000000000000000.1;

    public static double initialX, initialY, finalX, finalY;
    public static double velocityVectorX = 0.032;
    public static double velocityVectorY = 0.016;

    public static void main(String[] args) {
        setScale();
        StdDraw.enableDoubleBuffering();
        generateLineRandom();
        StdDraw.show();

        run();
    }

    /**
     * Sets the x and y scale for the screen
     */

    public static void setScale() {
        StdDraw.setXscale(MIN_VALUE, MAX_VALUE);
        StdDraw.setYscale(MIN_VALUE, MAX_VALUE);
    }

    /**
     * Generate one line random in the screen with a color random
     */

    public static void generateLineRandom() {

        initialX = randomValue();
        initialY = randomValue();

        finalX = initialX + 0.5;
        finalY = initialY + 0.5;

        StdDraw.enableDoubleBuffering();

        Color color = selectRandomColorAndGet();

        assignColor(color);

        drawLineAndShow(
                initialX,
                initialY,
                finalX,
                finalY
        );

        generateParallelLines(5);

    }

    /**
     * Generate lines parallels to main line created random
     *
     * @param lines quantity lines
     */

    public static void generateParallelLines(int lines) {

        //double actualInitialX = initialX;
        double actualInitialY = initialY;

        //double actualFinalX = finalX;
        double actualFinalY = finalY;

        for (int i = 0; i < lines; i++) {
            actualInitialY = actualInitialY - LINE_DISTANCE;
            actualFinalY = actualFinalY - LINE_DISTANCE;

            Color color = getColorByIndex(1 + i);

            if (color == null) {
                color = selectRandomColorAndGet();
                assignColor(color);

                drawLineAndShow(initialX, actualInitialY, finalX, actualFinalY, color);
            } else {
                drawLineAndShow(initialX, actualInitialY, finalX, actualFinalY, color);
            }

        }

    }

    /**
     * Select a random color and return it
     *
     * @return a color random
     */

    public static Color selectRandomColorAndGet() {
        Color color = randomColor();
        StdDraw.setPenColor();

        return color;
    }

    /**
     * Assign a color to color assigned array
     *
     * @param color a color for saved
     */

    public static void assignColor(Color color) {
        COLORS_ASSIGNED[counter_colors] = color;
        counter_colors++;
    }

    /**
     * Get a random color
     *
     * @return a random color
     */

    public static Color randomColor() {
        return COLORS[RANDOM.nextInt(COLORS.length)];
    }

    /**
     * Get a color of array by the index
     *
     * @param index the position of array
     * @return the color searched of array
     */

    public static Color getColorByIndex(int index) {
        return COLORS_ASSIGNED[index];
    }

    /**
     * Get a random value in a interval
     *
     * @return a random value
     */

    public static double randomValue() {
        return MIN_VALUE + (MAX_VALUE - MIN_VALUE) * Math.random();
    }

    /**
     * Draw line in the screen and show it
     *
     * @param initialX the x component initial
     * @param initialY the y component initial
     * @param finalX   the x component final
     * @param finalY   the y component final
     */

    public static void drawLineAndShow(double initialX, double initialY,
                                       double finalX, double finalY) {
        drawLineAndShow(initialX, initialY, finalX, finalY, Color.BLACK);
    }

    /**
     * Draw line in the screen and show it
     *
     * @param initialX the x component initial
     * @param initialY the y component initial
     * @param finalX   the x component final
     * @param color    the color line
     */

    public static void drawLineAndShow(double initialX, double initialY,
                                       double finalX, double finalY,
                                       Color color) {

        StdDraw.setPenColor(color);
        StdDraw.line(initialX, initialY, finalX, finalY);
    }

    private static double changeDirectionVectorVelocity(double velocityVector, double newVelocity) {
        if (velocityVector > 0) {
            return -newVelocity;
        }
        return newVelocity;
    }

    /**
     * Run the main program
     */

    public static void run() {

        while (true) {

            if (Math.abs(initialX + velocityVectorX * initialX) > 1.0 ||
            Math.abs(initialX + velocityVectorX * initialX + 0.5) > 1.0) {
                velocityVectorX = -velocityVectorX;
                //velocityVectorY = -velocityVectorY;
            }

            if (Math.abs(initialY + velocityVectorY * initialY) > 1.0 ||
                    Math.abs(initialY + velocityVectorY * initialY + 0.5) > 1.0) {
                //velocityVectorX = -velocityVectorX;
                velocityVectorY = -velocityVectorY;
            }


/*
            if (Math.abs(finalX + velocityVectorY * finalX) > 1.0) {
                velocityVectorX = -velocityVectorX;
                //velocityVectorY = -velocityVectorY;
            }

            if (Math.abs(finalY + velocityVectorY * finalY) > 1.0) {
                //velocityVectorX = -velocityVectorX;
                velocityVectorY = -velocityVectorY;
            }
*/

            System.out.println("[***********]");
            System.out.println("Posision nueva A: (" + initialX + " , " + initialY + " )");
            System.out.println("Posicion nueva B: ( " + finalX + " , " + finalY + " )");
            System.out.println("Vector de velocidad (" + velocityVectorX + " , " + velocityVectorY + ")");
            System.out.println("[***********]");

            initialX = initialX + velocityVectorX * initialX;
            initialY = initialY + velocityVectorY * initialY;

            finalX = initialX + 0.5;
            finalY = initialY + 0.5;

          /*  finalX = finalX + velocityVectorX * finalX;
            finalY = finalY + velocityVectorY * finalY;
*/
            StdDraw.pause(20);

            StdDraw.clear();

            drawLineAndShow(initialX, initialY,
                    finalX, finalY, getColorByIndex(0));

            generateParallelLines(5);
            StdDraw.show();

        }
    }

}
