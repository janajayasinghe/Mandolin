package mandolin.container;

import mandolin.Listener.ActionListener;
import mandolin.component.AButton;
import mandolin.component.HiddenComponent;
import mandolin.component.JButton;
import mandolin.component.table.DefaultListSelectionModel;
import mandolin.event.ActionEvent;
import mandolin.impersonator.Component;

/**
 * Title: Mandolin
 * Description: Table cell editor button
 * Copyright: B3
 * Company: B3
 *
 * @author Niranja Arjuna Jayasinghe
 * @version 1.0
 */
public class CellEditorButton extends HTMLContainer implements ActionListener {

	private DefaultListSelectionModel selectionModel;
	private Component editorComponent;
	private int column;
	private int row;
	private AButton editButton;
	private AButton closeButton;
	private boolean isEditing = false;
	private Component squatter;

	// This hidden component is used to hold the user input only. it should have same name of editorComponent. this component is not there on the web page.
	// This hidden component should have  in order to get the its value.
	private HiddenComponent editorValueComponent;

	public CellEditorButton(DefaultListSelectionModel selectionModel, int column, int row, String text) {
		this.selectionModel = selectionModel;
		this.column = column;
		this.row = row;

		this.editButton = new AButton(text);
		this.closeButton = new AButton("x");
		this.editorValueComponent = new HiddenComponent();
		this.editorValueComponent.setName(row + "." + column);

		this.add(this.editButton);
		this.add(this.closeButton);
		this.add(this.editorValueComponent);
		this.editButton.addActionListener(this);
		this.closeButton.addActionListener(this);
	}

	public void setEditorComponent(Component editorComponent) {
		this.editorComponent = editorComponent;
	}

	public void setEditing(boolean editing) {
		isEditing = editing;
	}

	public void setEditorComponentData() {
		Object value = new String[]{this.editorValueComponent.getValue()};
		this.editorComponent.setData(value);
	}

	@Override
	protected String getLayoutClass() {
		return null;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == editButton) {
			this.isEditing = true;
			this.selectionModel.setSelectedRow(this.row);
			this.selectionModel.setSelectedColumn(this.column);
		} else if (e.getSource() == closeButton) {
			this.isEditing = false;
			this.setEditorComponentData();
		}
	}

	@Override
	public void toHTML(StringBuffer buffer) {
		editButton.setPath(0, this);
		closeButton.setPath(1, this);
		if (editorComponent instanceof JButton) {
			JButton editorCompButton = (JButton) editorComponent;
			squatter = editorCompButton.getSquatter();
			editorCompButton.setSquatter(null);
		}
		if (editorComponent != null && isEditing) {
			buffer.append("<div style='float:right'>");
			closeButton.toHTML(buffer);
			buffer.append("</div>");
			editorComponent.toHTML(buffer);
		} else {
			editButton.toHTML(buffer);
		}
		if (squatter != null) {
			squatter.toHTML(buffer);
			squatter = null;
		}
		isEditing = false;
	}
}
