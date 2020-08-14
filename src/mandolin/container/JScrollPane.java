package mandolin.container;

import mandolin.impersonator.Component;
import mandolin.impersonator.JComponent;

/**
 * Title: Mandolin
 * Description:
 * Copyright: B3
 * Company: B3
 *
 * @author Niranja Arjuna Jayasinghe
 * @version 1.0
 */
public class JScrollPane extends HTMLContainer {

	public static final int VERTICAL_SCROLLBAR_AS_NEEDED = 0;
	public static final int HORIZONTAL_SCROLLBAR_AS_NEEDED = 0;
	public static final int VERTICAL_SCROLLBAR_NEVER = 0;
	public static final int HORIZONTAL_SCROLLBAR_NEVER = 0;


	public JScrollPane() {
		this(null);
	}

	public JScrollPane(Component component, int verticalScrollBar, int HorizontalScrollBar) {
		this(null);
	}

	public JScrollPane(JComponent component) {
		this.components.add(component);
	}

	@Override
	protected String getLayoutClass() {
		return "jscrollpane " + this.layout.getLayoutClass();
	}
}
