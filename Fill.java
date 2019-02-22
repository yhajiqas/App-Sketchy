package Sketchy;

import javafx.scene.paint.Color;

/*
 * This is the Fill Command, which implements Command Interface. 
 * It implements its own undo/redo methods.
 * 
 */

public class Fill implements Command {
	private Color _prev;
	private Color _curr;
	private int _z;
	private int _z1;

	public Fill(Sketchy sketchy, Color prev, Color curr) {
		_prev = prev;
		_curr = curr;
		_z = sketchy.getIndex1();
		_z1 = sketchy.getIndex3();
	}

	public void undo(Sketchy sketchy) {
		sketchy.getShapeArray().get(_z).fill(_prev);
		sketchy.getSavablesArray().get(_z1).fill(_prev);
	}

	public void redo(Sketchy sketchy) {
		sketchy.getShapeArray().get(_z).fill(_curr);
		sketchy.getSavablesArray().get(_z1).fill(_curr);
	}
}
