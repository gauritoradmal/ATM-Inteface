import java.util.Scanner;

public class ATM {
	
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		Bank thebank = new Bank("Bank of India");
		User auser = thebank.addUser("Gauri", "Toradmal","2101");
		
		Account newAccount = new Account("Checking", auser, thebank);
		auser.addAccount(newAccount);
		thebank.addAccount(newAccount);
		
		User curUser;
		
		while(true) {
			curUser = ATM.mainMenu(thebank,sc);
			ATM.printUserMenu(curUser, sc);
		}
	} 
	
	public static User mainMenu(Bank thebank, Scanner sc) {
		String userid;
		String pin;
		User Authuser;
		
		do {
			
			System.out.printf("\nWelcome to %s\n", thebank.getName());
			System.out.print("Enter User ID:");
			userid = sc.nextLine(); 
			System.out.print("Enter PIN:");
			pin = sc.nextLine();
			
			Authuser = thebank.userlogin(userid,pin);
			
			if(Authuser==null) {
				System.out.println("Incorrect user ID/ PIN combination. "+ "Please try again!");
			}
			
		}while(Authuser==null);
		
		return Authuser;
	}
	
	public static void printUserMenu(User curUser, Scanner sc) {
		curUser.printAccountSummary();
		
		int ch;
		
		do {
			System.out.printf("\nWelcome %s, what would you like to do?\n", curUser.getFirstName());
			System.out.println("1.Show transaction history");
			System.out.println("2.Withdrawal");
			System.out.println("3.Deposit");
			System.out.println("4.Transfer");
			System.out.println("5.Quit");
			
			System.out.println("Enter Choice:");
			ch = sc.nextInt();
			
			if(ch<1 || ch>5) {
				System.out.println("Invalid choice!");
			}
		}while(ch<1 && ch>5);
		
		switch(ch){
			case 1 : 
				ATM.showTransactionHistory(curUser, sc);
				break;
			case 2: 
				ATM.withdrawFunds(curUser, sc);
				break;
			case 3:
				ATM.depositFunds(curUser, sc);
				break;
			case 4:
				ATM.transferFunds(curUser, sc);
				break;
			case 5:
				System.out.println("Thank you for visiting!");
				break;
				
		}
		if(ch!=5) {
			ATM.printUserMenu(curUser, sc);
		}
		
	}
	
	public static void showTransactionHistory(User curUser, Scanner sc) {
		int theAcct;
		do {
			System.out.printf("Enter the number (1-%d) of the account whose transaction you want to see:", curUser.numAccounts());
			theAcct = sc.nextInt();
			
			if(theAcct<0 || theAcct>= curUser.numAccounts() ) {
				System.out.println("Invalid account! Please try again.");
			}
			
		}while(theAcct<0 || theAcct>= curUser.numAccounts());
		
		curUser.printAcctTransHistory(theAcct);
		
	}
	
	public static void transferFunds(User curUser, Scanner sc) {
		int fromAcct;
		int toAcct;
		double amount;
		double acctbal;
		
		do {
			System.out.printf("Enter the number (1-%d) of the account to transfer from:", curUser.numAccounts());
			fromAcct = sc.nextInt()-1;
			if(fromAcct<0 || fromAcct>= curUser.numAccounts()) {
				System.out.println("Invalid account! Please try again.");
			}
		}while(fromAcct<0 || fromAcct>= curUser.numAccounts());
		acctbal = curUser.getAcctBalance(fromAcct);
		
		do {
			System.out.printf("Enter the number (1-%d) of the account to transfer to:", curUser.numAccounts());
			toAcct = sc.nextInt()-1;
			if(toAcct<0 || toAcct>= curUser.numAccounts()) {
				System.out.println("Invalid account! Please try again.");
			}
		}while(toAcct<0 || toAcct>= curUser.numAccounts());
		
		do {
			System.out.println("Enter the amount to transfer:");
			amount=sc.nextDouble();
			if(amount<0) {
				System.out.println("Amount should be greater than zero");
			}
			else if(amount>acctbal) {
				System.out.println("Insufficient Balance!");
			}
		}while(amount<0 || amount>acctbal);
		
		curUser.addAcctTransaction(fromAcct, -1*amount, String.format("Transfer to account %s", curUser.getAcctId(toAcct)));
		curUser.addAcctTransaction(toAcct, amount, String.format("Transfer to account %s", curUser.getAcctId(fromAcct)));
		
	}
	
	public static void withdrawFunds(User curUser, Scanner sc) {
		int fromAcct;
		String memo;
		double amount;
		double acctbal;
		
		do {
			System.out.printf("Enter the number (1-%d) of the account to withdraw from:", curUser.numAccounts());
			fromAcct = sc.nextInt()-1;
			if(fromAcct<0 || fromAcct>= curUser.numAccounts()) {
				System.out.println("Invalid account! Please try again.");
			}
		}while(fromAcct<0 || fromAcct>= curUser.numAccounts());
		acctbal = curUser.getAcctBalance(fromAcct);
		
		do {
			System.out.println("Enter the amount to transfer:");
			amount=sc.nextDouble();
			if(amount<0) {
				System.out.println("Amount should be greater than zero");
			}
			else if(amount>acctbal) {
				System.out.println("Insufficient Balance!");
			}
		}while(amount<0 || amount>acctbal);
		
		System.out.println("Enter a memo:");
		memo = sc.nextLine();
		
		curUser.addAcctTransaction(fromAcct, -1*amount, memo);
		
	}
	
	public static void depositFunds(User curUser, Scanner sc) {
		int toAcct;
		String memo;
		double amount;
		
		do {
			System.out.printf("Enter the number (1-%d) of the account to deposit to:", curUser.numAccounts());
			toAcct = sc.nextInt()-1;
			if(toAcct<0 || toAcct>= curUser.numAccounts()) {
				System.out.println("Invalid account! Please try again.");
			}
		}while(toAcct<0 || toAcct>= curUser.numAccounts());
		
		do {
			System.out.println("Enter the amount to transfer:");
			amount=sc.nextDouble();
			if(amount<0) {
				System.out.println("Amount should be greater than zero");
			}
		}while(amount<0);
		
		System.out.println("Enter a memo:");
		memo = sc.nextLine();
		
		curUser.addAcctTransaction(toAcct, amount, memo);
	}

}
