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
public class JButton extends HTMLComponent {

	protected String text = "	";

	public JButton() {
		this(null);
	}

	public JButton(String text) {
		this.text = text;
	}

	public void addActionListener(ActionListener actionListener) {
		this.addAction(actionListener.buildAction("onClick", actionListener.ACTION_AE));
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	@Override
	public void toHTML(StringBuffer buffer) {
		buffer.append("<input type='button' name ='" + getName() + "' id='" + getId() + "'" + " data-id='" + getId() + "'" +
				" class='jbutton" + (!isEnabled() ? " disabled" : "") + "' " +
				(!isEnabled() ? " disabled " : "") +
				this.getAction() +
				" style='" + this.getBGColor() + "'" +
				" value='" + text + "' data-path='" + getPath() + "' " + this.getToolTipText() + "/>");
		if(this.squatter !=  null && this.squatter.isVisible()) {
			this.squatter.toHTML(buffer);
		}
	}
}
