package mandolin.Listener;

import mandolin.event.ListSelectionEvent;

/**
 * Title: Mandolin
 * Description:
 * Copyright: B3
 * Company: B3
 *
 * @author Niranja Arjuna Jayasinghe
 * @version 1.0
 */
public interface ListSelectionListener extends Listener {
	String ACTION = "valueChangedListSelectionEvent";

	void valueChanged(ListSelectionEvent e);
}
