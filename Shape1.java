package Sketchy;

import cs015.fnl.SketchySupport.FileIO;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

/*
 * this is my Ellipse Shape class that implements the interfaces SketcheyShape and Savables.
 * implements all the methods that each interface require, i.e. translate, resize, rotate, fill and save. 
 */
public class Shape1 implements SketchyShape, Savables {
	private Ellipse _ellipse;

	public Shape1() {
		_ellipse = new Ellipse();
	}

	public void translate(double x, double y, double x1, double y1) {
		_ellipse.setCenterX(_ellipse.getCenterX() + (x1 - x));
		_ellipse.setCenterY(_ellipse.getCenterY() + (y1 - y));
	}

	public void rotate(double x, double y, double x1, double y1) {
		double g = Math.atan2(y - _ellipse.getCenterY(), x - _ellipse.getCenterX())
				- Math.atan2(y1 - _ellipse.getCenterY(), x1 - _ellipse.getCenterX());
		_ellipse.setRotate(((_ellipse.getRotate() - Math.toDegrees(g))));
	}

	public void reSize(double x, double y, double x1, double y1) {

		if (Math.abs(this.getCenterX() - x) > Math.abs(this.getCenterX() - x1)) {
			_ellipse.setRadiusX(_ellipse.getRadiusX() - Math.abs(x1 - x));

		}
		if (Math.abs(this.getCenterX() - x) < Math.abs(this.getCenterX() - x1)) {
			_ellipse.setRadiusX(_ellipse.getRadiusX() + Math.abs(x1 - x));
		}

		if (Math.abs(this.getCenterY() - y) > Math.abs(this.getCenterY() - y1)) {
			_ellipse.setRadiusY(_ellipse.getRadiusY() - Math.abs(y1 - y));

		}
		if (Math.abs(this.getCenterY() - y) < Math.abs(this.getCenterY() - y1)) {
			_ellipse.setRadiusY(_ellipse.getRadiusY() + Math.abs(y1 - y));
		}

	}

	public void fill(Color color) {
		_ellipse.setFill(color);
	}

	public void setLoc(double x, double y) {
		_ellipse.setCenterX(x);
		_ellipse.setCenterY(y);
	}

	public void setSize(double x, double y) {
		_ellipse.setRadiusX(x);
		_ellipse.setRadiusY(y);
	}

	public Node getNode() {
		return _ellipse;
	}

	public void setStroke(Color color) {
		_ellipse.setStroke(color);
	}

	public Color getStroke() {
		return (Color) _ellipse.getStroke();
	}

	public double getCenterX() {
		return _ellipse.getCenterX();
	}

	public double getCenterY() {
		return _ellipse.getCenterY();
	}

	public Color getColor() {
		return (Color) _ellipse.getFill();
	}

	public boolean contains(double x, double y) {
		return _ellipse.contains(x, y);
	}

	public void save(FileIO io) {
		io.writeString("Ellipse");
		io.writeDouble(_ellipse.getCenterX());
		io.writeDouble(_ellipse.getCenterY());
		io.writeDouble(_ellipse.getRadiusX());
		io.writeDouble(_ellipse.getRadiusY());
		io.writeDouble(_ellipse.getRotate());
		io.writeDouble(this.getColor().getRed());
		io.writeDouble(this.getColor().getGreen());
		io.writeDouble(this.getColor().getBlue());
	}

}
