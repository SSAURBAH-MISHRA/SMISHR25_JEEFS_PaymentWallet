package com.capgemini.beans;

public class Customer {
	private String name;
	private String mobileNumber;
	private Wallet wallet;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}

	public Customer(String name, String mobileNumber, Wallet wallet) {
		super();
		this.name = name;
		this.mobileNumber = mobileNumber;
		this.wallet = wallet;
	}

	public Customer() {
		super();
	}

	@Override
	public String toString() {
		return "Customer [name=" + name + ", mobileNumber=" + mobileNumber + ", wallet=" + wallet + "]";
	}

}
