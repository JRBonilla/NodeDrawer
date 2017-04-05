import java.awt.*;

/**
 *  By Jonathan Bonilla, 500640200
 */
public class RectangleNode extends GraphElement {
    /**
     * The width and height of the rectangle.
     */
    private final int RECTANGLE_WIDTH = 75;
    private final int RECTANGLE_HEIGHT = 25;

    /**
     * The x-shift and y-shift for the label (magic numbers
     * which position the label correctly onto the rectangle).
     */
    private final int LABEL_XSHIFT = 8;
    private final int LABEL_YSHIFT = 17;

    /**
     * The private rectangle used in this class.
     */
    private Rectangle rectangle;

    /**
     * The default rectangle node constructor.
     */
    public RectangleNode() {
        super();
    }

    /**
     * RectangleNode constructor
     * @param x the x coordinate.
     * @param y the y coordinate.
     */
    public RectangleNode(double x, double y) {
        super(x, y);
    }

    @Override
    /**
     * The draw method for the rectangle node.
     * @param g2 the Graphics2D variable to use.
     */
    void draw(Graphics2D g2) {
        rectangle = new Rectangle((int) getXPos(), (int) getYPos(), RECTANGLE_WIDTH, RECTANGLE_HEIGHT);
        g2.setColor(color);
        g2.draw(rectangle);
        g2.drawString(label, (float) getXPos() + LABEL_XSHIFT, (float) getYPos() + LABEL_YSHIFT);
    }

    @Override
    /**
     * Returns if the rectangle was clicked at x and y.
     * @param x the x coordinate
     * @param y the y coordinate
     */
    boolean isClicked(double x, double y) {
        return rectangle.contains(x, y);
    }

    @Override
    /**
     * Labels are applicable to the rectangle node.
     */
    boolean applyLabel() {
        return true;
    }
}
