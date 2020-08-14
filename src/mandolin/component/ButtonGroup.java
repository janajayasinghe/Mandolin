package mandolin.component;

import mandolin.util.RandomString;

/**
 * Title: Mandolin
 * Description:
 * Copyright: B3
 * Company: B3
 *
 * @author Niranja Arjuna Jayasinghe
 * @version 1.0
 */
public class ButtonGroup {

	private String name;

	public ButtonGroup() {
		this.name = getRandomName();
	}

	public void add(JRadioButton button) {
		button.setName(name);
	}

	public String getRandomName() {
		return "component-" + RandomString.getRandomID();
	}
}
