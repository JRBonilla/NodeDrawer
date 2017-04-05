import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 *  By Jonathan Bonilla, 500640200
 */
public class EllipseNode extends GraphElement {
    /**
     * The width and height of the ellipse.
     */
    private final int ELLIPSE_WIDTH = 75;
    private final int ELLIPSE_HEIGHT = 25;

    /**
     * The x-shift and y-shift for the label (magic numbers
     * which position the label correctly onto the rectangle).
     */
    private final int LABEL_XSHIFT = 8;
    private final int LABEL_YSHIFT = 17;

    /**
     * The private ellipse used in this class.
     */
    private Ellipse2D ellipse;

    // Default constructor
    public EllipseNode() {
        super();
    }

    /**
     * EllipseNode constructor
     * @param x the x coordinate.
     * @param y the y coordinate.
     */
    public EllipseNode(double x, double y) {
        super(x, y);
    }

    @Override
    /**
     * The draw method for the ellipse node.
     * @param g2 the Graphics2D variable to use.
     */
    void draw(Graphics2D g2) {
        ellipse = new Ellipse2D.Double(getXPos(), getYPos(), ELLIPSE_WIDTH, ELLIPSE_HEIGHT);
        g2.setColor(color);
        g2.draw(ellipse);
        g2.drawString(label,(float) getXPos() + LABEL_XSHIFT, (float) getYPos() + LABEL_YSHIFT);
    }

    @Override
    /**
     * Returns if the ellipse was clicked at x and y.
     * @param x the x coordinate
     * @param y the y coordinate
     */
    boolean isClicked(double x, double y) {
        return ellipse.contains(x, y);
    }

    @Override
    /**
     * Labels are applicable to the ellipse node.
     */
    boolean applyLabel() {
        return true;
    }
}
