package mandolin.container;

/**
 * Title: Mandolin
 * Description: This singleton(There is only one HTML page) class represents the HTML page.
 * Though this is a singleton class, it needs a single argument(which is JFrame) for the constructor since the JFrame is a mandatory
 * Copyright: B3
 * Company: B3
 *
 * @author Niranja Arjuna Jayasinghe
 * @version 1.0
 */
public class HTMLDocument {

	private JFrame mainFrame; // Supports only one Frame. If you want more than one, handle it here by declaring more JFrames

	private HTMLDocument() {
	}

	public JFrame getMainFrame() {
		return mainFrame;
	}

	public void setMainFrame(JFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	public void toHTML(StringBuffer buffer) {
		buffer.append("<html>");
		buffer.append("<head>");
		buffer.append(tempCSS());
		buffer.append(tempJS());
		buffer.append("</head>");
		buffer.append("<body>");
		buffer.append("<form name='appForm' id='appForm'>");
		mainFrame.toHTML(buffer);
		buffer.append("</form>");
		buffer.append("<div id='action-remover' class='action-remover'><div class='loader quantum-spinner'></div></div>");
		buffer.append("</body>");
		buffer.append("</html>");
	}

	private String tempJS() {
		return "<script type='text/javascript' src='web/script/mandolin.js'></script>\n";
	}

	private String tempCSS() {
		return "<link href='web/css/mandolin.css' rel='stylesheet' type='text/css'>\n";
	}

	private static class HTMLDocumentHolder {
		public static final HTMLDocument hd = new HTMLDocument();
	}

	public static HTMLDocument getInstance(JFrame frame) {
		HTMLDocument hd = null;
		if (frame != null) {
			hd = HTMLDocumentHolder.hd;
			hd.setMainFrame(frame);
		}
		return hd;
	}
}
