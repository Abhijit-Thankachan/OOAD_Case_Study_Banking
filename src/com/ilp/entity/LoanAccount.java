package com.ilp.entity;

import java.util.ArrayList;

public class LoanAccount extends Product {
	private double depositCharge = 0.3;

	public double getCharge() {
		return depositCharge;
	}

	public void setCharge(double depositCharge) {
		this.depositCharge = depositCharge;
	}

	public LoanAccount(String productCode, String productName, ArrayList<Service> serviceList) {
		super(productCode, productName, serviceList);
	}

}
