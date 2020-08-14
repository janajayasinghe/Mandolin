package mandolin.component;

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
public class JComboBox extends HTMLListComponent {

	private int selectedIndex = 0;

	public JComboBox() {
		super(false);
	}

	public JComboBox(Object[] options) {
		super(options, false);
	}

	public void setModel(DefaultComboBoxModel model) {
	}

	public void setSelectedIndex(int index) {
		this.selectedIndex = index;
		super.selectedIndices.clear();
		super.selectedIndices.add(selectedIndex);
	}

	public Object getSelectedItem() {
		if (options.size() > selectedIndex) {
			return options.get(selectedIndex);
		}
		return null;
	}

	public int getSelectedIndex() {
		return selectedIndex;
	}

	public void setSelectedItem(Object o) {
		int index = options.indexOf(o);
		if (index > -1) {
			this.setSelectedIndex(index);
		}
	}

	public void setMaximumRowCount(int x) {
	}

	public void setData(Object value) {
		if (value != null) {
			int[] vals = Stream.of((String[]) value).mapToInt(Integer::parseInt).toArray();
			this.setSelectedIndex(vals[0]);
		}
	}
}
