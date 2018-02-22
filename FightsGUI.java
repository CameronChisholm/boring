import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;


public class FightsGUI extends JFrame implements ActionListener 
{
	private JTabbedPane myTabs = new JTabbedPane();
	private JPanel fightsTab = new JPanel(null);

	private JLabel lblAddFight = new JLabel();
	private JLabel lblDeleteFight = new JLabel();

	private JLabel lblPromptScoreLimit = new JLabel();
	private JLabel lblScoreLimit = new JLabel();	
	private JLabel lblScoreLimitCustom = new JLabel();
	private JLabel lblPromptWinner = new JLabel();
	private JLabel lblPromptLoser = new JLabel();	
	private JLabel lblPromptLoserScore = new JLabel();

	private JComboBox cbScoreLimit;

	private JComboBox cbWinner;
	private JComboBox cbLoser;
	private JComboBox cbLoserScore;

	private JTextField tfScoreLimitCustom = new JTextField(6);

	private DefaultTableModel fightsTableModel;
	private JTable fightsTable;
	private JScrollPane fightsTableScroll;

	private JButton btSubmit = new JButton();
	private JButton btEdit = new JButton();
	private JButton btDelete = new JButton();

	private String[][] fightRecords;


	/*

	HOME PAGE VARIABLES

	*/

	private JPanel homePageTab = new JPanel(null);

	private JLabel lblPageTitle = new JLabel();

	/*
	
	RANKING TABLE

	*/

	private JPanel rankingTableTab = new JPanel(null);

	private DefaultTableModel rankingTableModel;
	private JTable rankingTable;
	private JScrollPane rankingTableScroll;

	private JLabel lblRankingTableTabDescription;

	/*

	EDIT FIGHT POP-UP BOX VARIABLES

	*/

	private JTextField tfScoreLimit = new JTextField();
	private JTextField tfWinnerName = new JTextField();
	private JTextField tfLoserName = new JTextField();
	private JTextField tfLoserScore = new JTextField();

	private JLabel lblScoreLimitEdit  = new JLabel();
	private JLabel lblSelectWinnerEdit = new JLabel();
	private JLabel lblSelectLoserEdit = new JLabel();
	private JLabel lblSelectLoserScoreEdit = new JLabel();

	/*
	JComboBox t4_NewFencerGender = new JComboBox(genders);
	JComboBox t4_NewDominantHand = new JComboBox(dominantHand);
	JComboBox t4_NewBirthDay = new JComboBox(days);
	JComboBox t4_NewBirthMonth = new JComboBox(months);
	JComboBox t4_NewBirthYear = new JComboBox(years);
	*/

	/*

	DELETE FIGHT POP UP BOX

	*/

	private JLabel promptUser = new JLabel();

	public boolean setUpEditPopUp(String[] tempRecordUserClickedOn)
	{
		tfScoreLimitCustom.setBounds(270,115,50,25);	

		lblScoreLimitEdit.setLocation(10,50);
		lblScoreLimitEdit.setSize(400,50);
		lblScoreLimitEdit.setOpaque(true);
		lblScoreLimitEdit.setFont(new Font("Courier",Font.PLAIN,20));
		lblScoreLimitEdit.setText("Enter Score Limit");

		lblSelectWinnerEdit.setLocation(10,50);
		lblSelectWinnerEdit.setSize(400,50);
		lblSelectWinnerEdit.setOpaque(true);
		lblSelectWinnerEdit.setFont(new Font("Courier",Font.PLAIN,20));
		lblSelectWinnerEdit.setText("Enter Winner's Name");

		lblSelectLoserEdit.setLocation(10,50);
		lblSelectLoserEdit.setSize(400,50);
		lblSelectLoserEdit.setOpaque(true);
		lblSelectLoserEdit.setFont(new Font("Courier",Font.PLAIN,20));
		lblSelectLoserEdit.setText("Enter Loser's Name");

		lblSelectLoserScoreEdit.setLocation(10,50);
		lblSelectLoserScoreEdit.setSize(400,50);
		lblSelectLoserScoreEdit.setOpaque(true);
		lblSelectLoserScoreEdit.setFont(new Font("Courier",Font.PLAIN,20));
		lblSelectLoserScoreEdit.setText("Enter Loser's Score");
	
		tfScoreLimit.setText(tempRecordUserClickedOn[0]);
		tfWinnerName.setText(tempRecordUserClickedOn[1]);
		tfLoserName.setText(tempRecordUserClickedOn[2]);
		tfLoserScore.setText(tempRecordUserClickedOn[3]);

		Object[] newUserInformation = {
		    "", lblScoreLimitEdit,
		    "", tfScoreLimit,
		    "", lblSelectWinnerEdit,
		    "", tfWinnerName,
		    "", lblSelectLoserEdit,
		    "", tfLoserName,
		    "", lblSelectLoserScoreEdit,
		    "", tfLoserScore

		};

		int option = JOptionPane.showConfirmDialog(null, newUserInformation, "Edit Fight", JOptionPane.OK_CANCEL_OPTION);

		boolean confirmed = false;	
	
		if(option==0)
		{
			confirmed = true;
		}

		return confirmed;
	}

