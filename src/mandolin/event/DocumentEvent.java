package mandolin.event;

import mandolin.impersonator.Component;

/**
 * Created by caresys on 7/8/2020.
 */
public class DocumentEvent implements Event {

	protected Component firingObject;

	public DocumentEvent(Component firingObject) {
		this.firingObject = firingObject;
	}

	public Component getDocument() {
		return firingObject;
	}
}
