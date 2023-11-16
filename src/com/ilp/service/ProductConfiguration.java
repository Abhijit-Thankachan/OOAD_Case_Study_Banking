package com.ilp.service;

import java.util.ArrayList;
import java.util.Scanner;

import com.ilp.entity.CurrentAccount;
import com.ilp.entity.LoanAccount;
import com.ilp.entity.Product;
import com.ilp.entity.SavingsMaxAccount;
import com.ilp.entity.Service;

public class ProductConfiguration {
	public static ArrayList<Service> createService() {
		ArrayList<Service> serviceList = new ArrayList<Service>();
		Scanner scanner = new Scanner(System.in);
		char continueChoice;
		do {
			System.out.println("Enter the Service you want to add");
			String ServiceName = scanner.nextLine();
			System.out.println("Enter the Service code");
			String ServiceCode = scanner.nextLine();
			System.out.println("Enter the Service rate");
			double ServiceRate = scanner.nextDouble();
			Service service = new Service(ServiceCode,ServiceName,ServiceRate );
			serviceList.add(service);
			System.out.println("Do you want to continue creating services(y/n): ");
			continueChoice = scanner.next().charAt(0);
			scanner.nextLine();		}
		while(continueChoice=='y');
		return serviceList;
	}
	
	public static ArrayList<Product> createProduct(ArrayList<Service> serviceList) {
		ArrayList<Product> ProductList = new ArrayList<Product>();
		char productCreation;
		Scanner scanner = new Scanner(System.in);
		String productName,productCode;
		ArrayList<Service> servicesOfProductList = new ArrayList<Service>();
		do {
			System.out.println("Products you can create");
			System.out.println("1.Savings Max Account");
			System.out.println("2.Current Account");
			System.out.println("3.Loan Account");
			System.out.println("Choose products you want to create");
			int choice = scanner.nextInt();
			scanner.nextLine();
			switch(choice) {
			case 1:
				System.out.println("Enter the product name want to add");
				productName = scanner.nextLine();
				System.out.println("Enter the ID of the product");
				productCode = scanner.nextLine();
				servicesOfProductList = ProductConfiguration.createServicesOfProduct(serviceList);
				ProductList.add(new SavingsMaxAccount(productCode,productName, servicesOfProductList));
				break;
			case 2:
				System.out.println("Enter the product name want to add");
				productName = scanner.nextLine();
				System.out.println("Enter the ID of the product");
				productCode = scanner.nextLine();
				servicesOfProductList = ProductConfiguration.createServicesOfProduct(serviceList);
				ProductList.add(new CurrentAccount(productCode,productName, servicesOfProductList));
				break;
			case 3:
				System.out.println("Enter the product name want to add");
				productName = scanner.nextLine();
				System.out.println("Enter the ID of the product");
				productCode = scanner.nextLine();
				servicesOfProductList = ProductConfiguration.createServicesOfProduct(serviceList);
				ProductList.add(new LoanAccount(productCode,productName, servicesOfProductList));
				break;
			}
			System.out.println("Do you want to add more products(y/n): ");
			productCreation = scanner.next().charAt(0);
		}while( productCreation == 'y');
		return ProductList;	
}
	
	public static ArrayList<Service> createServicesOfProduct(ArrayList<Service> serviceList) {
		ArrayList<Service> servicesOfProductList = new ArrayList<Service>();
		Scanner scanner = new Scanner(System.in);
		System.out.println("The services available are");
		for(Service service: serviceList) {
			System.out.println(service.getServiceCode()+" . "+ service.getServiceName()+" rate "+ service.getRate());
			System.out.println();
			
		}
		
		char continueChoice;
		do {
			System.out.println("Enter the service code you want");
			String serviceChoice = scanner.nextLine();
			scanner.nextLine();
			for(Service service: serviceList) {
				if(service.getServiceCode().equalsIgnoreCase(serviceChoice)) {
					servicesOfProductList.add(service);
				}
			}
			System.out.println("Do you want to continue adding services(y/n): ");
			continueChoice = scanner.next().charAt(0);
		
		}while (continueChoice == 'y');
		return servicesOfProductList;
	}
}
