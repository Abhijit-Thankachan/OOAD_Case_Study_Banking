package com.ilp.service;

import java.util.Scanner;

import com.ilp.entity.Account;
import com.ilp.entity.CurrentAccount;
import com.ilp.entity.Customer;
import com.ilp.entity.LoanAccount;
import com.ilp.entity.Product;
import com.ilp.entity.SavingsMaxAccount;
import com.ilp.entity.Service;

public class ManageAccountService {
	public static Customer manageAccounts(String customerID, Customer customer) {
		Scanner scanner = new Scanner(System.in);
		if (!(customerID.equalsIgnoreCase(customer.getCustomerCode()))) {
			System.out.println("Customer not found!!!!!");
		} else {
			System.out.println(customer.getCustomerName() + " has the following accounts");
			for (Account account : customer.getAccountList()) {
				System.out.println(account.getAccountNo() + ". " + account.getProduct().getProductName());
			}
		}
		System.out.println("Enter the account no you want to operate on: ");
		String accountChoice = scanner.nextLine();
		char continueChoice;
		for (Account account : customer.getAccountList()) {
			if(accountChoice.equalsIgnoreCase(account.getAccountNo())) {
				do {
					System.out.println("Services Available");
					System.out.println("1. Cash Deposit");
					System.out.println("2. Cash Withdrawal");
					System.out.println("3. Display balance");
					System.out.println("Enter your service choice");
					int serviceChoice = scanner.nextInt();
					switch(serviceChoice) {
					case 1:
						account = depositMoney(account);
						break;
					case 2:
						account = withdrawMoney(account);
						break;
					case 3:
						displayAccount(customer, account);
						break;
					}
					System.out.println("Do you want to use other services:(y/n)");
					continueChoice = scanner.next().charAt(0);
				}
				while(continueChoice == 'y');
				
			}
		}
		return customer;
	}
	private static Account depositMoney(Account account) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the amount to be deposited:");
		double depositAmount = scanner.nextDouble();
		scanner.nextLine();
		if (account.getProduct() instanceof LoanAccount) {
			LoanAccount loanAccount = (LoanAccount) account.getProduct();
			System.out.println("Do you want to make a cheque deposit?(y/n): ");
			char chequeDeposit = scanner.next().charAt(0);
			scanner.nextLine();
			if (chequeDeposit == 'y') {
				double chequeCharge = loanAccount.getCharge() * depositAmount;
				System.out.println("Rs." + chequeCharge + " has been charged for cheque deposit");
				account.setBalance(account.getBalance() + depositAmount - chequeCharge);
			}
		} else {
			account.setBalance(account.getBalance() + depositAmount);
		}
		return account;
	}
	private static Account withdrawMoney(Account account) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the amount to be withdrawn:");
		double withdrawalAmount = scanner.nextDouble();
		scanner.nextLine();
		if (account.getProduct() instanceof SavingsMaxAccount) {
			SavingsMaxAccount savingsMaxAccount = (SavingsMaxAccount) account.getProduct();
			if (account.getBalance() >= savingsMaxAccount.getMinBalance() + withdrawalAmount) {
				account.setBalance(account.getBalance() - withdrawalAmount);
			} else {
				System.out.println("Withdrawal Cancelled! Minimum balance is required for Savings Account");
			}
		} else if (account.getBalance() < withdrawalAmount) {
			System.out.println("Withdrawal Cancelled! Account does not have sufficient balance");
		} else {
			account.setBalance(account.getBalance() - withdrawalAmount);
		}
		return account;
 
	}
	private static void displayAccount(Customer customer, Account account) {
		// TODO Auto-generated method stub
		System.out.println("*************************Customer-Account Details*************************");
		System.out.println("CustomerId" + "	" + "CustomerName" + "		" + "AccountType" + "		" + "Balance");
		System.out.println("**************************************************************************");
		System.out.println(customer.getCustomerCode() + " 		" + customer.getCustomerName() + " 		"
				+ account.getProduct().getProductName() + "		" + account.getBalance());
		Product product = account.getProduct();
		System.out.println("Services for " + product.getProductName());
		for (Service service : product.getServiceList()) {
			System.out.println(service.getServiceCode() + ". " + service.getServiceName());
		}
 
	}
}

