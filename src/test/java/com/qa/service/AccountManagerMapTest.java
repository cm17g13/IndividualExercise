package com.qa.service;

import com.qa.database.AccountManagerMapImpl;
import com.qa.persistence.domain.Account;
import com.qa.util.JSONUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;

public class AccountManagerMapTest {

    private AccountManagerMapImpl accountManager;

    private JSONUtil jsonConverter;

    String person1 = "{\"firstName\":\"Tib\",\"secondName\":\"Coder\",\"accountNumber\":\"1111\"}";

    @Before
    public void before() {
        this.accountManager = new AccountManagerMapImpl();
        this.jsonConverter = new JSONUtil();
       
    }

    @Test
    public void getAllAccountsTest() {
    	accountManager.createAccount(person1);
        String personArray = "[" + "{\"id\":" + accountManager.getCount() + "," + person1.substring(1) + "]";
        Assert.assertEquals(personArray, accountManager.getAllAccounts());
    }


    @Test
    public void findAnAccountTest() {
    	accountManager.createAccount(person1);
    	String person2 =  "{\"id\":" + accountManager.getCount() + "," + person1.substring(1);
        Assert.assertEquals(person2, accountManager.findAnAccount(accountManager.getCount()));
    }

    @Test
    public void createAccountTest() {
        Assert.assertEquals("{\"message\": \"the account has been added\"}", accountManager.createAccount(person1));
    }

    @Test
    public void updateAccountTest() {
    	accountManager.createAccount(person1);
    	String person2 =  "{\"id\":" + accountManager.getCount() + "," + person1.substring(1);
        Assert.assertEquals("{\"message\": \"the account has been updated\"}", accountManager.updateAccount(person2));

    }

    @Test
    public void deleteAccountTest() {
    	accountManager.createAccount(person1);
        Assert.assertEquals("{\"message\": \"the account has been deleted\"}", accountManager.deleteAccount(accountManager.getCount()));

    }
}
