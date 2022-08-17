import java.util.Date;

public class Transaction {
	
	private double Amount;
	private Date timestamp;
	private String memo;
	private Account toAccount;
	
	public Transaction(double Amount, Account toAccount) {
		this.Amount= Amount;
		this.toAccount = toAccount;
		this.timestamp = new Date();
		this.memo = "";
	}
	
	public Transaction(double Amount, String memo, Account toAccount) {
		this(Amount, toAccount);
		this.memo = memo;
	}
	
	public double getAmount() {
		return this.Amount;
	}
	
	public String getSummaryLine() {
		if(this.Amount>=0) {
			return String.format("%s : $%.02f : %s", this.timestamp.toString(),this.Amount,this.memo);
		}
		return String.format("%s : $(%.02f) : %s", this.timestamp.toString(),this.Amount,this.memo);
	}
	

}
