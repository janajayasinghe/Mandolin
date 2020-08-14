package mandolin.event;

import mandolin.component.JButton;

/**
 * Title: Mandolin
 * Description:
 * Copyright: B3
 * Company: B3
 *
 * @author Niranja Arjuna Jayasinghe
 * @version 1.0
 */
public class MouseEvent implements Event {

	public JButton getButton(){
		return new JButton();
	}

	public int getX(){
		return 0;
	}
}
