


public class Login 
{
	public static void main(String[] args)
	{
		LoginGUI testing = new LoginGUI();
		
		testing.startGUI();
		
	}

	public static boolean checkUserDetails(String tempUsername,String tempPassword)
	{
		String filename = "userDetails.txt";
		String userDetails = ReadWriteToTxt.read(filename);

		String username;
		String password;

		boolean match = false;

		String[] userDetailsArr = userDetails.split("-1");

		for(int i=0;i<userDetailsArr.length&&(match==false);i++)
		{
			String[] RecordArr = userDetailsArr[i].split(",");
			username = RecordArr[1];
			password = RecordArr[2];

			if(username.equals(tempUsername))
			{
				if(password.equals(tempPassword))
				{
					match = true;
				}
			}

		}

		return match;
	}
}