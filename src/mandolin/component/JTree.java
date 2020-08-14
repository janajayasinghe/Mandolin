package mandolin.component;

import javax.swing.tree.*;
import java.util.Enumeration;

/**
 * Title: Mandolin
 * Description:
 * Copyright: B3
 * Company: B3
 *
 * @author Niranja Arjuna Jayasinghe
 * @version 1.0
 */
public class JTree extends HTMLComponent {

	protected TreeModel model;
	protected TreeSelectionModel selectionModel;
	private String selectedIndexPath = "";

	public JTree(TreeModel model) {
		this.model = model;
		this.selectionModel = new DefaultTreeSelectionModel();
	}

	public TreeModel getModel() {
		return model;
	}

	public void setModel(TreeModel model) {
		this.model = model;
	}

	public DefaultMutableTreeNode getLastSelectedPathComponent() {
		return null;
	}

	public TreePath getSelectionPath() {
		return selectionModel.getSelectionPath();
	}

	public TreeSelectionModel getSelectionModel() {
		return this.selectionModel;
	}

	public void setSelectionPath(TreePath path) {
		this.selectionModel.setSelectionPath(path);
	}

	public void setData(Object value) {
		selectedIndexPath = value != null ? ((String[]) value)[0] : "";
		String[] pathIndices = selectedIndexPath.split("-");
		if (pathIndices.length > 0) {
			TreeNode[] treePath = new TreeNode[pathIndices.length];
			treePath[0] = (TreeNode) this.model.getRoot();
			for (int j = 1; j < pathIndices.length; j++) {
				int index = Integer.parseInt(pathIndices[j]);
				treePath[j] = treePath[j - 1].getChildAt(index);
			}

			//Make the TreePath from the absolute root.
			TreeNode[] firstArray = ((DefaultMutableTreeNode) this.model.getRoot()).getPath();      //source array
			int fal = firstArray.length;        //determines length of firstArray
			int sal = treePath.length;   //determines length of secondArray
			TreeNode[] result = new TreeNode[fal + sal - 1];  //resultant array of size first array and second array
			System.arraycopy(firstArray, 0, result, 0, fal);
			System.arraycopy(treePath, 1, result, fal, sal - 1);
			setSelectionPath(new TreePath(result));
		}
	}

	@Override
	public void toHTML(StringBuffer buffer) {
		DefaultMutableTreeNode rootNode = (DefaultMutableTreeNode) this.model.getRoot();
		buffer.append("<ul id='" + getId() + "' name='jtree-" + getName() + "' class='jtree' data-path='" + getPath() + "' >\n");
		this.printDescendants(rootNode, rootNode, "", buffer);
		buffer.append("</ul>");
		buffer.append("<input type='hidden' name='" + getName() + "' value='" + this.selectedIndexPath + "'>");
	}

	/**
	 * Prints children.
	 *
	 * @param rootNode
	 * @param node
	 * @param indexPath
	 * @param html
	 */
	public void printDescendants(DefaultMutableTreeNode rootNode, DefaultMutableTreeNode node, String indexPath, StringBuffer html) {
		TreeNode[] path = node.getPath();
		TreeNode parentNode = path[path.length - 2]; // Last element is this node.
		indexPath += (indexPath.length() > 0 ? "-" : "") + parentNode.getIndex(node);
		boolean isSelected = false;
		if (this.selectionModel.isPathSelected(new TreePath(path))) {
			isSelected = true;
			this.selectedIndexPath = indexPath;
		}
		html.append("<li id='" + indexPath + "'> <div " + (isSelected ? "class='active'" : "") + " >" +
				(!node.equals(rootNode) ? "<input type='checkbox' " + (isSelected ? "checked" : "") + " onClick=\"valueChangedTreeSelectionEvent(event,'" + indexPath + "', '" + this.getPath() + "');\">" : "") +
				path[path.length - 1] + "</div>");
		Enumeration children = node.children();
		if (children != null) {
			html.append("<ul>");
			while (children.hasMoreElements()) {
				printDescendants(rootNode, (DefaultMutableTreeNode) children.nextElement(), indexPath, html);
			}
			html.append("</ul>");
		}
		html.append("</li>");
	}

}
