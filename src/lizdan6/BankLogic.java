package lizdan6;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Saves all customers created in a list.
 * Changes the information about the customer and the customer's account through instance methods.
 * Gets information about the customer and the customer's accounts through instance methods.
 * Creates and removes account's from the customer through instance methods.
 * 
 * @author Liza Danielsson, lizdan6.
 */

@SuppressWarnings("serial")
public class BankLogic implements Serializable
{
		//ArrayList to save all customers/instances of class "Customer".
	public ArrayList<Customer> allCustomers = new ArrayList<Customer>();
	

	/**
	* Private method that finds index of one specific customer in "allCustomers" if the customer exists.
	* @param pNo - personal number unique for each customer.
	* @return customerIndex - index for where to find specific customer in "allCustomers".
	*/
	private int getCustomerIndex(String pNo)
	{	
			//Variables. String to save each pNo, and index to return if customer isn't found.
		String existingpNo = "";
		int customerIndex = -5;
		
			//Loop through "allCustomers".
		for(int i = 0; i < allCustomers.size(); i++)
		{
				//Save each pNo by calling instance method "getSocSecNr" for each instance in ArrayList.
			existingpNo = allCustomers.get(i).getSocSecNr();
			
				//If saved instance-pNo matches parameter pNo.
			if (existingpNo.equals(pNo))
			{
					//Customer is found at index i in "allCustomers".
				customerIndex = i;
			}
		}
		return customerIndex;
	}
	
	
	/**
	 * Gets the index for a specific customer in "allCustomers", then gets all account id:s of the customer's accounts.
	 * @param pNo - Personal number of the customer to get all account id:s from.
	 * @return ArrayList<Integer> - A list with customer's account id:s.
	 */
	public ArrayList<Integer> getAccountIds(String pNo)
	{
			//Get index of the customer in list of customers.
		int customerIndex = getCustomerIndex(pNo);
			//Variables to save information.
		ArrayList<String> accountInfo = new ArrayList<String>();
		ArrayList<Integer> accountIds = new ArrayList<Integer>();
		int accountId;
		
			//As long as index is not -5, there is a customer found.
		if (!(customerIndex == -5))
		{
				//Get all accounts from customer.
			accountInfo = allCustomers.get(customerIndex).getAccounts();
			
				//Loop trough all accounts.
			for(String account : accountInfo)
			{
					//Take only the four first characters to get the account-id.
				accountId = Integer.parseInt(account.substring(0, 4));
					//Add each account-id in "accountIds".
				accountIds.add(accountId);
				
			}	
			return accountIds;
		}
		else
		{
			return null;
		} 
	}
	
	
	
	/**
	* Gives information (personal number and names) about all customers that have been added in "allCustomers".
	* @return customerInfo - ArrayList with each element containing information about each customer.
	*/
	public ArrayList<String> getAllCustomers()
	{
		ArrayList<String> customerInfo = new ArrayList<String>();
		String infoToSave;
		
			//If there are no customers yet. 
		if(allCustomers == null)
		{
				//Return empty ArrayList.
			return customerInfo;
		}
		else
		{
				//Else, loop trough "allCustomers".
			for(int i = 0; i < allCustomers.size(); i++)
			{
					//Reset String for each loop/element.
				infoToSave = "";
					//Save personal number and names in String.
				infoToSave = allCustomers.get(i).getSocSecNr() + (" ");
				infoToSave += allCustomers.get(i).getNames();
					//Add String to ArrayList with all info.
				customerInfo.add(infoToSave);
			}
			return customerInfo;
		}
	}
	
	
	
	/**
	* Checks if there is a customer with the same personal number as the argument pNo.
	* If not, creates a new instance/customer with personal number and names.
	* @param name - First name of the customer that is about to be added.
	* @param surname - Last name of the customer that is about to be added.
	* @param pNo - Personal number of the customer that is about to be added.
	* @return boolean - True if there is no customer with the same pNo, false if there is.
	*/
	public boolean createCustomer(String name, String surname, String pNo)
	{
			//Get index as if pNo already exists in "allCustomers".
		int customerIndex = getCustomerIndex(pNo);
		
			//As long as index is NOT -5, there is a customer found, return false.
		if (!(customerIndex == -5))
		{
			return false;
		}
		else
		{
				//Else, no customer was found. Create new instance/customer. Add to "allCustomers".
			allCustomers.add(new Customer(name,surname,pNo));
			return true;
		}
	}	
	
	
	
