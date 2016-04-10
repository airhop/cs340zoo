package shared.jsonobject;

public class Login 
{
	private String username, password;
	private int ID;
	public Login(String u, String p, int id)
	{
		username = u;
		password = p;
		ID = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	public String toString()
	{
		return username + " " + password + " " + ID;
	}

	public int compareTo(Login login)
	{
		int same = 3;
		if(username.equals(login.getUsername()))
			same--;
		if(password.equals((login.getPassword())))
			same--;
		if(getID() == login.getID())
			same--;
		return same;
	}
}
