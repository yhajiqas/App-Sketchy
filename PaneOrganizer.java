package Sketchy;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/*/
 * this is the pane organizer Class.
 * Organizes the Pane (Skethcy class) and Vbox(Control class). 
 * Adds them to the BorderPane.
 */
public class PaneOrganizer {
	private BorderPane _root;
	private Sketchy _sketchy;
	private Control _control;

	// This is the Constructor.
	public PaneOrganizer() {
		_root = new BorderPane();
		Pane pane = new Pane();
		_sketchy = new Sketchy(pane);
		_control = new Control(_sketchy);
		_root.setCenter(pane);
		_root.setLeft(_control.getVBox());
	}

	// Accessor method that returns the BorderPane.
	public Pane getRoot() {
		return _root;
	}

}
