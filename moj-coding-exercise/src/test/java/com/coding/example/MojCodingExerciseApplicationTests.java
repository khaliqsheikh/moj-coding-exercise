package com.coding.example;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
	
	@Test
	public void testOkAndZeroAccountsOnCallGetAllAccounts() throws Exception {
		repository.deleteAll();
		
		mvc.perform(MockMvcRequestBuilders.get("/rest/accounts/json").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", hasSize(0)));
	}
	
	@Test
	public void testOkAndOneAccountOnCallGetAllAccounts() throws Exception {
		repository.deleteAll();
		
		mvc.perform(MockMvcRequestBuilders.post("/rest/accounts/json").contentType(MediaType.APPLICATION_JSON).content(createJSONAccount()))
        .andExpect(status().isOk());
	}
	
	
	

}