	public boolean setUpDeletePopUp()
	{

		promptUser.setLocation(10,50);
		promptUser.setSize(400,50);
		promptUser.setOpaque(true);
		promptUser.setFont(new Font("Courier",Font.PLAIN,20));
		promptUser.setText("Are you sure you want to delete?");

		Object[] newUserInformation = {
		    "", promptUser
		};

		int option = JOptionPane.showConfirmDialog(null, newUserInformation, "Delete Fight", JOptionPane.OK_CANCEL_OPTION);

		boolean isUserSure = false;	
	
		if(option==0)
		{
			isUserSure = true;
		}

		return isUserSure;


		
	}	


	public void startGUI()
	{
		setUpFightsTab();
		setupHomePage();
		setupRankingTable();

		myTabs.addTab("Home Page",homePageTab);
		myTabs.addTab("Managing Fights",fightsTab);
		myTabs.addTab("Ranking Table",rankingTableTab);
		

		this.setLayout(new GridLayout(1,1));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		this.setSize(800,700);
		this.setVisible(true);
		
		this.add(myTabs);
	}

	public void setupRankingTable()
	{
		//lblRankingTableTabDescription.setLocation(10,0);
		//lblRankingTableTabDescription.setSize(300,50);
		//lblRankingTableTabDescription.setOpaque(true);
		//lblrankingTableTabDescription.setFont(new Font("Courier",Font.PLAIN,30));
		//lblRankingTableTabDescription.setText("Ranking Table");	

		String[] rankingTableHeadings = {"Rank","Name of Fencer","Ranking Points"};

		String[] rankingDataRecords = HomePage.getRankingData();

		int amountOfRecords = rankingDataRecords.length;

		String[][] rankingTableData = new String[0][0];

		if(amountOfRecords!=0)
		{
			rankingTableData = new String[amountOfRecords][4];

			for(int i=0;i<amountOfRecords;i++)
			{
				String[] recordArr = rankingDataRecords[i].split(",");
				rankingTableData[i][0] = recordArr[0];
				rankingTableData[i][1] = recordArr[1];
				rankingTableData[i][2] = recordArr[2];
				rankingTableData[i][3] = recordArr[3];
			}
		}


		rankingTableModel = new DefaultTableModel(rankingTableData,rankingTableHeadings);

		rankingTable = new JTable(rankingTableModel);

		rankingTableScroll = new JScrollPane(rankingTable);

		rankingTableScroll.setSize(700,500);
		rankingTableScroll.setLocation(10,10);	

		//rankingTableTab.add(lblRankingTableTabDescription);
		rankingTableTab.add(rankingTableScroll);
	}

	public void setupHomePage()
	{
		lblPageTitle.setLocation(10,0);
		lblPageTitle.setSize(300,50);
		lblPageTitle.setOpaque(true);
		lblPageTitle.setFont(new Font("Courier",Font.PLAIN,30));
		lblPageTitle.setText("Home Page");

		homePageTab.add(lblPageTitle);
	}

