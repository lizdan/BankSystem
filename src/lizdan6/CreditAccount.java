package lizdan6;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Subclass of Account. Specifies rates, makes withdraw and keeps track of credit limit.
 * Implements Serializable to make it possible to write object to file.
 * 
 * @author Liza Danielsson, lizdan6.
 */
@SuppressWarnings("serial")
public class CreditAccount extends Account implements Serializable
{
		//Declare instance variables.
	private final int CREDIT_LIMIT = -5000;
	private BigDecimal rate = new BigDecimal("0.5");


		//Constructor. Sets balance, account type and rate in "Account".
	public CreditAccount(BigDecimal balance, String accountType) 
	{
		super(balance, accountType);
		setRate(rate);
	}
	
	
	
	
	/**
	 * Sets instance variable "rate" which differs depending on balance on account.
	 */
	private void setLocalRate()
	{
			//If balance is a positive amount, rate is 0.5.
		if(getBalance().intValue() >= 0)
		{
			rate = new BigDecimal("0.5");
		}
			//Else rate is 7.0.
		else
		{
			rate = new BigDecimal("7.0");
		}
	}
	

	@Override
	/**
	 * Removes an amount from the balance on the account.
	 * @param amount - The amount to be removed from the balance.
	 * @return boolean - True if withdraw is made, false if there is not enough money to be removed.
	 */
	boolean setWithdraw(int amount) {
			//Declare/Initialize needed variables for calculation.
		int rest;
		BigDecimal newBalance;
		BigDecimal transaction = new BigDecimal(Integer.toString(amount));
		
			//Calculate amount left for customer to use on credit.
		int moneyToUse = (CREDIT_LIMIT - getBalance().intValue());
		
			//Transform to positive amount.
		moneyToUse = Math.abs(moneyToUse);
		
			//Check if transaction is less or equal to money left to use. Save the "compare-value" to rest.
		rest = new BigDecimal(moneyToUse).compareTo(transaction);
	
			//If 1, credit amount is greater than transaction, or if 0, credit amount is equal to transaction (withdraw is allowed).
		if(rest == 1 || rest == 0)
		{
				//Subtract transaction from balance.
			newBalance = getBalance().subtract(transaction);
				//Set balance to new amount.
			setBalance(newBalance);
				//Set new rate in this class.
			setLocalRate();
				//Set new rate in super class.
			setRate(rate);
				//Save transaction as negative amount.
			setTransaction(transaction.negate());
			return true;
		}
		else
		{
			return false;
		}
	}
}