package com.coding.example;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.coding.example.model.Account;
import com.coding.example.repository.AccountsRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MojCodingExerciseApplication.class)
@SpringBootTest
public class MojCodingExerciseApplicationTests {

	private static final String API_URL = "/rest/accounts/json/";

	// Kept the tests quite simple
	// Would also have added the Repository as a Mock and tested all methods.
	// Also used POSTMAN to test functionality of RestAPI as described in Coding document
	
	private MockMvc mvc;
	
	@Autowired
    private WebApplicationContext wac;
	
	@Autowired
    private AccountsRepository repository;

	@Before
	public void setup() {
        this.mvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	private String createJSONAccount() {
		
	    return "{" +
	            "\"firstName\":\"John\"," +
	            "\"lastName\":\"Doe\"," +
	            "\"accountNumber\":\"1234\"" +
	            "}";
    }
	
	private String createInvalidJSONAccount() {
		
	    return "{" +
	            "\"firstName\":\"John\"," +
	            "}";
    }
	
	@Test
	public void testOkAndZeroAccountsOnCallGetAllAccounts() throws Exception {
		repository.deleteAll();
		
		mvc.perform(MockMvcRequestBuilders.get(API_URL).accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", hasSize(0)));
	}
	
	@Test
	public void testOkAndOneAccountOnCallGetAllAccounts() throws Exception {
		repository.deleteAll();
		
		mvc.perform(MockMvcRequestBuilders.post(API_URL).contentType(MediaType.APPLICATION_JSON).content(createJSONAccount()))
        .andExpect(status().isOk());
	}
	
	@Test
	public void testShouldReturnBadRequestForInvalidJSON() throws Exception {
		repository.deleteAll();
		
		mvc.perform(MockMvcRequestBuilders.post(API_URL).contentType(MediaType.APPLICATION_JSON).content(createInvalidJSONAccount()))
        .andExpect(status().isBadRequest());
	}
	
	@Test
	public void testOkAndDeleteAccountUsingID() throws Exception {
		repository.deleteAll();
		
		Account account = repository.save(createAccount());
		
		mvc.perform(MockMvcRequestBuilders.delete(API_URL+account.getId()))
		.andExpect(status().isOk());	
	}

	@Test
	public void testNotFoundWhenIdIsInvalid() throws Exception {
		
		mvc.perform(MockMvcRequestBuilders.delete(API_URL+"2"))
		.andExpect(status().isNotFound());	
	}
	
	private Account createAccount() {
		return new Account(1, "Other", "Doe", "12345");
	}

}

