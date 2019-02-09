package com.capgemini.repository;

import com.capgemini.beans.Customer;
import com.capgemini.exception.MobileNumberNotExistException;

public interface WalletRepo {

	public boolean save(Customer customer);

	public Customer findOne(String mobilenumber) throws MobileNumberNotExistException;

}
