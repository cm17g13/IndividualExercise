package com.qa.service;

import com.qa.persistence.domain.Account;

public interface AccountServiceInterface {

	public String getAllAccounts();

	public String findAnAccount(Long id);
	
	public String createAccount(String account);

	public String updateAccount(String account);

	public String deleteAccount(Long id);
	
	public Account findAccount(Long id);
	
}
