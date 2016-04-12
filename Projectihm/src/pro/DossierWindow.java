package pro;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import pro.TreeNode.TreeButton;

public class DossierWindow extends JPanel implements MouseListener,MouseMotionListener{
	
	private ImageIcon img;
	private int focustime;
	private JLabel imgLabel;
	private static boolean isroot = false;
	private Paneficher mypane;
	private JPanel panel;
	
	public Paneficher getMypane() {
		return mypane;
	}

	public void setMypane(Paneficher mypane) {
		this.mypane = mypane;
	}

	private TreeNode rootNode;
	private int x0,x1,y0,y1;
	private TreeButton selectedButton;
	private JButton deleteJButton;
	private JButton cloneJButton;
	private JButton colleJButton;
	private JButton undoJButton;
	private JButton redoJButton;
	private JButton closeButton;
	private JComboBox<String> colorComboBox;
	
	
	public DossierWindow(String tible,int width,int height,TreeNode parenTreeNode,MyFrame thisparent) {
		// TODO Auto-generated constructor stub
		super();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		rootNode = parenTreeNode;
		deleteJButton = new JButton("delete");
		cloneJButton = new JButton(" clone");
		//rangeJButton = new JButton("range ");
		undoJButton = new JButton(" undo  ");
		redoJButton = new JButton("  redo  ");
		colleJButton = new JButton(" colle ");
		closeButton = new JButton("close");
		
		String jg[] = {"white","blue","green","black"};
		
		colorComboBox = new JComboBox<String>(jg);
		
		Container pane = new Container();			
		pane.setLayout(new BoxLayout(pane, BoxLayout.LINE_AXIS));
		
		JPanel menuJPanel = new JPanel();
		
		
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		JPanel panel2 = new JPanel();
		panel2.add(colorComboBox);
		

		panel.add(Box.createVerticalStrut(20));
		panel.add(cloneJButton);
		
		panel.add(Box.createVerticalStrut(20));
		panel.add(colleJButton);

		panel.add(Box.createVerticalStrut(20));
		panel.add(deleteJButton);

		//panel.add(Box.createVerticalStrut(20));
		//panel.add(rangeJButton);
		
		panel.add(Box.createVerticalStrut(20));
		panel.add(undoJButton);
		panel.add(Box.createVerticalStrut(10));
		panel.add(redoJButton);
		
		panel.add(Box.createVerticalStrut(20));
		panel.add(closeButton);
		/*
		closeButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				System.exit(0);
			}
		});
		*/
		panel.add(Box.createVerticalStrut(20));
		//panel.add(Box.createRigidArea(new Dimension(0, 5)));
		//
		panel2.setPreferredSize(new Dimension(80, 20));
		panel.add(panel2);
		
		panel.add(Box.createVerticalGlue());
		
		pane.add(panel);

		mypane = new Paneficher(rootNode);
		mypane.setBackground(Color.WHITE);
		mypane.setPreferredSize(new Dimension(width, height));
		
		pane.add(mypane);
		this.add(pane);
		
		//System.out.println(this.getParent());
		//System.out.println(this.getParent().getParent());
		//System.out.println(this.getParent().getParent().getParent());
		mypane.addMouseListener(thisparent);
		mypane.addMouseMotionListener(thisparent);
		
		this.addMouseListener(thisparent);
		this.addMouseMotionListener(thisparent);
		
		colorComboBox.addMouseListener(this);
		
		closeButton.addMouseListener(this);
		undoJButton.addMouseListener(this);
		redoJButton.addMouseListener(this);
		
		this.setSize(width,height);
		this.setVisible(true);
		
		pane.revalidate();
	}
	
	public TreeButton getSelectedButton() {
		return selectedButton;
	}

	public void setSelectedButton(TreeButton selectedButton) {
		this.selectedButton = selectedButton;
	}

	public JButton getDeleteJButton() {
		return deleteJButton;
	}

	public void setDeleteJButton(JButton deleteJButton) {
		this.deleteJButton = deleteJButton;
	}

	public JButton getCloneJButton() {
		return cloneJButton;
	}

	public void setCloneJButton(JButton cloneJButton) {
		this.cloneJButton = cloneJButton;
	}



	public JButton getColleJButton() {
		return colleJButton;
	}

	public void setColleJButton(JButton colleJButton) {
		this.colleJButton = colleJButton;
	}

	public JButton getUndoJButton() {
		return undoJButton;
	}

	public void setUndoJButton(JButton undoJButton) {
		this.undoJButton = undoJButton;
	}

	public JButton getRedoJButton() {
		return redoJButton;
	}

	public void setRedoJButton(JButton redoJButton) {
		this.redoJButton = redoJButton;
	}

	public JButton getCloseButton() {
		return closeButton;
	}

	public void setCloseButton(JButton closeButton) {
		this.closeButton = closeButton;
	}

	public JComboBox<String> getColorComboBox() {
		return colorComboBox;
	}

	public void setColorComboBox(JComboBox<String> colorComboBox) {
		this.colorComboBox = colorComboBox;
	}

	public int getfocustime() {
		return focustime;
	}

	public void setfocustime(int getfocustime) {
		this.focustime = getfocustime;
	}
	
	public TreeNode getRootNode() {
		return rootNode;
	}

	public void setRootNode(TreeNode rootNode) {
		this.rootNode = rootNode;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getSource()==closeButton) {
			System.out.println("dsfdsfds");
			JPanel panelparent = (JPanel)this.getParent();
			this.getParent().remove(this);
			panelparent.repaint();
			panelparent.updateUI();
			panelparent.revalidate();
		}
		if (e.getSource()==undoJButton) {
			
			System.out.println("undo");
			if (mypane.buttonSupprimeArrayList.size()!=0) {
				mypane.add(mypane.buttonSupprimeArrayList.get(mypane.buttonSupprimeArrayList.size()-1));		
				mypane.buttonrendreArrayList.add(mypane.buttonSupprimeArrayList.get(mypane.buttonSupprimeArrayList.size()-1));
				mypane.buttonSupprimeArrayList.remove(mypane.buttonSupprimeArrayList.size()-1);
				mypane.repaint();
				mypane.updateUI();
				mypane.revalidate();
			}
		}
		
		if (e.getSource()==redoJButton) {
			
			System.out.println("redo");
			if (mypane.buttonrendreArrayList.size()!=0) {
				mypane.remove(mypane.buttonrendreArrayList.get(mypane.buttonrendreArrayList.size()-1));		
				mypane.buttonSupprimeArrayList.add(mypane.buttonrendreArrayList.get(mypane.buttonrendreArrayList.size()-1));
				mypane.buttonrendreArrayList.remove(mypane.buttonrendreArrayList.size()-1);
				mypane.repaint();
				mypane.updateUI();
				mypane.revalidate();
			}
		}
		if (e.getSource()==colorComboBox) {
			System.out.println("colorcombobox");
			if (colorComboBox.getSelectedItem()=="white") {
				System.out.println("colorcombobox");
				mypane.setBackground(Color.white);
			}
			if (colorComboBox.getSelectedItem()=="blue") {
				System.out.println("colorcombobox");
				mypane.setBackground(Color.blue);
			}
			if (colorComboBox.getSelectedItem()=="green") {
				System.out.println("colorcombobox");
				mypane.setBackground(Color.green);
			}
			if (colorComboBox.getSelectedItem()=="black") {
				System.out.println("colorcombobox");
				mypane.setBackground(Color.black);
			}
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
