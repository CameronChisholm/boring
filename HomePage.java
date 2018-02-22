public class HomePage
{
	public static String[] getRankingData()
	{
		String filename = "rankingData.txt";

		String fileContents = ReadWriteToTxt.read(filename);

		String[] rankingDataRecords = fileContents.split("-1");

		return rankingDataRecords;

	}
/*
	public static String[] getFightData()
	{
		String filename = "fightData.txt";

		String fileContents = ReadWriteToTxt.read(filename);

		String[] fightDataRecords = fileContents.split("-1");

		return
	}
*/
	public static String[] getAllUserNames()
	{
		String filename = "userDetails.txt";

		String fileContents = ReadWriteToTxt.read(filename);

		String[] userDetailsRecord = fileContents.split("-1");

		String[] allUserNames = new String[userDetailsRecord.length];

		for(int i=0;i<userDetailsRecord.length;i++)
		{
			String[] singleRecord = userDetailsRecord[i].split(",");
			allUserNames[i] = singleRecord[1];
		}

		return allUserNames;
	}

	public static void storeFight(
		String tempWinner,
		String tempLoser,
		String tempWinnerPts,
		String tempLoserPts
		)
	{
		String filename = "fightData.txt";
		String winnerIDNo = searchName(tempWinner);
		String loserIDNo = searchName(tempLoser);

		String winnerName = tempWinner;
		String loserName = tempLoser;

		String winnerPts = tempWinnerPts;
		String loserPts = tempLoserPts;

		String fightRecord = winnerIDNo+","+
							winnerName+","+
							winnerPts+","+
							loserIDNo+","+
							loserName+","+
							loserPts+","+"-1";

		ReadWriteToTxt.write(filename,fightRecord);
	}

	public static String searchName(String searchTerm)
	{
		String filename = "userDetails.txt";

		String fileContents = ReadWriteToTxt.read(filename);

		String[] userDetailsRecord = fileContents.split("-1");

		String[] allUserNames = new String[userDetailsRecord.length];

		String userIDNo = "";

		int nameIndex = 1;

		for(int i=0;i<userDetailsRecord.length;i++)
		{
			String[] singleRecord = userDetailsRecord[i].split(",");
			
			if(singleRecord[nameIndex].equals(searchTerm))
			{
				userIDNo = singleRecord[0];
			}
		}

		return userIDNo;

	}

	public static String[][] searchRankingTableName(String searchTerm,String[][] tempCurrentRankingRecords)
	{

		int tempAmountOfRecords = tempCurrentRankingRecords.length;
		
		int foundAmount = 0;

		String[][] matchingRecords;

		String name;
		
		for(int i=0;i<tempAmountOfRecords;i++)
		{
			
			name = tempCurrentRankingRecords[i][1]; 

			if(name.equals(searchTerm))
			{
				foundAmount++;
			}
		}

		matchingRecords = new String[foundAmount][4];

		int count = 0;

		for(int j=0;j<tempAmountOfRecords;j++)
		{
			name =  tempCurrentRankingRecords[j][1];

			if(name.equals(searchTerm))
			{
				matchingRecords[count] = tempCurrentRankingRecords[j]; 
				count++;

			}
		}

		return matchingRecords;
	}

	public static boolean doFightsExist()
	{
		boolean fightDataExists = false;
		String filename = "fightData.txt";
	
		String fileContents = ReadWriteToTxt.read(filename);
		
		if(fileContents.equals(""))
		{
			fightDataExists = false;
			System.out.println("This if statement has run.");
		}

		else
		{
			fightDataExists = true;
		}

		return fightDataExists;
	
	}

	public static String[][] getFightData()
	{
		String filename = "fightData.txt";

		String fileContents = ReadWriteToTxt.read(filename);
		
		String[] fightData = fileContents.split("-1");

/*
		for(int a=0;a<fightData.length;a++)
		{
			System.out.println(fightData[a]);
		}
*/
		String [][] fightRecords = new String[fightData.length][5];

		for(int i=0;i<fightData.length;i++)
		{
			String[] singleRecord = fightData[i].split(",");
			// Winner Name
			fightRecords[i][0] = singleRecord[2];
			// Winner Points
			fightRecords[i][1] = singleRecord[3];
			// Loser Name
			fightRecords[i][2] = singleRecord[5];
			// Loser Points
			fightRecords[i][3] = singleRecord[6];

			// Fight ID
			System.out.println(singleRecord[0]);
			fightRecords[i][4] = singleRecord[0];
		}
		
		return fightRecords;
	}
}