	/**
	* Checks if customer exists in "allCustomers" based on personal number.
	* If customer exists the method gives information about that specific customer.
	* Information looks like: personal number, names, and the customer's all accounts.
	* @param pNo - The personal number of the customer to get information about.
	* @return customerInfo - ArrayList with all information ordered as elements.
	*/
	public ArrayList<String> getCustomer(String pNo)
	{
			//ArrayLists to save information about both customer and customer's accounts.
		ArrayList<String> customerInfo = new ArrayList<String>();
		ArrayList<String> accountInfo = new ArrayList<String>();
		
		String infoToSave = "";
			//Get index for specific customer in "allCustomer".
		int customerIndex = getCustomerIndex(pNo);
		
			//As long as index isn't -5, the customer was found.
		if (!(customerIndex == -5))
		{
				//Save personal number and names to a String.
			infoToSave = allCustomers.get(customerIndex).getSocSecNr() + (" ");
			infoToSave += allCustomers.get(customerIndex).getNames();
			customerInfo.add(infoToSave);
			
				//Add information about customer's accounts in new ArrayList.
			accountInfo = allCustomers.get(customerIndex).getAccounts();
			
				//Loop trough ArrayList "accountInfo".
			for(int j = 0; j < accountInfo.size(); j++)
			{
					//Add each element from "accountInfo" in "customerInfo" that will be returned.
				customerInfo.add(accountInfo.get(j));
			}	
			return customerInfo;
		}
		else
		{
			return null;
		}	
	}
	
	
	
	/**
	* Changes a customer's first- and last name if customer is found.
	* @param name - The first name to change the custmer's name to.
	* @param surname - The last name to change the customer's name to.
	* @param pNo - Personal number of the customer to change names of.
	* @return boolean - Returns true if customer exists and names are changed, else returns false.
	*/
	public boolean changeCustomerName(String name, String surname, String pNo)
	{
			//Get index of customer with pNo in "allCustomers".
		int customerIndex = getCustomerIndex(pNo);
		boolean changed = false;
		
			//As long as index isn't -5, customer was found.
		if(!(customerIndex == -5))
		{
				//Check that name and surname aren't both empty.
			if(!(name == null) || (!(surname == null)))
			{
				//Save boolean returned from instance method that changes names.
			changed = allCustomers.get(customerIndex).changeName(name,surname);
			}
		}
		return changed;
	}

	
	
	/**
	* Gets index of where to find customer, based on personal number, in "allCustomers".
	* If customer exists, the method creates a saving account for that customer.
	* @param pNo - The personal number of the customer to create a saving account for.
	* @return accountNr - The unique Id of the account created, or not created, for the customer.
	*/
	public int createSavingsAccount(String pNo)
	{
			//Save index of where to find customer in "allCustomers".
		int customerIndex = getCustomerIndex(pNo);
			//Value to be returned if an account isn't created.
		int accountNr = -1;
	
			//As long as index isn't -5, customer was found.
		if (!(customerIndex == -5))
		{
				//Save value returned from instance method that creates new Savings account.
			accountNr = allCustomers.get(customerIndex).createSavingsAccount();
		}
		return accountNr;
	}
	
	
	
	/**
	* Gets index of where to find customer, based on personal number, in "allCustomers".
	* If customer exists, the method creates a credit account for that customer.
	* @param pNo - The personal number of the customer to create a saving account for.
	* @return accountNr - The unique Id of the account created, or not created, for the customer.
	*/
	public int createCreditAccount(String pNo)
	{
			//Save index of where to find customer in "allCustomers".
		int customerIndex = getCustomerIndex(pNo);
		
			//Value to be returned if an account isn't created.
		int accountNr = -1;

			//As long as index isn't -5, customer was found.
		if (!(customerIndex == -5))
		{
				//Save value returned from instance method that creates new credit account.
			accountNr = allCustomers.get(customerIndex).createCreditAccount();
		}
		return accountNr;
	}
	

	
	/**
	* Checks if customer exist and if so, gets the index of where to find the customer in "allCustomers".
	* Gives information about one specific account that belongs to the customer.
	* @param pNo - Personal number of the customer to get account information from.
	* @param accountId - Unique Id of the account to get information about.
	* @return infoToReturn - String with information (accountId, balance(kr), type, rate(%)).
	*/
	public String getAccount(String pNo, int accountId)
	{
			//Set return String to null in case of no customer found.
		String infoToReturn = null;
		
			//Save index of customer in "allCustomers".
		int customerIndex = getCustomerIndex(pNo);
		ArrayList<String> accounts = new ArrayList<String>();
		
			//As long as index isn't -5, customer found.
		if (!(customerIndex == -5))
		{
				//Save information about customer's all account by calling another method.
			accounts = allCustomers.get(customerIndex).getAccounts();
			
				//Loop through all accounts.
			for (int i = 0; i < accounts.size(); i++)
			{
					//If one account matches accountId.
				if (accounts.get(i).contains(Integer.toString(accountId)))
				{
						//Save that info in String.
					infoToReturn = accounts.get(i);
				}
			}
		}
		return infoToReturn;
	}
	
	
	
