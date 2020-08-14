package mandolin.util;

import java.util.ArrayList;

/**
 * Title: Mandolin
 * Description: This collection supports multiple null keys.
 * Copyright: B3
 * Company: B3
 *
 * @author Niranja Arjuna Jayasinghe
 * @version 1.0
 */
public class ElementMap<K, L> {

	private ArrayList<K> keyList;
	private ArrayList<L> valueList;
	//private CopyOnWriteArrayList<L> valueList;

	public ElementMap() {
		this.keyList = new ArrayList<>();
		this.valueList = new ArrayList<>();
	}

	/**
	 * Adds an value
	 * if the key exist, value will be replaced.
	 *
	 * @param key
	 * @param value
	 */
	public void add(K key, L value) {
		int index = keyList.indexOf(key);
		if (index > -1) {
			valueList.set(index, value);
		} else {
			this.keyList.add(key);
			this.valueList.add(value);
		}
	}

	/**
	 * Adds values without a key.
	 * Key will be null
	 *
	 * @param value
	 */
	public void add(L value) {
		keyList.add(null);
		valueList.add(value);
	}

	/**
	 * Removes all values.
	 */
	public void removeAll() {
		keyList.clear();
		valueList.clear();
	}

	/**
	 * returns the value by key
	 *
	 * @param key
	 * @return
	 */
	public L get(K key) {
		int index = keyList.indexOf(key);
		if (index > -1) {
			return valueList.get(index);
		}
		return null;
	}

	/**
	 * Returns value according to the index.
	 *
	 * @param index
	 * @return
	 */
	public L get(int index) {
		return valueList.get(index);
	}

	/**
	 * Returns last value
	 *
	 * @return
	 */
	public L getLast() {
		if (!valueList.isEmpty()) {
			return valueList.get(valueList.size() - 1);
		}
		return null;
	}


	/**
	 * Returns key list
	 *
	 * @return
	 */
	public ArrayList<K> getKeys() {
		return keyList;
	}

	/**
	 * Returns value list
	 *
	 * @return
	 */
	public ArrayList<L> getValues() {
		return new ArrayList(valueList);
	}

	public void remove(L value) {
		int index = valueList.indexOf(value);
		if (index > -1) {
			valueList.remove(index);
			keyList.remove(index);
		}
	}

	public int size() {
		return valueList.size();
	}
}
