package mandolin.component;

import mandolin.component.table.*;
import mandolin.container.HTMLContainer;
import mandolin.impersonator.Component;
import mandolin.util.Dimension;

import javax.swing.table.AbstractTableModel;
import java.awt.*;

/**
 * Title: Mandolin
 * Description:
 * Copyright: B3
 * Company: B3
 *
 * @author Niranja Arjuna Jayasinghe
 * @version 1.0
 */
public class JTable extends HTMLContainer {

	private int rowHeight;
	private AbstractTableModel model;
	private HTMLTable htmlTable;
	private DefaultListSelectionModel selectionModel = null;
	private TableColumnModel tableColumnModel = null;
	private JTableHeader tableHeader;

	public JTable(AbstractTableModel model) {
		this.model = model;
		this.tableColumnModel = new TableColumnModel();
		this.initColumnModel();
		this.tableHeader = new JTableHeader(this);
		this.selectionModel = new DefaultListSelectionModel();
		this.htmlTable = new HTMLTable(this);
		super.add(htmlTable);
		super.add(selectionModel);
		super.add(tableColumnModel);
	}

	/**
	 * Creates tableColumnModel
	 */
	private void initColumnModel() {
		for (int i = 0; i < this.model.getColumnCount(); i++) {
			this.tableColumnModel.addColumn(new TableColumn(i, 0, null, null));
		}
	}

	public DefaultListSelectionModel getSelectionModel() {
		return this.selectionModel;
	}

	public void setSelectionMode(int mode) {
		this.selectionModel.setSelectionMode(mode);
	}

	public int getRowHeight() {
		return this.rowHeight;
	}

	public int getSelectedRowCount() {
		return this.selectionModel.getSelectedRowCount();
	}

	public int getSelectedRow() {
		return this.selectionModel.getSelectedRow();
	}

	public void setRowSelectionInterval(int row, int column) {
	}

	public AbstractTableModel getModel() {
		return this.model;
	}

	public void setPreferredScrollableViewportSize(Dimension dimension) {
	}

	public void setAutoCreateColumnsFromModel(boolean autoCreate) {
	}

	public Color getSelectionBackground() {
		return null;
	}

	public String getColumnName(int col) {
		return this.model.getColumnName(col);
	}

	public int getColumnCount() {
		return this.model.getColumnCount();
	}

	public boolean isEditing() {
		return false;
	}

	public TableCellEditor getCellEditor() {
		return null;
	}

	public void setColumnModel(TableColumnModel columnModel) {
		this.tableColumnModel = columnModel;
	}

	public final void add(Component element) {
	}

	public final void add(String name, Component element) {
	}

	public final void add(Component element, int x) {
	}

	public int getRowCount() {
		return this.model.getRowCount();
	}

	public TableColumnModel getColumnModel() {
		return this.tableColumnModel;
	}

	public JTableHeader getTableHeader() {
		return this.tableHeader;
	}

	public boolean isCellEditable(int row, int col) {
		return false;
	}

	protected String getLayoutClass() {
		return "jtable-holder";
	}

	@Override
	public void toHTML(StringBuffer buffer) {
		super.toHTML(buffer);
	}
}
