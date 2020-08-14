package mandolin.util;

import java.awt.*;

/**
 * Created by caresys on 7/8/2020.
 */
public class FrameworkUtil {

	public static String getBGHexColor(Color color) {
		return color != null ? "#" + Integer.toHexString(color.getRGB()).substring(2) : "";
	}
}
