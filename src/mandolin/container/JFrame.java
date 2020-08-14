package mandolin.container;

import mandolin.impersonator.Component;
import mandolin.util.ActionRunner;

/**
 * Title: Mandolin
 * Description: This class is the heart of the framework since it fires all the user actions and holds JDialogs.
 * Adding a JFrame(Only one) is mandatory after the HTMLDocument.
 * Copyright: B3
 * Company: B3
 *
 * @author Niranja Arjuna Jayasinghe
 * @version 1.0
 */
public class JFrame extends HTMLContainer {

	/**
	 * Returns Component by name when the method is called from the servlet.
	 * If the page is not present in the frame, new one will be created.
	 * Since the JDialogs always present in the components map, this method will not go into the "if (container == null)".
	 * pageName is the path of the component tree
	 *
	 * @param pageName
	 * @return
	 */
	public final HTMLContainer getPage(String pageName) {
		String page = pageName.split("-")[0];
		HTMLContainer container = (HTMLContainer) this.components.get(page);
		if (container == null) {
			container = createContainerByName(page);
			this.add(page, container);
		}
		return container;
	}

	/**
	 * This method should be overridden by users. Otherwise blank page will  be rendered.
	 * if the JPanel implements any listener class, page name should start with $ prefix.
	 *
	 * @param pageName
	 * @return
	 */
	public HTMLContainer createContainerByName(String pageName) {
		// Example code
		/*switch (pageName) {
			case "$EmployeePanel": {
				return new EmployeePanel(frame, userNo);
			}
			default: {
				return null;
			}
		*/
		return null;
	}

	/**
	 * This method is called from Servlet when it receives a user action(eg; button click).
	 * Each user action runs on a thread in order to support JDialog rendering.Most of the time JDialogs are created in side methods like ActionPerformed.
	 * Then the JDialog should be rendered at runtime and main thread should be waited until user perform actions since method execution depends on user inputs on the JDialog.
	 * Following while loop pause the main thread by checking the actionRunner thread's status. when a method(eg: actionPerformed) is run by actionRunner adds a JDialog to the component list, actionRunner status is changed to the sleep mode.
	 * and method completes its execution and renders the HTML page. container(1st parameter) will be locked by the JDialog until the JDialog.dispose method called.
	 *
	 * @param container
	 * @param method
	 * @param firingObject
	 */
	public final void runAction(HTMLContainer container, String method, Component firingObject ) {
		ActionRunner actionRunner = new ActionRunner(container, method, firingObject);
		actionRunner.start();

		// Pause the main thread until the task(ActionRunner) is completed or sleep.
		while (actionRunner.getState() == Thread.State.NEW || actionRunner.getState() == Thread.State.RUNNABLE) {
			//Waiting until the task is completed or status == sleep
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * returns the CSS class of this Object
	 * class='jframe layoutclass'
	 * jframe = default class
	 * layoutClass = class is assigned by the layout
	 *
	 * @return
	 */
	protected String getLayoutClass() {
		return "jframe " + layout.getLayoutClass();
	}

}
