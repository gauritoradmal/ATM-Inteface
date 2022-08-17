import java.util.ArrayList;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {
	
	private String FirstName;
	private String LastName;
	private String userid;
	private byte pinhash[];
	private ArrayList<Account> Accounts;
	
	public User(String FirstName, String LastName, String pin, Bank thebank) {
		this.FirstName = FirstName;
		this.LastName= LastName;
		
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			this.pinhash = md.digest(pin.getBytes());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			System.err.print("error, caugth NoSuchAlogorithException");
			e.printStackTrace();
			System.exit(1);
		}
		
		this.userid=thebank.getNewUserId();
		
		this.Accounts= new ArrayList<Account>();
		
		//Log message
		System.out.printf("New user %s , %s with user id %s created.", LastName, FirstName, this.userid);
	}
	
	public void addAccount(Account acct) {
		this.Accounts.add(acct);
	}
	
	public String getUserId() {
		return this.userid;
	}
	
	public boolean ValidatePin(String apin) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			return MessageDigest.isEqual(md.digest(apin.getBytes()), this.pinhash);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			System.err.print("error, caugth NoSuchAlogorithException");
			e.printStackTrace();
			System.exit(1);
		}
		
		return false;
	}
	
	public String getFirstName() {
		return this.FirstName;
	}
	
	public void printAccountSummary() {
		System.out.printf("\n%s's account summary\n", this.FirstName);
		for(int i=0;i<this.Accounts.size();i++) {
			System.out.printf("%d) %s\n", i+1,this.Accounts.get(i).getSummaryLine());
		}
	}
	
	public int numAccounts() {
		return this.Accounts.size();
	}
	
	public void printAcctTransHistory(int acct) {
		this.Accounts.get(acct).printTransHistory();
	}
	
	public double getAcctBalance(int acct) {
		return this.Accounts.get(acct).getBalance();
	}
	
	public String getAcctId(int acct) {
		return this.Accounts.get(acct).getAccountId();
	}
	
	public void addAcctTransaction(int acct, double amount, String id) {
		this.Accounts.get(acct).addTransaction(amount,id);
	}
}
