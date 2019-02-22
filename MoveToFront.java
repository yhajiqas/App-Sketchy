package Sketchy;
/*
 * This is the MoveToFront Command, which implements the Command Interface. 
 * It implements its own undo/redo methods.
 * Undo and Redo methods retain the original layering both on canvas and shape list.
 */

public class MoveToFront implements Command {
	private int _u;
	private int _u1;
	private int _z;
	private int _z1;
	private SketchyShape _sketchyShape;
	private SketchyShape _secondShape;

	public MoveToFront(Sketchy sketchy) {
		_u = sketchy.getIndex();
		_u1 = sketchy.getIndex2();
		_z = sketchy.getIndex1();
		_z1 = sketchy.getIndex3();
		_sketchyShape = sketchy.getTarget();
		_secondShape = _sketchyShape;
	}

	public void undo(Sketchy sketchy) {
		sketchy.getPane().getChildren().remove(sketchy.getShapeArray().get(_z).getNode());
		sketchy.getShapeArray().remove(_z);
		sketchy.getSavablesArray().remove(_z1);
		sketchy.getShapeArray().add(_u, _sketchyShape);
		sketchy.getSavablesArray().add(_u1, _sketchyShape);
		sketchy.getPane().getChildren().add(_u1, _sketchyShape.getNode());
	}

	public void redo(Sketchy sketchy) {
		sketchy.getPane().getChildren().remove(_sketchyShape.getNode());
		sketchy.getShapeArray().remove(_sketchyShape);
		sketchy.getSavablesArray().remove(_sketchyShape);
		sketchy.getShapeArray().add(_z, _secondShape);
		sketchy.getSavablesArray().add(_z1, _secondShape);
		sketchy.getPane().getChildren().add(_secondShape.getNode());
		_secondShape.getNode().toFront();
	}
}