	public void setUpFightsTab()
	{
		lblAddFight.setLocation(10,0);
		lblAddFight.setSize(300,50);
		lblAddFight.setOpaque(true);
		lblAddFight.setFont(new Font("Courier",Font.PLAIN,30));
		lblAddFight.setText("Add Fight");

		lblDeleteFight.setLocation(400,0);
		lblDeleteFight.setSize(350,50);
		lblDeleteFight.setOpaque(true);
		lblDeleteFight.setFont(new Font("Courier",Font.PLAIN,30));
		lblDeleteFight.setText("Delete/Edit Fight");

		lblPromptScoreLimit.setLocation(10,50);
		lblPromptScoreLimit.setSize(400,50);
		lblPromptScoreLimit.setOpaque(true);
		lblPromptScoreLimit.setFont(new Font("Courier",Font.PLAIN,20));
		lblPromptScoreLimit.setText("What does the score go upto?");

		lblScoreLimit.setLocation(10,100);
		lblScoreLimit.setSize(200,50);
		lblScoreLimit.setOpaque(true);
		lblScoreLimit.setFont(new Font("Courier",Font.PLAIN,15));
		lblScoreLimit.setText("Suggested:");

		lblScoreLimitCustom.setLocation(200,100);
		lblScoreLimitCustom.setSize(200,50);
		lblScoreLimitCustom.setOpaque(true);
		lblScoreLimitCustom.setFont(new Font("Courier",Font.PLAIN,15));
		lblScoreLimitCustom.setText("Custom:");

		lblPromptWinner.setLocation(10,200);
		lblPromptWinner.setSize(200,50);
		lblPromptWinner.setOpaque(true);
		lblPromptWinner.setFont(new Font("Courier",Font.PLAIN,20));
		lblPromptWinner.setText("Select Winner");

		lblPromptLoser.setLocation(10,300);
		lblPromptLoser.setSize(200,50);
		lblPromptLoser.setOpaque(true);
		lblPromptLoser.setFont(new Font("Courier",Font.PLAIN,20));
		lblPromptLoser.setText("Select Loser");	

		lblPromptLoserScore.setLocation(10,400);
		lblPromptLoserScore.setSize(300,50);
		lblPromptLoserScore.setOpaque(true);
		lblPromptLoserScore.setFont(new Font("Courier",Font.PLAIN,20));
		lblPromptLoserScore.setText("Select Loser Score");

		btSubmit.setLocation(100,500);
		btSubmit.setSize(200,50);
		btSubmit.addActionListener(this);
		btSubmit.setFont(new Font("Courier",Font.PLAIN,20));
		btSubmit.setText("Submit");

		

		btEdit.setLocation(400,500);
		btEdit.setSize(200,50);
		btEdit.addActionListener(this);
		btEdit.setFont(new Font("Courier",Font.PLAIN,20));
		btEdit.setText("Edit");

		btDelete.setLocation(600,500);
		btDelete.setSize(200,50);
		btDelete.addActionListener(this);
		btDelete.setFont(new Font("Courier",Font.PLAIN,20));
		btDelete.setText("Delete");

		tfScoreLimitCustom.setBounds(270,115,50,25);	

		String[] allUserNames = HomePage.getAllUserNames();

		String[] DataCBScoreLimit = {
								"5",
								"10",
								"15"
							};

		String[] DataCBWinner = allUserNames;

		String[] DataCBLoser = allUserNames;

		String[] DataCBLoserScore = new String[15];
		
		for(int i=0;i<15;i++)
		{
			DataCBLoserScore[i] = Integer.toString(i+1);
		}



		cbScoreLimit = new JComboBox(DataCBScoreLimit);
		cbWinner = new JComboBox(DataCBWinner);
		cbLoser = new JComboBox(DataCBLoser);
		cbLoserScore = new JComboBox(DataCBLoserScore); 

		cbScoreLimit.setBounds(100,115,50,25);
		cbWinner.setBounds(10,250,100,25);
		cbLoser.setBounds(10,350,100,25);
		cbLoserScore.setBounds(10,450,50,25);


		String[] headings = {"Winner","Winner Points","Loser","Loser Points"};

		boolean areThereFights = false;

		areThereFights = HomePage.doFightsExist();

		if(areThereFights == true)
		{
			fightRecords = HomePage.getFightData();
		}
		else
		{
			fightRecords = new String[0][4];
		}

		fightsTableModel = new DefaultTableModel(fightRecords,headings);

		fightsTable = new JTable(fightsTableModel);

		fightsTableScroll = new JScrollPane(fightsTable);

		fightsTableScroll.setSize(300,400);
		fightsTableScroll.setLocation(400,50);

		fightsTab.add(btSubmit);
		fightsTab.add(fightsTableScroll);
		fightsTab.add(cbLoserScore);
		fightsTab.add(lblPromptLoserScore);
		fightsTab.add(cbLoser);
		fightsTab.add(lblPromptLoser);
		fightsTab.add(cbWinner);
		fightsTab.add(lblPromptWinner);
		fightsTab.add(tfScoreLimitCustom);
		fightsTab.add(lblScoreLimitCustom);
		fightsTab.add(cbScoreLimit);
		fightsTab.add(lblScoreLimit);
		fightsTab.add(lblPromptScoreLimit);
		fightsTab.add(lblDeleteFight);
		fightsTab.add(lblAddFight);
		fightsTab.add(btDelete);
		fightsTab.add(btEdit);
		
	}

