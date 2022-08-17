import java.util.ArrayList;

public class Account {
	private String Name;
	private String uuid;
	private User holder;
	private ArrayList<Transaction> Transactions;
	
	public Account(String Name, User holder, Bank thebank) {
		this.Name = Name;
		this.holder= holder;
		
		this.uuid= thebank.getNewAccountId();
		
		this.Transactions = new ArrayList<Transaction>();
		
		
		
	}
	
	public String getAccountId() {
		return this.uuid;
	}
	
	public String getSummaryLine() {
		double balance = this.getBalance();
		if(balance>=0) {
			return String.format("%s : $%.02f : %s", this.uuid,balance,this.Name);
		}
		
		return String.format("%s : $(%.02f) : %s", this.uuid,balance,this.Name);
		
	}
	
	public double getBalance() {
		double balance =0;
		for(Transaction t : this.Transactions) {
			balance+= t.getAmount();
		}
		return balance;
	}
	
	public void printTransHistory() {
		System.out.printf("Transaction history for account %s", this.uuid);
		for(int t=this.Transactions.size()-1;t<=0;t--) {
			System.out.println(this.Transactions.get(t).getSummaryLine());
		}
		
	}
	
	public void addTransaction(double amount, String id) {
		Transaction newTrans = new Transaction(amount, id, this);
		this.Transactions.add(newTrans);
	}
	
}
