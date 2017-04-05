import javax.sound.sampled.Line;
import java.awt.*;
import java.awt.geom.Line2D;

/**
 *  By Jonathan Bonilla, 500640200
 */
public class Edge extends GraphElement {

    /**
     * The private edge used in this class.
     */
    private Line2D.Double edge;

    /**
     * Collision size for the edge.
     */
    private final int COLLISION_SIZE = 4;

    /**
     * The terminal point positions for the edge.
     */
    private double x2, y2;

    // Default constructor
    public Edge() {
        super();
        x2 = 0;
        y2 = 0;
    }

    /**
     * Edge constructor. Initially sets the terminal point coordinates
     * to be the initial point coordinates.
     * @param x the x coordinate.
     * @param y the y coordinate.
     */
    public Edge(double x, double y) {
        super(x, y);
        x2 = getXPos();
        y2 = getYPos();
    }

    @Override
    /**
     * The draw method for the edge node.
     * @param g2 the Graphics2D to use.
     */
    void draw(Graphics2D g2) {
        edge = new Line2D.Double(getXPos(), getYPos(), x2, y2);
        g2.setColor(color);
        g2.draw(edge);
    }

    @Override
    /**
     * Sets the terminal point to xLoc and yLoc instead of the initial point.
     * @param xLoc the x location
     * @param yLoc the y location
     */
    public void moveTo(double xLoc, double yLoc) {
        this.x2 = xLoc;
        this.y2 = yLoc;
    }

    @Override
    /**
     * Checks if the edge was clicked.
     * @return if the edge intersects with the collision box for the mouse
     */
    boolean isClicked(double x, double y) {
        return edge.intersects(x - COLLISION_SIZE/2, y - COLLISION_SIZE/2, COLLISION_SIZE, COLLISION_SIZE);
    }

    @Override
    /**
     * Do not apply labels to edges.
     */
    boolean applyLabel() {
        return false;
    }
}
