package lizdan6;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


/**
 * The GUI class provides a graphical user interface for the BankLogic class.
 * It allows the user to interact with the BankLogic methods through various components such as
 * buttons, text fields, and combo boxes. The GUI is built using the Swing framework. 
 * The GUI class has several ActionListener inner classes that handle user input events and invoke the 
 * corresponding methods in the BankLogic class to perform various banking operations.
 * The GUI class also has several helper methods that update the state of the GUI components
 * and respond to certain user input events. These methods are mainly used by the ActionListener
 * classes to keep the GUI in sync with the underlying data in the BankLogic class.
 * 
 * @author Liza Danielsson, lizdan6.
 */


public class GUI {
		//Declare all class variables needed.
    private BankLogic bankLogic;
    private JFrame frame;
    private JPanel mainPanel;
    private JTextField textFieldName, textFieldSurname, textFieldPNo, textFieldAccountId, textFieldAmount;
    private JButton getAllCustomersButton, createCustomerButton, getCustomerButton, changeCustomerNameButton, createSavingsAccountButton, createCreditAccountButton,
            getAccountButton, depositButton, withdrawButton, getTransactionsButton, closeAccountButton, deleteCustomerButton,clearButton;
    private JComboBox<String> customerComboBox;
    private JComboBox<Integer> accountComboBox;

    /**
     * Constructor. Initializes bankLogic with the object sent from Main method, and creates the GUI by calling method.
     * @param bankLogic - The object sent from Main method.
     */
    public GUI(BankLogic bankLogic) {
        this.bankLogic = bankLogic;
        createGUI();
    }
    

    
    
