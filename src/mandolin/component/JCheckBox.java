package mandolin.component;

import mandolin.Listener.ActionListener;

/**
 * Title: Mandolin
 * Description:
 * Copyright: B3
 * Company: B3
 *
 * @author Niranja Arjuna Jayasinghe
 * @version 1.0
 */
public class JCheckBox extends HTMLComponent {

	protected String text;
	protected boolean isSelected = false;

	public JCheckBox() {
	}

	public JCheckBox(String text) {
		this(text, false);
	}

	public JCheckBox(String text, boolean isSelected) {
		this.text = text;
		this.isSelected = isSelected;
	}

	public void addActionListener(ActionListener actionListener) {
		this.addAction(actionListener.buildAction("onClick",actionListener.ACTION_AE));
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean selected) {
		this.isSelected = selected;
	}

	public void setHorizontalAlignment(int h) {
	}

	public void doClick() {
	}

	@Override
	public void setData(Object value) {
		String res = value != null ? ((String[]) value)[0] : "";
		setSelected(!res.isEmpty() && res.equals(getId()));
	}

	@Override
	public void toHTML(StringBuffer buffer) {
		buffer.append("<input type='checkbox' name='" + this.getName() + "' id='" + this.getId() + "' data-id='" + this.getId() + "'" +
				" class='jcheckbox" + (!isEnabled() ? " disabled" : "") + "' " +
				(!isEnabled() ? " disabled " : "") +
				this.getAction() +
				" value='" + this.getId() + "' " + (isSelected() ? "checked" : "") +
				" data-path='" + getPath() + "'/>\n" +
				"<label for='" + this.getId() + "'> " + text + "</label>");
	}
}
