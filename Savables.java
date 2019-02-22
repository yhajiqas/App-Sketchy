package Sketchy;

import cs015.fnl.SketchySupport.FileIO;
import javafx.scene.paint.Color;

// Savables Interface that is used by shapes and lines for save/load purposes.
public interface Savables {
	public void save(FileIO io);

	public void fill(Color color);

}
