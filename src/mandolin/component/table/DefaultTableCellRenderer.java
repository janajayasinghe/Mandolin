package mandolin.component.table;

import mandolin.component.JLabel;
import mandolin.component.JTable;
import mandolin.impersonator.Component;

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
public class DefaultTableCellRenderer extends JLabel implements TableCellRenderer {

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		setText((String) value);
		setBackground(Color.magenta);
		return this;
	}
}
