package com.capgemini.service;

import java.math.BigDecimal;

import com.capgemini.beans.Customer;
import com.capgemini.beans.Wallet;
import com.capgemini.exception.DuplicateIdentityException;
import com.capgemini.exception.InsufficientAmountException;
import com.capgemini.exception.MobileNumberNotExistException;
import com.capgemini.repository.WalletRepo;
import com.capgemini.repository.WalletRepoImpl;

public class WalletServiceImpl implements WalletService {

	WalletRepo walletRepo = new WalletRepoImpl();

	public WalletServiceImpl(WalletRepo walletRepo) {
		super();
		this.walletRepo = walletRepo;
	}

	@Override
	public Customer createAccount(String name, String mobileNumber, BigDecimal amount)
			throws DuplicateIdentityException, MobileNumberNotExistException {
		// TODO Auto-generated method stub
		Wallet wallet = new Wallet(amount);
		Customer customer = new Customer(name, mobileNumber, wallet);
		if (walletRepo.findOne(mobileNumber) != null) {
			throw new DuplicateIdentityException();
		}
		walletRepo.save(customer);
		return customer;
	}

	@Override
	public Customer showBalance(String mobileNumber) throws MobileNumberNotExistException {
		// TODO Auto-generated method stub
		if (walletRepo.findOne(mobileNumber) == null) {
			throw new MobileNumberNotExistException();
		}

		return walletRepo.findOne(mobileNumber);
	}

	@Override
	public Customer fundTransfer(String sourceMobileNumber, String targetMobileNumber, BigDecimal amount)
			throws MobileNumberNotExistException, InsufficientAmountException {

		this.depositAmount(targetMobileNumber, amount);
		this.withdrawAmount(sourceMobileNumber, amount);

		return walletRepo.findOne(sourceMobileNumber);
	}

	@Override
	public Customer depositAmount(String mobileNumber, BigDecimal amount) throws MobileNumberNotExistException {
		// TODO Auto-generated method stub
		Customer customer = walletRepo.findOne(mobileNumber);
		Wallet wallet = customer.getWallet();
		wallet.setBalance(wallet.getBalance().add(amount));
		customer.setWallet(wallet);
		return customer;
	}

	@Override
	public Customer withdrawAmount(String mobileNumber, BigDecimal amount)
			throws MobileNumberNotExistException, InsufficientAmountException {
		// TODO Auto-generated method stub
		Customer customer = walletRepo.findOne(mobileNumber);
		Wallet wallet = customer.getWallet();

		int a = amount.compareTo(wallet.getBalance());
		if (a == 1) {
			throw new InsufficientAmountException();
		}
		wallet.setBalance(wallet.getBalance().subtract(amount));

		return customer;
	}

}
