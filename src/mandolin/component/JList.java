package mandolin.component;

import mandolin.Listener.ListSelectionListener;

import java.util.stream.Stream;

/**
 * Title: Mandolin
 * Description:
 * Copyright: B3
 * Company: B3
 *
 * @author Niranja Arjuna Jayasinghe
 * @version 1.0
 */
public class JList extends HTMLListComponent {

	public JList() {
		super(true);
	}

	public JList(Object[] options) {
		super(options, true);
	}

	/**
	 * This method is called by container.setData
	 *
	 * @param value
	 */
	public void setData(Object value) {
		if (value != null) {
			int[] vals = Stream.of((String[]) value).mapToInt(Integer::parseInt).toArray();
			this.setSelectedIndices(vals);
		}
	}

	public int getSelectedIndex() {
		return this.selectedIndices.get(0);
	}

	public void addListSelectionListener(ListSelectionListener listSelectionListener) {
	}

	public void setVisibleRowCount(int count) {
	}
}
