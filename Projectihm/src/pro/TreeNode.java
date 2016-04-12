package pro;
import java.util.List;  
import java.util.ArrayList;  
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;  

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
  
public class TreeNode implements Serializable {  
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int parentId;  
    private int selfId;  
    private static int ii = -1;
    private int type;
	private boolean isdosseir;
    protected String nodeName;  
    private TreeButton button;  
    protected TreeNode parentNode;  
    protected int txt;
    protected List<TreeNode> childList;  
	static int j;
  
    public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public boolean isIsdosseir() {
		return isdosseir;
	}

	public void setIsdosseir(boolean isdosseir) {
		this.isdosseir = isdosseir;
	}
    
    public TreeNode() {  
    	txt = -1;
        initChildList();  
    }  
  
    public int getTxt() {
		return txt;
	}

	public void setTxt(int txt) {
		this.txt = txt;
	}

	public TreeNode(TreeNode parentNode) {  
    	txt = -1;
        this.setParentNode(parentNode); 
        initChildList();  
    }  
  
    public boolean isLeaf() {  
        if (childList == null) {  
            return true;  
        } else {  
            if (childList.isEmpty()) {  
                return true;  
            } else {  
                return false;  
            }  
        }  
    }  
  
    /* 插入一个child节点到当前节点中 */  
    public void addChildNode(TreeNode treeNode) {  
        initChildList();  
        childList.add(treeNode);  
        setSelfId(ii);
        
    }  
  
    public void initChildList() {  
        if (childList == null)  
            childList = new ArrayList<TreeNode>();  
    }  
  
    public boolean isValidTree() {  
        return true;  
    }  
  
    /* 返回当前节点的父辈节点集合 */  
    public List<TreeNode> getElders() {  
        List<TreeNode> elderList = new ArrayList<TreeNode>();  
        TreeNode parentNode = this.getParentNode();  
        if (parentNode == null) {  
            return elderList;  
        } else {  
            elderList.add(parentNode);  
            elderList.addAll(parentNode.getElders());  
            return elderList;  
        }  
    }  
  
    /* 返回当前节点的晚辈集合 */  
    public List<TreeNode> getJuniors() {  
        List<TreeNode> juniorList = new ArrayList<TreeNode>();  
        List<TreeNode> childList = this.getChildList();  
        if (childList == null) {  
            return juniorList;  
        } else {  
            int childNumber = childList.size();  
            for (int i = 0; i < childNumber; i++) {  
                TreeNode junior = childList.get(i);  
                juniorList.add(junior);  
                juniorList.addAll(junior.getJuniors());  
            }  
            return juniorList;  
        }  
    }  
  
    /* 返回当前节点的孩子集合 */  
    public List<TreeNode> getChildList() {  
        return childList;  
    }  
  
    /* 删除节点和它下面的晚辈 */  
    public void deleteNode() {  
        TreeNode parentNode = this.getParentNode();  
        int id = this.getSelfId();  
  
        if (parentNode != null) {  
           // parentNode.deleteChildNode(id);  
        }  
    }  
  
    /* 删除当前节点的某个子节点 */  
    public void deleteChildNode(TreeNode treeNode) {  
        List<TreeNode> childList = this.getChildList();  
        int childNumber = childList.size();  
        for (int i = 0; i < childNumber; i++) {  
            TreeNode child = childList.get(i);  
            if (child == treeNode) {  
                childList.remove(i);  
                return;  
            }  
        }  
    }  
  
    /* 动态的插入一个新的节点到当前树中 */  
    public boolean insertJuniorNode(TreeNode treeNode) {  
        int juniorParentId = treeNode.getParentId();  
        if (this.parentId == juniorParentId) {  
            addChildNode(treeNode);  
            return true;  
        } else {  
            List<TreeNode> childList = this.getChildList();  
            int childNumber = childList.size();  
            boolean insertFlag;  
  
            for (int i = 0; i < childNumber; i++) {  
                TreeNode childNode = childList.get(i);  
                insertFlag = childNode.insertJuniorNode(treeNode);  
                if (insertFlag == true)  
                    return true;  
            }  
            return false;  
        }  
    }  
  
    /* 找到一颗树中某个节点 */  
    public TreeNode findTreeNodeById(int id) {  
        if (this.selfId == id)  
            return this;  
        if (childList.isEmpty() || childList == null) {  
            return null;  
        } else {  
            int childNumber = childList.size();  
            for (int i = 0; i < childNumber; i++) {  
                TreeNode child = childList.get(i);  
                TreeNode resultNode = child.findTreeNodeById(id);  
                if (resultNode != null) {  
                    return resultNode;  
                }  
            }  
            return null;  
        }  
    }  
  
    /* 遍历一棵树，层次遍历 */  
    public void traverse() {  
        if (selfId < 0)  
            return;  
        print(this.selfId);  
        if (childList == null || childList.isEmpty())  
            return;  
        int childNumber = childList.size();  
        for (int i = 0; i < childNumber; i++) {  
            TreeNode child = childList.get(i);  
            child.traverse();  
        }  
    }  
  
