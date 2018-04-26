package com.qa.database;

import com.qa.persistence.domain.Account;
import com.qa.util.JSONUtil;
import javax.enterprise.inject.Alternative;
import java.util.HashMap;


@Alternative
public class AccountManagerMapImpl implements AccountManagerInterface {

    private HashMap<Long, Account> accounts;

    private JSONUtil jsonConverter;
    
    private Long count;

    public AccountManagerMapImpl() {
        this.accounts = new HashMap<Long, Account>();
        this.jsonConverter = new JSONUtil();
        count = 0L;
    }

    public String getAllAccounts() {
        return jsonConverter.getJSONForObject(accounts.values());
    }

    public String findAnAccount(Long id) {
        return jsonConverter.getJSONForObject(accounts.get(id));
    }

    public String createAccount(String account) {
    	String newAccountString = insertID(account);
        Account newAccount = jsonConverter.getObjectForJSON(newAccountString, Account.class);
        if(findAnAccount(newAccount.getId()) != null) {
            return "{\"message\": \"the account exists, and so was not added\"}";
        } else {
            accounts.put(newAccount.getId(), newAccount);
            return "{\"message\": \"the account has been added\"}";
        }
    }

    public String updateAccount(String account) {
        Account existingAccount = jsonConverter.getObjectForJSON(account, Account.class);
        if (findAnAccount(existingAccount.getId()) != null) {
            accounts.put(existingAccount.getId() ,existingAccount);
            return "{\"message\": \"the account has been updated\"}";
        } else {
            return "{\"message\": \"the account did not exist, and so was not updated\"}";
        }
    }


    public String deleteAccount(Long id) {

        String exists = findAnAccount(id);
        if (exists != null) {
           accounts.remove(id);
            return "{\"message\": \"the account has been deleted\"}";
        } else {
            return "{\"message\": \"the account did not exist, and so was not deleted\"}";
        }
    }
    
    public String insertID(String accountString) {
    	count++;
    	return "{\"id\":" + count + "," + accountString.substring(1);
 
    }
    
    public void setCount(Long count) {
    	this.count = count;
    }
    
    public Long getCount() {
    	return count;
    }
}