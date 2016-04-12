package pro;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MenuItem;
import java.awt.MouseInfo;
import java.awt.Paint;
import java.awt.Panel;
import java.awt.Point;
import java.awt.PopupMenu;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;

import pro.TreeNode.TreeButton;



public class MyFrame extends JFrame implements MouseListener,MouseMotionListener{
	
	private ArrayList<DossierWindow> panelwindows;
	private ArrayList<Txtwindow> txtwindows;
	private int flagdrag ;
	private int flag;
	private ImageIcon img;
	private JLabel imgLabel;
	private TreeButton selectedButton;
	private DossierWindow selecteDossierWindow;
	private int x0,x1,y0,y1;
	private TreeNode rootNode;
	private Paneficher mypane;
	private JButton buttonclose;
	private TreeButton waitbuttion;
	
	@SuppressWarnings("deprecation")
	public MyFrame(String tible,int width,int height,TreeNode parenTreeNode) {
		// TODO Auto-generated constructor stub
		super(tible);
		buttonclose = new JButton("close");
		
		selecteDossierWindow = null;
		flag = 0;
		flagdrag = 0;
		panelwindows = new ArrayList<DossierWindow>();
		txtwindows = new ArrayList<Txtwindow>();
		
		img = new ImageIcon("d://1.jpg");//这是背景图片  
	    imgLabel = new JLabel(img);//将背景图放在标签里。  
	  
	    this.setUndecorated(true);
	    this.getGraphicsConfiguration().getDevice().setFullScreenWindow(this);
	    
		this.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));//注意这里是关键，将背景标签添加到jfram的LayeredPane面板里。  
		imgLabel.setBounds(0,0,img.getIconWidth(), img.getIconHeight());//设置背景标签的位置  
		rootNode = new TreeNode();
		
		
		mypane = new Paneficher(rootNode);
		mypane.setBackground(Color.WHITE);
		mypane.setSize(new Dimension(this.getWidth(),this.getHeight()));
		mypane.setOpaque(false);
		
		Container cp=this.getContentPane();  
		cp.setLayout(new BorderLayout());
		
		//cp.add(mypane);
		//mypane.setOpaque(false);
		
		((JPanel)cp).setOpaque(false);
		this.setLayout(null);
		buttonclose.setBounds(this.getWidth()-100, this.getHeight()-50, 100, 30);
		
		this.add(buttonclose);
		this.add(mypane);
		
		pack();
		mypane.addMouseListener(this);
		mypane.addMouseMotionListener(this);
		
		this.setSize(width,height);
		this.setVisible(true);
		
		buttonclose.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				System.exit(0);
			}
		});
		
	}
	
	
	@SuppressWarnings({ "deprecation", "deprecation" })
	public void iconeRightClick(TreeNode treeNode) {
		
		JPopupMenu popupMenu1 = new JPopupMenu();
		JMenuItem menuItem1 = new JMenuItem();
		JMenuItem menuItem2 = new JMenuItem();
		JMenuItem menuItem3 = new JMenuItem();
		JMenuItem menuItem4 = new JMenuItem();
		
		
		menuItem1.setLabel("supprimer");
		menuItem2.setLabel("ouvirir");
		menuItem3.setLabel("copier");
		menuItem4.setLabel("renomer");
		
		popupMenu1.add(menuItem2);
		popupMenu1.add(menuItem3);
		popupMenu1.add(menuItem4);
		popupMenu1.add(menuItem1);
		
		menuItem2.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("unused")
			public void mousePressed(MouseEvent e) {
				System.out.println("ouvrir");
				DossierWindow dossierWindow2 = new DossierWindow("afd",800, 600, selectedButton.getTreeNodebybutton(),MyFrame.this);
				for (int i = 0; i < panelwindows.size(); i++) {
					DossierWindow dossiertemp = (DossierWindow)panelwindows.get(i);  
					dossiertemp.setfocustime(dossiertemp.getfocustime()+1);
				}
				MyFrame.this.panelwindows.add(dossierWindow2);
				dossierWindow2.setfocustime(0);
				dossierWindow2.setLocation(500,200);
				
				MyFrame.this.mypane.add(dossierWindow2);
				selecteDossierWindow = dossierWindow2;
				MyFrame.this.mypane.revalidate();
				MyFrame.this.revalidate();
			}
		});
		
		menuItem3.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("unused")
			public void mousePressed(MouseEvent e) {
				waitbuttion = selectedButton;
			}
				
		});
		
		menuItem1.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("unused")
			public void mousePressed(MouseEvent e) {
				Paneficher  paneficher = (Paneficher)(selectedButton.getParent());
				//paneficher.getParentTreeNode().getChildList().remove(((TreeButton)selectedButton).getTreeNodebybutton());
				paneficher.buttonSupprimeArrayList.add(selectedButton);
				paneficher.remove(selectedButton);
				paneficher.updateUI();
				paneficher.revalidate();
				
			}
		});
		
		treeNode.getbutton().addMouseListener(new MouseAdapter() {
			@SuppressWarnings("deprecation")
			public void mousePressed(MouseEvent e) {
				int mods=e.getModifiers();								
				if((mods&InputEvent.BUTTON3_MASK)!=0){	
					TreeButton button = (TreeButton)e.getSource();
					MyFrame.this.selectedButton = button;
					popupMenu1.show(((TreeButton)e.getSource()).getParent(),button.getX()+button.getWidth(),button.getY()+button.getHeight());	
					((TreeButton)e.getSource()).getParent().revalidate();
						//mypane.repaint();
				}
				if((mods&InputEvent.BUTTON1_MASK)!=0){	
					TreeButton button = (TreeButton)e.getSource();
					MyFrame.this.selectedButton = button;
					button.setIsselected(true);
					button.setBackground(Color.blue);
					((TreeButton)e.getSource()).getParent().revalidate();
					x0 = e.getX();
					y0 = e.getY();
						//mypane.repaint();
				}
			}
			 public void mouseClicked(MouseEvent e) {
			    int clickTimes = e.getClickCount();
			    if (clickTimes == 2) {
			    	if (selectedButton.getTreeNodebybutton().isIsdosseir()) {
				    	TreeButton button = (TreeButton)e.getSource();
				    	DossierWindow dossierWindow2 = new DossierWindow("afd",800, 600, button.getTreeNodebybutton(),MyFrame.this);
						for (int i = 0; i < panelwindows.size(); i++) {
							DossierWindow dossiertemp = (DossierWindow)panelwindows.get(i);  
							dossiertemp.setfocustime(dossiertemp.getfocustime()+1);
						}
						MyFrame.this.panelwindows.add(dossierWindow2);
						dossierWindow2.setfocustime(0);
						dossierWindow2.setLocation(500,200);
						
						mypane.add(dossierWindow2);
						selecteDossierWindow = dossierWindow2;
						mypane.updateUI();
						mypane.revalidate();
					}	
			    	else {
			    		Txtwindow txtwindow = null;
			    		TreeButton button = (TreeButton)e.getSource();
			    		if (button.getTreeNodebybutton().txt==-1) {
			    			
			    			txtwindow = new Txtwindow(rootNode, MyFrame.this);
			    			txtwindows.add(txtwindow);
			    			button.getTreeNodebybutton().setTxt(txtwindows.size()-1); 
							txtwindow.setLocation(500,100);
						}
				    	
			    		else {
			    			System.out.println(button.getTreeNodebybutton().getTxt()); 
			    			txtwindow =  txtwindows.get(button.getTreeNodebybutton().getTxt());
			    		}
						
						mypane.add(txtwindow);
						mypane.updateUI();
						mypane.revalidate();
						//selecteDossierWindow = dossierWindow2;
					}
				}
			    
			}
		});
		treeNode.getbutton().addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent e) {
				//flagdrag = 1;
				int mods=e.getModifiers();	
				if((mods&InputEvent.BUTTON1_MASK)!=0){					
					x1 = e.getX();
					y1 = e.getY();
					for (int i = 0;i < ((Paneficher)(((TreeButton)e.getSource()).getParent())).getParentTreeNode().getChildList().size(); i++) {				
						TreeButton buttontemp = ((Paneficher)(((TreeButton)e.getSource()).getParent())).getParentTreeNode().getChildList().get(i).getbutton();
						if (buttontemp.isIsselected()) {					
							buttontemp.setBounds(buttontemp.getX()+(x1-x0), buttontemp.getY()+(y1-y0), buttontemp.getWidth(), buttontemp.getHeight());
						}					
					}
					((TreeButton)e.getSource()).getParent().revalidate();
				}
			}
		});
		
		treeNode.getbutton().addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				//if (flagdrag==1) {
					int mods=e.getModifiers();	
					if((mods&InputEvent.BUTTON1_MASK)!=0){					
						for (int i = 0; i < panelwindows.size(); i++) {
							DossierWindow qq = panelwindows.get(i);
							Point point = MouseInfo.getPointerInfo().getLocation();
							if (point.getX()>qq.getX()&&point.getX()<qq.getX()+qq.getWidth()&&point.getY()>qq.getY()&&point.getY()<qq.getY()+qq.getHeight()&&(panelwindows.get(i).getMypane()!=((TreeButton)e.getSource()).getParent())) {
								if (panelwindows.get(i).getRootNode()==((TreeButton)e.getSource()).getTreeNodebybutton()) {
									System.out.println("faut");
									JButton faut= new JButton("cest interdit");
									faut.setSize(300,100);
									faut.setLocation(1000,200);
									mypane.add(faut);
									//((TreeButton)e.getSource()).setLocation(1000, 0);
									faut.addMouseListener(new MouseAdapter() {
										public void mousePressed(MouseEvent e) {
											mypane.remove((JButton)e.getSource());
											mypane.updateUI();
											mypane.revalidate();
										}
									});
									break;
								}
								else{
									Paneficher myPaneficher =  (Paneficher)(((TreeButton)e.getSource()).getParent());
									myPaneficher.remove((TreeButton)e.getSource());
									//((TreeButton)e.getSource()).getTreeNodebybutton().setParentNode(panelwindows.get(i).getRootNode());
									panelwindows.get(i).getMypane().addButton(((TreeButton)e.getSource()).getTreeNodebybutton(),null);					
									
									
									TreeNode ttnode = ((TreeButton)e.getSource()).getTreeNodebybutton();
									TreeNode ppNode =  (((TreeButton)e.getSource()).getTreeNodebybutton()).getParentNode();
									ppNode.childList.remove(ttnode);
									panelwindows.get(i).getRootNode().addChildNode(ttnode);
									//panelwindows.get(i).getMypane().updateUI();
									//panelwindows.get(i).getMypane().revalidate();
									mypane.revalidate();
									break;
					
								}
							}
							
						}
					//}
				}
				//flagdrag = 0;
			}
				
		});
	
	
	}
	
	public TreeNode getRootNode() {
		return rootNode;
	}

	public void setRootNode(TreeNode rootNode) {
		this.rootNode = rootNode;
	}

	
	

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	

	@Override
	public void mousePressed(MouseEvent e) {
		
		int flag = 0;
		x0 = e.getX();
		y0 = e.getY();
		
		for (int i = 0; i < mypane.getComponentCount(); i++) {
			
			System.out.println(mypane.getComponentCount());
			if (mypane.getComponent(i).getClass()==DossierWindow.class) {
				
				System.out.println("jin qu");
				DossierWindow dossiertemp = (DossierWindow)mypane.getComponent(i);  
				
				if (dossiertemp.contains(x0,y0)) {
					
					selecteDossierWindow = dossiertemp;
					break;
				}
			}
			
		}
		
		
	
		
		
		//mypane.setLayout(selecteDossierWindow,0);
		mypane.repaint();
		mypane.revalidate();
	
				
		
		int mods=e.getModifiers();
		//mouse click right
		if ((e.getSource().getClass()==Paneficher.class)&&(mods&InputEvent.BUTTON3_MASK)!=0) {	

			JPopupMenu popupMenu1 = new JPopupMenu();
			JMenuItem menuItem1 = new JMenuItem();
			JMenuItem menuItem2 = new JMenuItem();
			JMenuItem menuItem3 = new JMenuItem();
			JMenuItem menuItem4 = new JMenuItem();
			menuItem1.setLabel("paste");
			menuItem2.setLabel("range");
			menuItem3.setLabel("undo");
			menuItem4.setLabel("redo");
			
			menuItem1.addMouseListener(new MouseAdapter() {
				@SuppressWarnings("unused")
				public void mousePressed(MouseEvent e) {

					if (waitbuttion!=null) {
						
						if (selecteDossierWindow==null) {
							mypane.addButton(waitbuttion.getTreeNodebybutton(),waitbuttion.getTreeNodebybutton());
							mypane.updateUI();
							mypane.revalidate();
						}
						else{
							selecteDossierWindow.getMypane().addButton(waitbuttion.getTreeNodebybutton(),waitbuttion.getTreeNodebybutton());
							waitbuttion = null;
							selecteDossierWindow.getMypane().updateUI();
							selecteDossierWindow.getMypane().revalidate();
						}
					}
				}
			});	
			
			menuItem2.addMouseListener(new MouseAdapter() {
				@SuppressWarnings("unused")
				public void mousePressed(MouseEvent e) {
					if (selecteDossierWindow==null) {
						int y = 10;
						int x = 0;
						for (int i = 0; i < mypane.getComponentCount(); i++) {
							
							if (x+60>mypane.getSize().getWidth()) {
								y = y+90;
								x = 0; 
							}
							mypane.getComponent(i).setLocation(x, y);
							x = x+140;
						}
						mypane.updateUI();
						mypane.revalidate();
					}
					else{
						
						Paneficher mypane =  selecteDossierWindow.getMypane();
						int y = 0;
						int x = 0;
						for (int i = 0; i < mypane.getComponentCount(); i++) {
							
							if (x>mypane.getSize().getWidth()) {
								y = y+90;
								x = 0; 
							}
							mypane.getComponent(i).setLocation(x, y);
							x = x+140;
						}
						mypane.updateUI();
						mypane.revalidate();
				    }
				}
			});
			
			menuItem3.addMouseListener(new MouseAdapter() {
				@SuppressWarnings("unused")
				public void mousePressed(MouseEvent e) {
					if (selecteDossierWindow==null) {
						if (mypane.buttonSupprimeArrayList.size()!=0) {
							mypane.add(mypane.buttonSupprimeArrayList.get(mypane.buttonSupprimeArrayList.size()-1));		
							mypane.buttonrendreArrayList.add(mypane.buttonSupprimeArrayList.get(mypane.buttonSupprimeArrayList.size()-1));
							mypane.buttonSupprimeArrayList.remove(mypane.buttonSupprimeArrayList.size()-1);
							mypane.repaint();
							mypane.updateUI();
							mypane.revalidate();
						}
					}
					else{
						Paneficher mypane =  selecteDossierWindow.getMypane();
						if (mypane.buttonSupprimeArrayList.size()!=0) {
							mypane.add(mypane.buttonSupprimeArrayList.get(mypane.buttonSupprimeArrayList.size()-1));		
							mypane.buttonrendreArrayList.add(mypane.buttonSupprimeArrayList.get(mypane.buttonSupprimeArrayList.size()-1));
							mypane.buttonSupprimeArrayList.remove(mypane.buttonSupprimeArrayList.size()-1);
							mypane.repaint();
							mypane.updateUI();
							mypane.revalidate();
						}
					}
				}
			});	
			
			menuItem4.addMouseListener(new MouseAdapter() {
				@SuppressWarnings("unused")
				public void mousePressed(MouseEvent e) {
					
					if (selecteDossierWindow==null) {
						if (mypane.buttonrendreArrayList.size()!=0) {
							mypane.remove(mypane.buttonrendreArrayList.get(mypane.buttonrendreArrayList.size()-1));		
							mypane.buttonSupprimeArrayList.add(mypane.buttonrendreArrayList.get(mypane.buttonrendreArrayList.size()-1));
							mypane.buttonrendreArrayList.remove(mypane.buttonrendreArrayList.size()-1);
							mypane.repaint();
							mypane.updateUI();
							mypane.revalidate();
						}
					}
					else{
						Paneficher mypane =  selecteDossierWindow.getMypane();
						if (mypane.buttonrendreArrayList.size()!=0) {
							mypane.remove(mypane.buttonrendreArrayList.get(mypane.buttonrendreArrayList.size()-1));		
							mypane.buttonSupprimeArrayList.add(mypane.buttonrendreArrayList.get(mypane.buttonrendreArrayList.size()-1));
							mypane.buttonrendreArrayList.remove(mypane.buttonrendreArrayList.size()-1);
							mypane.repaint();
							mypane.updateUI();
							mypane.revalidate();
						}
					}		
				}
			});	
			
			
			
			JMenu menuNew = new JMenu();
			menuNew.setLabel("new");
	        JMenuItem dossierItem = new JMenuItem("dosser");
	        JMenuItem exeItem = new JMenuItem("exe");
	        JMenuItem txtItem = new JMenuItem("txt");
	        menuNew.add(dossierItem);
	        menuNew.add(exeItem);
	        menuNew.add(txtItem);
			add(popupMenu1);
			popupMenu1.add(menuNew);
			popupMenu1.add(menuItem3);
			popupMenu1.add(menuItem4);
			popupMenu1.add(menuItem1);
			popupMenu1.add(menuItem2);
			
			popupMenu1.show((JPanel)e.getSource(),e.getX(),e.getY());
			((JPanel)e.getSource()).revalidate();
			
			dossierItem.addMouseListener(new MouseAdapter() {
	        	public void mousePressed(MouseEvent f) {

					TreeNode treeNode = new TreeNode(((Paneficher)e.getSource()).getParentTreeNode());
					treeNode.setIsdosseir(true);
					((Paneficher)e.getSource()).getParentTreeNode().addChildNode(treeNode);
					((Paneficher)e.getSource()).addButton(treeNode);
					iconeRightClick(treeNode);
					((Paneficher)e.getSource()).updateUI();
					((Paneficher)e.getSource()).revalidate();
					((Paneficher)e.getSource()).repaint();
					//mypane.add(new Button());
					
				}
			});
			txtItem.addMouseListener(new MouseAdapter() {
	        	public void mousePressed(MouseEvent f) {

					TreeNode treeNode = new TreeNode(((Paneficher)e.getSource()).getParentTreeNode());
					treeNode.setIsdosseir(false);
					((Paneficher)e.getSource()).getParentTreeNode().addChildNode(treeNode);
					((Paneficher)e.getSource()).addButton(treeNode);
					iconeRightClick(treeNode);
					((Paneficher)e.getSource()).updateUI();
					((Paneficher)e.getSource()).revalidate();
					((Paneficher)e.getSource()).repaint();
					//mypane.add(new Button());
					
				}
			});
			
		}
		
		System.out.println(e.getSource());
		if ((e.getSource().getClass()==JTextArea.class)&&(mods&InputEvent.BUTTON3_MASK)!=0) {	

			System.out.println("txt cai dan");
			JPopupMenu popupMenu1 = new JPopupMenu();
			JMenuItem menuItem1 = new JMenuItem();
			JMenuItem menuItem2 = new JMenuItem();
			
			menuItem1.setLabel("enregistrer");
			menuItem2.setLabel("fermer");
			
			popupMenu1.add(menuItem1);
			popupMenu1.add(menuItem2);

			popupMenu1.show((JTextArea)e.getSource(),x0,y0);
			((JTextArea)e.getSource()).requestFocus();
			
			((JTextArea)e.getSource()).revalidate();
			
			
			
			menuItem1.addMouseListener(new MouseAdapter() {
				JTextArea myTxtwindow =  (JTextArea)e.getSource();
				public void mousePressed(MouseEvent e) {
					 ((Txtwindow)myTxtwindow.getParent()).setTxtString(myTxtwindow.getText());
				}
			});		
			
			menuItem2.addMouseListener(new MouseAdapter() {
				JTextArea myTxtwindow =  (JTextArea)e.getSource();
				public void mousePressed(MouseEvent e) {
					MyFrame.this.mypane.remove(myTxtwindow.getParent());
					mypane.updateUI();
					mypane.revalidate();
				}
			});	
			
			
		}
		//mouse left click
		else if ((e.getSource().getClass()==Paneficher.class)&&(mods&InputEvent.BUTTON1_MASK)!=0) {
			
			
			for (int i = 0; i < ((Paneficher)e.getSource()).getParentTreeNode().getChildList().size(); i++) {
				((Paneficher)e.getSource()).getParentTreeNode().getChildList().get(i).getbutton().setBackground(Color.white);
				((Paneficher)e.getSource()).getParentTreeNode().getChildList().get(i).getbutton().setIsselected(false);
				((Paneficher)e.getSource()).revalidate();
			}

		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {		
		
		System.out.println(flag);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		if (e.getSource().getClass()==Paneficher.class) {
			((Paneficher)e.getSource()).setDrawPosition(0, 0, 0, 0);
			((Paneficher)e.getSource()).repaint();
			((Paneficher)e.getSource()).revalidate();
		}
		if (flag==1) {
			((DossierWindow)e.getSource()).setLocation(0,0);
			
			flag = 0;
		}
		if (flag==2) {
			System.out.println("wo de");
			((DossierWindow)e.getSource()).setLocation((int) (screenSize.getWidth()-((DossierWindow)e.getSource()).getWidth()),0);
			mypane.updateUI();
			mypane.revalidate();
			flag = 0;
		}
			
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		MyFrame ffFrame = new MyFrame("window",screenSize.width,screenSize.height,null);
		ffFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				ffFrame.dispose();
			}
		});

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
		
		
		int mods=e.getModifiers();	
		
		if((e.getSource().getClass()==Paneficher.class)&&(mods&InputEvent.BUTTON1_MASK)!=0){	
			x1 = e.getX();
			y1 = e.getY();
			if (x1>x0&&y1>y0) {
				((Paneficher)e.getSource()).setDrawPosition(x0, y0, x1-x0, y1-y0);
			}
			else if (x1<x0&&y1>y0) {
				((Paneficher)e.getSource()).setDrawPosition(x1, y0, x0-x1, y1-y0);
			}
			else if (x1>x0&&y1<y0) {
				((Paneficher)e.getSource()).setDrawPosition(x0, y1, x1-x0, y0-y1);
			}
			if (x1<x0&&y1<y0) {
				((Paneficher)e.getSource()).setDrawPosition(x1, y1, x0-x1, y0-y1);
			}
			((Paneficher)e.getSource()).repaint();
			((Paneficher)e.getSource()).revalidate();
		}
		
		
		else if ((mods&InputEvent.BUTTON1_MASK)!=0){
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			System.out.println("jin qu le le le ");
			x1 = e.getX();
			y1 = e.getY();
			DossierWindow dossierWindow;
			System.out.println();
			dossierWindow = (DossierWindow)e.getSource();
			dossierWindow.show();
			//dossierWindow.setLocation(dossierWindow.getX()+(x1-x0),dossierWindow.getY()+(y1-y0));
			//System.out.println(mypane.getSize().getWidth());
			System.out.println("x1"+x1);
			if (MouseInfo.getPointerInfo().getLocation().getX()<=0.05) {
				flag = 1;
			}
			
			
			else if (MouseInfo.getPointerInfo().getLocation().getY()<0.05) {
				System.out.println("cheng li");
				flag = 2;
			}
			else
				dossierWindow.setLocation(dossierWindow.getX()+(x1-x0),dossierWindow.getY()+(y1-y0));
			mypane.repaint();
			mypane.updateUI();
			mypane.revalidate();
			
		}
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
