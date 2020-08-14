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
public class JSpinner extends HTMLComponent {
	private SpinnerNumberModel model;
	private Number value;

	public JSpinner() {
		this.model = new SpinnerNumberModel();
	}

	public JSpinner(SpinnerNumberModel model) {
		this.model = model;
	}

	public Number getValue() {
		return value;
	}

	public void setData(Object value) {
		String res = value != null ? ((String[]) value)[0] : "";
		this.value = Integer.valueOf(res);
	}

	@Override
	public void toHTML(StringBuffer buffer) {
		buffer.append("<input type='number' id='" + getId() + "' name='" + getName() + "' step='" + model.getStepSize() + "'" +
				" min='" + this.model.getMinimum() + "'" +
				" min='" + this.model.getMaximum() + "'" +
				" value='" + this.value + "'" +
				">");
	}
}