    /**
     * This method creates the GUI for the bank system application.
     */
    private void createGUI() {
    		//Create the main window frame.
    	frame = new JFrame("Bank System");
    	
    		//Create the main panel and set its layout to a vertical box layout.
    	mainPanel = new JPanel();
    	mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

    		//Create the menu bar and add a file menu with three menu items.
    	JMenuBar menuBar = new JMenuBar();
    	JMenu fileMenu = new JMenu("File");
    	JMenuItem loadBankInfo = new JMenuItem("Load bank");
    	JMenuItem saveItem = new JMenuItem("Save bank");
    	JMenuItem saveTransactions = new JMenuItem("Save Transactions");
    	fileMenu.add(loadBankInfo);
    	fileMenu.add(saveItem);
    	fileMenu.add(saveTransactions);
    	menuBar.add(fileMenu);
    	frame.setJMenuBar(menuBar);

    		//Create the customer section.
    	JPanel customerSection = new JPanel(new BorderLayout());

    		// Create the customer input panel and add three labels and three text fields.
    	JPanel customerInputPanel = new JPanel(new GridLayout(3, 2));
    	
    	JLabel nameLabel = new JLabel("Name:");
    	JLabel surnameLabel = new JLabel("Surname:");
    	JLabel pNoLabel = new JLabel("Personal Number:");
    	textFieldName = new JTextField();
    	textFieldSurname = new JTextField();
    	textFieldPNo = new JTextField();
    	
    	customerInputPanel.add(nameLabel);
    	customerInputPanel.add(textFieldName);
    	customerInputPanel.add(surnameLabel);
    	customerInputPanel.add(textFieldSurname);
    	customerInputPanel.add(pNoLabel);
    	customerInputPanel.add(textFieldPNo);

    		//Create the customer button panel and add six buttons.
    	JPanel customerButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

    	getAllCustomersButton = new JButton("Show all customers");
    	createCustomerButton = new JButton("Create customer");
    	getCustomerButton = new JButton("Show customer");
    	changeCustomerNameButton = new JButton("Change customer name");
    	deleteCustomerButton = new JButton("Delete customer");
    	clearButton = new JButton("Clear");
    	
    	customerButtonPanel.add(createCustomerButton);
    	customerButtonPanel.add(changeCustomerNameButton);
    	customerButtonPanel.add(deleteCustomerButton);
    	customerButtonPanel.add(getCustomerButton);
    	customerButtonPanel.add(getAllCustomersButton);
    	customerButtonPanel.add(clearButton);

    		//Add the input and button panels and a combo box to the customer section panel.
    	customerSection.add(customerInputPanel, BorderLayout.CENTER);
        customerSection.add(customerButtonPanel, BorderLayout.SOUTH);
    	customerComboBox = new JComboBox<>();
        customerSection.add(customerComboBox, BorderLayout.NORTH);

        	//Add the customer section panel to the main panel.
    	mainPanel.add(customerSection);

    		//Create the account section.
    	JPanel accountSection = new JPanel(new BorderLayout());

    		//Create the account input panel and add two labels and two text fields.
    	JPanel accountInputPanel = new JPanel(new GridLayout(2, 2));
    	
    	JLabel accountIdLabel = new JLabel("Account ID:");
    	JLabel amountLabel = new JLabel("Amount:");
    	textFieldAccountId = new JTextField();
    	textFieldAmount = new JTextField();
    	
    	accountInputPanel.add(accountIdLabel);
    	accountInputPanel.add(textFieldAccountId);
    	accountInputPanel.add(amountLabel);
    	accountInputPanel.add(textFieldAmount);

    		//Create the account button panel and add seven buttons.
    	JPanel accountButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

    	createSavingsAccountButton = new JButton("Create Savingsaccount");
    	createCreditAccountButton = new JButton("Create Creditaccount");
    	getAccountButton = new JButton("Show account");
    	depositButton = new JButton("Deposit");
    	withdrawButton = new JButton("Withdraw");
    	getTransactionsButton = new JButton("Show transactions");
    	closeAccountButton = new JButton("Close account");
    	
    	accountButtonPanel.add(createSavingsAccountButton);
    	accountButtonPanel.add(createCreditAccountButton);
    	accountButtonPanel.add(depositButton);
    	accountButtonPanel.add(withdrawButton);
    	accountButtonPanel.add(getAccountButton);
    	accountButtonPanel.add(getTransactionsButton);
    	accountButtonPanel.add(closeAccountButton);
    	    
    		//Add account input- and button panels to account section.
    	accountSection.add(accountInputPanel, BorderLayout.CENTER);
    	accountSection.add(accountButtonPanel, BorderLayout.SOUTH);
    	    
    		//Add account combo box to account section.
    	accountComboBox = new JComboBox<>();
    	accountSection.add(accountComboBox, BorderLayout.NORTH);
    	    
    		//Add account section to main panel.
    	mainPanel.add(accountSection);
    	    
    		//Add action listeners to buttons and comboboxes.
    	getAllCustomersButton.addActionListener(new GetAllCustomersListener());
        createCustomerButton.addActionListener(new CreateCustomerListener());
        getCustomerButton.addActionListener(new GetCustomerListener());
        changeCustomerNameButton.addActionListener(new ChangeCustomerNameListener());
        createSavingsAccountButton.addActionListener(new CreateSavingsAccountListener());
        createCreditAccountButton.addActionListener(new CreateCreditAccountListener());
        getAccountButton.addActionListener(new GetAccountListener());
        depositButton.addActionListener(new DepositListener());
        withdrawButton.addActionListener(new WithdrawListener());
        getTransactionsButton.addActionListener(new GetTransactionsListener());
        closeAccountButton.addActionListener(new CloseAccountListener());
        deleteCustomerButton.addActionListener(new DeleteCustomerListener());
        clearButton.addActionListener(new ClearListener());
        customerComboBox.addActionListener(new CustomerComboBoxListener());
        accountComboBox.addActionListener(new AccountComboBoxListener());
        saveItem.addActionListener(new SaveCustomerListener());
        loadBankInfo.addActionListener(new loadBankListener());
        saveTransactions.addActionListener(new SaveTransactionsListener());
            
        	//Add mainPanel to the frames content pane and set properties of frame.
    	frame.setContentPane(mainPanel);
    	frame.setSize(1050,320);
    	frame.setLocationRelativeTo(null);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setVisible(true);    
    }
    
    
    
    /**
     * This method listens to changes in the customerComboBox, and updates the text fields to show the selected customer's
     * personal number, first name and surname. It also calls the UpdateAccountComboBox() method to update the available 
     * accounts for the selected customer. If no customer is selected, the text fields are set to null.
     */
    private class CustomerComboBoxListener implements ActionListener {
    		//Handle the event when an item is selected from the customerComboBox.
    	@Override
    	public void actionPerformed(ActionEvent e) {
    			//Check if an item is selected.
    		if (customerComboBox.getSelectedIndex() != -1) {
    				//Split the selected item to get the personal number, name and surname.
    	        String pNo = customerComboBox.getSelectedItem().toString().split(" ")[0];
    	        String name = customerComboBox.getSelectedItem().toString().split(" ")[1];
    	        String surName = customerComboBox.getSelectedItem().toString().split(" ")[2];
    	        
    	        	//Set the name, surname and personal number in their respective text fields.
    	        textFieldName.setText(name);
    	        textFieldSurname.setText(surName);
    	        textFieldPNo.setText(pNo);
    	        
    	        	//Update the accountComboBox with accounts of the selected customer.
    	        UpdateAccountComboBox(pNo);
    	    }
    		else {
    				//If no item selected, clear the name, surname and personal number text fields.
    			textFieldName.setText(null);
    	        textFieldSurname.setText(null);
    	        textFieldPNo.setText(null);
    		}
    	}
    }
    
    
    
