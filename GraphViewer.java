import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *  By Jonathan Bonilla, 500640200
 */
public class GraphViewer {

    /**
     * Static string which stores the current mode that the graph viewer is in.
     * The graph mode being either default for dragging and selection, or one of the
     * three drawing modes: rectangle, ellipse, or edge. The graph mode can only be one
     * of these at any given moment, and is what tells the graph component what to do
     * with mouse inputs.
     */
    private static String graphMode;

    /**
     * Main method
     * @param args console arguments
     */
    public static void main(String[] args) {
        // Frame dimensions
        final int FRAME_WIDTH = 700;
        final int FRAME_HEIGHT = 500;

        // Initially set the graph viewer mode to default.
        graphMode = "Default";

        // The graph component for this class.
        GraphComponent graphComponent = new GraphComponent();

        // Initialize all the buttons for this graph viewer.
        JButton rectangleButton = new JButton("Rectangle");
        JButton ellipseButton = new JButton("Ellipse");
        JButton edgeButton = new JButton("Edge");
        JButton labelButton = new JButton("Label");

        // Initialize the text field for the graph viewer
        // and set its size to 10.
        JTextField labelTextField = new JTextField(10);

        /**
         * The three JPanels for the graph viewer. The top panel keeps the top buttons
         * (rectangle, ellipse, and edge) and the bottom panel keeps the bottom label button
         * and text field. The main panel combines the two panels.
         */
        JPanel mainPanel = new JPanel();
        JPanel topPanel = new JPanel();
        JPanel bottomPanel = new JPanel();

        /**
         * Inner ActionListener class for all buttons.
         */
        class ButtonListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent event) {
                // String which stores the properties of the button that was clicked.
                String buttonText = event.getSource().toString();

                // Set the graph mode to the appropriate mode based on which button was pressed.
                // However, if the label button was pressed, tell the graph component to add
                // the label to element.
                if (buttonText.contains("Rectangle")) setGraphMode("Rectangle");
                else if (buttonText.contains("Ellipse")) setGraphMode("Ellipse");
                else if (buttonText.contains("Edge")) setGraphMode("Edge");
                else if (buttonText.contains("Label")) graphComponent.addLabel(labelTextField.getText());
                else setGraphMode("Default");
            }
        }

        // The action listener for this class.
        ButtonListener buttonListener = new ButtonListener();

        // Setup the main frame.
        JFrame frame = new JFrame();
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setTitle("Graph Draw");
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        mainPanel.setLayout(new BorderLayout()); // Set the layout of the main panel.

        frame.add(graphComponent, BorderLayout.CENTER); // Add the component to the frame.
        frame.add(mainPanel, BorderLayout.NORTH);       // Add the main panel to the frame.

        // Add the main buttons to top panel.
        topPanel.add(rectangleButton);
        topPanel.add(ellipseButton);
        topPanel.add(edgeButton);

        // Add the label button and field to the bottom panel.
        bottomPanel.add(labelButton);
        bottomPanel.add(labelTextField);

        // Add the top panel and bottom panel to main panel.
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Add the action listeners to the buttons.
        rectangleButton.addActionListener(buttonListener);
        ellipseButton.addActionListener(buttonListener);
        edgeButton.addActionListener(buttonListener);
        labelButton.addActionListener(buttonListener);
    }

    // Static string which simply returns the current graph mode.
    public static String getGraphMode() {
        return graphMode;
    }

    /**
     * Public static method which allows other classes to change the current graph viewer mode.
     * @param newGraphMode the new graph mode for the graph viewer.
     */
    public static void setGraphMode(String newGraphMode) {
        // If the graph mode is either rectangle, ellipse, edge, or default, set graphMode to the new graph mode.
        // Otherwise, reset it to default.
        if (newGraphMode.equals("Rectangle") || newGraphMode.equals("Ellipse") || newGraphMode.equals("Edge") || newGraphMode.equals("Default")) {
            graphMode = newGraphMode;
        } else {
            graphMode = "Default";
        }
    }
}