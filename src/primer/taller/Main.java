package primer.taller;

import edu.princeton.cs.stdlib.StdDraw;

import java.awt.Color;

/**
 * This program try to simulate a screen protector
 *
 * @author Benjamín Miranda 21544970-k
 */

public class Main {

    private static final String TITLE = "Screen Saver";
    private static final double MIN_VALUE = -1.0;
    private static final double MAX_VALUE = 1.0;

    private static final double VELOCITY_MIN = 0.005;
    private static final double VELOCITY_MAX = 0.006;

    private static final Color DEFAULT_COLOR = Color.BLACK;
    private static final Color[] COLORS_ASSIGNED = {
            Color.RED, Color.BLUE, Color.ORANGE, Color.DARK_GRAY, Color.YELLOW, Color.PINK
    };

    private static final double LINE_DISTANCE = 000000000000000000000000.1;
    private static final double CIRCLE_RADIUS = 0.01;

    public static double initialPositionX, initialPositionY, finalPositionX, finalPositionY;
    public static double lastPositionInitialX, lastPositionInitialY, lastPositionFinalX, lastPositionFinalY;

    public static double velocityVectorX, velocityVectorY, velocityVectorX2, velocityVectorY2;

    public static void main(String[] args) {
        setScale();
        StdDraw.enableDoubleBuffering();
        assignVelocityRandom();
        generateLineRandom();
        StdDraw.show();
        StdDraw.setTitle(TITLE);

        run();
    }

    /**
     * Sets the x and y scale for the screen
     */

    private static void setScale() {
        StdDraw.setXscale(MIN_VALUE, MAX_VALUE);
        StdDraw.setYscale(MIN_VALUE, MAX_VALUE);
    }

    /**
     * Set a random velocity for the all velocity vector
     */

    private static void assignVelocityRandom() {

        double velocityRandom = randomValueInInterval(VELOCITY_MIN, VELOCITY_MAX);

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

        initialPositionX = randomValueInInterval(0.3, 1);
        initialPositionY = randomValueInInterval(0.3, 1);

        finalPositionX =  randomValueInInterval(0.3, 1);
        finalPositionY = randomValueInInterval(0.3, 1);

        //Enabled double buffering for avoid flicker

        StdDraw.enableDoubleBuffering();

        //Using method selectRandomColorAndGet for get a random color and added to color array

        //Draw line in the screen

        drawLineAndPoint(
                initialPositionX,
                initialPositionY,
                finalPositionX,
                finalPositionY
        );

        generateParallelLines(5);

    }

    /**
     * Generate lines parallels to main line created random
     *
     * @param lines quantity lines
     */

    private static void generateParallelLines(int lines) {

        double actualInitialY = initialPositionY;
        double actualFinalY = finalPositionY;

        //Iterate for every line and calculate the distance between a line and the next
        // subtract a distance defined for calculate the other lines

        for (int i = 0; i < lines; i++) {
            actualInitialY = actualInitialY - LINE_DISTANCE;
            actualFinalY = actualFinalY - LINE_DISTANCE;

            //Use the array colors for get the color in those line

            Color color = getColorByIndex(1 + i);

            //Check if the color is null for set a random color for those line

            drawLineAndPoint(initialPositionX, actualInitialY, finalPositionX, actualFinalY, color);

            //Store the last line

            lastPositionInitialY = actualInitialY;
            lastPositionFinalY = actualFinalY;

        }

        lastPositionInitialX = initialPositionX;
        lastPositionFinalX = finalPositionX;

    }

    /**
     * Get a color of array by the index
     *
     * @param index the position of array
     * @return the color searched of array
     */

    private static Color getColorByIndex(int index) {
        return COLORS_ASSIGNED[index];
    }

    /**
     * Get a random value in a interval
     *
     * @return a random value
     */

    private static double randomValueInInterval(double minValue, double maxValue) {
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

    private static void drawLineAndPoint(double initialX, double initialY,
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

    private static void drawLineAndPoint(double initialX, double initialY,
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

    private static void run() {

        while (true) {

            //Check if the coordinate x is out the screen, if the check is true then the vector velocity is changed

            if (Math.abs(initialPositionX + velocityVectorX) > 1.0) {
                velocityVectorX = -velocityVectorX;
            }

            if (Math.abs(finalPositionX + velocityVectorX2) > 1.0) {
                velocityVectorX2 = -velocityVectorX2;
            }

            if (Math.abs(initialPositionY + velocityVectorY) > 1.0 ||
                    Math.abs(lastPositionInitialY + velocityVectorY) > 1.0) {
                velocityVectorY = -velocityVectorY;
            }

            if (Math.abs(finalPositionY + velocityVectorY2) > 1.0 ||
                    Math.abs(lastPositionFinalY + velocityVectorY2) > 1.0) {
                velocityVectorY2 = -velocityVectorY2;
            }

            //Check if the coordinate y is out the screen, if the check is true then the vector velocity is changed

            initialPositionX = initialPositionX + velocityVectorX;
            initialPositionY = initialPositionY + velocityVectorY;

            finalPositionX = initialPositionX + velocityVectorX2;
            finalPositionY = finalPositionY + velocityVectorY2;

            StdDraw.clear();

            drawLineAndPoint(initialPositionX, initialPositionY,
                    finalPositionX, finalPositionY, getColorByIndex(0));

            generateParallelLines(5);
            StdDraw.show();

        }
    }

}
