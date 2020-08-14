package mandolin.container;

import mandolin.component.HTMLComponent;

/**
 * Title: Mandolin
 * Description:
 * Copyright: B3
 * Company: B3
 *
 * @author Niranja Arjuna Jayasinghe
 * @version 1.0
 */
public class JSplitPane extends HTMLContainer {

	public int getDividerSize() {
		return 0;
	}

	public int getHeight() {
		return 0;
	}

	public HTMLComponent getBottomComponent() {
		return null;
	}

	public HTMLComponent getTopComponent() {
		return null;
	}

	public HTMLComponent getRightComponent(){
		return null;
	}

	public HTMLComponent getLeftComponent(){
		return null;
	}

	public int setDividerLocation(int y) {
		return 0;
	}

	@Override
	protected String getLayoutClass() {
		return "";
	}
}
