package com.qa.database;

import javax.inject.Inject;
import javax.persistence.*;
import javax.transaction.Transactional;
import static javax.transaction.Transactional.TxType.*;
import java.util.Collection;
import com.qa.persistence.domain.Account;
import com.qa.util.JSONUtil;

@Transactional(SUPPORTS)
public class AccountManagerDBImpl implements AccountManagerInterface {

	@Inject
	private JSONUtil jsonConverter;

	@PersistenceContext(unitName = "primary")
	private EntityManager manager;

	
	public String getAllAccounts() {
		
		TypedQuery<Account> query = manager.createQuery("SELECT a FROM ACCOUNTS a", Account.class);
		Collection<Account> accounts = query.getResultList();
		return jsonConverter.getJSONForObject(accounts);
	}
	

	public String findAnAccount(Long id) {

		Account account = findAccount(id);
		System.out.println("Thing " +account);
		if(account != null) {
			return jsonConverter.getJSONForObject(account);
		} 
		return "{\"message\": \"account was not found\"}";
	
	}
	
	
	@Transactional(REQUIRED)
	public String createAccount(String account) {
		
		Account newAccount = jsonConverter.getObjectForJSON(account, Account.class);
		if(findAccount(newAccount.getId()) != null) {
			return "{\"message\": \"the account exists, and so was not added\"}";
		} else {
			manager.persist(newAccount);
			return "{\"message\": \"the account has been added\"}";
		}
	}
	

	@Transactional(REQUIRED)
	public String updateAccount(String account) {
		
		Account existingAccount = jsonConverter.getObjectForJSON(account, Account.class);
		if (findAccount(existingAccount.getId()) != null) {
			manager.merge(existingAccount);
			return "{\"message\": \"the account has been updated\"}";
		} else {
			return "{\"message\": \"the account did not exist, and so was not updated\"}";
		}
	}
	
	
	@Transactional(REQUIRED)
	public String deleteAccount(Long id) {
		
		Account exists = findAccount(id);
		if (exists != null) {
			manager.remove(exists);
			return "{\"message\": \"the account has been deleted\"}";
		} else {
			return "{\"message\": \"the account did not exist, and so was not deleted\"}";
		}	
	}
	
	public Account findAccount(Long id) {
		return manager.find(Account.class, id);
	}
	
	@Transactional(REQUIRED)
	public void setEntityManager(EntityManager manager) {
		this.manager = manager;
	}
	
	@Transactional(REQUIRED)
	public void setJsonConverter(JSONUtil jsonConverter) {
		this.jsonConverter = jsonConverter;
	}

}

