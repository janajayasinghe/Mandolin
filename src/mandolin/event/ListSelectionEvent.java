package mandolin.event;

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
public class ListSelectionEvent implements Event {

	protected Component firingObject; // componentID (that comes from the front-end) is assigned by the container.fireAction

	public ListSelectionEvent(Component firingObject) {
		this.firingObject = firingObject;
	}

	public Component getSource() {
		return firingObject;
	}
}
