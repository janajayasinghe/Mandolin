package mandolin.container;

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
public class JMenuItem extends HTMLContainerComponent {

	protected String text = "	";

	public JMenuItem() {
		this(null);
	}

	public JMenuItem(String text) {
		this.text = text;
	}

	/**
	 * This method should be called before adding JMenuItem
	 * @param actionCommand
	 */
	public void setActionCommand(String actionCommand) {
		this.id = actionCommand;
	}

	public String getActionCommand() {
		return id;
	}

	@Override
	protected String getLayoutClass() {
		return null;
	}

	@Override
	public void toHTML(StringBuffer buffer) {
		buffer.append("<li>");
		buffer.append("<a data-id='" + this.getId() + "' data-path='" + this.getPath() + "'  " + this.getAction() + ">" + text + "</a>");
		if (this.components.size() > 0) {
			buffer.append("<ul>");
			int compCount = 0;
			for (Component com : this.components.getValues()) {
				com.setPath(compCount, this);
				com.toHTML(buffer);
				compCount++;
			}
			buffer.append("</ul>");
		}
		buffer.append("</li>");
	}
}
