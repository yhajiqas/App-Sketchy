package Sketchy;

import java.util.ArrayList;
import java.util.Stack;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/*
 * This is the main class where the entire program is run. 
 * uses 3 array-lists of SketchyShape, CurvedLine, and Savables as its data structure.
 * uses 2 stacks, undo and redo stacks, for undoin/redoing purposes.
 * handles the MouseEvent by using a mouse-handler.
 * has the following methods. 
 */
public class Sketchy {
	private Pane _pane;
	private CurvedLine _curvedline;
	private int _num;
	private SketchyShape _selected;
	private SketchyShape _target;
	private ArrayList<SketchyShape> _shape;
	private ArrayList<CurvedLine> _lines;
	private ArrayList<Savables> _savables;
	private Stack<Command> _undo;
	private Stack<Command> _redo;
	private int _r;
	private int _g;
	private int _b;
	private int _z;
	private int _z1;
	private int _u;
	private int _u1;

	/*
	 * This is the constructor of Sketchy. It uses a canvas (pane) to
	 * graphically illustrate the shapes and lines. It also uses an instance of
	 * undo and redo stacks as well as 3 array-lists shape, lines, and savables.
	 * Utilizes a mouse-handler to perform the mouse events i.e. resize,
	 * translate, rotation etc.
	 */
	public Sketchy(Pane pane) {
		_pane = pane;
		_shape = new ArrayList<SketchyShape>();
		_lines = new ArrayList<CurvedLine>();
		_savables = new ArrayList<Savables>();
		_undo = new Stack<Command>();
		_redo = new Stack<Command>();
		MouseHandler myMouseHandler = new MouseHandler();
		_pane.addEventHandler(MouseEvent.MOUSE_PRESSED, myMouseHandler);
		_pane.addEventHandler(MouseEvent.MOUSE_DRAGGED, myMouseHandler);
		_pane.setFocusTraversable(true);

	}

	/*
	 * returns the rgb values which will be used in the mouse-handler as well as
	 * control class.
	 */
	public int getRValue() {
		return _r;
	}

	public int getGValue() {
		return _g;
	}

	public int getBValue() {
		return _b;
	}

	/*
	 * sets the rgb values. is used by the control class, whenever the color
	 * sliders change.
	 */
	public void setRGBValue(int r, int g, int b) {
		_r = r;
		_g = g;
		_b = b;
	}

	/*
	 * checks to see if a shape is selected and if the shape list is empty.
	 * removes the selected shape from canvas, shape list and savables list.
	 * instantiates a new sketchyshape (_target) which is essentially a copy of
	 * the selected shape, and is used to reproduce the selected shape upon
	 * undoing in Delete Command. saves the index of the selected shape to be
	 * later used in Delete class, for the purpose of correct layering.
	 */
	public void removeSelected() {
		if (_shape.isEmpty() == false && _selected.getStroke() != null) {
			_u = _shape.indexOf(_selected);
			_u1 = _savables.indexOf(_selected);
			_target = _shape.get(_u);
			_pane.getChildren().remove(_shape.get(_u).getNode());
			_shape.remove(_shape.get(_u));
			_savables.remove(_savables.get(_u1));
			_selected.setStroke(null);
			_selected = null;

		}
	}

	// Accessor methods which return the desired index.
	public int getIndex() {
		return _u;
	}

	public int getIndex1() {
		return _z;
	}

	public int getIndex2() {
		return _u1;
	}

	public int getIndex3() {
		return _z1;
	}

	// accessor method which returns the desired arraylist.
	public ArrayList<CurvedLine> getLineArray() {
		return _lines;
	}

	public ArrayList<SketchyShape> getShapeArray() {
		return _shape;
	}

	public ArrayList<Savables> getSavablesArray() {
		return _savables;
	}

	// Accessor methods which return the desired shape.
	public SketchyShape getTarget() {
		return _target;
	}

	public SketchyShape getSelected() {
		return _selected;
	}

	/*
	 * setter method which is used by the control class to set the desired radio
	 * button.
	 */
	public void setNum(int x) {
		_num = x;
	}

	/*
	 * checks to see if a shape is selected. brings the selected shape to the
	 * front instantiates a MoveToFront Command and pushes it to the undo stack.
	 * removes the redo stack.
	 */
	public void bringToFront() {
		if (_selected.getStroke() != null) {
			_u = _shape.indexOf(_selected);
			_u1 = _savables.indexOf(_selected);
			_target = _selected;
			_pane.getChildren().remove(_selected.getNode());
			_shape.remove(_selected);
			_savables.remove(_selected);
			_shape.add(_target);
			_savables.add(_target);
			_pane.getChildren().add(_target.getNode());
			_z = _shape.indexOf(_target);
			_z1 = _savables.indexOf(_target);
			Command mtf = new MoveToFront(this);
			_undo.push(mtf);
			_redo.clear();
		}
	}

