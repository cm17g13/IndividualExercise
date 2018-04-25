package com.qa.service;

import com.qa.domain.Account;
import com.qa.util.JSONUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;

@RunWith(MockitoJUnitRunner.class)
public class AccountManagerDBTest {
	
	@InjectMocks
	private AccountManagerDBRepo accountManager;

    private JSONUtil jsonConverter;
	
	@Mock
    private EntityManager manager;

	@Mock
    private TypedQuery<Account> query;

	String person1 = "{\"firstName\":\"Callum\",\"secondName\":\"McGregor\",\"accountNumber\":\"1234\"}";
	
	@Before
	public void before() {
		accountManager.setEntityManager(manager);
		this.jsonConverter = new JSONUtil();
		accountManager.setJsonConverter(jsonConverter);
		accountManager.createAccount(person1);
	}

	@Test
	public void getAllAccountsTest() {
     
        String personArray = "[" + person1 + "]";
        Assert.assertEquals(personArray, accountManager.getAllAccounts());
	}
	

	@Test
	public void findAnAccountTest() {
        
        Assert.assertEquals(person1, accountManager.findAnAccount("1234"));
	}

	@Test
	public void createAccountTest() {
		accountManager.deleteAccount("1234");
        Assert.assertEquals("{\"message\": \"the account has been added\"}", accountManager.createAccount(person1));
    }

	@Test
	public void updateAccountTest() {
  
        Assert.assertEquals("{\"message\": \"the account has been updated\"}", accountManager.updateAccount("1234" ,person1));

	}

	@Test
	public void deleteAccountTest() {
       
        Assert.assertEquals("{\"message\": \"the account has been deleted\"}", accountManager.deleteAccount("1234"));

    }
}
