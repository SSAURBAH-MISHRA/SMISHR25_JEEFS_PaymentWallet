package com.capgemini.test;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.capgemini.exception.DuplicateIdentityException;
import com.capgemini.exception.InsufficientAmountException;
import com.capgemini.exception.MobileNumberNotExistException;
import com.capgemini.repository.WalletRepo;
import com.capgemini.repository.WalletRepoImpl;
import com.capgemini.service.WalletService;
import com.capgemini.service.WalletServiceImpl;

public class WalletImplementationTest {
	WalletRepo walletRepo = new WalletRepoImpl();
	WalletService walletService = new WalletServiceImpl(walletRepo);

	@Before
	public void setUp() throws Exception {
	}

	@Test(expected = com.capgemini.exception.MobileNumberNotExistException.class)
	public void test() throws MobileNumberNotExistException, DuplicateIdentityException {
		walletService.createAccount("Saurabh", "123456", new BigDecimal("1000"));
		walletService.showBalance("123");
	}

	@Test(expected = com.capgemini.exception.DuplicateIdentityException.class)
	public void test1() throws DuplicateIdentityException, MobileNumberNotExistException {
		walletService.createAccount("Saurabh", "12345", new BigDecimal("2000"));
		walletService.createAccount("Saurabh", "12345", new BigDecimal("1000"));
	}

	@Test(expected = com.capgemini.exception.InsufficientAmountException.class)
	public void test2() throws MobileNumberNotExistException, InsufficientAmountException, DuplicateIdentityException {
		walletService.createAccount("Saurabh", "123456", new BigDecimal("10000.0"));
		walletService.withdrawAmount("123456", new BigDecimal("15000.00"));
	}
}