package Sketchy;

import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.paint.Color;
/*
 * This is the CurvedLine Class.
 * It uses an array-list of LineSegments to create a curvedline.
 * Has the following methods.
 */

public class CurvedLine {
	private ArrayList<LineSegment> _lines;
	private LineSegment _line;
	private double _x;
	private double _y;
	private double _x1;
	private double _y1;

	public CurvedLine() {
		_line = new LineSegment(_x, _y, _x1, _y1);
		_lines = new ArrayList<LineSegment>();
	}

	public void addLine(LineSegment line) {
		_lines.add(line);
	}

	public void setStroke(Color color) {
		_line.setStroke(color);
	}

	public Color getStroke() {
		return (Color) _line.getStroke();
	}

	public ArrayList<LineSegment> getLineArray() {
		return _lines;
	}

	public Node getNode() {
		return _line.getNode();
	}

}
