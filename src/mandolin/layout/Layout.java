package mandolin.layout;

import mandolin.container.HTMLContainer;

/**
 * Title: Mandolin
 * Description: Layout generates HTML of container children according to the visit method.Basic structure of a layout is CSS display:table.
 * This represents visitor (design patten).
 * Copyright: B3
 * Company: B3
 *
 * @author Niranja Arjuna Jayasinghe
 * @version 1.0
 */
public interface Layout {

	/**
	 * display:table;
	 *
	 * @return
	 */
	String getLayoutClass();

	/**
	 * display:table-row;
	 *
	 * @return
	 */
	String getContainerClass();

	/**
	 * display:table-cell;
	 *
	 * @return
	 */
	String getChildClass();

	/**
	 * Includes child component to necessary div tags
	 *
	 * @param container
	 */
	void visit(HTMLContainer container, StringBuffer buffer);

}
