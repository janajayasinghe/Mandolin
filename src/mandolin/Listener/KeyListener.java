package mandolin.Listener;

import mandolin.event.KeyEvent;

/**
 * Title: Mandolin
 * Description:
 * Copyright: B3
 * Company: B3
 *
 * @author Niranja Arjuna Jayasinghe
 * @version 1.0
 */
public interface KeyListener extends Listener {

	String ACTION_KT = "keyTypedKeyEvent";
	String ACTION_KP = "keyPressedKeyEvent";
	String ACTION_KR = "keyReleasedKeyEvent";

	void keyTyped(KeyEvent e);

	void keyPressed(KeyEvent e);

	void keyReleased(KeyEvent e);
}
