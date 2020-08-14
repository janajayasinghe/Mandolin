package mandolin.component;

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
public class JLabel extends HTMLComponent {

	public static final int CENTER = 0;

	private String text;
	protected Component labelFor = null;
	protected boolean visible = true;

	public JLabel() {
		this("");
	}

	public JLabel(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public JLabel(String label, int alignment) {
		this.text = label;
	}

	public void setLabelFor(Component HTMLComponent) {
		labelFor = HTMLComponent;
	}

	public void setIcon(Object icon) {
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.isVisible = visible;
	}

	public void setHorizontalTextPosition(int position) {
	}

	public String getCSSClass() {
		return "jlabel" + (!visible ? " invisible" : "");
	}

	@Override
	public void toHTML(StringBuffer buffer) {
		buffer.append("<label " + (labelFor != null ? "for='" + labelFor.getId()+"'" : "") + " class='" + getCSSClass() + "' style='"+getBGColor()+"'> " + text + "</label>");
	}
}
