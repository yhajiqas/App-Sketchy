package Sketchy;

import cs015.fnl.SketchySupport.FileIO;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
/*
 * This is my LineSegment Class. 
 * Multiple instances of this class will be used in CurvedLine Class to create a curvedline. 
 * Has the following methods.
 */

public class LineSegment implements Savables {
	private Line _line;

	public LineSegment(double x, double y, double x1, double y1) {
		_line = new Line(x, y, x1, y1);
	}

	public void setStroke(Color color) {
		_line.setStroke(color);
	}

	public Color getStroke() {
		return (Color) _line.getStroke();
	}

	public double getStartX() {
		return _line.getStartX();
	}

	public double getStartY() {
		return _line.getStartY();
	}

	public double getEndX() {
		return _line.getEndX();
	}

	public double getEndY() {
		return _line.getEndY();
	}

	public void save(FileIO io) {
		io.writeString("Line");
		io.writeDouble(_line.getStartX());
		io.writeDouble(_line.getStartY());
		io.writeDouble(_line.getEndX());
		io.writeDouble(_line.getEndY());
		io.writeDouble(this.getStroke().getRed());
		io.writeDouble(this.getStroke().getGreen());
		io.writeDouble(this.getStroke().getBlue());
		io.writeDouble(0);
	}

	public void fill(Color color) {
		_line.setFill(color);
	}

	public Node getNode() {
		return _line;
	}

}
