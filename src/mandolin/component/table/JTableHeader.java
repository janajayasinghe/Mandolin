package mandolin.component.table;

import mandolin.component.HTMLComponent;
import mandolin.component.JTable;
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
public class JTableHeader extends HTMLComponent {

	private JTable table;
	private AbstractTableModel model;
	private TableColumnModel tableColumnModel;
	private boolean updateRealTime;

	public JTableHeader(JTable table) {
		this.table = table;
		this.model = table.getModel();
		this.tableColumnModel = table.getColumnModel();
	}

	public void setUpdateTableInRealTime(boolean updateRealTime) {
		this.updateRealTime = updateRealTime;
	}

	@Override
	public void toHTML(StringBuffer buffer) {
		buffer.append("<thead><tr class=\"header\">\n");
		if (table.getSelectionModel().getAction().length() > 0) {
			buffer.append("<th></th>\n");
		}
		for (int i = 0; i < this.model.getColumnCount(); i++) {
			TableCellRenderer cellRenderer = tableColumnModel.getColumn(i).getHeaderRenderer();
			if (cellRenderer != null) {
				Component comp = cellRenderer.getTableCellRendererComponent(this.table, this.model.getColumnName(i), false, false, 0, i);
				buffer.append("<th>");
				comp.toHTML(buffer);
				buffer.append("</th>\n");
			} else {
				buffer.append("<th>" + this.model.getColumnName(i) + "</th>\n");
			}
		}
		buffer.append("</tr></thead>\n");
	}
}
