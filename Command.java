package Sketchy;

// command interface that uses the undo and redo methods. 
public interface Command {
	public void undo(Sketchy sketchy);

	public void redo(Sketchy sketchy);
}
