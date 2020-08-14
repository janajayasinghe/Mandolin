package mandolin.component.table;


import mandolin.container.HTMLContainer;

/**
 * Title: Mandolin
 * Description:
 * Copyright: B3
 * Company: B3
 *
 * @author Niranja Arjuna Jayasinghe
 * @version 1.0
 */
public abstract class AbstractCellEditor extends HTMLContainer implements TableCellEditor {

	private boolean viible;

	public void startCellEditing() {
		this.viible = true;
	}

	public void stopCellEditing() {
		this.viible = false;
	}

	public boolean iCellEditing(){
		return this.viible;
	}

	public Object getCellEditorValue() {
		return null;
	}

	@Override
	public void toHTML(StringBuffer buffer) {

	}
}
