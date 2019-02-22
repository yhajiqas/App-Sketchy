package Sketchy;

import cs015.fnl.SketchySupport.FileIO;
import javafx.scene.Node;
import javafx.scene.paint.Color;

/*
 * This is the SketchyShape interface which extends Savables interface.
 * Has the followig methods that are used by both of the existing shapes, namely Rectangle and Ellipse. 
 */
public interface SketchyShape extends Savables {

	public void translate(double x, double y, double x1, double y1);

	public void rotate(double x, double y, double x1, double x2);

	public void reSize(double x, double y, double x1, double y1);

	public void fill(Color color);

	public void setLoc(double x, double y);

	public void setStroke(Color color);

	public Color getStroke();

	public double getCenterX();

	public double getCenterY();

	public Node getNode();

	public void setSize(double x, double y);

	public boolean contains(double x, double y);

	public Color getColor();

	public void save(FileIO io);

}
