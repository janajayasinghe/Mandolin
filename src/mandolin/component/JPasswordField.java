package mandolin.component;

/**
 * Title: Mandolin
 * Description:
 * Copyright: B3
 * Company: B3
 *
 * @author Niranja Arjuna Jayasinghe
 * @version 1.0
 */
public class JPasswordField extends HTMLInput {
	protected int length = 10;
	protected String text = "";
	protected boolean editable = true;

	public JPasswordField() {
	}

	public JPasswordField(int length) {
	}

	public char[] getPassword() {
		return new char[]{};
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public void toHTML(StringBuffer buffer) {
		buffer.append("<input name='" + getName() + "' id='" + getId() + "' data-id='" + getId() + "' type='password' class='jtextfield' value='" + getText() + "' data-path='" + getPath() + "'/>");
	}
}
