package mandolin.impersonator;

import mandolin.border.Border;

import java.awt.*;

import static nurse.swing2web.com.b3.mandolin.util.FrameworkUtil.getBGHexColor;

/**
 * Created by caresys on 6/29/2020.
 */
public abstract class JComponent extends Container {

	public static final int LEFT_ALIGNMENT = 0;
	public Color background;
	protected Border border;

	public abstract void add(Component component);

	public void setBackground(Color color) {
		this.background = color;
	}

	public Color getBackground() {
		return this.background;
	}

	public Border getBorder() {
		return border;
	}

	public void setBorder(Border border) {
		this.border = border;
	}

	public String getBGColor() {
		return (background != null ? " background-color:" + getBGHexColor(this.background) : "");
	}

}
