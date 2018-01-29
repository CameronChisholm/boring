public class GenerateAccount
{

	private String username;
	private String password;
	private String userID;
	private String rankingPoints = "0";

	private String filename = "userDetails.txt";

	public GenerateAccount(String tempUsername, String tempPassword)
	{
		username = tempUsername;
		password = tempPassword;
	}
	
	public void assignID()
	{
		String userDetails = ReadWriteToTxt.read(filename);	

		String[] userDetailsArr = userDetails.split("-1");
		
		int highest = 0;
		int tempUserID; 

		for(int i=0;i<userDetailsArr.length;i++)
		{
			String[] RecordArr = userDetailsArr[i].split(",");
			tempUserID = Integer.parseInt(RecordArr[0]);

			if(tempUserID>highest)
			{
				highest = tempUserID;
			}
		}
		userID = Integer.toString(highest+1);	
	}

	public void saveNewUserDetails(GenerateAccount newUser)
	{

		String username = newUser.username;
		String password = newUser.password;
		String userID = newUser.userID; 
		String rankingPoints = newUser.rankingPoints;

		String userDetails = userID+","+username+","+password+","+rankingPoints+",-1";
		

		ReadWriteToTxt.write(filename,userDetails);
	}
}