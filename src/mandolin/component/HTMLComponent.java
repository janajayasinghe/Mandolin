package mandolin.component;

import mandolin.impersonator.Component;
import mandolin.impersonator.JComponent;

import java.util.ArrayList;

/**
 * Title: Mandolin
 * Description: This class represents behaviours of components. (example components are JButton, JTextArea and JComboBox)
 * Copyright: B3
 * Company: B3
 *
 * @author Niranja Arjuna Jayasinghe
 * @version 1.0
 */
public class HTMLComponent extends JComponent {

	protected String name;
	protected ArrayList<String> actions; //action is assigned from listeners
	protected boolean enabled = true;
	protected String toolTipText = "";

	public HTMLComponent() {
		generateID(); // assigns an random ID
		actions = new ArrayList<>();
	}

	/**
	 * This constructor is called from ActionEvent.getSource() only.
	 *
	 * @param id
	 */
	public HTMLComponent(String id) {
		this.id = id;
	}

	public final void setName(String name) {
		this.name = name;
	}

	/**
	 * if a name is not assigned, ID will be assigned to the name.
	 *
	 * @return
	 */
	public final String getName() {
		name = name != null ? name : getId();
		return name;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public final void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getToolTipText() {
		return (this.toolTipText.length() > 0 ? "title='" + this.toolTipText + "'" : "");
	}

	public void setToolTipText(String toolTipText) {
		this.toolTipText = toolTipText;
	}

	public void addAction(String act) {
		this.actions.add(act);
	}

	public String getAction() {
		String actionString = "";
		for (String act : actions) {
			actionString += act + " ";
		}
		return actionString;
	}

	@Override
	public final void add(Component component) {
		// DO Not Support
	}

	/**
	 * data-id and data-path should be presented in the component in order to perform an action added by listeners.
	 *
	 * @return
	 */
	@Override
	public void toHTML(StringBuffer buffer) {
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof HTMLComponent)) return false;

		HTMLComponent that = (HTMLComponent) o;

		return id != null ? id.equals(that.id) : that.id == null;
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}

}
