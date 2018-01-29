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
		fightRecords = HomePage.getFightData();

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
		String[][] updatedFightRecords = new String[amountOfFights+1][4];
		
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

		for(int j=0;j<updatedFightRecords.length;j++)
		{
			System.out.println(updatedFightRecords[j][0]);
			System.out.println(updatedFightRecords[j][1]);
			System.out.println(updatedFightRecords[j][2]);
			System.out.println(updatedFightRecords[j][3]);
		}
		
		fightRecords = updatedFightRecords;
		fightsTableModel.fireTableDataChanged();
	}


	public void actionPerformed(ActionEvent e)
 	{
		if(e.getSource()==btSubmit)
		{
		
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

			HomePage.storeFight(winnerName,loserName,winnerScore,loserScore);
		}

		else if(e.getSource()==btEdit)
		{
			System.out.println(fightsTable.getValueAt(fightsTable.getSelectedRow(), 0).toString());
		}
		
	}
	
}