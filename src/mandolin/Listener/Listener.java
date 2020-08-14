package mandolin.Listener;

/**
 * Title: Mandolin
 * Description: Every Listener should have constant(s) ACTION_X. eg: methodNameEventName
 * Copyright: B3
 * Company: B3
 *
 * @author Niranja Arjuna Jayasinghe
 * @version 1.0
 */
public interface Listener {

	default String buildAction(String htmlEvent, String action) {
		return this.buildAction(htmlEvent, action, null);
	}

	default String buildAction(String htmlEvent, String action, String funcParam) {
		return htmlEvent + "=\"" + action + (funcParam != null ? "(" + funcParam + ");" : "(event, this.dataset.id, this.dataset.path);\"");
	}

}