    /**
     * This method listens to changes in the accountComboBox, and updates the text fields to show the selected account's 
     * account ID. It also sets the amount text field to null. If no account is selected, both text fields are set to null.
     */
    private class AccountComboBoxListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        		//Get the selected item from the accountComboBox.
            Object selectedItem = accountComboBox.getSelectedItem();
            
            	//If an item is selected, set the account ID in the textFieldAccountId and clear textFieldAmount.
            if (selectedItem != null) {
                int selectedAccountId = (int) selectedItem;
                textFieldAccountId.setText(Integer.toString(selectedAccountId));
                textFieldAmount.setText(null);
            }
            	//Else, clear both textFieldAccountId and textFieldAmount.
            else
            {
            	textFieldAccountId.setText(null);
                textFieldAmount.setText(null);
            }
        }
    }
    
    
    
    /**
     * Updates the account combo box with account IDs associated with the given personal number.
     * @param pNo - The personal number from whom to get all account IDs from.
     */
    private void UpdateAccountComboBox(String pNo) {
    		//Remove all items in the combo box.
        accountComboBox.removeAllItems();
        
        	//Get account IDs for the personal number and add them to the combo box.
        ArrayList<Integer> accountIds = bankLogic.getAccountIds(pNo);
        if (accountIds != null) {
            for (int accountId : accountIds) {
                accountComboBox.addItem(accountId);
            }
        }
    }
    
    
    
    /**
     * Updates the customer combo box with all customers in the bank system.
     */
    private void UpdateCustomerComboBox() {
    		//Remove all items in the combo box.
        customerComboBox.removeAllItems();
        
        	//Get all customers and add them to the combo box.
        ArrayList<String> customers = bankLogic.getAllCustomers();
        for (String customer : customers) {
            customerComboBox.addItem(customer);
        } 
        	//Set selected index to -1 so no item is selected.
        customerComboBox.setSelectedIndex(-1);
    }
    
    
    
    /**
     * Selects the last account ID in the list of account IDs.
     */
    private void setLastAccountSelected() {
    		//Get amount of accounts in list, minus one to get last index.
    	int lastIndex = accountComboBox.getItemCount() - 1;
    	
    		//Select retrieved index.
    	accountComboBox.setSelectedIndex(lastIndex);
    }
    
    
    
    /**
     * Checks that input that is sent to method is a valid integer.
     * @param input - User input from textFieldAmount.
     * @return int amount - The amount of input if valid and -1 if not.
     */
    private int checkAmountInput(String input) {
    		//Try parse input to an integer. Return amount if successful.
    	try {
    	    int amount = Integer.parseInt(input);
    	    return amount;
    	    //Catch if parse didn't work. Return -1.
    	} catch (NumberFormatException ex) {
    	    return -1;
    	}
    }
    
    
   
    /**
     * ActionListener for the 'Show All Customers' button. Gets all customers in the bank and displays them in a dialog box.
     */
    private class GetAllCustomersListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            	//Get all customers from bankLogic.
            ArrayList<String> allCustomers = bankLogic.getAllCustomers();
            
            	//Display all customers in a dialog box.
            JOptionPane.showMessageDialog(frame, String.join("\n", allCustomers));
        }
    }
   
    
    
    /**
     * ActionListener for the 'Create Customer' button. Creates a new customer in the bank with the given name, surname, 
     * and personal number. Displays a dialog box indicating whether the customer was created successfully or not.
     */
    private class CreateCustomerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        		//Get name, surname, and personal number from text fields.
            String name = textFieldName.getText();
            String surname = textFieldSurname.getText();
            String pNo = textFieldPNo.getText();
            
            	//Check that personal number is 10 digits only using a Regex-pattern.
            if(pNo.matches("\\d{10}")) {
            		//Try to create the customer in bankLogic.
            	if (bankLogic.createCustomer(name, surname, pNo)) {
            			//If successful, update the customer combo box and select the newly created customer.
            		UpdateCustomerComboBox();
            		int lastIndex = customerComboBox.getItemCount() - 1;
            		customerComboBox.setSelectedIndex(lastIndex);
                    JOptionPane.showMessageDialog(frame, "Customer created successfully!");
                } 
            	else {
                    JOptionPane.showMessageDialog(frame, "Failed to create customer.");
                }
            }
            	//Show error message if input of personal number is other than 10 digits.
            else {
            	JOptionPane.showMessageDialog(frame, "Personal number must be 10 digits only.");
            }
        }
    }
    
    
    
    /**
     * ActionListener for the 'Show Customer' button. Gets the customer with the given personal number,
     * and displays their information in a dialog box.
     */
    private class GetCustomerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        		//Get personal number from text field.
            String pNo = textFieldPNo.getText();
            
            	//Get customer information from bankLogic.
            ArrayList<String> customerInfo = bankLogic.getCustomer(pNo);

            	//If customer found, display customer information in a dialog box.
            if (customerInfo != null && !customerInfo.isEmpty()) {
                JOptionPane.showMessageDialog(frame, String.join("\n", customerInfo));
             
            } 
            	//Else, display error message in a dialog box.
            else {
                JOptionPane.showMessageDialog(frame, "Customer not found.");
            }
        }
    }
    
    
    
    /**
     * Listens for user click on the "Change Customer Name" button, gets the entered customer
     * information from text fields and calls the corresponding method in BankLogic to change the
     * customer's name. Displays success or failure message in a dialog box.
     */
    private class ChangeCustomerNameListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        		//Get customer information entered in text fields.
            String name = textFieldName.getText();
            String surname = textFieldSurname.getText();
            String pNo = textFieldPNo.getText();

            	//Call changeCustomerName method in BankLogic and display result in a dialog box.
            if (bankLogic.changeCustomerName(name, surname, pNo)) {
                JOptionPane.showMessageDialog(frame, "Customer name updated successfully!");
            } 
            else {
                JOptionPane.showMessageDialog(frame, "Failed to update customer name.");
            }
        }
    }

    
    
    /**
     * Listens for user click on the "Create Savings Account" button, gets the entered customer
     * personal number from text field and calls the corresponding method in BankLogic to create a new
     * savings account for the customer. Displays success or failure message in a dialog box.
     */
    private class CreateSavingsAccountListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        		//Get customer information entered in text fields.
            String pNo = textFieldPNo.getText();
            
            	//Call createSavingsAccount method in BankLogic and display result in a dialog box.
            int accountId = bankLogic.createSavingsAccount(pNo);
            if (accountId != -1) {
                JOptionPane.showMessageDialog(frame, "Savings account created with ID: " + accountId);
                UpdateAccountComboBox(pNo);
                
                	//Call method to select the created account.
                setLastAccountSelected(); 
            } 
            else {
                JOptionPane.showMessageDialog(frame, "Failed to create savings account.");
            }
        }
    }

    
    
    /**
     * Listens for user click on the "Create Credit Account" button, gets the entered customer
     * personal number from text field and calls the corresponding method in BankLogic to create a new
     * credit account for the customer. Displays success or failure message in a dialog box.
     */
    private class CreateCreditAccountListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        		//Get customer information entered in text fields.
            String pNo = textFieldPNo.getText();
            
            	//Call createSavingsAccount method in BankLogic and display result in a dialog box.
            int accountId = bankLogic.createCreditAccount(pNo);
            if (accountId != -1) {
                JOptionPane.showMessageDialog(frame, "Credit account created with ID: " + accountId);
                UpdateAccountComboBox(pNo);
                
                	//Call method to select the created account.
                setLastAccountSelected();
            } 
            else {
                JOptionPane.showMessageDialog(frame, "Failed to create credit account.");
            }
        }
    }

    
    
    /**
     * ActionListener for the "Show Account" button. Gets the details of the account with the specified ID and displays them in a dialog box.
     */
    private class GetAccountListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        		//As long as textField for account Id is not empty.
        	if(!textFieldAccountId.getText().isEmpty()){
        			//Get personal number and account ID from text fields.
                String pNo = textFieldPNo.getText();
                int accountId = Integer.parseInt(textFieldAccountId.getText());

                	//Call BankLogic method to get account information.
                String accountInfo = bankLogic.getAccount(pNo, accountId);

                	//Check if account info was found, show message dialog with info if found.
                if (accountInfo != null && !accountInfo.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, accountInfo);
                } 
                	//Else, show error dialog.
                else {
                    JOptionPane.showMessageDialog(frame, "Account not found.");
                }
        	}
        }
    }

    
    
    /**
     * ActionListener for the "Deposit" button. Deposits the specified amount into the account with the specified ID,
     * and displays a dialog box indicating whether the deposit was successful or not.
     */
    private class DepositListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        		//As long as textField for account Id is not empty.
        	if(!textFieldAccountId.getText().isEmpty()){
        			//Get personal number, account ID, and deposit amount from text fields.
                String pNo = textFieldPNo.getText();
                int accountId = Integer.parseInt(textFieldAccountId.getText());
                	//Check if amount is valid by calling method.
                int amount = checkAmountInput(textFieldAmount.getText());
                
                	//As long as amount is not -1, amount input is valid.
                if(amount != -1) {
                		//Call BankLogic method to deposit money.
                	if (bankLogic.deposit(pNo, accountId, amount)) {
                        JOptionPane.showMessageDialog(frame, "Deposit successful!");
                    } 
                	else {
                        JOptionPane.showMessageDialog(frame, "Failed to deposit.");
                    }
                }
                	//Else, input was not valid. Show error.
                else {
                	JOptionPane.showMessageDialog(frame, "Amount needs to be a positive number only.");
                }
        	}
        }
    }

    
    
    /**
     * ActionListener for the "Withdraw" button. Withdraws the specified amount from the account with the specified ID,
     * and displays a dialog box indicating whether the withdrawal was successful or not.
     */
    private class WithdrawListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        		//As long as textField for account Id is not empty.
        	if(!textFieldAccountId.getText().isEmpty()){
        			//Get personal number, account ID, and deposit amount from text fields.
                String pNo = textFieldPNo.getText();
                int accountId = Integer.parseInt(textFieldAccountId.getText());
                	//Check if amount is valid by calling method.
                int amount = checkAmountInput(textFieldAmount.getText());
                
                	//As long as amount is not -1, amount input is valid.
                if(amount != -1) {
                		//Call BankLogic method to deposit money.
                	if (bankLogic.withdraw(pNo, accountId, amount)) {
                		JOptionPane.showMessageDialog(frame, "Withdraw successful!");
                	} 
                	else {
                		JOptionPane.showMessageDialog(frame, "Failed to withdraw.");
                	}
                }
                	//Else, input was not valid. Show error.
                else {
                	JOptionPane.showMessageDialog(frame, "Amount needs to be a positive number only.");
                }
        	}
        }
    }

    
    
    /**
     * ActionListener that gets transactions for the selected account and displays them in a message.
     */
    private class GetTransactionsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        		 //As long as textField for account Id is not empty.
        	if(!textFieldAccountId.getText().isEmpty()){
        			//Get the personal number and account ID from the text fields.
                String pNo = textFieldPNo.getText();
                int accountId = Integer.parseInt(textFieldAccountId.getText());

                	//Get the list of transactions for the account by calling method in BankLogic.
                ArrayList<String> transactions = bankLogic.getTransactions(pNo, accountId);

                	//If there are transactions, display them in a message dialog.
                if (transactions != null && !transactions.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, String.join("\n", transactions));
                } 
                	//Else, display error message.
                else {
                    JOptionPane.showMessageDialog(frame, "No transactions found.");
                }
        	}
        }
    }

    
    
    /**
     * ActionListener that closes the selected account, displays the account ID in a message, 
     * and updates the account ComboBox.
     */
    private class CloseAccountListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        	if(!textFieldAccountId.getText().isEmpty()){
        			//Get the personal number and account ID from the text fields.
                String pNo = textFieldPNo.getText();
                int accountId = Integer.parseInt(textFieldAccountId.getText());

                	//Try to close the account by calling method in BankLogic.
                String result = bankLogic.closeAccount(pNo, accountId);

                	//If the account was closed, display a success message and update the account combo box.
                if (result != null && !result.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Account closed: " + result);
                    UpdateAccountComboBox(pNo);
                } 
                	//Else, display an error message.
                else {
                    JOptionPane.showMessageDialog(frame, "Failed to close account.");
                }
        	}
        }
    }

    
    
    /**
     * ActionListener that deletes a customer and all of their accounts.
     * If customer is successfully deleted, shows a message dialog with the closed accounts.
     */
    private class DeleteCustomerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        		//Get personal number of customer from text field.
            String pNo = textFieldPNo.getText();
          
            	//Delete customer and save list of closed accounts.
            ArrayList<String> closedAccounts = bankLogic.deleteCustomer(pNo);

            	//If customer and accounts were successfully deleted, show message dialog with list of closed accounts.
            if (closedAccounts != null && !closedAccounts.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Customer deleted. \nClosed accounts:\n" + String.join("\n", closedAccounts));
                UpdateCustomerComboBox();
                UpdateAccountComboBox(pNo);
            } 
            	//Else, show error message.
            else {
                JOptionPane.showMessageDialog(frame, "Failed to delete customer.");
            }
        }
    }
    
    
    
    /**
     * ActionListener that clears all input fields and resets the customer combo box to have no selection.
     */
    private class ClearListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        		//Set all text fields to null.
            textFieldName.setText(null);
            textFieldSurname.setText(null);
            textFieldPNo.setText(null);
            textFieldAccountId.setText(null);
            textFieldAmount.setText(null);
            
            	//Reset customer combo box to have no selection.
            customerComboBox.setSelectedIndex(-1);
            accountComboBox.setSelectedIndex(-1);
        }
    }
    
    
    
    
    /**
     * ActionListener for the menuItem Save Customer. Calls for instance method to save all customers as objects in a file.
     */
    private class SaveCustomerListener implements ActionListener {
    	public void actionPerformed(ActionEvent e) {
    			//Call instance method to save/write customers to file.
    		bankLogic.saveCustomersToFile();
    	}
    }
    
    
    
    
    /**
     * ActionsListerner for the menuItem Load Bank. Reads all customers/objects from a file into the bank by calling
     * an instance method.
     */
    private class loadBankListener implements ActionListener {
    	public void actionPerformed(ActionEvent e) {
    		try {
				if(bankLogic.loadBank()) {
					UpdateCustomerComboBox();
					customerComboBox.setSelectedIndex(0);
				}
				else{
					JOptionPane.showMessageDialog(frame, "Error when loading customers to bank.");
				}
			}
    		catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
    	}
    }
    
    
    
    
    /**
     * ActionListener for the menuItem Save Transactions. Checks that personal number and account Id is correctly filled in
     * into the text fields, and if so, checks that the account belongs to the specified customer before calling an instance
     * method to save the transactions for the account into a text file. Shows error messages for each error that can occur.
     */
    private class SaveTransactionsListener implements ActionListener {
    	public void actionPerformed(ActionEvent e) {
    			//As long as the text field with accountNr is not empty.
    		if(!textFieldAccountId.getText().isEmpty() && !textFieldPNo.getText().isEmpty()) {
    				//Check that personal number is in right format.
    			if(textFieldPNo.getText().length() == 10) {
    				//Get personal number and the account ID.
        			String pNo = textFieldPNo.getText();
                    int accountId = Integer.parseInt(textFieldAccountId.getText());
                    
                    	//Save all transactions from account in a list.
                    ArrayList<Integer> accounts = bankLogic.getAccountIds(pNo);
                    
                    	//If account entered exists amongst customers account, continue.
                    if(accounts.contains(accountId)) {
                    		//Write all transactions to file trough instance method.
                    	bankLogic.saveTransactions(pNo, accountId);
                    }
                    else {
                    		//If account does not belong to customer, show error message.
                    	JOptionPane.showMessageDialog(frame, "Account " + accountId + " does not belong to customer " + pNo + ".");
                    }
    			} 
    			else {
    					//If personal number in text field is not 10 digits, show error message.
    				JOptionPane.showMessageDialog(frame, "Personal number needs to be 10 digits.");
    			}
    		} 
    		else {
    				//If text fields for account Id or personal number is empty, show error message.
    			JOptionPane.showMessageDialog(frame, "Please enter account-ID and personal number to save transactions for account.");
    		}
    	}
    }
    
    
    
    
    
    /**
     * The main method that initializes the bankLogic object and the GUI object, and runs the GUI in a new thread.
     */
    public static void main(String[] args) {
    		//Create a new instance of the BankLogic class.
        BankLogic bankLogic = new BankLogic();
        
        	//Create a new GUI object in a new thread.
        SwingUtilities.invokeLater(() -> new  GUI(bankLogic));
    }
}

