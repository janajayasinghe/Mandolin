package mandolin.component.table;

import mandolin.container.HTMLContainer;


/**
 * Title: Mandolin
 * Description:
 * Copyright: B3
 * Company: B3
 *
 * @author Niranja Arjuna Jayasinghe
 * @version 1.0
 */
public class TableColumnModel extends HTMLContainer {

	public TableColumn getColumn(int col) {
		return (TableColumn) components.get("" + col);
	}

	public int getColumnCount() {
		return components.size();
	}

	public void addColumn(TableColumn column) {
		components.add("" + column.getIndex(), column);
	}

	public void removeColumn(TableColumn column) {
		components.remove(column);
	}

	public int getColumnIndexAtX(int x) {
		return 0;
	}

	@Override
	protected String getLayoutClass() {
		return null;
	}

	public String toHTML() {
		return "";
	}
}