	/**
	* Checks if customer exists, if so, method gets customer's index in "allCustomers".
	* Calls instance method to add an amount to the specific account that belongs to the customer.
	* @param pNo - Personal number of the customer whose account money is to be added to.
	* @param accountId - Unique Id of the account to add money to.
	* @param amount - The amount to add to the account's balance.
	* @return boolean - True if customer was found and money added, false if not.
	*/
	public boolean deposit(String pNo, int accountId, int amount)
	{
			//Save customer's index for "allCustomers".
		int customerIndex = getCustomerIndex(pNo);
		boolean sucess = false;
	
			//If index isn't -5 (customer found) and if amount is greater than zero.
		if (!(customerIndex == -5) && (amount > 0))
		{
				//Save boolean returned from instance method that makes deposit.
			sucess = allCustomers.get(customerIndex).setDeposit(accountId,amount);
		}
		return sucess;
	}
	
	
	
	/**
	* Checks if customer exists, gets index of customer in "allCustomers".
	* Calls for instance method that removes an amount from the specific account that belongs to the customer.
	* @param pNo - Personal number of the customer whose account money is to be removed from.
	* @param accountId - Unique Id of the account to remove money from.
	* @param amount - The amount to remove from the account's balance.
	* @return boolean - True if customer exists and money is withdrawn, else false.
	*/
	public boolean withdraw(String pNo, int accountId, int amount)
	{
			//Save index for customer.
		int customerIndex = getCustomerIndex(pNo);
		boolean sucess = false;
		
			//If index isn't -5, customer found.
		if (!(customerIndex == -5))
		{
				//If amount isn't lesser than 0.
			if (!(amount < 0))
			{
				//Saves boolean returned from instance method that makes a withdraw.
			sucess = allCustomers.get(customerIndex).setWithdraw(accountId,amount);
			}
		}
		return sucess;
	}
	
	
	
	/**
	 * Checks if customer exists, gets index of customer in "allCustomers".
	 * Calls for instance method that returns an ArrayList with Strings holding information about all transactions
	 * made on the specific account that belongs to the customer.
	 * @param pNo - Personal number of the customer whose account to get transactions information from.
	 * @param accountId - Unique Id of the account to get transactions information from.
	 * @return transactionsInfo - ArrayList with Strings holding transactions information (date/time, amount, balance after).
	 */
	public ArrayList<String> getTransactions(String pNo, int accountId)
	{
			//Get index of customer.
		int customerIndex = getCustomerIndex(pNo);
				
			//Create ArrayLists to save information in.
		ArrayList<String> transactionsInfo = new ArrayList<String>();
		
			//If index isn't -5, customer found.
		if (!(customerIndex == -5))
		{
				//Save ArrayList returned from instance method.
			transactionsInfo = allCustomers.get(customerIndex).getTransactions(accountId);
		}
		return transactionsInfo;
	}
	
	
	
	/**
	* Checks if customer exists, gets index of customer in "allCustomers".
	* Calls instance method to close one account and save information about it.
	* @param pNo - Personal number of the customer whose account will be closed.
	* @param accountId - Unique Id of the account to be closed.
	* @return removeInfo - String with information about closed account...
	* (accountNr,balance(kr),type,ratecost(kr).
	*/
	public String closeAccount(String pNo, int accountId)
	{
			//Get customer index.
		int customerIndex = getCustomerIndex(pNo);
			//Set String to be returned to null, if no customer is found.
		String removeInfo = null;
		
			//If index ins't -5, customer found.
		if (!(customerIndex == -5))
		{
				//Save String from instance method that removes and returns information about the account.
			removeInfo = allCustomers.get(customerIndex).removeAccount(accountId);
		}
		return removeInfo;	
	}
	
	
	
