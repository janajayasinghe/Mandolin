package mandolin.container;

import mandolin.Listener.ActionListener;
import mandolin.layout.Layout;

/**
 * Title: Mandolin
 * Description:
 * Copyright: B3
 * Company: B3
 *
 * @author Niranja Arjuna Jayasinghe
 * @version 1.0
 */
public class JPanel extends HTMLContainer {

	public JPanel() {
	}

	public JPanel(Layout layout) {
		this.layout = layout;
	}

	public void setAlignmentX(int x) {
	}

	public void removeMouseMotionListener(ActionListener lister) {
	}

	public static void setDefaultCloseOperation(int x) {
	}

	/**
	 * returns the CSS class of this Object
	 * class='jpanel layoutclass'
	 * jpanel = default class
	 * layoutClass = class is assigned by the layout
	 *
	 * @return
	 */
	@Override
	protected String getLayoutClass() {
		return "jpanel " + layout.getLayoutClass();
	}
}
