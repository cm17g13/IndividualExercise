package com.qa.service;

import com.qa.database.AccountManagerDBImpl;
import com.qa.persistence.domain.Account;
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
	private AccountManagerDBImpl accountManager;

    private JSONUtil jsonConverter;
	
	@Mock
    private EntityManager manager;

	@Mock
    private TypedQuery<Account> query;
	
	private Account person1Account = new Account("Callum", "McGregor", "1234");

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
        Mockito.when(manager.createQuery(Mockito.anyString(), Mockito.eq(Account.class))).thenReturn(query);
        ArrayList<Account> accounts = new ArrayList<Account>();
        accounts.add(new Account("Callum", "McGregor", "1234"));
        Mockito.when(query.getResultList()).thenReturn(accounts);
        String personArray = "[" + person1 + "]";
        Assert.assertEquals(personArray, accountManager.getAllAccounts());
	}
	

	@Test
	public void findAnAccountTest() {
        Mockito.when(manager.find(Mockito.eq(Account.class), Mockito.anyLong())).thenReturn(person1Account);
        Assert.assertEquals(person1, accountManager.findAnAccount(1L));
	}

	@Test
	public void createAccountTest() {
		Mockito.when(manager.find(Mockito.eq(Account.class), Mockito.anyLong())).thenReturn(null);
        Assert.assertEquals("{\"message\": \"the account has been added\"}", accountManager.createAccount(person1));
    }

	@Test
	public void updateAccountTest() {
		Mockito.when(manager.find(Mockito.eq(Account.class), Mockito.anyLong())).thenReturn(person1Account);
        Assert.assertEquals("{\"message\": \"the account has been updated\"}", accountManager.updateAccount(person1));

	}

	@Test
	public void deleteAccountTest() {
		Mockito.when(manager.find(Mockito.eq(Account.class), Mockito.anyLong())).thenReturn(person1Account);
        Mockito.when(query.getSingleResult()).thenReturn(new Account("Callum", "McGregor", "1234"));
        Assert.assertEquals("{\"message\": \"the account has been deleted\"}", accountManager.deleteAccount(1L));

    }
}
