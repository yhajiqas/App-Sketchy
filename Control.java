package Sketchy;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import cs015.fnl.SketchySupport.FileIO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
/*
 * This is my control class where all of buttons, radio buttons, sliders as well as their corresponding labels are set up. The mouse handler ,however, is located in my game class since it was more efficient to use
 * it from the Game class. the sliders set up the desired rgb values which will set the color of the desired shape/line to that color as instructed in the Sketchy handout. 
 * I used a Vbox to contain all the nodes ( buttons, etc) and added it to the root pane in the pane organizer. 
 * in addition, i used handlers to add functionality to each button and radio buttons as well as sliders. 
 * an instance of Game is passed in in the constructor to access the methods in the game class when they are needed to be called. 
 * 
 */

public class Control {
	private RadioButton _b1;
	private RadioButton _b2;
	private RadioButton _b3;
	private RadioButton _b4;
	private Slider _Red;
	private Slider _Green;
	private Slider _Blue;
	private ToggleGroup _group;
	private VBox _vbox;
	private Sketchy _sketchy;
	private int _r;
	private int _g;
	private int _b;

	// this is the constructor of Control class.
	public Control(Sketchy sketchy) {
		_vbox = new VBox();
		_sketchy = sketchy;
		_vbox.setSpacing(10);
		_vbox.setPadding(new Insets(50, 50, 30, 30));
		this.setUpRadioButton();
		this.setUpSliders();
		this.setUpButtons();
	}

	// Accessor method that returns the vbox.
	public VBox getVBox() {
		return _vbox;
	}

