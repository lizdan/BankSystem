package lizdan6;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Abstract superclass for SavingsAccount and CreditAccount. Sets balance, account type, account number, rate, saves 
 * all transactions in an ArrayList and makes deposits. Gives information about the account and all transactions.
 * Implements Serializable to make it possible to write object to file.
 * 
 * @author Liza Danielsson, lizdan6.
 */
@SuppressWarnings("serial")
abstract class Account implements Serializable
{	
		//Initialize class variable and declare instance variables.
	private static int lastAccountNr = 1000;
	private String accountType;
	private BigDecimal balance;
	private int accountNr;
	private BigDecimal rate;
	private ArrayList<String> transactions = new ArrayList<String>();
	
	
		//Constructor, set instance variables.
	public Account(BigDecimal balance, String accountType)
	{
		this.balance = balance;
			//Adds 1 to lastAccountNr for every new instance.
		lastAccountNr++;   
		this.accountNr = lastAccountNr;
		this.accountType = accountType;
	}
	
	
	
	
		//Overrides readObject to keep track of the highest value of accountNr.
	private void readObject(ObjectInputStream in) throws ClassNotFoundException, IOException {
			//Reads the object from file.
		in.defaultReadObject(); 
		
			//Checks if accountNr (that is set when object is red from file) is higher than lastAccountNr.
		if(accountNr > lastAccountNr) {
				//If so, set lastAccountNr to the higher value.
			lastAccountNr = accountNr;
				//Then increase by one.
			lastAccountNr++;
		}
    }
	

		//Sets rate.
	public void setRate(BigDecimal rate)
	{
		this.rate = rate;
	}
		//Sets balance.
	public void setBalance(BigDecimal balance)
	{
		this.balance = balance;
	}
		//Gives balance.
	public BigDecimal getBalance()
	{
		return balance;
	}
		//Gives account type.
	public String getAccountType()
	{
		return accountType;
	}
		//Give account number.
	public int getAccountNr()
	{
		return accountNr;
	}
		//Gives all transaction in ArrayList.
	public ArrayList<String> getTransactions()
	{
		return transactions;
	}
	
	
	/**
	* Returns formatted information about the account.
	* @return String - Formatted text/info (ID, amount(kr), type, rate(%)) about the account.
	*/
	public String getInfo()
	{
			//Format balance in a new String as Sweden currency.
		String balanceStr = NumberFormat.getCurrencyInstance(new Locale("sv","SE")).format(getBalance());
				
			//Format rate to percentage, save in new String.
		NumberFormat percentFormat = NumberFormat.getPercentInstance(new Locale("sv","SE"));
		percentFormat.setMaximumFractionDigits(1); 
		String percentStr = percentFormat.format(rate.divide(new BigDecimal("100")));
				
			//Return ID, balance as formatted String, type and percent as formatted String.
		return (getAccountNr() + " " + balanceStr + " " + getAccountType() + " " + percentStr);
	}
	
	
	/**
	* Adds an amount to the balance on the account.
	* @param amount - The amount to be added to the accounts balance.
	* @return boolean - Always true if method is called.
	*/
	public boolean setDeposit(int amount)
	{
			//New BigDecimal with amount as value.
		BigDecimal transaction = new BigDecimal(Integer.toString(amount));
		
			//Add new BigDecimal to balance.
		this.balance = this.balance.add(transaction);
		setTransaction(transaction);
		return true;
	}
	
	
	/**
	* Removes an amount from the balance on the account. Is override in subclasses.
	* @param amount - The amount to be removed from the balance.
	* @return boolean - True if withdraw is made, false if there is not enough money to be removed.
	*/
	abstract boolean setWithdraw(int amount);
	
	
	/**
	* Saves transaction (date/time, amount, balance after) in ArrayList "transactions".
	* @param amount - The amount added or removed from the account.
	*/
	public void setTransaction(BigDecimal amount)
	{
		String transactionInformation;
		
			//Specific format to save date as.
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
			//Create new instance of Date, save as String with format above.
		Date currentTime = new Date();
		String strDate = sdf.format(currentTime);
		
			//Save amount and balance in formatted Strings.
		String strAmount = NumberFormat.getCurrencyInstance(new Locale("sv","SE")).format(amount);
		String balanceStr = NumberFormat.getCurrencyInstance(new Locale("sv","SE")).format(balance);
		
		transactionInformation = strDate + " " + strAmount + " Saldo: " + balanceStr;
		transactions.add(transactionInformation);
	}
	
	
	/**
	* Returns information about an account, formatted in different way than method "getInfo".
	* @return String - Formatted text/info (ID, amount(kr), type, rate cost(kr) about the account.
	*/
	public String getRemoveInfo()
	{
		BigDecimal rateCost;
			//Multiply balance with rate/100(to get percentage to calculate with).
		rateCost = this.balance.multiply(rate.divide(new BigDecimal("100")));
		
			//Format both balance and the cost of the rate to SE currency in Strings.
		String balanceStr = NumberFormat.getCurrencyInstance(new Locale("sv","SE")).format(balance);
		String rateStr = NumberFormat.getCurrencyInstance(new Locale("sv","SE")).format(rateCost);
		
		return (accountNr + " " + balanceStr + " " + accountType + " " + rateStr);
	}
}