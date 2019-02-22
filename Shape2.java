package Sketchy;

import cs015.fnl.SketchySupport.FileIO;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/*
 * this is my Rectangle Shape class that implements the interfaces SketcheyShape and Savables.
 * implements all the methods that each interface require, i.e. translate, resize, rotate, fill and save. 
 */
public class Shape2 implements SketchyShape, Savables {
	private Rectangle _rect;

	public Shape2() {
		_rect = new Rectangle();
	}

	public void translate(double x, double y, double x1, double y1) {
		_rect.setX(_rect.getX() + (x1 - x));
		_rect.setY(_rect.getY() + (y1 - y));
	}

	public void rotate(double x, double y, double x1, double y1) {
		double g = Math.atan2(y - this.getCenterY(), x - this.getCenterX())
				- Math.atan2(y1 - this.getCenterY(), x1 - this.getCenterX());
		_rect.setRotate((_rect.getRotate() - Math.toDegrees(g)));
	}

	public void reSize(double x, double y, double x1, double y1) {
		if (Math.abs(this.getCenterX() - x) > Math.abs(this.getCenterX() - x1)) {
			_rect.setWidth(_rect.getWidth() - Math.abs(x1 - x));
			_rect.setX(_rect.getX() + Math.abs(x1 - x) / 2);
		}
		if (Math.abs(this.getCenterX() - x) < Math.abs(this.getCenterX() - x1)) {
			_rect.setWidth(_rect.getWidth() + Math.abs(x1 - x));
			_rect.setX(_rect.getX() - Math.abs(x1 - x) / 2);
		}
		if (Math.abs(this.getCenterY() - y) > Math.abs(this.getCenterY() - y1)) {
			_rect.setHeight(_rect.getHeight() - Math.abs(y1 - y));
			_rect.setY(_rect.getY() + Math.abs(y1 - y) / 2);
		}
		if (Math.abs(this.getCenterY() - y) < Math.abs(this.getCenterY() - y1)) {
			_rect.setHeight(_rect.getHeight() + Math.abs(y1 - y));
			_rect.setY(_rect.getY() - Math.abs(y1 - y) / 2);
		}

	}

	public void fill(Color color) {
		_rect.setFill(color);

	}

	public void setLoc(double x, double y) {
		_rect.setX(x);
		_rect.setY(y);
	}

	public void setSize(double x, double y) {
		_rect.setWidth(x);
		_rect.setHeight(y);
	}

	public void setStroke(Color color) {
		_rect.setStroke(color);
	}

	public Color getStroke() {
		return (Color) _rect.getStroke();
	}

	public double getCenterX() {
		return _rect.getX() + _rect.getWidth() / 2;
	}

	public double getCenterY() {
		return _rect.getY() + _rect.getHeight() / 2;
	}

	public Node getNode() {
		return _rect;
	}

	public Color getColor() {
		return (Color) _rect.getFill();
	}

	public boolean contains(double x, double y) {
		return _rect.contains(x, y);
	}

	public void save(FileIO io) {
		io.writeString("Rectangle");
		io.writeDouble(_rect.getX());
		io.writeDouble(_rect.getY());
		io.writeDouble(_rect.getWidth());
		io.writeDouble(_rect.getHeight());
		io.writeDouble(_rect.getRotate());
		io.writeDouble(this.getColor().getRed());
		io.writeDouble(this.getColor().getGreen());
		io.writeDouble(this.getColor().getBlue());
	}
}
