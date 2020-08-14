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
public class KeyEvent implements Event {
	protected Component firingObject;
	public static final int VK_ENTER = '\n';

	private int keyCode;
	private char keyChar;

	public KeyEvent(Component firingObject) {
		this.firingObject = firingObject;
		this.keyCode = 13;
	}

	public Component getSource() {
		return firingObject;
	}

	public int getKeyCode() {
		return keyCode;
	}

	public void setKeyCode(int keyCode) {
		this.keyCode = keyCode;
	}

	public char getKeyChar() {
		return keyChar;
	}

	public void setKeyChar(char keyChar) {
		this.keyChar = keyChar;
	}
}
