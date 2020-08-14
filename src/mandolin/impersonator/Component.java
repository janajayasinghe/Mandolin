package mandolin.impersonator;

import mandolin.Listener.Listener;
import mandolin.container.HTMLContainer;
import mandolin.util.RandomString;

/**
 * Title: Mandolin
 * Description: Example Servlet
 * Copyright: B3
 * Company: B3
 *
 * @author Niranja Arjuna Jayasinghe
 * @version 1.0
 */
public abstract class Component implements Cloneable {
	protected String path = ""; // Path is derived from Components' ids.
	protected String id = "";
	protected Component squatter;
	protected boolean isVisible = true;

	public String getId() {
		return id;
	}

	/**
	 * Returns ID without $ prefix.
	 *
	 * @return
	 */
	public String getIdWithoutPrefix() {
		if (id.startsWith("$")) {
			return id.substring(1);
		}
		return id;
	}

	/**
	 * Sets an ID
	 * If the Component is a subclass of any listener class then add "$" as prefix.
	 *
	 * @param id
	 */
	public void setId(String id) {
		if (this instanceof Listener && !id.startsWith("$")) {
			id = "$" + id;
		}
		this.id = id;
	}

	public void setName(String name) {
	}

	/**
	 * This method is called by container.setData
	 *
	 * @param value
	 */
	public void setData(Object value) {
	}

	/**
	 * If an ID is not assigned, a random Id will be generated for the element.
	 */
	protected void generateID() {
		id = (id != null && !id.isEmpty()) ? id : RandomString.getRandomID();
	}

	public abstract void toHTML(StringBuffer buffer);

	/**
	 * Sets the path that is used to send as a page via  URL param. This should be done at the page rendering stage.
	 *
	 * @param index
	 * @param parentContainer
	 */
	public final void setPath(String index, Component parentContainer) {
		String parentPath = parentContainer.getPath();
		String compId = this.getId();
		String newId = (parentPath.length() > 0 ? parentPath + "-" : "");
		String cId = (compId.length() == 0 ? index : compId);
		this.path = newId + cId;
		if (this.squatter != null) {
			this.squatter.setId("squat." + cId);
			this.squatter.setPath(newId + this.squatter.getId());
		}
	}

	public final void setPath(int index, Component parentContainer) {
		this.setPath("" + index, parentContainer);
	}

	public final void setPath(String path) {
		this.path = path;
	}

	/**
	 * Path is derived from Components' ids.
	 *
	 * @return
	 */
	public final String getPath() {
		return path;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean visible) {
		isVisible = visible;
	}

	public Component getSquatter() {
		return squatter;
	}

	public void setSquatter(HTMLContainer squatter) {
		this.squatter = squatter;
	}

	public Component clone() throws CloneNotSupportedException {
		return (Component) super.clone();
	}
}
