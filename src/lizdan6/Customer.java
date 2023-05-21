package lizdan6;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Sets information about a customer when a customer is added. 
 * Creates a list of the customers accounts where an account will be saved when created for the customer.
 * Gives information about the customer, such as personal number and name. 
 * Sets new name for the customer if the name is changed.
 * Gives information and manipulates the customers accounts (make deposit, withdraw, remove account).
 * Implements Serializable to make it possible to write object to file.
 * 
 * @author Liza Danielsson, lizdan6.
 */

@SuppressWarnings("serial")
public class Customer implements Serializable
{
		//Declare instance variables.
	private String socSecNr;
	private String firstName;
	private String lastName;
		
		//New ArrayList to save each Customers account-instances.
	private ArrayList<Account> accounts = new ArrayList<Account>();
	
		//Constructor. Sets names and personalNumber when instance is created.
	public Customer(String firstName, String lastName, String socSecNr)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.socSecNr = socSecNr;
	}
	
	/**
	* Gives one customer's personal number.
	* @return socSecNr - One person's/instance's unique String as Social Security number.
	*/
	public String getSocSecNr()
	{
		return socSecNr;
	}
	

	
	/**
	* Gives first- and last-name of one customer.
	* @return fullName - String with both names.
	*/
	public String getNames()
	{
		return firstName + " " + lastName;
	}
	
	
	/**
	* Private method to get right index for specific account in ArrayList "Accounts", in this class only.
	* @param accountId - The unique Id of the account to find index for.
	* @return accountIndex - Value/index as where to find the account in "accounts".
	*/
	private int getAccountIndex(int accountId)
	{
		ArrayList<String> accountInfo = new ArrayList<String>();
		
			//Save all of one customer's accounts in new ArrayList.
		accountInfo = getAccounts();
		
			//Value that might be returned if account isn't found.
		int accountIndex = -5;
		
			//Loop trough all accounts.
		for (int i = 0; i < accountInfo.size(); i++)
		{
				//If accountId exists in one of the element in the ArrayList.
			if(accountInfo.get(i).contains(Integer.toString(accountId)))
			{
					//The accounts index in "Accounts" is i.
				accountIndex = i;
			}
		}
		return accountIndex;	
	}
	
	
	/**
	* Creates a new instance of class "Account" and type/subclass "SavingsAccount".
	* @return accountNr - The unique Id connected to the account created.
	*/
	public int createSavingsAccount()
	{
			//Set start balance to 0.
		BigDecimal balance = new BigDecimal("0.00");
		
			//Create instance of Account as SavingsAccount.
		Account newAccount = new SavingsAccount(balance, "Sparkonto");
		
			//Add instance to ArrayList with customer's all accounts.
		accounts.add(newAccount);
		return newAccount.getAccountNr();
	}
	
	
	/**
	 * Creates a new instance of class "Account" and type/subclass "CreditAccount".
	 * @return accountNr - The unique Id connected to the account created.
	 */
	public int createCreditAccount()
	{
			//Set start balance to 0.
		BigDecimal balance = new BigDecimal("0.00");
		
			//Create instance of Account as CreditAccount.
		Account newAccount = new CreditAccount(balance, "Kreditkonto");
		
			//Add instance to ArrayList with customer's all accounts.
		accounts.add(newAccount);
		return newAccount.getAccountNr();
	}
	
	
	/**
	* Gives information about one customer's all accounts/instances saved in "accounts".
	* @return accountInfo - ArrayList with information about each account owned by one customer.
	*/
	public ArrayList<String> getAccounts()
	{
		ArrayList<String> accountInfo = new ArrayList<String>();
		
			//Loop trough "accounts".
		for (int i = 0; i < accounts.size(); i++)
		{
				//Call "getInfo" in class "Account" for each instance in ArrayList.
			accountInfo.add(accounts.get(i).getInfo());
		}
		return accountInfo;
	}
	
	
	/**
	* Changes name/names of one customer. Either both or first- or last name.
	* @param newName - New first name to change to.
	* @param newSurName - New last name to change to.
	* @return boolean - True if any name is changed, false if not.
	*/
	public boolean changeName(String newName, String newSurName)
	{
			//If first name is empty and last name is not.
		if (newName.isBlank() && (!(newSurName.isBlank())))
		{
				//Change last name only.
			this.lastName = newSurName;
			return true;
		}
			//If last name is empty and first name is not.
		else if (newSurName.isBlank() && (!(newName.isBlank())))
		{
				//Change first name only.
			this.firstName = newName;
			return true;
		}
		else if(!(newName.isBlank()) && (!(newSurName.isBlank())))
		{
			this.firstName = newName;
			this.lastName = newSurName;
			return true;
		}
		else
		{
				//Else, none of the names are changed.
			return false;
		}
	}
	
	
	/**
	* Finds the right customer based on personal number.
	* Calls for method to add a specific amount to one customer's account based on accountId.
	* @param accountId - Unique number for the account to add money to.
	* @param amount - The amount of money to add to the specific account's balance.
	* @return boolean success - Returns true if account is found in "accounts", else returns false.
	*/
	public boolean setDeposit(int accountId, int amount)
	{
			//Saves index of account in "accounts".
		int accountIndex = getAccountIndex(accountId);
		boolean success = false;
		
			//As long as accountIndex isn't -5 there was an account found.
		if(!(accountIndex == -5))
		{
			//Call instance method to add to account's balance.
			success = accounts.get(accountIndex).setDeposit(amount);
		}
		return success;
	}
	
	
	/**
	* Finds the right account in "accounts" based on accountId.
	* Calls for instance method to remove wanted amount of money from balance on one specific account.
	* @param accountId - Unique number for the account to remove money from.
	* @param amount - The amount of money to remove from the specific account's balance.
	* @return boolean success - Returns true if account is found in "accounts", else returns false.
	*/
	public boolean setWithdraw(int accountId, int amount)
	{
			//Get index of the account in "accounts".
		int accountIndex = getAccountIndex(accountId);
		boolean success = false;
		
			//As long as index isn't -5 the account was found.
		if(!(accountIndex == -5))
		{
				//Call instance method to try to withdraw form account's balance.
			success = accounts.get(accountIndex).setWithdraw(amount);
		}
		return success;
	}
	
	
	/**
	 * Gets information (date/time, amount and balance after) about all transactions made on specific account.
	 * @param accountId - Unique number for the account to get transactions from.
	 * @return transactionInfo - ArrayList with Strings holding information about each transaction.
	 */
	public ArrayList<String> getTransactions(int accountId)
	{
		ArrayList<String> transactionsInfo = null;
		
			//Get index in "accounts" for account with accountId.
		int accountIndex = getAccountIndex(accountId);
		
			//As long as index isn't -5, account is found.
		if(!(accountIndex == -5))
		{
				//Save ArrayList return from "getTransaction" in new ArrayList.
			transactionsInfo = new ArrayList<String>(accounts.get(accountIndex).getTransactions());
		}
		return transactionsInfo;
	}
	

	/**
	* Gives final information about an account's accountNr, balance, type and cost based on rate if the account is removed.
	* @param accountId - Unique number for the account to remove.
	* @return removeInfo - String with final information about removed account.
	*/
	public String removeAccount(int accountId)
	{
			//Get index in "accounts" for account with accountId.
		int accountIndex = getAccountIndex(accountId);
		
			//String as null, returns if the account isn't found.
		String removeInfo = null;
		
			//As long as index isn't -5, account is found.
		if(!(accountIndex == -5))
		{
				//Call instance method for specific account to get final information.
			removeInfo = accounts.get(accountIndex).getRemoveInfo();
				//Remove account index from "accounts".
			accounts.remove(accountIndex);
		}
		return removeInfo;
	}
}