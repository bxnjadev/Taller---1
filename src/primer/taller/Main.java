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
    public static double lastPositionInitialX, lastPositionInitialY, lastPositionFinalX, lastPositionFinalY;

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

        //Generate the lines random

        initialX = randomValue();
        initialY = randomValue();

        finalX = initialX + 0.5;
        finalY = initialY + 0.5;

        //Enabled double buffering for avoid flicker

        StdDraw.enableDoubleBuffering();

        //Using method selectRandomColorAndGet for get a random color and added to color array

        Color color = selectRandomColorAndGet();

        assignColor(color);

        //Draw line in the screen

        drawLine(
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

            if (color == null) {
                color = selectRandomColorAndGet();
                assignColor(color);

                drawLine(initialX, actualInitialY, finalX, actualFinalY, color);
            } else {
                drawLine(initialX, actualInitialY, finalX, actualFinalY, color);
            }

            //Store the last line

            lastPositionInitialY = actualInitialY;
            lastPositionFinalY = actualFinalY;

        }

        lastPositionInitialX = initialX;
        lastPositionFinalX = finalX;

    }

    /**
     * Select a random color and return it
     *
     * @return a color random
     */

    public static Color selectRandomColorAndGet() {

        //As said in the javadoc get a random and set in the pen color and return it

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

    public static void drawLine(double initialX, double initialY,
                                       double finalX, double finalY) {
        drawLine(initialX, initialY, finalX, finalY, DEFAULT_COLOR);
    }

    /**
     * Draw line in the screen and show it
     *
     * @param initialX the x component initial
     * @param initialY the y component initial
     * @param finalX   the x component final
     * @param color    the color line
     */

    public static void drawLine(double initialX, double initialY,
                                       double finalX, double finalY,
                                       Color color) {

        StdDraw.setPenColor(color);
        StdDraw.line(initialX, initialY, finalX, finalY);
    }

    /**
     * Run the main program
     */

    public static void run() {

        while (true) {

            //Check if the coordinate x is out the screen, if the check is true then the vector velocity is changed

            if (Math.abs(initialX + velocityVectorX) > 1.0 ||
                    Math.abs(initialX + velocityVectorX + 0.5) > 1.0) {
                velocityVectorX = -velocityVectorX;
            }

            //Check if the coordinate y is out the screen, if the check is true then the vector velocity is changed

            if (Math.abs(initialY + velocityVectorY ) > 1.0 ||
                    Math.abs(initialY + velocityVectorY + 0.5) > 1.0 ||
            lastPositionInitialY < -1.0 || lastPositionFinalY < -1.0) {
                velocityVectorY = -velocityVectorY;
            }


            //Sum the new position

            initialX = initialX + velocityVectorX;
            initialY = initialY + velocityVectorY;

            finalX = initialX + 0.5;
            finalY = initialY + 0.5;

            StdDraw.pause(20);

            StdDraw.clear();

            drawLine(initialX, initialY,
                    finalX, finalY, getColorByIndex(0));

            generateParallelLines(5);
            StdDraw.show();

        }
    }

}
