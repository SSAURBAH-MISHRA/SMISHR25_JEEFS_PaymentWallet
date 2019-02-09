package com.capgemini.view;

import java.math.BigDecimal;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.capgemini.exception.DuplicateIdentityException;
import com.capgemini.exception.DuplicateMobileNumberException;
import com.capgemini.exception.InsufficientAmountException;
import com.capgemini.exception.InvalidMobileNumberException;
import com.capgemini.exception.MobileNumberNotExistException;
import com.capgemini.repository.WalletRepo;
import com.capgemini.repository.WalletRepoImpl;
import com.capgemini.service.WalletService;
import com.capgemini.service.WalletServiceImpl;

public class WalletMainApp {

	private static WalletRepo walletRepository = new WalletRepoImpl();
	private static WalletService walletService = new WalletServiceImpl(walletRepository);
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws MobileNumberNotExistException, InsufficientAmountException,
			InvalidMobileNumberException, DuplicateIdentityException, DuplicateMobileNumberException {
		display();
	}

	private static void display() throws MobileNumberNotExistException, InsufficientAmountException,
			InvalidMobileNumberException, DuplicateIdentityException, DuplicateMobileNumberException {
		int choice;
		System.out.println("*************Payment Wallet Project************");
		while (true) {
			System.out.println("1.Create a new account");
			System.out.println("2.Show Balance");
			System.out.println("3.Fund Transfer");
			System.out.println("4.Deposit Amount");
			System.out.println("5.Withdraw Amount");
			System.out.println("6.Exit");

			System.out.print("Enter your choice : ");
			choice = sc.nextInt();

			switch (choice) {
			case 1:
				createAccount();
				break;
			case 2:
				showBalance();
				break;
			case 3:
				fundTransfer();
				break;
			case 4:
				depositAmount();
				break;
			case 5:
				withdrawAmount();
				break;
			case 6:
				System.exit(0);
				break;
			default:
				System.out.println("Invalid choice.");

			}
		}

	}

	private static void createAccount()
			throws DuplicateIdentityException, MobileNumberNotExistException, DuplicateMobileNumberException {
		String mobileNumber="12";
		String Name="12";
		String amount;

		while(mobileNumber.length()!=10) {
			System.out.println("Enter a 10 Digit number:");
		mobileNumber = sc.next();
		}

		while(Pattern.compile("[0-9]").matcher(Name).find()) 
		{
			System.out.println("Enter a Name without Any Digit");
		Name = sc.next();
		}

		System.out.print("Enter amount : ");
		amount = sc.next();

		BigDecimal amount1 = new BigDecimal(amount);
		walletService.createAccount(Name, mobileNumber, amount1);
		System.out.println("Account is Successfully Created\n");
	}

	private static void showBalance() throws MobileNumberNotExistException, InvalidMobileNumberException {
		String mobileNumber;
		System.out.print("Enter your valid mobile number : ");
		mobileNumber = sc.next();
		System.out
				.println("Your Account Balance is " + walletService.showBalance(mobileNumber).getWallet().getBalance());
	}

	private static void fundTransfer() throws MobileNumberNotExistException, InsufficientAmountException {
		String senderMobileNumber;
		String receiverMobileNumber;
		String amount;
		System.out.print("Enter your valid mobile number : ");
		senderMobileNumber = sc.next();

		System.out.print("Enter other one valid mobile number : ");
		receiverMobileNumber = sc.next();

		System.out.print("Enter amount : ");
		amount = sc.next();

		BigDecimal amount1 = new BigDecimal(amount);
		walletService.fundTransfer(senderMobileNumber, receiverMobileNumber, amount1);
		System.out.println("Fund is Transfered From:" + senderMobileNumber + "    To:" + receiverMobileNumber
				+ "    Amount:" + amount);
	}

	private static void depositAmount() throws MobileNumberNotExistException, InvalidMobileNumberException {
		String mobileNumber;
		String amount;

		System.out.print("Enter your valid mobile number : ");
		mobileNumber = sc.next();

		System.out.print("Enter amount : ");
		amount = sc.next();

		BigDecimal amount1 = new BigDecimal(amount);
		walletService.depositAmount(mobileNumber, amount1);
		System.out.println("Amount- " + amount + " is Deposited to" + mobileNumber);
	}

	private static void withdrawAmount() throws MobileNumberNotExistException, InsufficientAmountException {
		String mobileNumber;
		String amountString;
		System.out.print("Enter your valid mobile number : ");
		mobileNumber = sc.next();
		System.out.print("Enter amount : ");
		amountString = sc.next();

		BigDecimal amount = new BigDecimal(amountString);
		walletService.withdrawAmount(mobileNumber, amount);
		System.out.println("Amount- " + amount + " is Withdrawn from" + mobileNumber);
	}

}