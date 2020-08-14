package mandolin.layout;

import mandolin.container.HTMLContainer;
import mandolin.impersonator.Component;

/**
 * Title: Mandolin
 * Description: FlowLayout arranges components to a one direction(horizontal or vertical)
 * Copyright: B3
 * Company: B3
 *
 * @author Niranja Arjuna Jayasinghe
 * @version 1.0
 */
public class FlowLayout implements Layout {

	public static final int HORIZONTAL = 0;
	public static final int VERTICAL = 1;
	public static final int FLOAT_LEFT = 0;
	public static final int FLOAT_RIGHT = 1;

	private int direction;
	private int floatContent;

	public FlowLayout() {
		this.direction = HORIZONTAL;
		this.floatContent = FLOAT_LEFT;
	}

	public FlowLayout(int direction) {
		this.direction = direction;
	}

	public FlowLayout(int direction, int floatContent) {
		this.direction = direction;
		this.floatContent = floatContent;
	}

	public String getLayoutClass() {
		return "flow-layout" + (floatContent == FLOAT_RIGHT ? " float-right" : "");
	}

	@Override
	public String getContainerClass() {
		return "flow-layout-container";
	}

	public String getChildClass() {
		return "flow-layout-child";
	}

	/**
	 * Returns HTML of child elements
	 *
	 * @return
	 */
	@Override
	public final void visit(HTMLContainer container, StringBuffer buffer) {
		if (this.direction == HORIZONTAL) {
			buffer.append("<div class='" + this.getContainerClass() + "'>");
		}
		int compCount = 0;
		for (Component comp : container.getComponents().getValues()) {
			if (comp != null) { // TODO: handle null objects.
				if (this.direction == VERTICAL) {
					buffer.append("<div class='" + this.getContainerClass() + "'>");
				}
				comp.setPath(compCount, container);
				buffer.append("<div class='" + this.getChildClass() + "'>");
				comp.toHTML(buffer);
				buffer.append("</div>");
				if (this.direction == VERTICAL) {
					buffer.append("</div>");
				}
			}
			compCount++;
		}
		if (this.direction == HORIZONTAL) {
			buffer.append("</div>");
		}
	}
}
