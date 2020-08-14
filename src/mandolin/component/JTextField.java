package mandolin.component;

import mandolin.Listener.ActionListener;
import mandolin.Listener.KeyListener;

/**
 * Title: Mandolin
 * Description:
 * Copyright: B3
 * Company: B3
 *
 * @author Niranja Arjuna Jayasinghe
 * @version 1.0
 */
public class JTextField extends HTMLInput {
	protected int length = 10;
	protected String text = "";
	protected boolean editable = true;

	public JTextField() {
		this.document = new PlainDocument();
	}

	public JTextField(int length) {
		this.length = length;
		this.document = new PlainDocument();
	}

	public JTextField(Document document, String text, int length) {
		this.document = document;
		this.text = text;
		this.length = length;
	}

	public void addActionListener(ActionListener actionListener) {
		this.addAction(actionListener.buildAction("onClick", actionListener.ACTION_AE));
	}

	public void addKeyListener(KeyListener keyListener) {
		this.addAction(keyListener.buildAction("onkeypress", keyListener.ACTION_KP));
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setData(Object value) {
		String res = value != null ? ((String[]) value)[0] : "";
		setText(res);
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	@Override
	public void toHTML(StringBuffer buffer) {
		buffer.append("<input name='" + getName() + "' id='" + getId() + "' data-id='" + getId() + "' type='text' class='jtextfield' value='" + getText() + "' data-path='" + getPath() + "'" +
				" style='" + this.getBGColor() + "' " + this.getAction() + this.getDocument().getAction() +	"/>");
	}
}