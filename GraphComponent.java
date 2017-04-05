import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

/**
 *  By Jonathan Bonilla, 500640200
 */
public class GraphComponent extends JComponent {

    /**
     * The primary ArrayList which holds all of the nodes and edges.
     */
    private ArrayList<GraphElement> graphElements;

    /**
     * An inner mouse listener class used only for the graph component
     */
    class ComponentMouseListener implements MouseListener {

        @Override
        public void mousePressed(MouseEvent e) {
            /**
             * If the mouse is pressed on the component, check what mode the
             * graph viewer is in, and if it isn't in default mode, add the appropriate
             * element to the component. However, if it is in default mode, then check if
             * any elements were selected.
             */
            if (GraphViewer.getGraphMode().equals("Rectangle")) addRectangle(e.getX(), e.getY());
            else if (GraphViewer.getGraphMode().equals("Ellipse")) addEllipse(e.getX(), e.getY());
            else if (GraphViewer.getGraphMode().equals("Edge")) addEdge(e.getX(), e.getY());
            else if (GraphViewer.getGraphMode().equals("Default")) checkGraphElementSelection(graphElements, e);
        }

        /**
         * Method which checks if any elements were selected on the component and
         * decides what to do with those elements.
         * @param graphElements The ArrrayList to look in.
         * @param event The mouse event to use.
         */
        public void checkGraphElementSelection(ArrayList<GraphElement> graphElements, MouseEvent event) {
            /**
             * Get the x coordinate and y coordinate from the mouse.
             */
            int x = event.getX();
            int y = event.getY();

            /**
             * Check if the left or right mouse button was clicked.
             */
            boolean isLeftButtonClick = SwingUtilities.isLeftMouseButton(event);
            boolean isRightButtonClick = SwingUtilities.isRightMouseButton(event);

            /**
             * If the left button was clicked, check if any elements were
             * selected.
             */
            if (isLeftButtonClick) {
                for (GraphElement graphElement : graphElements) {
                    // If the graph element was clicked and it wasn't previously
                    // selected, select it.
                    if (graphElement.isClicked(x, y) && !graphElement.selected) {
                        selectElement(graphElement);
                    }
                    // If it wasn't clicked and it was previously selected, deselect it.
                    else if (!(graphElement.isClicked(x, y)) && graphElement.selected) {
                        deselectElement(graphElement);
                    }
                }
            }
            /**
             * If the right button was clicked, check if any elements were selected,
             * and if so, remove them.
             */
            else if (isRightButtonClick) {
                removeElements(graphElements, event);
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            /**
             * If mouse button is released and the graph viewer is not in
             * default mode, then set the graph viewer to default mode.
             */
            if (!(GraphViewer.getGraphMode().equals("Default"))) {
                GraphViewer.setGraphMode("Default");
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {} //

        @Override
        public void mouseEntered(MouseEvent e) {} //

        @Override
        public void mouseExited(MouseEvent e) {} //

    }

    /**
     * An inner MouseMotionListener class which checks for mouse movement
     * on the graph component.
     */
    class ComponentMouseMotionListener implements MouseMotionListener {

        @Override
        public void mouseDragged(MouseEvent e) {
            /**
             * If the mouse is dragged in default mode, then check
             * if any nodes (not edges) were selected and if so, drag them.
             */
            if (GraphViewer.getGraphMode().equals("Default")) {
                for (GraphElement graphElement : graphElements) {
                    if (graphElement.selected && !(graphElement instanceof Edge)) {
                        graphElement.moveTo(e.getX(), e.getY());
                        repaint();
                    }
                }
            }
            /**
             * However, if the graph viewer is currently drawing edges, then move the last
             * edge created (aka the current one) and move it's end point, to the current
             * mouse position.
             */
            else if (GraphViewer.getGraphMode().equals("Edge")) {
                graphElements.get(graphElements.size() - 1).moveTo(e.getX(), e.getY());
                repaint();
            }
        }

        @Override
        public void mouseMoved(MouseEvent e) {} //
    }

    /**
     * The private ComponentMouseListener and ComponentMouseMotionListener
     * instance variables used this component.
     */
    private ComponentMouseListener componentMouseListener;
    private ComponentMouseMotionListener componentMouseMotionListener;

    /**
     * The GraphComponent constructor which initializes its instance variables,
     * and add the componentMouseListener and componentMouseMotionListener.
     */
    public GraphComponent() {
        graphElements = new ArrayList<GraphElement>();
        componentMouseListener = new ComponentMouseListener();
        componentMouseMotionListener = new ComponentMouseMotionListener();
        addMouseListener(componentMouseListener);
        addMouseMotionListener(componentMouseMotionListener);
    }

    /**
     * Method which adds a new rectangle to the component at x and y.
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public void addRectangle(double x, double y) {
        graphElements.add(new RectangleNode(x, y));
        repaint();
    }

    /**
     * Method which adds a new ellipse to the component at x and y.
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public void addEllipse(double x, double y) {
        graphElements.add(new EllipseNode(x, y));
        repaint();
    }

    /**
     * Method which adds a new edge to the component at x and y.
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public void addEdge(double x, double y) {
        graphElements.add(new Edge(x, y));
        repaint();
    }

    /**
     * Method which adds a label to the selected element.
     * @param label the label to add to the element.
     */
    public void addLabel(String label) {
        for (GraphElement graphElement : graphElements) {
            if (graphElement.selected && graphElement.applyLabel()) {
                graphElement.setLabel(label);
                repaint();
            }
        }
    }

    /**
     * Selects the specified GraphElement by changing its colour to blue
     * and setting selected to true.
     * @param graphElement the graph element to select.
     */
    public void selectElement(GraphElement graphElement) {
        graphElement.selected = true;
        graphElement.setColor(Color.BLUE);
        repaint();
    }

    /**
     * Deselects the specified GraphElement by changing its colour to black
     * and setting selected to false.
     * @param graphElement the graph element to deselect.
     */
    public void deselectElement(GraphElement graphElement) {
        graphElement.selected = false;
        graphElement.setColor(Color.BLACK);
        repaint();
    }

    /**
     * Checks if any graph elements were selected, and if so, removes them from the ArrayList.
     * @param graphElements the ArrayList to look in.
     * @param event the mouse event to use.
     */
    public void removeElements(ArrayList<GraphElement> graphElements, MouseEvent event) {
        for (int i = 0; i < graphElements.size(); i++) {
            GraphElement graphElement = graphElements.get(i);
            if (graphElement.isClicked(event.getX(), event.getY())) {
                graphElements.remove(i);
                repaint();
            }
        }
    }

    /**
     * Draws every element in the graphElements ArrayList.
     * @param g the graphics variable.
     */
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        for (GraphElement graphElement : graphElements) {
            graphElement.draw(g2);
        }
    }
}