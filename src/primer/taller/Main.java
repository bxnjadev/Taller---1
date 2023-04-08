package primer.taller;

import edu.princeton.cs.stdlib.StdDraw;

import java.awt.Color;
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
    private static final Color[] COLORS_ASSIGNED = {
            Color.RED, Color.BLUE, Color.ORANGE, Color.DARK_GRAY, Color.YELLOW, Color.PINK
    };

    private static int counter_colors = 0;

    private static final double LINE_DISTANCE = 000000000000000000000000.1;
    private static final double CIRCLE_RADIUS = 0.01;

    public static double initialX, initialY, finalX, finalY;
    public static double lastPositionInitialX, lastPositionInitialY, lastPositionFinalX, lastPositionFinalY;

    public static double velocityVectorX, velocityVectorY, velocityVectorX2, velocityVectorY2;

    public static void main(String[] args) {
        setScale();
        StdDraw.enableDoubleBuffering();
        assignVelocityRandom();
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
     * Set a random velocity for the all velocity vector
     */

    public static void assignVelocityRandom() {

        double velocityRandom = randomValueInInterval(0.001, 0.008);

        velocityVectorX = velocityRandom;
        velocityVectorX2 = velocityRandom;
        velocityVectorY = velocityRandom;
        velocityVectorY2 = velocityRandom;

    }

    /**
     * Generate one line random in the screen with a color random
     */

    public static void generateLineRandom() {

        //Generate the lines random

        initialX = randomValueInInterval(MIN_VALUE, MAX_VALUE);
        initialY = randomValueInInterval(MIN_VALUE, MAX_VALUE);

        finalX = initialX + 0.3;
        finalY = initialY + 0.3;

        //Enabled double buffering for avoid flicker

        StdDraw.enableDoubleBuffering();

        //Using method selectRandomColorAndGet for get a random color and added to color array

        //Draw line in the screen

        drawLineAndPoint(
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

        double actualInitialY = initialY;
        double actualFinalY = finalY;

        //Iterate for every line and calculate the distance between a line and the next
        // subtract a distance defined for calculate the other lines

        for (int i = 0; i < lines; i++) {
            actualInitialY = actualInitialY - LINE_DISTANCE;
            actualFinalY = actualFinalY - LINE_DISTANCE;

            //Use the array colors for get the color in those line

            Color color = getColorByIndex(1 + i);

            //Check if the color is null for set a random color for those line

            drawLineAndPoint(initialX, actualInitialY, finalX, actualFinalY, color);

            //Store the last line

            lastPositionInitialY = actualInitialY;
            lastPositionFinalY = actualFinalY;

        }

        lastPositionInitialX = initialX;
        lastPositionFinalX = finalX;

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

    public static double randomValueInInterval(double minValue, double maxValue) {
        return  minValue + (maxValue - minValue) * Math.random();
    }

    /**
     * Draw line in the screen and show it
     *
     * @param initialX the x component initial
     * @param initialY the y component initial
     * @param finalX   the x component final
     * @param finalY   the y component final
     */

    public static void drawLineAndPoint(double initialX, double initialY,
                                        double finalX, double finalY) {
        drawLineAndPoint(initialX, initialY, finalX, finalY, DEFAULT_COLOR);
    }

    /**
     * Draw line and a point in the screen and show it
     *
     * @param initialX the x component initial
     * @param initialY the y component initial
     * @param finalX   the x component final
     * @param color    the color line
     */

    public static void drawLineAndPoint(double initialX, double initialY,
                                        double finalX, double finalY,
                                        Color color) {

        StdDraw.setPenColor(color);

        StdDraw.filledCircle(initialX, initialY, CIRCLE_RADIUS);
        StdDraw.filledCircle(finalX, finalY, CIRCLE_RADIUS);
        StdDraw.line(initialX, initialY, finalX, finalY);
    }

    /**
     * Run the main program
     */

    public static void run() {

        while (true) {

            //Check if the coordinate x is out the screen, if the check is true then the vector velocity is changed

            if (Math.abs(initialX + velocityVectorX) > 1.0) {
                velocityVectorX = -velocityVectorX;
            }

            if (Math.abs(finalX + velocityVectorX) > 1.0) {
                velocityVectorX2 = -velocityVectorX2;
            }

            if (Math.abs(initialY + velocityVectorY) > 1.0 ||
                    Math.abs(lastPositionInitialY + velocityVectorY) > 1.0) {
                velocityVectorY = -velocityVectorY;
            }

            if (Math.abs(finalY + velocityVectorY) > 1.0 ||
                    Math.abs(lastPositionFinalY + velocityVectorY2) > 1.0) {
                velocityVectorY2 = -velocityVectorY2;
            }

            //Check if the coordinate y is out the screen, if the check is true then the vector velocity is changed

            initialX = initialX + velocityVectorX;
            initialY = initialY + velocityVectorY;

            finalX = finalX + velocityVectorX2;
            finalY = finalY + velocityVectorY2;

            StdDraw.clear();

            drawLineAndPoint(initialX, initialY,
                    finalX, finalY, getColorByIndex(0));

            generateParallelLines(5);
            StdDraw.pause(10);

            StdDraw.show();

        }
    }

}
