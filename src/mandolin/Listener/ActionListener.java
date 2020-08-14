package mandolin.Listener;


import mandolin.event.ActionEvent;

/**
 * Title: Mandolin
 * Description:
 * Copyright: B3
 * Company: B3
 *
 * @author Niranja Arjuna Jayasinghe
 * @version 1.0
 */
public interface ActionListener extends Listener {

	String ACTION_AE = "actionPerformedActionEvent"; // Action event
	String ACTION_AC = "actionPerformedActionCommand"; // Action command

	void actionPerformed(ActionEvent e);
}