	/**
	* Checks if customer exists, saves index of customer in "allCustomers".
	* Gets information about the customer to be removed and then removes the customer from "allCustomer".
	* @param pNo - Personal number of the customer to be removed.
	* @return removedCustomerInfo - ArrayList with information(customer information,accounts).
	*/
	public ArrayList<String> deleteCustomer(String pNo)
	{
			//Get index of customer.
		int customerIndex = getCustomerIndex(pNo);
		
			//Create ArrayLists to save information in.
		ArrayList<String> removedCustomerInfo = new ArrayList<String>();  
		ArrayList<String> accounts = new ArrayList<String>();
		
			//To save substring of an account information to get account Id:s.
		int account = 0;
		
			//If index isn't -5, customer found.
		if (!(customerIndex == -5))
		{
				//add info about customer to ArrayList to be returned.
			removedCustomerInfo.add(pNo + " " + allCustomers.get(customerIndex).getNames());
			
				//Save all accounts.
			accounts = allCustomers.get(customerIndex).getAccounts();
				
				//Loop through all accounts.
			for(int j = 0; j < accounts.size(); j++) //Loop through all accounts in ArrayList to close each account.
			{
					//Save the first 4 digits(the account Id).
				account = Integer.valueOf(accounts.get(j).substring(0, 4));
				
					//Save returned information from instance method that removes each account.
				removedCustomerInfo.add(allCustomers.get(customerIndex).removeAccount(account)); 
			}
				//Remove customer from "allCustomers".
			allCustomers.remove(customerIndex); //remove customer after saving all information.
			return removedCustomerInfo;
		}
		else
		{
			return null;
		}
	}
	
	
	/**
	 * Saves all customers from the list of customers "allCustomers" to a file with name "customers.dat". 
	 * Each customer is saved as an object in the file. Shows error message if not succeeded.
	 */
	public void saveCustomersToFile() 
	{
	    try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("lizdan6_files/customers.dat"))) {
	    		//Write all customers saved in "allCustomers" to the file as objects.
	    	outputStream.writeObject(allCustomers);
	    } 
	    catch (Exception e) {
	    		//Show error message if saving to file failed.
	        System.err.println("Error saving customers to file: " + "customers.dat");
	        e.printStackTrace();
	    }
	}
	
	/**
	 * Loads all customers from a file "customers.dat" as objects into the list of customers "allCustomers".
	 * Each customer is loaded as an object "Customer" from the file. 
	 * Each customer/object has accounts and transactions related to him/her which are also restored from file.
	 * Shows error message if not succeeded.
	 * @return boolean - True if loaded and false if not.
	 * @throws ClassNotFoundException 
	 */
	@SuppressWarnings("unchecked")
	public boolean loadBank() throws ClassNotFoundException
	{
		try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("lizdan6_files/customers.dat"))) {
				//Convert the objects from the file to an ArrayList with Customers to add them to the list "allCustomers".
			allCustomers = (ArrayList<Customer>)inputStream.readObject();
			return true;
			
		} catch (IOException e) {
			return false; 
	    }
	}
	
	
	
	/**
	 * Writes the current date, information about the customer and the account as well as all the transactions from the 
	 * specific account connected to the specific customer in a new text file with filename "TransactionsXXXX.txt", 
	 * where XXXX is the account number.
	 * @param pNo - The personal number of the customer owning the account.
	 * @param accountNr - The account number from where to get all transactions and write them to the file.
	 */
	public void saveTransactions(String pNo, int accountNr) {
			//Filename of the file to save.
		final String FILE_NAME = "lizdan6_files/Transactions"+Integer.toString(accountNr)+".txt";
		
			//Try create a new printWriter and a new file with filename above.
		try (PrintWriter outputWriter = new PrintWriter(new BufferedWriter(new FileWriter(FILE_NAME)))) {
				//Get all transactions from person and account sent to method.
			ArrayList<String> Transactions = getTransactions(pNo, accountNr);
			
				//Write current date to the file.
			LocalDate currentDate = LocalDate.now();
			
				//Write person number and account ID for the transactions-account.
			outputWriter.write("Datum för utskrift: " + currentDate.toString() + "\n");
			outputWriter.write("Personnummer: " + pNo + ", Kontonummer: " + accountNr + "\n");
			outputWriter.write("\nTransaktioner:\n");
			
				//Loop trough the transactions and write each one to the file.
			for (String transaction : Transactions) {
				outputWriter.write(transaction);
				outputWriter.write("\n");
				
			}
	    } 
	    catch (IOException e) {
	    		//Show error message if writing to file failed.
	        System.err.println("Error saving transactions to file: " + "Transactions.txt");
	        e.printStackTrace();
	    }
	}
}