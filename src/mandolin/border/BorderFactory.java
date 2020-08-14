package mandolin.border;

import java.awt.*;

/**
 * Title: Mandolin
 * Description: BorderFactory creates border object according to the parameters. All the swing borders are not supported yet.
 * Copyright: B3
 * Company: B3
 *
 * @author Niranja Arjuna Jayasinghe
 * @version 1.0
 */
public class BorderFactory {

	private BorderFactory() {
	}

	public static Border createEmptyBorder(int top, int left, int bottom, int right) {
		return new Border(top, left, bottom, right);
	}

	public static Border createEtchedBorder() {
		return new Border(null, Border.LOWERED_ETCHED, Color.black, 0, 0, 0, 0);
	}

	public static Border createLineBorder(Color color) {
		return new Border(null, Border.LINE, Color.black, 0, 0, 0, 0);
	}

	public static Border createRaisedBevelBorder() {
		return new Border(null, Border.RAISED_BEVAL, Color.black, 0, 0, 0, 0);
	}

	public static Border createLoweredBevelBorder() {
		return new Border(null, Border.LOWERED_BEVAL, Color.black, 0, 0, 0, 0);
	}

	public static Border createTitledBorder(Border border, String title, int titleJustification, int titlePosition, Font titleFont) {
		return new Border(title, border.getBorderType(), border.getColor(), border.getTop(), border.getLeft(), border.getBottom(), border.getRight());
	}

	public static Border createTitledBorder(String title) {
		return new Border(title, Border.LINE, Color.black, 0, 0, 0, 0);
	}

}
