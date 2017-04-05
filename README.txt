Jonathan Bonilla
500640200

Assignment #1

***How to use***
Click on either "Rectangle", "Ellipse", or "Edge" to tell the program what to draw. Then click anywhere to draw
the appropriate element. You may only draw one element at a time, meaning you must click "Rectangle", "Ellipse", or "Edge"
each time you want to draw an element.
Select an element by left clicking it and deselect it by clicking anywhere else.
You can drag a rectangle node or ellipse node by left clicking and holding it, and then dragging it to the desired location.
Edges cannot be dragged.
If you right click an element, it will delete that element.
If an element is selected and you press the label button, a label will be added to that element.

***Code Structure***
GraphViewer: The main class which constructs the JFrame, JButtons, JPanels and JTextFields for the program. Has an inner
action listener class for the buttons. As well, it contains a private GraphComponent. It also contains the graph mode
which tells the graph component what to draw.
GraphComponent: Draws everything on the screen. Handles mouse input and mouse movement. Handles selection, deselection,
and removal of elements. Additionally contains an inner MouseListener and MouseMotionListener.
RectangleNode: The rectangle node class.
EllipseNode: The ellipse node class.
Edge: The edge class.
GraphElement: The super class for RectangleNode, EllipseNode, and Edge.

***Errors***
If an element is selected on top of another element, and the first is dragged, then both are dragged and selected.
But if you select a corner of one of the elements it deselects the other.