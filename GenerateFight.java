public class GenerateFight
{
	private String fightID;
	private String filename = "fightData.txt";
	
	

	public GenerateFight()
	{
		generateFightID();
	}

	public void generateFightID()
	{
		// Local Variables
		String fightRecords;
		String[] fightRecordsArr;
		int highest;
		int tempFightID;
		int i;
		boolean isFileEmpty;

		// Reading from the file containing the fight details (records)
		fightRecords = ReadWriteToTxt.read(filename);	
		fightRecordsArr = fightRecords.split("-1");
		
		// Highest ID starts at 0, until other IDs found
		highest = 0;
		
		// Calling a checkingFileContent - finding out if there are any records in the file

		// File is empty is this statement is true (vice versa) 
		isFileEmpty = !(checkingFileContent(fightRecords));

		if(isFileEmpty)
		{
			fightID = "1";

			System.out.println("This if statement ran.");
		}

		else
		{
			try
			{
				for(i=0;i<fightRecordsArr.length;i++)
				{
					String[] RecordArr;

					RecordArr = fightRecordsArr[i].split(",");
					tempFightID = Integer.parseInt(RecordArr[0]);

					if(tempFightID>highest)
					{
						highest = tempFightID;
					}
				}

				fightID = Integer.toString(highest+1);	
			}

			catch(Exception ex)
			{
				ex.printStackTrace();

			}
		}	
	}

	public boolean checkingFileContent(String tempFight)
	{
		// Local Variables
		boolean doRecordsExist;

		// Checks if the file is empty or not
		if(tempFight.equals(""))
		{
			doRecordsExist = false;
		}

		else
		{
			doRecordsExist = true;
		}

		// This methods only checks if the file is COMPLETELY empty or not

		// Doesn't account for if the file is corrupt

		return doRecordsExist;
	}

	public void storeFight(
		String tempWinner,
		String tempLoser,
		String tempWinnerPts,
		String tempLoserPts
		)
	{
	
		String winnerIDNo = HomePage.searchName(tempWinner);
		String loserIDNo = HomePage.searchName(tempLoser);

		String winnerName = tempWinner;
		String loserName = tempLoser;

		String winnerPts = tempWinnerPts;
		String loserPts = tempLoserPts;

		String fightRecord = fightID+","+
							winnerIDNo+","+
							winnerName+","+
							winnerPts+","+
							loserIDNo+","+
							loserName+","+
							loserPts+","+"-1";

		ReadWriteToTxt.write(filename,fightRecord);
	}
}