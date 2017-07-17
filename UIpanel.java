import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Hashtable;
import java.awt.Toolkit;
/**
 * 
 * This uses the Singleton Pattern.
 *
 */
public class UIpanel {
	
	private static UIpanel instance = new UIpanel(); // singleton pattern
	private JFrame frame;
	private JTextField txtUser;
	private JTree userTree;
	private UserGroup rootGroup;
	private DefaultMutableTreeNode root;
	private JTextField txtGroupID;
	private DefaultTreeModel treeModel;
	private Hashtable<String, Users> users;
	private Hashtable<String, UserGroup> groups;
	private VisitorsInterface visitorsCount;
	private VisitorsInterface goodVisitors;
	
	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	private UIpanel() {
		initialize();
		this.frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(UIpanel.class.getResource("/edu/cpp/cs356/assignment2/icon.png")));
		frame.setResizable(false);
		frame.setTitle("Mini Twitter - Admin Panel");
		frame.setBounds(100, 100, 602, 394);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		rootGroup = new UserGroup("Root");
		users = new Hashtable<String, Users>();
		groups = new Hashtable<String, UserGroup>();
		visitorsCount = new VisitorCount();
		goodVisitors = new Positive();
		
		userTree = new JTree();
		userTree.setBorder(BorderFactory.createEmptyBorder(1, 5, 1, 5));
		userTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		root = new DefaultMutableTreeNode(rootGroup);
		treeModel = new DefaultTreeModel( root );
		userTree.setModel( treeModel );
		userTree.setBounds(10, 13, 190, 323);
		frame.getContentPane().add(userTree);

		DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) userTree.getCellRenderer();
		renderer.setLeafIcon(null);
		renderer.setClosedIcon(null);
		renderer.setOpenIcon(null);

		JButton btnAddUser = new JButton("Add User");
		btnAddUser.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String userID = txtUser.getText();
				if ( users.containsKey(userID) ){
					txtUser.setText("UserID Already Exists");
				} else {
					DefaultMutableTreeNode node = getAddingGroup();
					UserGroup addingGroup = (UserGroup)node.getUserObject();
					
					Users newUser = new Users(userID);
					addingGroup.addGroup(newUser);
					DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(newUser);
					node.add( newNode );
					users.put(newUser.toString(), newUser );
					
					visitorsCount.resetCount();
					rootGroup.allowVisitor(visitorsCount);
					
					treeModel.reload();
					userTree.expandPath(new TreePath(node.getPath()));
					txtUser.setText("");
				}

			}
		});
		btnAddUser.setMargin(new Insets(2, 2, 2, 2));
		btnAddUser.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnAddUser.setBounds(421, 11, 136, 48);
		frame.getContentPane().add(btnAddUser);

		txtUser = new JTextField();
		txtUser.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtUser.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_ENTER) {
					btnAddUser.doClick();
				}
			}
		});
		txtUser.setText("      User ID");
		setHintText( txtUser, "User ID");
		txtUser.setBounds(250, 12, 150, 46);
		frame.getContentPane().add(txtUser);
		txtUser.setColumns(10);

		JButton btnAddGroup = new JButton("Add Group");
		btnAddGroup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String groupID = txtGroupID.getText();
				if ( groups.containsKey(groupID)){
					txtGroupID.setText("Group already exists.");
					
				} else {
					DefaultMutableTreeNode node = getAddingGroup();
					UserGroup addingGroup = (UserGroup)node.getUserObject();
					UserGroup newUserGroup = new UserGroup(txtGroupID.getText());
					addingGroup.addGroup( newUserGroup );
					node.add( new DefaultMutableTreeNode(newUserGroup) );
					groups.put(groupID, newUserGroup);

					visitorsCount.resetCount();
					rootGroup.allowVisitor(visitorsCount);
					
					treeModel.reload();
					userTree.expandPath(new TreePath(node.getPath()));
					txtGroupID.setText("");
				}
			
			}
		});
		btnAddGroup.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnAddGroup.setBounds(421, 70, 136, 48);
		frame.getContentPane().add(btnAddGroup);


		txtGroupID = new JTextField();
		txtGroupID.setFont(new Font("Tahoma", Font.PLAIN, 19));
		txtGroupID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_ENTER) {
					btnAddGroup.doClick();
				}
			}
		});
		setHintText( txtGroupID, "Group ID");
		txtGroupID.setText("     Group ID");
		txtGroupID.setBounds(250, 70, 150, 48);
		frame.getContentPane().add(txtGroupID);
		txtGroupID.setColumns(10);

		JButton btnOpenUser = new JButton("Open User View");
		btnOpenUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Users user = getSelectedUser();
				if ( user != null ){
					new UPanel(user, users);
				}
			}
		});
		btnOpenUser.setBounds(250, 130, 307, 42);
		frame.getContentPane().add(btnOpenUser);

		JButton btnUserTotal = new JButton("Show User Total");
		btnUserTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VisitorCount v = (VisitorCount)visitorsCount;
				String message = "There are " + v.getUsersNum() + " users total.";
				JOptionPane.showMessageDialog(null, message , "User Count", JOptionPane.PLAIN_MESSAGE);
			}
		});
		btnUserTotal.setMargin(new Insets(2, 2, 2, 2));
		btnUserTotal.setBounds(247, 219, 142, 53);
		frame.getContentPane().add(btnUserTotal);

		JButton btnGroupTotal = new JButton("Show Group Total");
		btnGroupTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VisitorCount visitor = (VisitorCount)visitorsCount;
				String msg = "There are " + visitor.getGroupsNum() + " groups total.";
				JOptionPane.showMessageDialog(null, msg , "Group Count", JOptionPane.PLAIN_MESSAGE);
			}
		});
		btnGroupTotal.setMargin(new Insets(2, 2, 2, 2));
		btnGroupTotal.setBounds(415, 219, 142, 53);
		frame.getContentPane().add(btnGroupTotal);

		JButton btnMessagesCount = new JButton("Message Count");
		btnMessagesCount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VisitorCount visitor = (VisitorCount)visitorsCount;
				String message = "There are " + visitor.getMessageNum() + " messages total.";
				JOptionPane.showMessageDialog(null, message , "Message Count", JOptionPane.PLAIN_MESSAGE);
			}
		});
		btnMessagesCount.setMargin(new Insets(2, 2, 2, 2));
		btnMessagesCount.setBounds(247, 283, 142, 53);
		frame.getContentPane().add(btnMessagesCount);

		JButton btnPositivePercent = new JButton("Positive Msg %");
		btnPositivePercent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goodVisitors.resetCount();
				rootGroup.allowVisitor(goodVisitors);
				Positive visitor = (Positive)goodVisitors;
				String msg = String.format("%d%% of messages are positive.", (int)(visitor.getPositivePercentage()*100) );
				JOptionPane.showMessageDialog(null, msg , "Positive Messages", JOptionPane.PLAIN_MESSAGE);
			}
		});
		btnPositivePercent.setMargin(new Insets(2, 2, 2, 2));
		btnPositivePercent.setBounds(415, 283, 142, 53);
		frame.getContentPane().add(btnPositivePercent);
	}

	private void setHintText( JTextField txt, String msg){
		txt.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				if(txt.getText().trim().equals(""))
					txt.setText(msg);
			}

			public void focusGained(FocusEvent e) {
				if( txt.getText().trim().equals(msg))
					txt.setText("");
			}
		});
	}

	private Users getSelectedUser(){
		Users user = null;
		DefaultMutableTreeNode node = (DefaultMutableTreeNode)userTree.getLastSelectedPathComponent();
		if ( node != null ){
			try {
				user = (Users)node.getUserObject();
			} catch ( ClassCastException ex ){
			}
		}
		return user;
	}

	private DefaultMutableTreeNode getAddingGroup() {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode)userTree.getLastSelectedPathComponent();
		if ( node == null ){
			node = root;
		}
		try {
			UserGroup addingGroup = (UserGroup)node.getUserObject();
		} catch ( ClassCastException ex ){
			node = (DefaultMutableTreeNode)node.getParent();
		}
		return node;
	}

	public static UIpanel getInstance() {
		return instance;
	}
}
