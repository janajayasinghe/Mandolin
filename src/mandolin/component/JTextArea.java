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
public class JTextArea extends HTMLInput {

	protected String text = "";
	private int rows = 3;
	private int columns = 20;

	public JTextArea() {
		this("");
	}

	public JTextArea(String text) {
		this.text = text;
		this.document = new PlainDocument();
	}

	public JTextArea(int rows, int columns) {
		this.document = new PlainDocument();
		this.rows = rows;
		this.columns = columns;
	}

	public void setLineWrap(boolean wrap) {
	}

	public void setWrapStyleWord(boolean wrapStyle) {
	}

	public void setEditable(boolean editable) {
	}

	public String getText() {
		return this.text != null ? this.text : "";
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setData(Object value) {
		String res = value != null ? ((String[]) value)[0] : "";
		setText(res);
	}

	@Override
	public void toHTML(StringBuffer buffer) {
		buffer.append("<textarea name='" + getName() + "' id='" + getId() + "' data-id='" + getId() + "' class='jtextfield'  data-path='" + getPath() + "'" +
				" style='" + this.getBGColor() + "' " + this.getAction() + this.getDocument().getAction() +
				" cols='" + this.columns + "'" +
				" rows='" + this.rows + "'" +
				"/>" +
				getText() +
				"</textarea>");
	}
}
