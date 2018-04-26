package com.qa.interoperability;

import javax.inject.Inject;
import javax.ws.rs.*;
import com.qa.service.AccountServiceInterface;

@Path("/account")
public class AccountEndpoint {

	@Inject
	private AccountServiceInterface service;
	
	@GET
	@Path("/json")
	@Produces({"application/json"})
	public String getAllAccounts() {
		return service.getAllAccounts();
	}
	
	@GET
	@Path("/json/{id}")
	@Produces({"application/json"})
	public String findAnAccount(@PathParam("id") Long id) {
		return service.findAnAccount(id);
	}
	
	@POST
	@Path("/json")
	@Produces({"application/json"})
	public String createAccount(String account) {
		return service.createAccount(account);
	}

	@PUT
	@Path("/json")
	@Produces({"application/json"})
	public String updateAccount(String account) {
		return service.updateAccount(account);
	}
	
	@DELETE
	@Path("/json/{id}")
	@Produces({"application/json"})
	public String deleteAccount(@PathParam("id") Long id) {
		return service.deleteAccount(id);
	}
	
}
