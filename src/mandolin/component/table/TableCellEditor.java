package mandolin.component.table;

import mandolin.component.JTable;
import mandolin.impersonator.Component;

/**
 * Title: Mandolin
 * Description:
 * Copyright: B3
 * Company: B3
 *
 * @author Niranja Arjuna Jayasinghe
 * @version 1.0
 */
public interface TableCellEditor extends CellEditor {

	Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column);

}
