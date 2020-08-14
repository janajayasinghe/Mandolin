package mandolin.container;

import mandolin.component.HTMLComponent;
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
public class JPopupMenu extends HTMLContainer {

	protected boolean isVisible = false;
	protected Component invoker;

	public JPopupMenu() {
	}

	public JMenuItem add(String s) {
		return add(new JMenuItem(s));
	}

	public JMenuItem add(JMenuItem component) {
		super.add(component);
		return component;
	}

	public void addSeparator() {
		super.add(new JPopupMenu.Separator());
	}

	public void show(Component invoker, int x, int y) {
		this.invoker = invoker;
		this.isVisible = true;
		this.setId("squat."+invoker.getId());
		invoker.setSquatter(this);
	}

	@Override
	protected String getLayoutClass() {
		return "jpopupmenu ";
	}

	@Override
	public void toHTML(StringBuffer buffer) {
		buffer.append("<div class='jpopupmenu-model' onClick='closePopupMenu(this)'></div>");
		if (this.components.size() > 0) {
			buffer.append("<ul id='" + getId() + "' class='" + this.getLayoutClass() + "' >\n");
			int compCount = 0;
			for (Component com : this.components.getValues()) {
				com.setPath(compCount, this);
				com.toHTML(buffer);
				compCount++;
			}
			buffer.append("</ul>");
		}
		this.isVisible = false;
	}

	static public class Separator extends HTMLComponent {
		@Override
		public void toHTML(StringBuffer buffer) {
			buffer.append("<li class='separator'>&nbsp;</li>");
		}
	}
}
