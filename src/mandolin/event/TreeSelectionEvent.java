package mandolin.event;

import mandolin.impersonator.Component;

/**
 * Created by caresys on 7/5/2020.
 */
public class TreeSelectionEvent implements Event {

	protected Component firingObject; // componentID (that comes from the front-end) is assigned by the container.fireAction

	public TreeSelectionEvent(Component firingObject) {
		this.firingObject = firingObject;
	}

	public Component getSource() {
		return firingObject;
	}
}
