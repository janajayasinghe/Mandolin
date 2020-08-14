package mandolin.component;

import mandolin.Listener.DocumentListener;

/**
 * Title: Mandolin
 * Description:
 * Copyright: B3
 * Company: B3
 *
 * @author Niranja Arjuna Jayasinghe
 * @version 1.0
 */
public abstract class Document extends HTMLComponent {

	public abstract void addDocumentListener(DocumentListener listener);

}
