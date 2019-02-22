
package Sketchy;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

/**
 * @author <yhajiqas> Project Specification: 1. Created a Main Class (Sketchy)
 *         which runs the program. 2. Created a Control Class, which handles the
 *         buttons, radio buttons, and sliders as well as their functionality.
 *         3. Created 3 interfaces : a.SketchyShape b. Savables c.Commands. 4.
 *         Created 6 Command Classes (Create, Draw, Fill,Delete, MoveToFront,
 *         MoveToBack) which implement the Commands Interface. 5. Created 2
 *         Shape Classes (Shape1, Shape2) with their corresponding methods, both
 *         of which implement Savables interface and SketchyShape interface. 6.
 *         Created a LineSegment Class which creates one individual line,
 *         multiple instances of which will be used to draw a curved line. 7.
 *         Created a CurvedLine Class which uses an array-list to hold all the
 *         individual line segments. 8. Created two stacks, Undo and Redo, to be
 *         used for undoing/redoing purposes. 9. In Sketchy Class, I used 3
 *         array-lists (SketchyShape,Lines,and Savables) to be used as the main
 *         data structure for this project.
 * 
 *         Functionality Specification: 1. Each of the two shapes(including
 *         Rectangle) are drawn from center(center remains constant), with the
 *         desired color. 2. Curved lines are drawn with the desired color. 3.
 *         Shapes can be selected(highlighted), resized, translated, rotated,
 *         and saved. 4. Undo/Redo works for Create, Draw, Fill, MoveToFront,
 *         MoveToBack. 5. Undo/Redo preserves the original layering (for all
 *         commands, including delete) 6. Select takes into account rotation and
 *         works perfectly.(when multiple shapes are overlapping, selects the
 *         shape that's on top) 7. Save/Load preserves the original state of
 *         drawing including the original layering. 8. Save/Load handles the
 *         event where cancel button is pressed. 9. Utilized indices to keep
 *         track of the selected shape/curvedlines, in order to preserve correct
 *         layering upon undoing/redoing. 10. Canvas clears when loading.
 */

public class App extends Application {
	// Created a top-level object, set up the scene and showed the stage.
	@Override
	public void start(Stage stage) {
		PaneOrganizer organizer = new PaneOrganizer();
		Scene scene = new Scene(organizer.getRoot(), 800, 800);
		stage.setTitle("Sketchy!");
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] argv) {
		launch(argv);
	}
}
