package com.softfz.ui.mainUI;

import java.awt.Component;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;
/**
 * 自定义树节点渲染风格类，以图文的方式显示树状菜单
 * @author huihewu
 * @version V1.0
 * @time 2013-1-11 上午10:59:11
 */
public class IconTreeCellRender extends DefaultTreeCellRenderer {

	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value,
			boolean sel, boolean expanded, boolean leaf, int row,
			boolean hasFocus) {
		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf,
				row, hasFocus);
		IconTreeNode node = (IconTreeNode) value;
		setIcon(node.getIcon());
		setText(node.getTitle());
		
		return this;
	}

}
