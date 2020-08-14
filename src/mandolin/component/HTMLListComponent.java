package mandolin.component;

import mandolin.Listener.ActionListener;
import mandolin.Listener.ItemListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Title: Mandolin
 * Description: This class holds the behaviours of list selections (single and multiple)
 * Copyright: B3
 * Company: B3
 *
 * @author Niranja Arjuna Jayasinghe
 * @version 1.0
 */
public class HTMLListComponent extends HTMLComponent {

	protected ArrayList options;
	protected ArrayList<Integer> selectedIndices;
	protected boolean multiple;

	public HTMLListComponent(boolean multiple) {
		this.multiple = multiple;
		this.options = new ArrayList();
		this.selectedIndices = new ArrayList<>();
	}

	public HTMLListComponent(Object[] options, boolean multiple) {
		this(multiple);
		this.options.addAll(Arrays.asList(options));
	}

	public void insertItemAt(Object item, int position) {
		options.add(position, item);
	}

	public void addActionListener(ActionListener actionListener) {
		this.addAction(actionListener.buildAction("onChange", actionListener.ACTION_AE));
	}

	public void addItemListener(ItemListener listener) {
	}

	public List<Integer> getSelectedIndices() {
		return selectedIndices;
	}

	public void setSelectedIndices(int[] selectedIndices) {
		this.selectedIndices.clear();
		for (int x : selectedIndices) {
			this.selectedIndices.add(x);
		}
	}

	public HTMLListComponent getSource() {
		return this;
	}

	public void removeAllItems() {
		options.clear();
	}

	public int getIndexOf(Object o) {
		return options.indexOf(o);
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

	public void addItem(Object item) {
		options.add(item);
	}

	public int getSize() {
		return options.size();
	}

	public boolean isSelectedIndex(int index) {
		return selectedIndices.contains(index);
	}

	public int getItemCount() {
		return options.size();
	}

	public Object getItemAt(int position) {
		return options.get(position);
	}

	@Override
	public void toHTML(StringBuffer buffer) {
		buffer.append("<select id='" + getId() + "' data-id='" + getId() + "' name='" + getName() + "' class='jcombobox' data-path='" + getPath() + "' " + this.getAction() + " " + (multiple ? "multiple" : "") + ">\n");
		if (options != null) {
			for (int i = 0; i < options.size(); i++) {
				buffer.append("<option value='" + i + "' " + (selectedIndices.contains(i) ? "selected" : "") + ">" + options.get(i).toString() + "</option>\n");
			}
		}
		buffer.append("</select>");
	}
}