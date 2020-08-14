package mandolin.event;

import mandolin.component.HTMLComponent;
import mandolin.impersonator.JComponent;

/**
 * Title: Mandolin
 * Description:
 * Copyright: B3
 * Company: B3
 *
 * @author Niranja Arjuna Jayasinghe
 * @version 1.0
 */
public class ItemEvent implements Event {

	public static final int SELECTED = 1;

	public int getStateChange() {
		return 0;
	}

	public JComponent getSource() {
		return new HTMLComponent(null);
	}
}
