package pro;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Panel;
import java.awt.Point;
import java.awt.PopupMenu;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import pro.TreeNode.TreeButton;

public class Paneficher extends JPanel{
	
	private TreeNode myTreeNode;
	private TreeNode parentTreeNode;
	ArrayList<TreeButton> buttonSupprimeArrayList;
	ArrayList<TreeButton> buttonrendreArrayList;
	private int x0;
	private int y0;
	private int x1;
	private int y1;
	private int placex;
	private int placey;
	
	public TreeNode getParentTreeNode() {
		return parentTreeNode;
	}

	public void setParentTreeNode(TreeNode parentTreeNode) {
		this.parentTreeNode = parentTreeNode;
	}

	public	Paneficher(TreeNode paTreeNode) {
		parentTreeNode = paTreeNode;
		this.setLayout(null);
		placex = this.getX()+10;
		placey = this.getY()+10;
		List<TreeNode> list = paTreeNode.getChildList();
		for(int i = 0;i < list.size(); i ++){
			this.add(list.get(i).getbutton());
		}
		
		buttonSupprimeArrayList = new ArrayList<TreeButton>();
		buttonrendreArrayList = new ArrayList<TreeButton>();
	}
	public void addButton(TreeNode treeNode) {
		treeNode.setbutton();
		treeNode.getbutton().setBounds(placex,placey,120,80);
		if (this.getWidth()-placex-this.getX()<250) {
			placey = placey+100;
			placex = 10;
		}
		else {
			placex = placex+130;
		}
		
		this.add(treeNode.getbutton());
	}
	
	public void addButton(TreeNode treeNode,TreeNode parent) {
		
		System.out.println("zui hou ");
		//treeNode.setbutton();
		if (parent!=null) {
			treeNode.setbutton(parent);
			treeNode.getbutton().setBounds(placex,placey,120,80);
			if (this.getWidth()-placex-this.getX()<250) {
				placey = placey+100;
				placex = 10;
			}
			else {
				placex = placex+130;
			}
			
			this.add(treeNode.getbutton());
		}
		else {
			treeNode.getbutton().setBounds(placex,placey,120,80);
			if (this.getWidth()-placex-this.getX()<250) {
				placey = placey+100;
				placex = 10;
			}
			else {
				placex = placex+130;
			}
			
			this.add(treeNode.getbutton());
		}
		
	}
	
	public void setDrawPosition(int x0,int y0,int x1,int y1) {
		this.x0 = x0;
		this.x1 = x1;
		this.y0 = y0;
		this.y1 = y1;
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		g.setColor(Color.blue);
		//g.fillRect(0, 0, getWidth(), getHeight());
		g.drawRect(x0, y0, x1, y1);
		
	}
}
