package mandolin.component;

/**
 * Title: Mandolin
 * Description: This is a hidden input.
 * Copyright: B3
 * Company: B3
 *
 * @author Niranja Arjuna Jayasinghe
 * @version 1.0
 */
public class HiddenComponent extends HTMLComponent {

	private String value = "";

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setData(Object value) {
		this.value = value != null ? ((String[]) value)[0] : "";
	}

	@Override
	public void toHTML(StringBuffer buffer) {
		buffer.append("<input type='hidden' name='" + getName() + "' id='" + getId() + "' data-path='" + getPath() + "' value='" + value + "'/>");
	}
}
