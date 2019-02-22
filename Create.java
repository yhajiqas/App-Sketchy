package Sketchy;

/*
 * This is the Create Command which implements the Command Interface.
 * It implements its own undo/redo methods.
 * Undo/Redo methods retain original layering both on canvas and shape list. 
 */
public class Create implements Command {
	private SketchyShape _sketchyShape;
	private int _u;
	private int _u1;

	public Create(Sketchy sketchy) {
		_u = sketchy.getIndex();
		_u1 = sketchy.getIndex2();
		_sketchyShape = sketchy.getShapeArray().get(_u);
	}

	public void undo(Sketchy sketchy) {
		sketchy.getPane().getChildren().remove(sketchy.getShapeArray().get(_u).getNode());
		sketchy.getShapeArray().remove(sketchy.getShapeArray().get(_u));
		sketchy.getSavablesArray().remove(sketchy.getSavablesArray().get(_u1));
	}

	public void redo(Sketchy sketchy) {
		sketchy.getShapeArray().add(_u, _sketchyShape);
		sketchy.getSavablesArray().add(_u1, _sketchyShape);
		sketchy.getPane().getChildren().add(_sketchyShape.getNode());
	}

}
