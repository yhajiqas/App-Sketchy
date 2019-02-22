package Sketchy;

/* This is the Draw Command, which implements the Command Interface.
 * It implements its own undo/redo command.
 * Undo/Redo preserve the original layering. 
 */

public class Draw implements Command {
	private CurvedLine _c;
	private int _u;
	private int _u1;

	public Draw(Sketchy sketchy) {
		_u = sketchy.getIndex();
		_u1 = sketchy.getIndex2();
		_c = sketchy.getLineArray().get(_u);
	}

	public void undo(Sketchy sketchy) {

		for (int i = 0; i < sketchy.getLineArray().get(_u).getLineArray().size(); i++) {
			sketchy.getPane().getChildren().remove(sketchy.getLineArray().get(_u).getLineArray().get(i).getNode());
			sketchy.getSavablesArray().remove(sketchy.getLineArray().get(_u).getLineArray().get(i));
		}
		sketchy.getLineArray().remove(_u);
	}

	public void redo(Sketchy sketchy) {
		for (int i = 0; i < _c.getLineArray().size(); i++) {
			sketchy.getPane().getChildren().add(_c.getLineArray().get(i).getNode());
			sketchy.getSavablesArray().add(_u1, _c.getLineArray().get(i));
		}
		sketchy.getLineArray().add(_u, _c);
	}
}