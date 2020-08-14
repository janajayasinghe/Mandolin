package mandolin.component.table;

import mandolin.Listener.ListSelectionListener;
import mandolin.component.HTMLComponent;

/**
 * Title: Mandolin
 * Description:
 * Copyright: B3
 * Company: B3
 *
 * @author Niranja Arjuna Jayasinghe
 * @version 1.0
 */
public class DefaultListSelectionModel extends HTMLComponent {

	public static final int NO_SELECTION = 0;
	public static final int SINGLE_SELECTION = 1;

	private int selectedRow = -1;
	private int selectedColumn = -1;
	private int selectionMode;

	public DefaultListSelectionModel() {
		this(NO_SELECTION);
	}

	public DefaultListSelectionModel(int mode) {
		this.selectionMode = mode;
	}

	public int getSelectionMode() {
		return selectionMode;
	}

	public void setSelectionMode(int selectionMode) {
		this.selectionMode = selectionMode;
	}

	public int getSelectedRow() {
		return selectedRow;
	}

	public int getSelectedColumn() {
		return selectedColumn;
	}

	public void setSelectedColumn(int selectedColumn) {
		this.selectedColumn = selectedColumn;
	}

	public int getSelectedRowCount() {
		return selectedRow > -1 ? 1 : 0;
	}

	public void addListSelectionListener(ListSelectionListener listener) {
		this.addAction(listener.buildAction("onClick", listener.ACTION));
	}

	public void setSelectedRow(int selectedRow) {
		this.selectedRow = selectedRow;
	}

	public void setData(Object value) {
		String[] sellString = (((String[]) value)[0]).split("\\.");
		this.selectedColumn = Integer.parseInt(sellString[0]);
		this.selectedRow = Integer.parseInt(sellString[1]);
	}

	@Override
	public void toHTML(StringBuffer buffer) {
		buffer.append(this.getAction().length() > 0 ? "<input type='hidden' name='" + getName() + "' id='" + getId() + "' data-path='" + getPath() + "' value='" + selectedColumn + "." + selectedRow + "'/>" : "");
	}
}
