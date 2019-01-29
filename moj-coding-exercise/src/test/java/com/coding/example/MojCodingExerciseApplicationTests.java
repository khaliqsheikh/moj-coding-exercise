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


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MojCodingExerciseApplication.class)
@SpringBootTest
public class MojCodingExerciseApplicationTests {

	private MockMvc mvc;
	
	@Autowired
    private WebApplicationContext wac;

	@Before
	public void setup() {
        this.mvc = MockMvcBuilders.webAppContextSetup(wac).build();

	}
	
	@Test
	public void testOkAndZeroAccountsOnCallGetAllAccounts() throws Exception {
				
		mvc.perform(MockMvcRequestBuilders.get("/rest/accounts/json").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", hasSize(0)));
		
	}

}

