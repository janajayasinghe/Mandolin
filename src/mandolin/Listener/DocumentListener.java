package mandolin.Listener;

import mandolin.event.DocumentEvent;

/**
 * Created by caresys on 6/30/2020.
 */
public interface DocumentListener extends Listener {

	String ACTION_INSERT = "insertUpdateDocumentEvent";
	String ACTION_REMOVE = "removeUpdateDocumentEvent";
	String ACTION_CHANGE = "changedUpdateDocumentEvent";

	void insertUpdate(DocumentEvent e);

	void removeUpdate(DocumentEvent e);

	void changedUpdate(DocumentEvent e);
}
