package Sketchy;

/*
 * This is the MoveToBack Command which implements the Commmand Interface. 
 * It implements its own undo/redo methods.
 * Undo/Redo methods retain the original layering both on canvas and shape list. 
 */
public class MoveToBack implements Command {
	private int _u;
	private int _u1;
	private SketchyShape _sketchyShape;
	private SketchyShape _secondShape;

	public MoveToBack(Sketchy sketchy) {
		_u = sketchy.getIndex();
		_u1 = sketchy.getIndex2();
		_sketchyShape = sketchy.getTarget();
		_secondShape = _sketchyShape;
	}

	public void undo(Sketchy sketchy) {
		sketchy.getPane().getChildren().remove(0);
		sketchy.getShapeArray().remove(0);
		sketchy.getSavablesArray().remove(0);
		sketchy.getShapeArray().add(_u, _sketchyShape);
		sketchy.getSavablesArray().add(_u1, _sketchyShape);
		sketchy.getPane().getChildren().add(_u1, _sketchyShape.getNode());
	}

	public void redo(Sketchy sketchy) {
		sketchy.getPane().getChildren().remove(_sketchyShape.getNode());
		sketchy.getShapeArray().remove(_sketchyShape);
		sketchy.getSavablesArray().remove(_sketchyShape);
		sketchy.getShapeArray().add(0, _secondShape);
		sketchy.getSavablesArray().add(0, _secondShape);
		sketchy.getPane().getChildren().add(0, _secondShape.getNode());

	}
}
