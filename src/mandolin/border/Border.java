package mandolin.border;

import mandolin.util.FrameworkUtil;

import java.awt.*;

/**
 * Title: Mandolin
 * Description:
 * Copyright: B3
 * Company: B3
 *
 * @author Niranja Arjuna Jayasinghe
 * @version 1.0
 */
public class Border {

	public static final int EMPTY = 0;
	public static final int LINE = 1;
	public static final int RAISED_ETCHED = 2;
	public static final int LOWERED_ETCHED = 3;
	public static final int RAISED_BEVAL = 4;
	public static final int LOWERED_BEVAL = 5;

	private int top = 0;
	private int left = 0;
	private int bottom = 0;
	private int right = 0;
	private Color color = Color.black;
	private int borderType = 0;
	private String title = null;

	public Border() {
		this(null, EMPTY, Color.black, 0, 0, 0, 0);
	}

	public Border(int top, int left, int bottom, int right) {
		this(null, EMPTY, Color.black, top, left, bottom, right);
	}

	public Border(String title, int borderType, Color color, int top, int left, int bottom, int right) {
		this.title = title;
		this.borderType = borderType;
		this.color = color;
		this.top = top;
		this.left = left;
		this.bottom = bottom;
		this.right = right;
	}

	public int getTop() {
		return top;
	}

	public void setTop(int top) {
		this.top = top;
	}

	public int getLeft() {
		return left;
	}

	public void setLeft(int left) {
		this.left = left;
	}

	public int getBottom() {
		return bottom;
	}

	public void setBottom(int bottom) {
		this.bottom = bottom;
	}

	public int getRight() {
		return right;
	}

	public void setRight(int right) {
		this.right = right;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getBorderType() {
		return borderType;
	}

	public void setBorderType(int borderType) {
		this.borderType = borderType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	private String getBorder() {
		switch (borderType) {
			case EMPTY: {
				return "border: none;";
			}
			case LINE: {
				return "border: 1px solid " + FrameworkUtil.getBGHexColor(color) + ";";
			}
			case RAISED_ETCHED: {
				return "border: 1px ridge " + FrameworkUtil.getBGHexColor(color) + ";";
			}
			case LOWERED_ETCHED: {
				return "border: 1px groove " + FrameworkUtil.getBGHexColor(color) + ";";
			}
			case RAISED_BEVAL: {
				return "border: 1px outset " + FrameworkUtil.getBGHexColor(color) + ";";
			}
			case LOWERED_BEVAL: {
				return "border: 1px inset " + FrameworkUtil.getBGHexColor(color) + ";";
			}
			default:
				return "border: none;";
		}
	}

	private String getTagStyle() {
		return "padding-top:" + top + ";" +
				"padding-left:" + left + ";" +
				"padding-bottom:" + bottom + ";" +
				"padding-right:" + right + ";" +
				getBorder();
	}

	public String getStartTag(String id, String cssClass, String style) {
		if (this.title != null) {
			return "<fieldset id='\"+id+\"' class='" + cssClass + "' style='" + this.getTagStyle() + " " + style + "'>\n" +
					"<legend style='color:" + FrameworkUtil.getBGHexColor(color) + "'>" + title + "</legend>\n";
		} else {
			return "<div id='" + id + "'  class='" + cssClass + "' style='" + style + "'>\n";
		}
	}

	public String getCloseTag() {
		if (this.title != null) {
			return "</fieldset>\n";
		} else {
			return "</div>\n";
		}
	}
}
