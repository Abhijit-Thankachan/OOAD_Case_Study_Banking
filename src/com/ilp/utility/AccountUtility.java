package com.ilp.utility;

import java.util.ArrayList;
import java.util.Scanner;

import com.ilp.entity.Account;
import com.ilp.entity.Customer;
import com.ilp.entity.Product;
import com.ilp.entity.Service;
import com.ilp.service.ProductConfiguration;
import com.ilp.service.CustomerAccountConifguration;
import com.ilp.service.ManageAccountService;

public class AccountUtility {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Customer customer = null;
		ArrayList<Service> serviceList = new ArrayList<Service>();
		ArrayList<Product> productList = new ArrayList<Product>();

		Scanner scanner = new Scanner(System.in);
		char continueChoice;
		do {
			System.out.println("************Welcome To Bank************");
			System.out.println("1.Create Services");
			System.out.println("2.Create Products");
			System.out.println("3.Create Accounts");
			System.out.println("4.Manage Accounts");
			System.out.println("5.Display Accounts");
			System.out.println("6.Transaction Bill");
			System.out.println("7.Exit");
			System.out.println("Enter your choice :");
			int menuChoice = scanner.nextInt();
			scanner.nextLine();
			switch (menuChoice) {
			case 1:
				serviceList = ProductConfiguration.createService();
				break;
			case 2:
				productList = ProductConfiguration.createProduct(serviceList);
				break;
			case 3:
				customer = CustomerAccountConifguration.createCustomer(customer,productList);
				break;
			case 4:
				System.out.println("Enter customer ID :");
				String customerID = scanner.nextLine();
				scanner.nextLine();
				ManageAccountService.manageAccounts(customerID,customer);
				break;
			case 5:
				CustomerAccountConifguration.displayCustomer(customer);
				break;
			case 6:
				System.out.println("Enter the customer ID :");
				String customerCode = scanner.nextLine();
				CustomerAccountConifguration.transactionBill(customer,customerCode);
				break;
			
			case 7:
				System.out.println("***** Thank you! ******");
				System.exit(0);
			}

			System.out.println("Do you want to continue to main menu(y/n): ");
			continueChoice = scanner.next().charAt(0);
		} while (continueChoice == 'y');


}
}
