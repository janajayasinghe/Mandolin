package mandolin.container;

import mandolin.component.HiddenComponent;
import mandolin.impersonator.Component;
import mandolin.impersonator.JComponent;

/**
 * Title: Mandolin
 * Description: Tabbed pane
 * Copyright: B3
 * Company: B3
 *
 * @author Niranja Arjuna Jayasinghe
 * @version 1.0
 */
public class JTabbedPane extends HTMLContainer {

	public static final int HORIZONTAL = 0;
	public static final int VERTICAL = 2;

	private int direction;
	private HTMLTabbedPane HTabbedPane;
	private HiddenComponent selectionModel;//This holds the selected tab index.

	public JTabbedPane() {
		this(HORIZONTAL);
		generateID();
	}

	public JTabbedPane(int direction) {
		this.direction = direction;
		this.selectionModel = new HiddenComponent();
		this.HTabbedPane = new HTMLTabbedPane(this);
		this.selectionModel.setValue("0");
		super.add(HTabbedPane);
		super.add(selectionModel);
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public HiddenComponent getSelectionModel() {
		return selectionModel;
	}

	public void setSelectionModel(HiddenComponent selectionModel) {
		this.selectionModel = selectionModel;
	}

	public void add(Component element) {
		this.HTabbedPane.components.add(element);
	}

	/**
	 * Adds elements with a name
	 *
	 * @param name
	 * @param element
	 */
	public void add(String name, Component element) {
		element.setId(name);
		this.HTabbedPane.components.add(name, element);
	}

	public int getTabCount() {
		return this.HTabbedPane.components.size();
	}

	public void setTabComponentAt(int position, JComponent component) {
		// do not support yet
	}

	public int getSelectedIndex() {
		return Integer.parseInt(selectionModel.getValue());
	}

	public void setSelectedIndex(int index) {
		selectionModel.setValue("" + index);
	}

	public void setEnabledAt(int index, boolean enable) {
		selectionModel.setValue("" + index);
	}

	@Override
	protected String getLayoutClass() {
		return "jtabbedpane-holder";
	}

}
