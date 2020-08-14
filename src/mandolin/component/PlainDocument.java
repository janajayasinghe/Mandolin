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
public class PlainDocument extends Document {

	public int getLength() {
		return 0;
	}

	@Override
	public void addDocumentListener(DocumentListener listener) {
		this.addAction(listener.buildAction("onChange", listener.ACTION_INSERT));
	}

}
