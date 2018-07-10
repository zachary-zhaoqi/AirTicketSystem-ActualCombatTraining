package com.softfz.ui.mainUI;

import javax.swing.ImageIcon;
import javax.swing.tree.DefaultMutableTreeNode;
/**
 * 自定义树节点类
 * @author huihewu
 * @version V1.0
 * @time 2013-1-11 上午10:59:37
 */
public class IconTreeNode extends DefaultMutableTreeNode {
	private ImageIcon icon;
	private String title;

	public IconTreeNode(ImageIcon icon, String title) {
		super();
		this.icon = icon;
		this.title = title;
	}

	public ImageIcon getIcon() {
		return icon;
	}

	public String getTitle() {
		return title;
	}

	public static ImageIcon getIcon(String picName) {
		return new ImageIcon(IconTreeNode.class.getResource("treeicon/"
				+ picName));
	}
}