	/*
	 * sets up the radio buttons. adds the radio buttons and their labels to the
	 * vbox uses listeners to update the change in selected radio button. the
	 * listeners also change the value of _num which will be used in the Sketchy
	 * class for choosing the desired option e.g. select, draw with pen, etc.
	 */
	public void setUpRadioButton() {
		_group = new ToggleGroup();
		Label lt = new Label("Drawing Options");
		_b1 = new RadioButton("Select");
		_b1.setToggleGroup(_group);

		_b2 = new RadioButton("Draw With Line");
		_b2.setToggleGroup(_group);

		_b3 = new RadioButton("Draw Rectangle");
		_b3.setToggleGroup(_group);

		_b4 = new RadioButton("Draw Ellipse");
		_b4.setToggleGroup(_group);

		_vbox.getChildren().addAll(lt, _b1, _b2, _b3, _b4);
		_vbox.setStyle("-fx-background-color: orange;");

		_group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

			public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
				if (_group.getSelectedToggle() != null)
					;
				_group.getSelectedToggle().setSelected(true);
				if (_b1.isSelected()) {
					_sketchy.setNum(1);

				}
				if (_b2.isSelected()) {
					_sketchy.setNum(2);
					_sketchy.setRGBValue((int) _Red.getValue(), (int) _Green.getValue(), (int) _Blue.getValue());
				}
				if (_b3.isSelected()) {
					_sketchy.setNum(3);
					_sketchy.setRGBValue((int) _Red.getValue(), (int) _Green.getValue(), (int) _Blue.getValue());
				}

				if (_b4.isSelected()) {
					_sketchy.setNum(4);
					_sketchy.setRGBValue((int) _Red.getValue(), (int) _Green.getValue(), (int) _Blue.getValue());
				}
			}
		});

	}

	/*
	 * sets up the sliders . adds the labels and sliders to the vbox. uses
	 * listeners to update the changes in the values of sliders.
	 */
	public void setUpSliders() {
		Label label = new Label("Set the Color");
		_Red = new Slider(0, 255, 255);
		_Green = new Slider(0, 255, 0);
		_Blue = new Slider(0, 255, 0);

		Label label_1 = new Label("Red: " + _Red.getValue());
		Label label_2 = new Label("Green: " + _Green.getValue());
		Label label_3 = new Label("Blue: " + _Blue.getValue());

		_vbox.getChildren().addAll(label, label_1, _Red, label_2, _Green, label_3, _Blue);

		_Red.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				label_1.setText("Red: " + (int) _Red.getValue());
				_r = (int) _Red.getValue();
				_sketchy.setRGBValue(_r, (int) _Green.getValue(), (int) _Blue.getValue());
			}
		});

		_Green.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				label_2.setText("Green: " + (int) _Green.getValue());
				_g = (int) _Green.getValue();
				_sketchy.setRGBValue((int) _Red.getValue(), _g, (int) _Blue.getValue());

			}
		});

		_Blue.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				label_3.setText("Blue: " + (int) _Blue.getValue());
				_b = (int) _Blue.getValue();
				_sketchy.setRGBValue((int) _Red.getValue(), (int) _Green.getValue(), _b);
			}
		});

	}

	/*
	 * sets up the buttons with their respective names and labels. adds them all
	 * to the vbox.
	 */
	public void setUpButtons() {
		Label label_4 = new Label("Shape Actions");
		Button b1 = new Button("Bring To Front");
		b1.setOnAction(new ButtonHandler1());
		Button b2 = new Button("Move To Back");
		b2.setOnAction(new ButtonHandler2());
		Button b3 = new Button("Fill");
		b3.setOnAction(new ButtonHandler3());
		Button b4 = new Button("Delete");
		b4.setOnAction(new ButtonHandler4());
		Label label_5 = new Label("Operations");
		Button b5 = new Button("Undo");
		b5.setOnAction(new ButtonHandler5());
		Button b6 = new Button("Redo");
		b6.setOnAction(new ButtonHandler6());
		Button b7 = new Button("Save");
		b7.setOnAction(new ButtonHandler7());
		Button b8 = new Button("Load");
		b8.setOnAction(new ButtonHandler8());
		_vbox.getChildren().addAll(label_4, b1, b2, b3, b4, label_5, b5, b6, b7, b8);
	}

	/*
	 * checks if a shape is selected. performs the bringToFront method ( found
	 * in the Sketchy class) which brings the selected shape to the front.
	 */
	private class ButtonHandler1 implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			if (_sketchy.getSelected() != null) {
				_sketchy.bringToFront();

			}
		}
	}

	/*
	 * checks if a shape is selected. performs the moveToBack method (found in
	 * Sketchy Class) which moves the selected to the back of the canvas.
	 */
	private class ButtonHandler2 implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			if (_sketchy.getSelected() != null) {
				_sketchy.moveToBack();

			}
		}
	}

	/*
	 * checks if a shape is selected. Retrieves the previous and current color
	 * of the selected shape. fills the selected shape with the current color.
	 * creates an instance of command fill and pushes it to the undo stack.
	 * clears the redo stack.
	 */
	private class ButtonHandler3 implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			if (_sketchy.getSelected() != null && _sketchy.getSelected().getStroke() != null) {
				Color prev = _sketchy.getSelected().getColor();
				Color curr = Color.rgb((int) _Red.getValue(), (int) _Green.getValue(), (int) _Blue.getValue());
				_sketchy.setSelectedColor(curr);
				Command fill = new Fill(_sketchy, prev, curr);
				_sketchy.getUndoStack().push(fill);
				_sketchy.getRedoStack().clear();
			}

		}
	}

	/*
	 * checks if a shape is selected. // performs delete on the selected shape.
	 * creates an instance of delete command. adds the created command to the
	 * undo stack. clears the redo stack.
	 */
	private class ButtonHandler4 implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			if (_sketchy.getSelected() != null && _sketchy.getSelected().getStroke() != null) {
				_sketchy.removeSelected();
				Command delete = new Delete(_sketchy);
				_sketchy.getUndoStack().push(delete);
				_sketchy.getRedoStack().clear();
			}
		}
	}

	/*
	 * checks to see if the undo stack is empty. if not, pops the last command
	 * and gets it to perform its undo method. adds/pushes the command into the
	 * redo stack.
	 */
	private class ButtonHandler5 implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			if (_sketchy.isUndoFull()) {
				Command c = _sketchy.getUndoStack().pop();
				c.undo(_sketchy);
				_sketchy.getRedoStack().push(c);
			}
		}

	}

	/*
	 * checks to see if the redo stack is empty. // if not, pops the last
	 * command and gets it to perform its redo method. // adds/pushes the
	 * command to the undo stack.
	 */
	private class ButtonHandler6 implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			if (_sketchy.isRedoFull()) {
				Command d = _sketchy.getRedoStack().pop();
				d.redo(_sketchy);
				_sketchy.getUndoStack().push(d);
			}
		}

	}

	/*
	 * first checks to whether getFileName returns null, if not, it opens file
	 * for writing/saving. iterates through the savables list and uses the save
	 * method for each of the smart shapes to write them into the selected file.
	 */
	private class ButtonHandler7 implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			FileIO io = new FileIO();
			String k = FileIO.getFileName(true, _sketchy.getPane().getScene().getWindow());
			if (k != null) {
				io.openWrite(k);

				for (int i = 0; i < _sketchy.getSavablesArray().size(); i++) {
					_sketchy.getSavablesArray().get(i).save(io);
				}

				io.closeWrite();
			}
		}
	}

	/*
	 * first checks whether getFileName returns null, if not, it opens the file
	 * // for reading. // clears the canvas as well as shape/line arrays. //
	 * continues reading and reconstructing shapes/lines so long as the file has
	 * // more data. // Finally, it adds the new shapes/lines to the canvas as
	 * well as savables list lists.
	 */
	private class ButtonHandler8 implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			FileIO io = new FileIO();
			String k = FileIO.getFileName(false, _sketchy.getPane().getScene().getWindow());

			if (k != null) {
				io.openRead(k);

				_sketchy.getPane().getChildren().clear();
				_sketchy.getUndoStack().clear();
				_sketchy.getRedoStack().clear();
				_sketchy.getSavablesArray().clear();
				_sketchy.getShapeArray().clear();
				_sketchy.getLineArray().clear();

				while (io.hasMoreData()) {
					String s = io.readString();
					double d1 = io.readDouble();
					double d2 = io.readDouble();
					double d3 = io.readDouble();
					double d4 = io.readDouble();
					double d5 = io.readDouble();
					double d6 = io.readDouble();
					double d7 = io.readDouble();
					double d8 = io.readDouble();

					if (s.equals("Ellipse")) {
						Shape1 ellipse = new Shape1();
						ellipse.setLoc(d1, d2);
						ellipse.setSize(d3, d4);
						ellipse.getNode().setRotate(d5);
						ellipse.fill(Color.rgb((int) (d6 * 255), (int) (d7 * 255), (int) (d8 * 255)));
						_sketchy.getPane().getChildren().add(ellipse.getNode());
						_sketchy.getShapeArray().add(ellipse);
						_sketchy.getSavablesArray().add(ellipse);
					}
					if (s.equals("Rectangle")) {
						Shape2 rect = new Shape2();
						rect.setLoc(d1, d2);
						rect.setSize(d3, d4);
						rect.getNode().setRotate(d5);
						rect.fill(Color.rgb((int) (d6 * 255), (int) (d7 * 255), (int) (d8 * 255)));
						_sketchy.getPane().getChildren().add(rect.getNode());
						_sketchy.getShapeArray().add(rect);
						_sketchy.getSavablesArray().add(rect);

					}
					if (s.equals("Line")) {

						LineSegment line = new LineSegment(d1, d2, d3, d4);
						line.setStroke((Color) (Color.rgb((int) (d5 * 255), (int) (d6 * 255), (int) (d7 * 255))));
						_sketchy.getPane().getChildren().add(line.getNode());
						_sketchy.getSavablesArray().add(line);
					}
				}
				io.closeRead();
			}
		}
	}
}
