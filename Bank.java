import java.util.ArrayList;
import java.util.Random;

public class Bank {
	private String name;
	private ArrayList<User> users;
	private ArrayList<Account> accounts;
	
	public Bank(String name) {
		this.name = name;
		this.users = new ArrayList<User>();
		this.accounts = new ArrayList<Account>();
	}
	
	public String getNewUserId() {
		String userid;
		Random rng = new Random();
		int len = 6;
		boolean nonunique = false;
		
		do {
			userid ="";
			for(int i=0;i<len;i++) {
				userid+=((Integer)rng.nextInt(10)).toString();
			}
			for(User u: users) {
				if(userid.compareTo(u.getUserId())==0) {
					nonunique= true;
					break;
				}
			}
		}while(nonunique);
		
		return userid;
		
	}
	
	public String getNewAccountId() {
		String uuid;
		Random rng = new Random();
		int len = 10;
		boolean nonunique = false;
		
		do {
			uuid ="";
			for(int i=0;i<len;i++) {
				uuid+=((Integer)rng.nextInt(10)).toString();
			}
			for(Account a: accounts) {
				if(uuid.compareTo(a.getAccountId())==0) {
					nonunique= true;
					break;
				}
			}
		}while(nonunique);
		
		return uuid;
	}
	
	public void addAccount(Account acct) {
		this.accounts.add(acct);
	}
	
	public User addUser(String FirstName, String LastName, String pin) {
		User newuser = new User(FirstName, LastName, pin, this);
		this.users.add(newuser);
		
		Account newaccount = new Account("Savings", newuser, this);
		newuser.addAccount(newaccount);
		this.addAccount(newaccount);
		
		return newuser;
	}
	
	public User userlogin(String userid, String pin) {
		for(User u : users) {
			if(u.getUserId().compareTo(userid)==0 && u.ValidatePin(pin)) {
				return u;
			}
		}
		return null;
	}
	
	public String getName() {
		return this.name;
	}
	
}
