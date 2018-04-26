package com.qa.database;

import com.qa.util.JSONUtil;
import com.qa.persistence.domain.Account;

public abstract interface AccountManagerInterface {

    String getAllAccounts();

    String findAnAccount(Long id);

    String createAccount(String account);

    String updateAccount(Account account);
    
    String deleteAccount(Long id);
    
    Account findAccount(Long id);
    
    JSONUtil getConverter();
    
}
