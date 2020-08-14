package mandolin.container;

import mandolin.impersonator.Component;

/**
 * Title: Mandolin
 * Description: This class represents model dialog which is generated from JDialogs
 * Copyright: B3
 * Company: B3
 *
 * @author Niranja Arjuna Jayasinghe
 * @version 1.0
 */
public class JDialog extends HTMLContainer {

	public static final int DISPOSE_ON_CLOSE = 0;

	protected JFrame owner;
	protected String title;
	protected boolean isModel = false;
	private Component previousPage; // Last visible page before this dialog appears.

	// JDialog will be added to the JFrame. So JDialog does not support the default constructor(no arg).
	public JDialog(JFrame owner, boolean isModel) {
		this(owner, "", isModel);
	}

	public JDialog(JFrame owner, String title, boolean isModel) {
		this.owner = owner;
		this.isModel = isModel;
		this.title = title;
		this.generateID();
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return this.title;
	}

	/**
	 * sets the parent container for this dialog.
	 * If there are multiple dialogs, parent container is previous dialog which is added before this dialog.
	 * Or else it may be a JPanel.
	 */
	public final void setPreviousPage(Component previousPage) {
		this.previousPage = previousPage;
	}

	/**
	 * Adds this JDialog to the owner's components list and wait the current thread until user disposes the dialog.
	 * Normally a dialog is added in actionPerformed method and an actions run using threads(ActionRunner)
	 * since the caller method needs to be paused until user performs actions of the top dialog.
	 * When a user adds a dialog previousPage.wait is called in order to pause the current method.
	 * whenever user finishes his task of the top dialog, previousPage.notify() will be called in order to resume the previous task.
	 */
	public void setVisible(boolean visible) {
		this.isVisible = visible;
		this.setPreviousPage(owner.components.getLast());
		owner.add(this);
		synchronized (this.previousPage) {
			try {
				this.previousPage.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Removes this JDialog from the owner's components list and notify the thread that the top dialog's task has been completed.
	 */
	public final void dispose() {
		owner.remove(this);
		synchronized (this.previousPage) {
			this.previousPage.notify();
		}
	}

	public HTMLContainer getContentPane() {
		return this;
	}

	public void setDefaultCloseOperation(int operation) {
	}

	/**
	 * Assigns an ID and parent iD to the dialog.
	 * Dialog's id is used in css z-index by removing the '$' prefix that is added by setId(). Therefore the id should be "$+integer"
	 */
	protected void generateID() {
		this.setId("" + owner.getComponentCount());
	}

	/**
	 * returns the CSS class of this Object
	 * class='jdialog layoutclass'
	 * jframe = default class
	 * layoutClass = class is assigned by the layout
	 *
	 * @return
	 */
	@Override
	protected String getLayoutClass() {
		return "jdialog " + this.layout.getLayoutClass();
	}

	/**
	 * returns a number in order to use as the z-index.
	 * JDialogs's id is always a number. but there may be $ prefix if the JDailog implement listener classes.
	 * Then this method removes prefix and returns only the id(a number)
	 *
	 * @return
	 */
	private int getZIndex() {
		return Integer.parseInt(this.getIdWithoutPrefix());
	}

	@Override
	public void toHTML(StringBuffer buffer) {
		int z = getZIndex();
		buffer.append("<div class='" + (this.isModel ? "jdialog-model" : "jdialog-not-model") + "' style='display:" + (!this.isVisible ? "none" : "block") + ";z-index: " + (z * 100) + ";'>");
		buffer.append(border.getStartTag(this.getId(), getLayoutClass(), "display:" + (!this.isVisible ? "none" : "block") + ";"));
		if (this.title != null) {
			buffer.append("<div class='jdialog-title'>" + this.title + "</div>");
		}
		buffer.append("<div class='" + this.layout.getContainerClass() + "'>");
		layout.visit(this, buffer);
		buffer.append("</div>");
		buffer.append(border.getCloseTag());
		buffer.append("</div>");
	}
}