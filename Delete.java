package Sketchy;
/*
 * This is the Delete Command, which implements the Command interface.
 * It has its own undo/redo methods.
 * Undo/Redo methods retain the original layering both on canvas and shape list. 
 */

public class Delete implements Command{
	private SketchyShape _sketchyShape;
	private int _u;
	private int _u1;
	public Delete(Sketchy sketchy){
		_u = sketchy.getIndex();
		_u1= sketchy.getIndex2();
		_sketchyShape = sketchy.getTarget();
	}
	
	public void undo(Sketchy sketchy){
		sketchy.getShapeArray().add(_u,_sketchyShape);
		sketchy.getSavablesArray().add(_u1,_sketchyShape);
		sketchy.getPane().getChildren().add(_u1,_sketchyShape.getNode());
		
	}
	
	public void redo(Sketchy sketchy){
		sketchy.getPane().getChildren().remove(_sketchyShape.getNode());
		sketchy.getShapeArray().remove(sketchy.getShapeArray().get(_u));
		sketchy.getSavablesArray().remove(sketchy.getSavablesArray().get(_u1));
	}
}
