package mandolin.component;

import mandolin.Listener.ActionListener;
import mandolin.container.JDialog;
import mandolin.container.JFrame;
import mandolin.container.JPanel;
import mandolin.event.ActionEvent;
import mandolin.impersonator.Component;

/**
 * Title: Mandolin
 * Description:
 * Copyright: B3
 * Company: B3
 *
 * @author Niranja Arjuna Jayasinghe
 * @version 1.0
 */
public class JOptionPane extends HTMLComponent {

	/**
	 * Type meaning Look and Feel should not supply any options -- only
	 * use the options from the <code>JOptionPane</code>.
	 */
	public static final int DEFAULT_OPTION = -1;
	/**
	 * Type used for <code>showConfirmDialog</code>.
	 */
	public static final int YES_NO_OPTION = 0;
	/**
	 * Type used for <code>showConfirmDialog</code>.
	 */
	public static final int YES_NO_CANCEL_OPTION = 1;
	/**
	 * Type used for <code>showConfirmDialog</code>.
	 */
	public static final int OK_CANCEL_OPTION = 2;

	//
	// Return values.
	//
	/**
	 * Return value from class method if YES is chosen.
	 */
	public static final int YES_OPTION = 0;
	/**
	 * Return value from class method if NO is chosen.
	 */
	public static final int NO_OPTION = 1;
	/**
	 * Return value from class method if CANCEL is chosen.
	 */
	public static final int CANCEL_OPTION = 2;
	/**
	 * Return value form class method if OK is chosen.
	 */
	public static final int OK_OPTION = 0;
	/**
	 * Return value from class method if user closes window without selecting
	 * anything, more than likely this should be treated as either a
	 * <code>CANCEL_OPTION</code> or <code>NO_OPTION</code>.
	 */
	public static final int CLOSED_OPTION = -1;

	//
	// Message types. Used by the UI to determine what icon to display,
	// and possibly what behavior to give based on the type.
	//
	/**
	 * Used for error messages.
	 */
	public static final int ERROR_MESSAGE = 0;
	/**
	 * Used for information messages.
	 */
	public static final int INFORMATION_MESSAGE = 1;
	/**
	 * Used for warning messages.
	 */
	public static final int WARNING_MESSAGE = 2;
	/**
	 * Used for questions.
	 */
	public static final int QUESTION_MESSAGE = 3;
	/**
	 * No icon is used.
	 */
	public static final int PLAIN_MESSAGE = -1;

	public static void showMessageDialog(Component component, String text, String title, int type) {
		showConfirmDialog((JFrame)component, text, title, type);
	}

	public static int showConfirmDialog(JFrame parentComponent, Object message, String title, int optionType) {
		OptionPaneDialog d = new OptionPaneDialog(parentComponent, (String)message);
		d.setTitle(title);
		d.setVisible(true);
		return d.getStatus();
	}


	@Override
	public void toHTML(StringBuffer buffer) {
	}

	private static class OptionPaneDialog extends JDialog implements ActionListener {

		JButton yesBtn = new JButton("Yes");
		JButton noBtn = new JButton("No");
		String message = "";
		private int status = NO_OPTION;

		public OptionPaneDialog(JFrame owner, String message) {
			super(owner, false);
			init();
		}

		private void init() {
			JPanel messagePanel = new JPanel();
			messagePanel.add(new JLabel(message));
			JPanel buttonPanel = new JPanel();
			buttonPanel.add(yesBtn);
			buttonPanel.add(noBtn);
			yesBtn.addActionListener(this);
			noBtn.addActionListener(this);
			add(messagePanel);
			add(buttonPanel);
		}

		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(yesBtn)) {
				this.status = YES_OPTION;
			} else if (e.getSource().equals(noBtn)) {
				this.status = NO_OPTION;
			}
			dispose();
		}

		public int getStatus() {
			return status;
		}
	}
}
