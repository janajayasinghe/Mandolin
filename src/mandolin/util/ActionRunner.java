package mandolin.util;

import mandolin.container.HTMLContainer;
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
public class ActionRunner extends Thread {

	private HTMLContainer container;
	private String method;
	private Component firingObject;

	public ActionRunner(HTMLContainer container, String method, Component firingObject) {
		this.container = container;
		this.method = method;
		this.firingObject = firingObject;
	}

	@Override
	public void run() {
		container.fireAction(method, firingObject);
	}

}
