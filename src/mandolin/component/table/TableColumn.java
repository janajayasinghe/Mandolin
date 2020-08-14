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
public class TableColumn extends HTMLContainer {

	private int width;
	private int index;
	private TableCellRenderer cellRenderer;
	private TableCellRenderer headerRenderer;
	private AbstractCellEditor cellEditor;

	public TableColumn(int index, int width, TableCellRenderer cellRenderer, AbstractCellEditor cellEditor) {
		super();
		this.index = index;
		this.width = width;
		this.cellRenderer = cellRenderer;
		this.setCellEditor(cellEditor);
	}

	public void setPreferredWidth(int width) {
		this.width = width;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public TableCellRenderer getCellRenderer() {
		return cellRenderer;
	}

	public void setCellRenderer(TableCellRenderer cellRenderer) {
		this.cellRenderer = cellRenderer;
	}

	public AbstractCellEditor getCellEditor() {
		return cellEditor;
	}

	public void setCellEditor(AbstractCellEditor cellEditor) {
		this.cellEditor = cellEditor;
		if (cellEditor != null) {
			cellEditor.setId("cellEditor");
			this.add(this.cellEditor);
		}
	}

	public void setResizable(boolean resizable) {
	}

	public void setMaxWidth(int maxWidth) {
	}

	public void setHeaderRenderer(TableCellRenderer renderer) {
		this.headerRenderer = renderer;
	}

	public TableCellRenderer getHeaderRenderer() {
		return this.headerRenderer;
	}

	public int getModelIndex() {
		return index;
	}

	@Override
	protected String getLayoutClass() {
		return null;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof TableColumn)) return false;
		if (!super.equals(o)) return false;

		TableColumn that = (TableColumn) o;

		return index == that.index;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + index;
		return result;
	}

	public void toHTML(StringBuffer buffer) {
	}
}