	public void updateFightTable(
		String tempWinner,
		String tempLoser,
		String tempWinnerPts,
		String tempLoserPts
		)
	{
		int amountOfFights = fightRecords.length;
		String[][] updatedFightRecords = new String[amountOfFights+1][5];
		
		for(int i=0;i<amountOfFights+1;i++)
		{
			if(i<amountOfFights)
			{
				updatedFightRecords[i][0] = fightRecords[i][0];
				updatedFightRecords[i][1] = fightRecords[i][1];
				updatedFightRecords[i][2] = fightRecords[i][2];
				updatedFightRecords[i][3] = fightRecords[i][3];
			}

			else
			{
				updatedFightRecords[i][0] = tempWinner;
				updatedFightRecords[i][1] = tempWinnerPts;
				updatedFightRecords[i][2] = tempLoser;
				updatedFightRecords[i][3] = tempLoserPts;
			}
		}

		System.out.println("Original Array");
		output2DArray(fightRecords);
		fightRecords = updatedFightRecords;
		System.out.println("Edited Array");
		output2DArray(fightRecords);
	}

	public void output2DArray(String[][] tempArray)
	{
		String record ="";
		for(int i=0;i<tempArray.length;i++)
		{
			for(int j=0;j<5;j++)
			{
				record=tempArray[i][j]+record;
			}
			System.out.println(record);
		}
	}


	public void actionPerformed(ActionEvent e)
 	{

 		String[] recordClickedOn = new String[4];
 		String field;


		if(e.getSource()==btSubmit)
		{
			// Creating a new instance of a Fight.

			GenerateFight newFight;
			
			// Gathering all the relevant information to be able to store the fight.

			String winnerName = cbWinner.getSelectedItem().toString();
			String loserName = cbLoser.getSelectedItem().toString();

			String customScoreLimit = tfScoreLimitCustom.getText();

			String winnerScore;

			if(customScoreLimit.equals(""))
			{
				winnerScore = cbScoreLimit.getSelectedItem().toString();
			}

			else
			{
				winnerScore = customScoreLimit;
			}

			String loserScore = cbLoserScore.getSelectedItem().toString();

			updateFightTable(winnerName,loserName,winnerScore,loserScore);

			// All the relevant information that we need to create a fight has been gathered.
			newFight = new GenerateFight();

			newFight.storeFight(winnerName,loserName,winnerScore,loserScore);
		}

		
		
		else if(e.getSource()==btEdit)
		{
			
			try
			{
				for(int i=0;i<4;i++)
				{
					field = fightsTable.getValueAt(fightsTable.getSelectedRow(), i).toString();
					recordClickedOn[i] = field;
				}

				setUpEditPopUp(recordClickedOn);
			}
			catch(Exception ex)
			{
				System.out.println("Error, Code=1.1");
			}	


		}

		else if(e.getSource()==btDelete)
		{
			try
			{

				for(int i=0;i<4;i++)
				{
					field = fightsTable.getValueAt(fightsTable.getSelectedRow(), i).toString();
					recordClickedOn[i] = field;
				}

				boolean confirmDelete = setUpDeletePopUp();

			}
			catch(Exception ex)
			{
				System.out.println("Error, Code=1.2");
			}


				
		}
		
	}
	
}