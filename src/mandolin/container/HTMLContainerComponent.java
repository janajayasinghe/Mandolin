package mandolin.container;

import mandolin.Listener.ActionListener;

import java.util.ArrayList;

/**
 * Created by caresys on 8/8/2020.
 */
public class HTMLContainerComponent extends HTMLContainer {

	protected ArrayList<String> actions; //action is assigned from listeners

	public HTMLContainerComponent(){
		this.actions = new ArrayList<>();
	}

	public void addAction(String act) {
		this.actions.add(act);
	}

	public void addActionListener(ActionListener actionListener) {
		this.addAction(actionListener.buildAction("onClick", actionListener.ACTION_AE));
	}

	public String getAction() {
		String actionString = "";
		for (String act : actions) {
			actionString += act + " ";
		}
		return actionString;
	}

	@Override
	protected String getLayoutClass() {
		return null;
	}

}
