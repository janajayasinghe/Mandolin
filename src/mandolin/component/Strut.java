package mandolin.component;

/**
 * Title: Mandolin
 * Description:
 * Copyright: B3
 * Company: B3
 *
 * @author Niranja Arjuna Jayasinghe
 * @version 1.0
 */
public class Strut extends HTMLComponent {
	private boolean isVertical = false;
	private int widthOrHeight = 0;

	public Strut(boolean isVertical, int widthOrHeight) {
		this.isVertical = isVertical;
		this.widthOrHeight = widthOrHeight;
	}

	private String getHTMLClass() {
		return isVertical ? "vertical-strut" : "horizontal-strut";
	}

	private String getStyle() {
		return "style='" + (isVertical ? "width" : "height") + ": " + widthOrHeight + "px;'";
	}

	@Override
	public void toHTML(StringBuffer buffer) {
		buffer.append("<div class='" + getHTMLClass() + "' " + getStyle() + "></div>");
	}
}
