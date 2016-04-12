package pro;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.TextArea;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Txtwindow extends JPanel{
	
	private JTextArea myArea;
	private TreeNode rootNode;
	private String txtString;
	
	public String getTxtString() {
		return txtString;
	}

	public void setTxtString(String txtString) {
		this.txtString = txtString;
	}

	public Txtwindow(TreeNode parenTreeNode,MyFrame thisparent){
		super();
		rootNode = parenTreeNode;
		this.setLayout(null);	
		myArea = new JTextArea("txt");
		this.setSize(800,600);
		myArea.setSize(this.getWidth(), this.getHeight());
		this.add(myArea);
		this.setVisible(true);
		myArea.addMouseListener(thisparent);
	}
	
}
