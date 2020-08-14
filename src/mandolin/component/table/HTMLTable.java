package mandolin.component.table;

import mandolin.component.HTMLComponent;
import mandolin.component.JTable;
import mandolin.container.CellEditorButton;
import mandolin.impersonator.Component;

import javax.swing.table.AbstractTableModel;

/**
 * Title: Mandolin
 * Description:
 * Copyright: B3
 * Company: B3
 *
 * @author Niranja Arjuna Jayasinghe
 * @version 1.0
 */
public class HTMLTable extends HTMLComponent {

	private JTable table;
	private AbstractTableModel model;
	private JTableHeader tableHeader;
	private DefaultListSelectionModel selectionModel;
	private TableColumnModel tableColumnModel;

	public HTMLTable(JTable table) {
		this.table = table;
		this.model = table.getModel();
		this.tableHeader = table.getTableHeader();
		this.tableColumnModel = table.getColumnModel();
		this.selectionModel = table.getSelectionModel();
		this.selectionModel.setId(this.getId() + ".selection.model");
	}

	public DefaultListSelectionModel getSelectionModel() {
		return selectionModel;
	}

	public void setSelectionModel(DefaultListSelectionModel selectionModel) {
		this.selectionModel = selectionModel;
	}

	@Override
	public void toHTML(StringBuffer buffer) {
		tableColumnModel.setPath(2, table);

		buffer.append("<table id='" + this.getId() + "' class='jtable' cellpadding=4 width=100% border=0 cellspacing=1>\n");
		// Headers
		tableHeader.toHTML(buffer);
		//Body
		buffer.append("<tbody>\n");
		for (int i = 0; i < model.getRowCount(); i++) {
			boolean isRowSelected = selectionModel.getSelectedRow() == i;
			buffer.append("<tr class='" + (isRowSelected ? "selected" : "") + "'>\n");
			if (selectionModel.getAction().length() > 0) {
				buffer.append("<td data-col='-1' data-row='" + i + "'><input type='checkbox' " + selectionModel.getAction() + " " + (isRowSelected ? "checked " : "") +
						"data-id='" + getId() + "-" + i + "' " +
						"data-path='" + getPath() + "' " +
						"></td>");
			}
			for (int j = 0; j < model.getColumnCount(); j++) {
				boolean isColumnSelected = selectionModel.getSelectedColumn() == j;
				TableColumn column = tableColumnModel.getColumn(j);
				column.setPath(j, tableColumnModel);

				buffer.append("<td data-col='" + j + "' data-row='" + i + "'>");
				StringBuffer cellText = new StringBuffer();
				TableCellRenderer cellRenderer = column.getCellRenderer();
				if (cellRenderer != null) { // Apply renderer
					Component comp = cellRenderer.getTableCellRendererComponent(this.table, model.getValueAt(i, j), false, false, i, j);
					comp.toHTML(cellText);
				} else {
					cellText.append(model.getValueAt(i, j));
				}

				AbstractCellEditor cellEditor = column.getCellEditor();
				if (cellEditor != null && model.isCellEditable(i, j)) {  // Sets cell editor
					cellEditor.setPath(j, column);

					String compId = i + "." + j;
					String cellEditorID = compId + ".editor";

					CellEditorButton cellEditorButton = (CellEditorButton) cellEditor.getComponent("$" + cellEditorID);
					if (cellEditorButton == null) {
						cellEditorButton = new CellEditorButton(selectionModel,j, i, cellText.toString());
						cellEditorButton.setId(cellEditorID);
						cellEditorButton.setPath(cellEditorID, cellEditor);
						cellEditor.add(cellEditorButton.getId(), cellEditorButton);
					}
					if(isRowSelected && isColumnSelected){
						// Since the getTableCellEditorComponent return same object always, HiddenComponents(one component for one row) will be added to the CellEditor,
						// in order to save the value at the backend.
						Component editorComp = cellEditor.getTableCellEditorComponent(table, model.getValueAt(i, j), true, i, j);
						editorComp.setName(compId);
						editorComp.setId(compId);
						editorComp.setPath(editorComp.getId(), cellEditor);

						cellEditorButton.setEditorComponent(editorComp);
						cellEditor.add(compId, editorComp);
					} else {
						cellEditorButton.setEditorComponent(null);
					}
					cellEditorButton.toHTML(buffer);
				} else {
					buffer.append(cellText.toString());
				}
				buffer.append("</td>\n");
			}
			buffer.append("</tr>\n");
		}
		buffer.append("</tbody></table>\n");
	}
}
