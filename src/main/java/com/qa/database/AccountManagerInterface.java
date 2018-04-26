package com.qa.database;

public abstract interface AccountManagerInterface {

    String getAllAccounts();

    String findAnAccount(Long id);

    String createAccount(String account);

    String updateAccount(String account);
    
    String deleteAccount(Long id);

    
}