	/*
	 * Checks to see if a shape is selected. Moves the selected shape to the
	 * front. Instantiates a MoveToBack command. and pushes it to the undo
	 * stack. Clears the redo stack.
	 */
	public void moveToBack() {
		if (_selected.getStroke() != null) {
			_u = _shape.indexOf(_selected);
			_u1 = _savables.indexOf(_selected);
			_target = _selected;
			_pane.getChildren().remove(_selected.getNode());
			_shape.remove(_selected);
			_savables.remove(_selected);
			_shape.add(0, _target);
			_savables.add(0, _target);
			_pane.getChildren().add(0, _target.getNode());
			Command mtb = new MoveToBack(this);
			_undo.push(mtb);
			_redo.clear();
		}
	}

	/*
	 * Checks if a shape is selected. Fills the selected shape with the desired
	 * color. Saves the index of the selected shape in Shape list and Savables
	 * list to be used later for undoing/redoing purpose.
	 */
	public void setSelectedColor(Color color) {
		if (_shape.isEmpty() == false && _selected != null) {
			_selected.fill(color);
			_z = _shape.indexOf(_selected);
			_z1 = _savables.indexOf(_selected);
		}
	}

	// boolean method that checks if the undo/redo stack is full.
	public boolean isUndoFull() {
		if (_undo.isEmpty()) {
			return false;
		}
		return true;
	}

	public boolean isRedoFull() {
		if (_redo.isEmpty()) {
			return false;
		}
		return true;
	}

	/*
	 * Accessor method that returns the canvas. Is used by the PaneOrganizer
	 * class.
	 */
	public Pane getPane() {
		return _pane;
	}

	// Accessor methods that return the undo/redo stacks.
	public Stack<Command> getUndoStack() {
		return _undo;
	}

	public Stack<Command> getRedoStack() {
		return _redo;
	}

	/*
	 * Method that rotates a point around another point. Used for properly
	 * selecting and resizing the rotated shape.
	 */
	public double rotatePointX(double x, double y, double x1, double y1, double a) {
		double sine = Math.sin(Math.toRadians(a));
		double cosine = Math.cos(Math.toRadians(a));
		double z1 = x - x1;
		double w1 = y - y1;
		double z2 = ((z1 * cosine) + (w1 * sine));
		x = z2 + x1;
		return x;
	}

	public double rotatePointY(double x, double y, double x1, double y1, double a) {
		double sine = Math.sin(Math.toRadians(a));
		double cosine = Math.cos(Math.toRadians(a));
		double z1 = x - x1;
		double w1 = y - y1;
		double w2 = ((w1 * cosine) - (z1 * sine));
		y = w2 + y1;
		return y;
	}

	// Private inner class, MouseHandler where the MouseEvents are handled.
	private class MouseHandler implements EventHandler<MouseEvent> {
		private double _x;
		private double _y;

