import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LoginGUI extends JFrame implements ActionListener 
{

	private JTabbedPane myTabs = new JTabbedPane();

	private JPanel createAnAccountTab = new JPanel(null);
	
	
	private JLabel lblEnterUsr = new JLabel();
	
	private JLabel lblEnterPasswd = new JLabel();
	
	private JLabel lblReEnterPasswd = new JLabel();

	private JTextField tfEnterUsr = new JTextField(6);

	private JPasswordField pfEnterPasswd = new JPasswordField(10);

	private JPasswordField pfReEnterPasswd = new JPasswordField(10);

	private JButton btSubmitUserDetails = new JButton();


	public void startCreateAnAccountTab()
	{
		for(int i=0;i<50;i++){System.out.print("-");}
		System.out.println();
		System.out.println("Author:Cameron Chisholm");
		for(int i=0;i<50;i++){System.out.print("-");}
		System.out.println();
		
		setUpCreateAnAccountTab();
	}
	
	public void setUpCreateAnAccountTab()
	{
		lblEnterUsr.setLocation(75,100);
		lblEnterUsr.setSize(100,50);
		lblEnterUsr.setOpaque(true);
		lblEnterUsr.setText("Enter Name:");
		
		lblEnterPasswd.setLocation(75,200);
		lblEnterPasswd.setSize(100,50);
		lblEnterPasswd.setOpaque(true);
		lblEnterPasswd.setText("Enter Password:");
		
		lblReEnterPasswd.setLocation(75,300);
		lblReEnterPasswd.setSize(125,50);
		lblReEnterPasswd.setOpaque(true);
		lblReEnterPasswd.setText("Re-enter Password:");
		

		pfEnterPasswd.addActionListener(this);
		pfEnterPasswd.setLocation(200,200);
		pfEnterPasswd.setSize(200,50);
		
		pfReEnterPasswd.addActionListener(this);
		pfReEnterPasswd.setLocation(200,300);
		pfReEnterPasswd.setSize(200,50);

		tfEnterUsr.addActionListener(this);
		tfEnterUsr.setLocation(200,100);
		tfEnterUsr.setSize(200,50);

		btSubmitUserDetails.setLocation(200,400);
		btSubmitUserDetails.setSize(200,50);
		btSubmitUserDetails.addActionListener(this);
		btSubmitUserDetails.setText("Submit");
		


		createAnAccountTab.add(tfEnterUsr);
		createAnAccountTab.add(pfEnterPasswd);
		createAnAccountTab.add(pfReEnterPasswd);
		createAnAccountTab.add(lblEnterUsr);
		createAnAccountTab.add(lblEnterPasswd);
		createAnAccountTab.add(lblReEnterPasswd);
		createAnAccountTab.add(btSubmitUserDetails);
		
	}

	/*

	LOGIN TAB 

	*/
	private JPanel loginTab = new JPanel(null);
	
	// This is the submit button for the login form
	private JButton btLogin = new JButton();
	
	// This is the text field for the login form
	private JTextField tfUsr = new JTextField(6);
	
	// This is the password field for the login form
	private JPasswordField pfPasswd = new JPasswordField(10);
	
	// This is the label describing the user text field
	private JLabel lblUsr = new JLabel();
	
	// This is the label describing the password field
	private JLabel lblPasswd = new JLabel();

	// This is the label displaying the welcome message at the top of the window
	private JLabel lblWelcomeMessage = new JLabel();

	// This label tells the user they can login or create an account
	private JLabel lblOr = new JLabel();

	// This button allows the user to go and create an account rather than login
	private JButton btCreateAnAccount = new JButton();
	
	
	public void startGUI()
	{
		for(int i=0;i<50;i++){System.out.print("-");}
		System.out.println();
		System.out.println("Author:Cameron Chisholm");
		for(int i=0;i<50;i++){System.out.print("-");}
		System.out.println();
		
		setUpLoginTab();
		myTabs.addTab("Login",loginTab);
		this.setLayout(new GridLayout(1,1));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		
		this.setSize(800,500);
		this.setVisible(true);
		
		this.add(myTabs);

		startCreateAnAccountTab();
		
	}
	
	public void setUpLoginTab()
	{
		btLogin.setLocation(110,300);
		btLogin.setSize(200,50);
		btLogin.addActionListener(this);
		btLogin.setText("Login");

		btCreateAnAccount.setLocation(500,200);
		btCreateAnAccount.setSize(200,50);
		btCreateAnAccount.addActionListener(this);
		btCreateAnAccount.setText("Create an Account");
		
		pfPasswd.addActionListener(this);
		pfPasswd.setLocation(110,200);
		pfPasswd.setSize(200,50);
		
		tfUsr.addActionListener(this);
		tfUsr.setLocation(110,100);
		tfUsr.setSize(200,50);
		
		lblUsr.setLocation(10,100);
		lblUsr.setSize(100,50);
		lblUsr.setOpaque(true);
		lblUsr.setText("Enter Username:");
		
		lblPasswd.setLocation(10,200);
		lblPasswd.setSize(100,50);
		lblPasswd.setOpaque(true);
		lblPasswd.setText("Enter Password:");

		lblWelcomeMessage.setLocation(10,10);
		lblWelcomeMessage.setSize(600,50);
		lblWelcomeMessage.setOpaque(true);
		lblWelcomeMessage.setFont(new Font("Courier",Font.PLAIN,30));
		lblWelcomeMessage.setText("Welcome to your Ranking System");

		lblOr.setLocation(400,200);
		lblOr.setSize(100,50);
		lblOr.setOpaque(true);
		lblOr.setFont(new Font("Courier",Font.PLAIN,30));
		lblOr.setText("Or");
		
		loginTab.add(lblPasswd);
		loginTab.add(lblUsr);
		loginTab.add(pfPasswd);
		loginTab.add(tfUsr);
		loginTab.add(btLogin);
		loginTab.add(lblWelcomeMessage);
		loginTab.add(lblOr);
		loginTab.add(btCreateAnAccount);
		
		System.out.println("Login Page has been setup.");
	}
	
	public void actionPerformed(ActionEvent e)
 	{
		if(e.getSource()==btLogin)
		{
			
			System.out.println("login");

			String username = tfUsr.getText();
			String password = pfPasswd.getText();

			boolean checkSuccessful = Login.checkUserDetails(username,password);

			if(checkSuccessful)
			{
				FightsGUI currentUser = new FightsGUI();
				currentUser.startGUI();
							
			}

			else if(checkSuccessful==false)
			{
				System.out.println("Incorrect Details");
			}


		}

		else if(e.getSource()==btCreateAnAccount)
		{
			myTabs.remove(0);
			this.setSize(500,800);
			myTabs.addTab("Create an Account",createAnAccountTab);

		}

		else if(e.getSource()==btSubmitUserDetails)
		{
			String username = tfEnterUsr.getText();
			String password = pfEnterPasswd.getText();
			GenerateAccount newUser = new GenerateAccount(username,password);
			newUser.assignID();
			newUser.saveNewUserDetails(newUser);

			myTabs.remove(0);
			myTabs.addTab("Login",loginTab);
			this.setLayout(new GridLayout(1,1));
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setSize(800,500);
			this.setVisible(true);
		
			this.add(myTabs);
		}
		
	}
}