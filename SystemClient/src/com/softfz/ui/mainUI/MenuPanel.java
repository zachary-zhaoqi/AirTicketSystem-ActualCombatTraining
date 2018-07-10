package com.softfz.ui.mainUI;

import java.awt.CardLayout;
import java.awt.Component;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import com.softfz.ui.panel.AddAdminPanel;
import com.softfz.ui.panel.AddFlightPanel;
import com.softfz.ui.panel.AddSaleNetPanel;
import com.softfz.ui.panel.AdminListPanel;
import com.softfz.ui.panel.DiscountJPanel;
import com.softfz.ui.panel.FlightListPanel;
import com.softfz.ui.panel.FlightMonthTotalPanel;
import com.softfz.ui.panel.NetMonthTotalPanel;
import com.softfz.ui.panel.PasswordChangePanel;
import com.softfz.ui.panel.SaleNetListPanel;


/**
 * 系统菜单面板
 * 
 * @author FengWang
 * @version V1.0
 * @time 2013-10-13 下午20:13:34
 */
public class MenuPanel extends JScrollPane implements TreeSelectionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8934143096036318881L;
	private JTree menuTree;
	private JPanel workPanel;
	private CardLayout workPanelLayout;
	private Map<String, Class> viewPanelMap = new HashMap<String, Class>();
	private List<String> hasCreateNames  = new ArrayList<String>();
	public MenuPanel(JPanel workPanel, CardLayout workPanelLayout) {
		this.workPanel = workPanel;
		this.workPanelLayout = workPanelLayout;
		{
			// 添加卡显示项
		
			workPanel.add("",new JPanel());
			
			viewPanelMap.put("密码修改", PasswordChangePanel.class);
			
			viewPanelMap.put("新增管理员", AddAdminPanel.class);
			viewPanelMap.put("管理员列表", AdminListPanel.class);
			
			viewPanelMap.put("新增销售网点", AddSaleNetPanel.class);
			viewPanelMap.put("销售网点列表", SaleNetListPanel.class);			
			
			viewPanelMap.put("新增航班", AddFlightPanel.class);
			viewPanelMap.put("航班列表", FlightListPanel.class);
			viewPanelMap.put("航班折扣", DiscountJPanel.class);
//			viewPanelMap.put("航班折扣", FlightDiscountPanel.class);
			
			viewPanelMap.put("网点月统计", NetMonthTotalPanel.class);
			viewPanelMap.put("航班月统计", FlightMonthTotalPanel.class);
	}
		buildTree();
		
	}

	private void buildTree() {
		DefaultMutableTreeNode top = new IconTreeNode(
				IconTreeNode.getIcon("root.png"), "系统管理");
		
		DefaultMutableTreeNode sysSet = new IconTreeNode(
				IconTreeNode.getIcon("system.png"), "系统设置");
		DefaultMutableTreeNode manage = new IconTreeNode(
				IconTreeNode.getIcon("systemuser.png"), "管理员管理");
		DefaultMutableTreeNode saleNet = new IconTreeNode(
				IconTreeNode.getIcon("network.png"), "销售网点管理");
		DefaultMutableTreeNode flight = new IconTreeNode(
				IconTreeNode.getIcon("flight.png"), "航班管理");
		DefaultMutableTreeNode sale = new IconTreeNode(
				IconTreeNode.getIcon("sale.png"), "销售统计");
		
		top.add(sysSet);
		top.add(manage);
		top.add(saleNet);
		top.add(flight);
		top.add(sale);
		// 读取菜单文件构造树菜单
		URL url = MenuPanel.class.getResource("menutree.txt");
		InputStream is = null;
		try {
			is = url.openStream();
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			BufferedReader reader = new BufferedReader(isr);
			// read one line at a time, put into tree
			String line = reader.readLine();
			while (line != null) {
				char linetype = line.charAt(0);
				String nodestr = line.substring(2);
				String[] tmp = nodestr.split("_");
				switch (linetype) {
				case 'a':
					sysSet.add(new IconTreeNode(IconTreeNode.getIcon(tmp[1]),
							tmp[0]));
					break;
				case 'b':
					manage.add(new IconTreeNode(IconTreeNode.getIcon(tmp[1]),
							tmp[0]));
					break;
				case 'c':
					saleNet.add(new IconTreeNode(IconTreeNode.getIcon(tmp[1]),
							tmp[0]));
					break;
				case 'd':
					flight.add(new IconTreeNode(IconTreeNode.getIcon(tmp[1]),
							tmp[0]));
					break;
				case 'e':
					sale.add(new IconTreeNode(IconTreeNode.getIcon(tmp[1]),
							tmp[0]));
					break;
				}
				line = reader.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		menuTree = new JTree(top);
		menuTree.setCellRenderer(new IconTreeCellRender());
		menuTree.setEditable(false);
		menuTree.setToggleClickCount(1);
		menuTree.setRowHeight(25);
		for (int i = 1; i <= menuTree.getRowCount(); i++) {
			menuTree.expandRow(i);
		}
		this.setViewportView(menuTree);
		menuTree.addTreeSelectionListener(this);
		menuTree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);
	}

	@Override
	public void valueChanged(TreeSelectionEvent e) {

		// 获取树节点，节点名称
		IconTreeNode node = (IconTreeNode) e.getNewLeadSelectionPath()
				.getLastPathComponent();
		
		// 切换显示内容
		if (node.isLeaf()) {
			String name = node.getTitle();
			if(!hasCreateNames.contains(name)){
				Component panel=null;
				try {
					panel = (Component) viewPanelMap.get(name).newInstance();
				} catch (InstantiationException e1) {
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					e1.printStackTrace();
				}
				workPanel.add(panel, name);
				hasCreateNames.add(name);
			}
			workPanelLayout.show(workPanel, name);
		}

	}
}
