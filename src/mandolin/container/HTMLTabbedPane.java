package mandolin.container;

import mandolin.component.HiddenComponent;
import mandolin.impersonator.Component;

/**
 * Title: Mandolin
 * Description: Tabbed pane
 * Copyright: B3
 * Company: B3
 *
 * @author Niranja Arjuna Jayasinghe
 * @version 1.0
 */
public class HTMLTabbedPane extends HTMLContainer {

	private JTabbedPane jTabbedPane;
	private HiddenComponent selectionModel; // TODO:  Whenever a user refresh the page, selected tab will be selected only if he/she has fired an action.
	private boolean isVertical;

	public HTMLTabbedPane(JTabbedPane jTabbedPane) {
		this.jTabbedPane = jTabbedPane;
		this.selectionModel = jTabbedPane.getSelectionModel();
		this.isVertical = jTabbedPane.getDirection() == JTabbedPane.VERTICAL;
	}

	@Override
	protected String getLayoutClass() {
		return (this.isVertical ? "jtabbedpane-v " : "jtabbedpane ") + layout.getLayoutClass();
	}

	@Override
	public void toHTML(StringBuffer buffer) {
		buffer.append(border.getStartTag(jTabbedPane.getId() + "-0", getLayoutClass(), ""));
		buffer.append("<div class='tab'>");
		int i = 0;
		for (String link : components.getKeys()) {
			String HClass = (selectionModel.getValue().equals("" + i) ? "tablinks active" : "tablinks");
			buffer.append("<button class='" + HClass + "' onclick=\"showTab(event,'" + jTabbedPane.getId() + "','" + selectionModel.getName() + "'," + i + ")\">" + link + "</button>");
			i++;
		}
		buffer.append("</div>");
		i = 0;
		for (Component component : components.getValues()) {
			component.setPath(i, this);
			buffer.append("<div class='" + (selectionModel.getValue().equals("" + i) ? "tab-content active" : "tab-content") + "'>");
			component.toHTML(buffer);
			buffer.append("</div>");
			i++;
		}
		buffer.append(border.getCloseTag());
	}
}
