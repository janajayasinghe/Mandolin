package mandolin.component;

/**
 * Created by caresys on 8/8/2020.
 */
public class AButton extends JButton {

	public AButton(String text) {
		this.text = text;
	}

	@Override
	public void toHTML(StringBuffer buffer) {
		buffer.append("<a id='" + getId() + "'" + " data-id='" + getId() + "'" +
				" class='abutton" + (!isEnabled() ? " disabled" : "") + "' " +
				(!isEnabled() ? " disabled " : "") +
				this.getAction() +
				" data-path='" + getPath() + "'/>");
		buffer.append(text);
		buffer.append("</a>");
	}
}
