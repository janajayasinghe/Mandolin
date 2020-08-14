package mandolin.container;

import mandolin.border.Border;
import mandolin.component.HTMLComponent;
import mandolin.event.*;
import mandolin.exception.EventNotFoundException;
import mandolin.impersonator.Component;
import mandolin.impersonator.JComponent;
import mandolin.layout.FlowLayout;
import mandolin.layout.Layout;
import mandolin.util.ElementMap;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * Title: Mandolin
 * Description: This class represents behaviours of containers. (example containers are JPanel, JDialog and JFrame)
 * Copyright: B3
 * Company: B3
 *
 * @author Niranja Arjuna Jayasinghe
 * @version 1.0
 */
public abstract class HTMLContainer extends JComponent {

	protected Layout layout; // Default layout is FlowLayout.
	protected ElementMap<String, Component> components; // Children Component.

	public HTMLContainer() {
		this.layout = new FlowLayout();
		this.components = new ElementMap();
		this.border = new Border();
	}

	/**
	 * Sets the layout of this container.
	 * default layout of a any container is FlowLayout.
	 *
	 * @param layout
	 */
	public void setLayout(Layout layout) {
		this.layout = layout;
	}

	public Layout getLayout() {
		return this.layout;
	}

	/**
	 * Adds a component.
	 *
	 * @param component
	 */
	public void add(Component component) {
		if (component != null && component.getId().length() > 0) {
			this.components.add(component.getId(), component);
		} else {
			this.components.add(component);
		}
	}

	/**
	 * Adds elements with a name (name is component.id )
	 *
	 * @param name
	 * @param component
	 */
	public void add(String name, Component component) {
		component.setId(name);
		this.components.add(name, component);
	}

	/**
	 * Adds a component at x position.
	 * Not yet sported.
	 *
	 * @param element
	 * @param x
	 */
	public void add(Component element, int x) {
		this.add(element);
	}

	/**
	 * removes specific component.
	 *
	 * @param component
	 */
	public void remove(Component component) {
		this.components.remove(component);
	}

	/**
	 * Removes all the components.
	 */
	public void removeAll() {
		components.removeAll();
	}

	/**
	 * returns all the components that belong to this container.
	 *
	 * @return
	 */
	public ElementMap<String, Component> getComponents() {
		return this.components;
	}

	/**
	 * returns a component according to the id.
	 * ID can be both int(as an string) or string.
	 *
	 * @param id
	 * @return
	 */
	public Component getComponent(final String id) {
		boolean isSquatter = id.contains("squat.");
		String newId = new String(id);
		if (isSquatter) {
			newId = newId.replace("squat.", "").replace("$", "");
		}
		Component component;
		try {
			component = this.components.get(Integer.parseInt(newId));
		} catch (NumberFormatException e) {
			component = this.components.get(newId);
		}
		if (isSquatter) {
			component = component.getSquatter();
		}
		return component;
	}

	/**
	 * Returns no of components int this  container.
	 *
	 * @return
	 */
	public int getComponentCount() {
		return this.components.size();
	}

	/**
	 * implemented method should return the CSS class of this Object as below
	 * default class + class is assigned by the layout
	 * eg: class='jframe layout.getLayoutClass()'
	 *
	 * @return
	 */
	protected abstract String getLayoutClass();

	/**
	 * Binds data that comes from the front-end for components(only inputs)
	 * dataMap is the object returns from HttpRequest.getparameterMap.
	 * This method is called recursively from top container to the bottom container.
	 *
	 * @param dataMap
	 */
	public final void setData(Map<String, String[]> dataMap) {
		// TODO: improve the performance
		for (Component c : this.components.getValues()) {
			if (c instanceof HTMLComponent) {
				HTMLComponent component = (HTMLComponent) c;
				component.setData(dataMap.get(component.getName()));
			} else {
				HTMLContainer container = (HTMLContainer) c;
				if (container != null) { // TODO : handle null components.
					container.setData(dataMap);
				}
			}
		}
	}

	/**
	 * Fires a method according to the user action. (if a user clicks on a button, button fires JS onClick and send a request to the server)
	 * Then the JFrame.runAction creates a thread (ActionRunner) in order to run this method.
	 * method parameter consist of methodName and the event name(ActionPerformed-ActionEvent)
	 *
	 * @param method
	 * @param firingObject
	 */
	public final void fireAction(String method, Component firingObject) {
		try {
			String[] methodAndEvent = method.split("-");
			Event event = getEventByEventName(methodAndEvent[1], firingObject);
			Method action = HTMLContainer.class.getMethod(methodAndEvent[0], event.getClass());
			action.invoke(this, event);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns the event object.
	 * componentId of a input tag is used to identify the swing components(JButton,JTextField,JRadioButton etc).
	 *
	 * @param eventName
	 * @param firingObject
	 * @return
	 * @throws EventNotFoundException
	 */
	private Event getEventByEventName(String eventName, Component firingObject) throws EventNotFoundException {
		switch (eventName) {
			case "ActionEvent": {
				return new ActionEvent(firingObject);
			}
			case "ListSelectionEvent": {
				return new ListSelectionEvent(firingObject);
			}
			case "TreeSelectionEvent": {
				return new TreeSelectionEvent(firingObject);
			}
			case "DocumentEvent": {
				return new DocumentEvent(firingObject);
			}
			case "KeyEvent": {
				return new KeyEvent(firingObject);
			}
			default: {
				throw new EventNotFoundException();
			}
		}
	}

	/**
	 * Wraps child element's HTML with the container tags.
	 * Children's HTML is generated from layout class
	 * since the arrangement of HTML tags are different from layout to layout.
	 *
	 * @param buffer
	 */
	public void toHTML(StringBuffer buffer) {
		buffer.append(border.getStartTag(this.getId(), getLayoutClass(), ""));
		layout.visit(this, buffer);
		buffer.append(border.getCloseTag());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof HTMLContainer)) return false;

		HTMLContainer container = (HTMLContainer) o;

		return id.equals(container.id);
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}

	// ================================================
	// TODO : remove following methods and find a way to invoke superclass method

	public void actionPerformed(ActionEvent e) {
	}

	public void valueChanged(ListSelectionEvent e) {
	}

	public void valueChanged(TreeSelectionEvent e) {
	}

	public void insertUpdate(DocumentEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
	}
}
