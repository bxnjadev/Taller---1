package primer.taller;

import edu.princeton.cs.stdlib.StdDraw;

import java.awt.*;
import java.util.Random;

public class Main {

    private static final Random RANDOM = new Random();

    private static final double MIN_VALUE = -1.0;
    private static final double MAX_VALUE = 1.0;
    private static final Color[] COLORS = {Color.RED, Color.BLUE, Color.ORANGE, Color.DARK_GRAY};
    private static final double LINE_DISTANCE = 00000000.1;

    public static double initialX, initialY, finalX, finalY;

    public static void main(String[] args) {
        setScale();
        generateLineRandom();
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

        generateParallelLines(4);

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

    public static void selectRandomColor(){
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
        StdDraw.line(initialX, initialY, finalX, finalY);
        StdDraw.show();
    }

}
