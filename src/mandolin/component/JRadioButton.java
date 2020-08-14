package mandolin.component;


import mandolin.Listener.ActionListener;
import mandolin.Listener.ItemListener;

/**
 * Title: Mandolin
 * Description:
 * Copyright: B3
 * Company: B3
 *
 * @author Niranja Arjuna Jayasinghe
 * @version 1.0
 */
public class JRadioButton extends HTMLComponent {
	protected String text = "";
	protected boolean selected = false;

	public JRadioButton() {
	}

	public JRadioButton(String text) {
		this.text = text;
	}

	public JRadioButton(String text, boolean selected) {
		this.text = text;
		this.selected = selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public void addActionListener(ActionListener actionListener) {
		this.addAction(actionListener.buildAction("onClick", actionListener.ACTION_AE));
	}

	public void addItemListener(ItemListener itemListener) {
	}

	public boolean isSelected() {
		return selected;
	}

	public void setData(Object value) {
		String res = value != null ? ((String[]) value)[0] : "";
		setSelected(!res.isEmpty() && res.equals(getId()));
	}

	@Override
	public void toHTML(StringBuffer buffer) {
		buffer.append("<input type='radio' name='" + this.getName() + "' id='" + this.getId() + "' data-id='" + this.getId() + "' class='jradiobutton'" + this.getAction() + " value='" + this.getId() + "' " + (isSelected() ? "checked" : "") + " data-path='" + getPath() + "'/>" +
				"<label for='" + this.getId() + "'>" + text + "</label>");
	}
}
