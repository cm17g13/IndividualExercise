package com.qa.service;

import javax.inject.Inject;
import com.qa.database.AccountManagerInterface;
import com.qa.persistence.domain.Account;
import com.qa.util.JSONUtil;

public class AccountService implements AccountServiceInterface {

	@Inject
	private AccountManagerInterface repo;
	
	
	public String getAllAccounts() {
		return repo.getAllAccounts();
	}
	
	public String findAnAccount(Long id) {
		return repo.findAnAccount(id);
	}
	
	public String createAccount(String account) {
		return repo.createAccount(account);
	}
	
	public String updateAccount(String account) {
		Account updateAccount = repo.getConverter().getObjectForJSON(account, Account.class);
		Account newAccount = updateFields(updateAccount);
		return repo.updateAccount(newAccount);
	}
	
	public String deleteAccount(Long id) {
		return repo.deleteAccount(id);
	}

	
	public Account findAccount(Long id) {
		return repo.findAccount(id);
	}
	
	public Account updateFields(Account updateAccount) {
		Account existingAccount =  repo.findAccount(updateAccount.getId());
		if(existingAccount != null) {
			if(!updateAccount.getFirstName().trim().equals("")) {
				existingAccount.setFirstName(updateAccount.getFirstName());
			}
			if(!updateAccount.getSecondName().trim().equals("")) {
				existingAccount.setSecondName(updateAccount.getSecondName());
			}
			if(!updateAccount.getAccountNumber().trim().equals("")) {
				existingAccount.setAccountNumber(updateAccount.getAccountNumber());
			}
		}
		return existingAccount;
		
	}
	

}
