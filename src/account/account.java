package account;

public class account 
{

    private String username;
    private String password;
    private String[][] accounts = {{"admin", "password"}};

    public account(String username, String password) 
    {
    	this.username = username;
        this.password = password;
    }

    public boolean checkPassword()
    {
        if((username.equalsIgnoreCase(accounts[0][0])) && (password.equalsIgnoreCase(accounts[0][1])))
            return true;
        else
            return false;
    }
}