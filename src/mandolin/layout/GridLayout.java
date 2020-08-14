package mandolin.layout;

import mandolin.container.HTMLContainer;
import mandolin.impersonator.Component;

/**
 * Title: Mandolin
 * Description: GridLayout arranges components as a table.
 * Currently number of rows are ignored.
 * Copyright: B3
 * Company: B3
 *
 * @author Niranja Arjuna Jayasinghe
 * @version 1.0
 */
public class GridLayout implements Layout {

	private int rows;
	private int columns;

	public GridLayout(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
	}

	@Override
	public String getLayoutClass() {
		return "grid-layout";
	}

	@Override
	public String getContainerClass() {
		return "grid-layout-container";
	}

	@Override
	public String getChildClass() {
		return "grid-layout-child";
	}

	/**
	 * Returns HTML of child elements
	 *
	 * @return
	 */
	@Override
	public final void visit(HTMLContainer container, StringBuffer buffer) {
		int i = 0;
		int startTagCount = 0;
		int endTagCount = 0;
		int compCount = 0;
		for (Component comp : container.getComponents().getValues()) {
			if (comp != null) { // TODO: handle null objects.
				if (i % this.columns == 0) {
					buffer.append("<div class='" + this.getContainerClass() + "'>");
					startTagCount++;
				}
				comp.setPath(compCount, container);
				buffer.append("<div class='" + this.getChildClass() + "'>");
				comp.toHTML(buffer);
				buffer.append("</div>");
				if (i % this.columns == (this.columns - 1)) {
					buffer.append("</div>");
					endTagCount++;
				}
				i++;
			}
			compCount++;
		}
		if (startTagCount != endTagCount) {
			buffer.append("</div>");
		}
	}
}
