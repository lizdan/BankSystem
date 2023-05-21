package lizdan6;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Subclass of Account. Specifies rate, makes withdraw, checks that only one free withdraw is made each year.
 * Implements Serializable to make it possible to write object to file.
 * 
 * @author Liza Danielsson, lizdan6.
 */
@SuppressWarnings("serial")
public class SavingsAccount extends Account implements Serializable
{	
	private BigDecimal rate = new BigDecimal("1.2");
	private LocalDateTime freeWithdraw; 

		//Constructor. Sets balance, account type and rate in "Account".
	public SavingsAccount(BigDecimal balance, String accountType) 
	{
		super(balance, accountType);
		setRate(rate);
	}
	
	/**
	 * Checks/controls that the last free transaction was made at least a year ago.
	 * @return Boolean - True if withdraw should be free and false if not.
	 */
	private Boolean checkFreeTransaction()
	{
			//Save current time.
		LocalDateTime currentTime = LocalDateTime.now();
				
			//If no withdraw has been made yet, set the variable freeWithdraw to current time.
		if(freeWithdraw == null) {
			freeWithdraw = LocalDateTime.parse(currentTime.toString());
			return true;
		}
			
			//Add 1 year to date when free withdraw was made last.
		LocalDateTime oneYearAhead = freeWithdraw.plusYears(1);
		
			//Check if current time is equal, or after, to one year ahead.
		if (currentTime.equals(oneYearAhead) || currentTime.isAfter(oneYearAhead)) {
				//If so, save current time as new date to when free withdraw was made.
			freeWithdraw = currentTime;
			return true;
		}
		else {
			return false;
		}
	}
	

	@Override
	/**
	 * Removes an amount from the balance on the account.
	 * @param amount - The amount to be removed from the balance.
	 * @return boolean - True if withdraw is made, false if there is not enough money to be removed.
	 */
	boolean setWithdraw(int amount) 
	{
			//Declare/Initialize needed variables for calculation.
		int rest;
		BigDecimal newBalance;
		BigDecimal withdrawRate = new BigDecimal("0.02");
		BigDecimal transaction = new BigDecimal(Integer.toString(amount));
		
			//Calculate rate cost.
		BigDecimal withdrawCost = transaction.multiply(withdrawRate);
		
			//Check if free withdraw is allowed, if not, continue.
		if(!checkFreeTransaction())
		{
				//Add rate cost to transaction/amount.
			transaction = transaction.add(withdrawCost);
		}
		
			//Check if transaction is less or equal to balance. Save the "compare-value" to rest.
		rest = getBalance().compareTo(transaction);
	
			//If 1, balance is greater than transaction or if 0, balance is equal to transaction (withdraw is allowed).
		if(rest == 1 || rest == 0)
		{
				//Subtract transaction from balance.
			newBalance = getBalance().subtract(transaction);
				//Set balance to new amount. 
			setBalance(newBalance);
				//Call set method from "Account" to save transaction (negative amount).
			setTransaction(transaction.negate());
			return true;
		}
		else
		{
			return false;
		}
	}
}