package com.ilp.service;

import java.util.ArrayList;
import java.util.Scanner;

import com.ilp.entity.Account;
import com.ilp.entity.Customer;
import com.ilp.entity.Product;
import com.ilp.entity.Service;

public class CustomerAccountConifguration {
	public static Account createAccount(ArrayList<Product> ProductList ) {
		Scanner scanner = new Scanner(System.in);
		Product productOfAccount= null;
		System.out.println("Enter your AccountNo:");
		String accountNo = scanner.nextLine();
		System.out.println("Enter the Balance :");
		double accountBalance = scanner.nextDouble();
		scanner.nextLine();
		System.out.println("****** Products Available ******");
		for(Product product: ProductList) {
			System.out.println(product.getProductCode()+" . "+ product.getProductName());
		}
		System.out.println("Enter product ID you want to add");
		String productChoice = scanner.nextLine();
		for(Product product: ProductList) {
			if(product.getProductCode().equalsIgnoreCase(productChoice)) {
				productOfAccount = product;
			}
		}
		
		return new Account(accountNo,accountBalance,productOfAccount);
	}
	public static Customer createCustomer(Customer customer,ArrayList<Product> ProductList){
		Account account = null;
		if (customer == null) {
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter the Customer Code: ");
			String customerCode = scanner.nextLine();
			System.out.println("Enter the Customer Name: ");
			String customerName = scanner.nextLine();
			ArrayList<Account> accountList = new ArrayList<Account>();
			account = CustomerAccountConifguration.createAccount(ProductList);
			accountList.add(account);
			customer = new Customer(customerCode, customerName, accountList);
		} else {
			ArrayList<Account> accountList = customer.getAccountList();
			account = CustomerAccountConifguration.createAccount(ProductList);
			accountList.add(account);
			customer.setAccountList(accountList);
		}

		return customer;
		
	}
	public static void displayCustomer(Customer customer) {
		// TODO Auto-generated method stub
		System.out.println("*************************Customer-Account Details*************************");
		System.out.println("CustomerId" + "	" + "CustomerName" + "		" + "AccountType" + "		" + "Balance");
		System.out.println("**************************************************************************");
		for (Account account : customer.getAccountList()) {
			System.out.println(customer.getCustomerCode() + " 		" + customer.getCustomerName() + " 		"
					+ account.getProduct().getProductName() + "		" + account.getBalance());
		}
		for (Account account : customer.getAccountList()) {
			Product product = account.getProduct();
			System.out.println("Services for " + product.getProductName());
			for (Service service : product.getServiceList()) {
				System.out.println(service.getServiceCode() + ". " + service.getServiceName());
			}
		}
	}
	public static void transactionBill(Customer customer, String customerCode) {
		// TODO Auto-generated method stub
		if (!(customerCode.equalsIgnoreCase(customer.getCustomerCode()))) {
			System.out.println("Customer not found!!!!!");
		} else {
			System.out.println(customer.getCustomerName() + " has the following accounts");
			for (Account account : customer.getAccountList()) {
				System.out.println(account.getAccountNo() + ". " + account.getProduct().getProductName());
			}
 
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter the account no you want to operate on: ");
			String accountChoice = scanner.nextLine();
			for (Account account : customer.getAccountList()) {
				if (accountChoice.equalsIgnoreCase(account.getAccountNo())) {
					Product product = account.getProduct();
					System.out.println("Choose the Service you want to use");
					for (Service service : product.getServiceList()) {
						System.out.println(service.getServiceCode() + ". " + service.getServiceName());
					}
					System.out.println("Enter the service ID you want to use: ");
					String serviceChoice = scanner.nextLine();
					for (Service service : product.getServiceList()) {
						if (serviceChoice.equalsIgnoreCase(service.getServiceCode())) {
							System.out.println("Rate of service = Rs." + service.getRate());
							System.out.println("Enter the number of transactions: ");
							double noOfTransactions = scanner.nextDouble();
							scanner.nextLine();
							double totalCost = noOfTransactions * service.getRate();
							System.out.println("Total Cost = Rs." + totalCost);
						}
					}
				}
			}
		}
 
	}
}