    public void print(String content) {  
        System.out.println(content);  
    }  
  
    public void print(int content) {  
        System.out.println(String.valueOf(content));  
    }  
  
    public void setChildList(List<TreeNode> childList) {  
        this.childList = childList;  
    }  
  
    public int getParentId() {  
        return parentId;  
    }  
  
    public void setParentId(int parentId) {  
        this.parentId = parentId;  
    }  
  
    public int getSelfId() {  
        return selfId;  
    }  
  
    public void setSelfId(int selfId) {  
        this.selfId = selfId;  
    }  
  
    public TreeNode getParentNode() {  
        return parentNode;  
    }  
  
    public void setParentNode(TreeNode parentNode) {  
        this.parentNode = parentNode;  
    }  
  
    public String getNodeName() {  
        return nodeName;  
    }  
  
    public void setNodeName(String nodeName) {  
        this.nodeName = nodeName;  
    }  
  
    public TreeButton getbutton() {  
        return button;  
    }  
    
    public class TreeButton extends JButton{
    	private boolean isselected;
    	public boolean isIsselected() {
			return isselected;
		}

		public void setIsselected(boolean isselected) {
			this.isselected = isselected;
		}

		public TreeButton(String string, Icon icon) {	
    		super(string+j, icon);
    		isselected = false;
    		j++;
		}
		public TreeButton(String string, Icon icon,TreeNode parent) {	
			
    		super(parent.getbutton().getLabel(), icon);
    		isselected = false;
    		System.out.println("zui hou ");
		}

		public TreeNode getTreeNodebybutton() {
			return TreeNode.this;
		}

    }
    
    public void setbutton() {  
    	
    	if (isdosseir) {
    		byte[] image = null;
        	try {
    			File file = new File("D:\\folder1.gif");// 利用文件visa.gif建立一个File组件。

    			int size = (int) file.length();// 并求出此文件的长度。
    			FileInputStream in = new FileInputStream(file);// 将文件组件放入FileInputStream中。
    			image = new byte[size];
    			in.read(image);// 将数据文件读进byte array中。
    		} catch (IOException e) {
    			System.err.println("File open falure:" + e.getMessage());
    		}
    		Icon icon = new ImageIcon(image);
    		
    		button = new TreeButton("D",icon);
    		button.setBackground(Color.white);
    		button.getTreeNodebybutton();
		}
    	else {
    		byte[] image = null;
        	try {
    			File file = new File("D:\\txt.png");// 利用文件visa.gif建立一个File组件。

    			int size = (int) file.length();// 并求出此文件的长度。
    			FileInputStream in = new FileInputStream(file);// 将文件组件放入FileInputStream中。
    			image = new byte[size];
    			in.read(image);// 将数据文件读进byte array中。
    		} catch (IOException e) {
    			System.err.println("File open falure:" + e.getMessage());
    		}
    		Icon icon = new ImageIcon(image);
    		
    		button = new TreeButton("T",icon);
    		button.setBackground(Color.white);
    		button.getTreeNodebybutton();
		}
    	
		//System.out.println(this.);
    }  
 public void setbutton(TreeNode parent) {  
    	
	 System.out.println("zui hou ");
    	if (isdosseir) {
    		byte[] image = null;
        	try {
    			File file = new File("D:\\folder1.png");// 利用文件visa.gif建立一个File组件。

    			int size = (int) file.length();// 并求出此文件的长度。
    			FileInputStream in = new FileInputStream(file);// 将文件组件放入FileInputStream中。
    			image = new byte[size];
    			in.read(image);// 将数据文件读进byte array中。
    		} catch (IOException e) {
    			System.err.println("File open falure:" + e.getMessage());
    		}
    		Icon icon = new ImageIcon(image);
    		
    		button = new TreeButton("D",icon,parent);
    		button.setName(parent.getbutton().getName());
    		button.setBackground(Color.white);
    		this.parentNode = parent.getParentNode();
    		this.childList = parent.getChildList();
		}
    	else {
    		byte[] image = null;
        	try {
    			File file = new File("D:\\txt.png");// 利用文件visa.gif建立一个File组件。

    			int size = (int) file.length();// 并求出此文件的长度。
    			FileInputStream in = new FileInputStream(file);// 将文件组件放入FileInputStream中。
    			image = new byte[size];
    			in.read(image);// 将数据文件读进byte array中。
    		} catch (IOException e) {
    			System.err.println("File open falure:" + e.getMessage());
    		}
    		Icon icon = new ImageIcon(image);
    		
    		button = new TreeButton("T",icon,parent);
    		button.setName(parent.getbutton().getName());
    		button.setBackground(Color.white);
    		button.getTreeNodebybutton();
    		this.parentNode = parent.getParentNode();
		}
    	
		//System.out.println(this.);
    }  
} 