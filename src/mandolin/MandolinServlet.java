package mandolin;

import mandolin.container.HTMLContainer;
import mandolin.container.HTMLDocument;
import mandolin.container.JFrame;
import mandolin.impersonator.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Title: Mandolin
 * Description: Example Servlet
 * Copyright: B3
 * Company: B3
 *
 * @author Niranja Arjuna Jayasinghe
 * @version 1.0
 */
public class MandolinServlet extends HttpServlet {

	private ExecutorService executor;

	public void init() throws ServletException {
		executor = Executors.newCachedThreadPool();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processHTTPRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processHTTPRequest(request, response);
	}

	public void processHTTPRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println(play(request, new JFrame()));
	}

	/**
	 * Generates HTML page from JAVA class
	 * page -
	 * method -
	 * id -
	 *
	 * @param request
	 * @param frame
	 * @return
	 */
	public static String play(HttpServletRequest request, JFrame frame) {
		HttpSession httpSession = request.getSession();
		String path = request.getParameter("path");
		String method = request.getParameter("method");// method = method name + Event (eg: actionPerformed-ActionEvent)
		StringBuffer htmlBuffer = new StringBuffer();

		JFrame webFrame = (JFrame) httpSession.getAttribute("webFrame");
		if (webFrame == null) {
			webFrame = frame;
			httpSession.setAttribute("webFrame", webFrame);
		}
		HTMLContainer container = webFrame.getPage(path);

		if (method != null) {
			Component[] components = getFiringContainerAndObject(path, container);
			HTMLContainer firingContainer = (HTMLContainer) components[0];
			Component firingObject = components[1];
			firingContainer.setData(request.getParameterMap());
			webFrame.runAction(firingContainer, method, firingObject);
			webFrame.toHTML(htmlBuffer);
		} else {
			HTMLDocument doc = HTMLDocument.getInstance(webFrame);
			doc.setMainFrame(webFrame);
			doc.toHTML(htmlBuffer);
		}
		return htmlBuffer.toString();
	}

	/**
	 * Returns the last HTMLContainer of the path that has an id with $.
	 * Page variable is constructed by ids of all the parent components(a tree).
	 * if an id starts with $ symbol, action should be fired at that component(HTMLContainer).
	 * if there are multiple ids that start with $, last HTMLContainer will be returned from this method.
	 *
	 * @param page
	 * @param container
	 * @return
	 */
	private static Component[] getFiringContainerAndObject(String page, HTMLContainer container) {
		String[] ids = page.split("-");
		HTMLContainer returnContainer = container;
		HTMLContainer currentContainer = container;
		// 0th element is the container that come from the parameter. Last element is a HTMLComponent.
		// Therefore ignore both 0th and the last elements.
		Component comp = null;
		for (int i = 1; i < ids.length; i++) {
			comp = currentContainer.getComponent(ids[i]);
			if (comp instanceof HTMLContainer) {
				currentContainer = (HTMLContainer) comp;
				if (ids[i].startsWith("$")) {
					returnContainer = currentContainer;
				}
			}
		}
		return new Component[]{returnContainer, comp};
	}

	public void destroy() {
		executor.shutdownNow();
	}
}