		public void handle(MouseEvent e) {
			switch (_num) {

			/*
			 * This is when the select radio button is pressed. Selects the
			 * desired shape to perform shape manipulations i.e. translate,
			 * rotate, resize. highlights the selected shape by a blue stroke.
			 * saves the index of the selected shape for undoing/redoing
			 * purposes.
			 */
			case 1:

				if (e.getEventType() == MouseEvent.MOUSE_PRESSED) {
					for (int i = _shape.size() - 1; i >= 0; i--) {
						_shape.get(i).setStroke(null);
					}
					for (int i = _shape.size() - 1; i >= 0; i--) {
						if (_shape.get(i).contains(
								Sketchy.this.rotatePointX(e.getX(), e.getY(), _shape.get(i).getCenterX(),
										_shape.get(i).getCenterY(), _shape.get(i).getNode().getRotate()),
								Sketchy.this.rotatePointY(e.getX(), e.getY(), _shape.get(i).getCenterX(),
										_shape.get(i).getCenterY(), _shape.get(i).getNode().getRotate()))) {

							_selected = _shape.get(i);
							_u = _shape.indexOf(_selected);
							_u1 = _savables.indexOf(_selected);
							_selected.setStroke(Color.BLUE);
							_x = e.getX();
							_y = e.getY();
							break;
						}
					}
				}

				if (e.getEventType() == MouseEvent.MOUSE_DRAGGED) {

					if (e.isControlDown() && _selected != null && _selected.getStroke() != null) {
						_selected.rotate(_x, _y, e.getX(), e.getY());
						_x = e.getX();
						_y = e.getY();

					}

					if (e.isShiftDown() && _selected != null && _selected.getStroke() != null) {
						double x = Sketchy.this.rotatePointX(_x, _y, _selected.getCenterX(), _selected.getCenterY(),
								_selected.getNode().getRotate());
						double y = Sketchy.this.rotatePointY(_x, _y, _selected.getCenterX(), _selected.getCenterY(),
								_selected.getNode().getRotate());
						double x1 = Sketchy.this.rotatePointX(e.getX(), e.getY(), _selected.getCenterX(),
								_selected.getCenterY(), _selected.getNode().getRotate());
						double y1 = Sketchy.this.rotatePointY(e.getX(), e.getY(), _selected.getCenterX(),
								_selected.getCenterY(), _selected.getNode().getRotate());
						_selected.reSize(x, y, x1, y1);
						_x = e.getX();
						_y = e.getY();
					}
					if (_selected != null && _selected.getStroke() != null) {
						_selected.translate(_x, _y, e.getX(), e.getY());
						_x = e.getX();
						_y = e.getY();
					}
				}
				break;

			/*
			 * This is when the "Draw with Pen" radio button is selected. Draws
			 * lines on canvas with the desired color and saves them to the
			 * savables list. Instantiates a "Draw" Command and pushes it to the
			 * undo stack. clears the redo stack.
			 */
			case 2:

				if (e.getEventType() == MouseEvent.MOUSE_PRESSED) {
					for (int i = 0; i < _shape.size(); i++) {
						_shape.get(i).setStroke(null);
					}
					_curvedline = new CurvedLine();
					_lines.add(_curvedline);
					_u = _lines.indexOf(_curvedline);
					_u1 = _savables.size();
					_x = e.getX();
					_y = e.getY();
					Command draw = new Draw(Sketchy.this);
					_undo.push(draw);
					_redo.clear();

				}

				if (e.getEventType() == MouseEvent.MOUSE_DRAGGED) {

					LineSegment line = new LineSegment(_x, _y, e.getX(), e.getY());
					line.setStroke(
							Color.rgb(Sketchy.this.getRValue(), Sketchy.this.getGValue(), Sketchy.this.getBValue()));
					_curvedline.addLine(line);
					_savables.add(line);
					_pane.getChildren().add(line.getNode());
					_x = e.getX();
					_y = e.getY();

				}
				break;

			/*
			 * This is when the "Draw Rectangle" radio button is selected. Draws
			 * a rectangle from the center using the desired color. Adds it to
			 * the shape list, savables list, and canvas. Saves the saved
			 * shape's index for undoing/redoing purposes. Instantiates a
			 * "Create" Command and pushes it to the undo stack. Clears the redo
			 * stack.
			 */
			case 3:

				if (e.getEventType() == MouseEvent.MOUSE_PRESSED) {
					for (int i = 0; i < _shape.size(); i++) {
						_shape.get(i).setStroke(null);
					}
					Shape2 rect = new Shape2();
					_x = e.getX();
					_y = e.getY();
					rect.setLoc(_x, _y);
					rect.fill(Color.rgb(Sketchy.this.getRValue(), Sketchy.this.getGValue(), Sketchy.this.getBValue()));
					_selected = rect;
					_selected.setStroke(Color.BLUE);
					_pane.getChildren().add(_selected.getNode());
					_shape.add(_selected);
					_savables.add(_selected);
					_u = _shape.indexOf(_selected);
					_u1 = _savables.indexOf(_selected);
					Command c = new Create(Sketchy.this);
					_undo.push(c);
					_redo.clear();
				}

				if (e.getEventType() == MouseEvent.MOUSE_DRAGGED) {
					_selected.reSize(_x, _y, e.getX(), e.getY());
					_x = e.getX();
					_y = e.getY();
				}
				break;

			/*
			 * This is when the "Draw Ellipse" radio button is selected. Draws
			 * an Ellipse from the center using the desired color. Adds it to
			 * the shape list, savables list, and canvas. Saves the saved
			 * shape's index for undoing/redoing purposes. Instantiates a
			 * "Create" Command and pushes it to the undo stack. Clears the redo
			 * stack.
			 */
			case 4:

				if (e.getEventType() == MouseEvent.MOUSE_PRESSED) {
					for (int i = 0; i < _shape.size(); i++) {
						_shape.get(i).setStroke(null);
					}
					Shape1 ellipse = new Shape1();
					_x = e.getX();
					_y = e.getY();
					ellipse.setLoc(_x, _y);
					ellipse.fill(
							Color.rgb(Sketchy.this.getRValue(), Sketchy.this.getGValue(), Sketchy.this.getBValue()));
					_selected = ellipse;
					_selected.setStroke(Color.BLUE);
					_pane.getChildren().add(_selected.getNode());
					_shape.add(_selected);
					_savables.add(_selected);
					_u = _shape.indexOf(_selected);
					_u1 = _savables.indexOf(_selected);
					Command create = new Create(Sketchy.this);
					_undo.push(create);
					_redo.clear();
				}

				if (e.getEventType() == MouseEvent.MOUSE_DRAGGED) {
					_selected.reSize(_x, _y, e.getX(), e.getY());
					_x = e.getX();
					_y = e.getY();
				}
				break;
			}
			e.consume();

		}
	}
}
