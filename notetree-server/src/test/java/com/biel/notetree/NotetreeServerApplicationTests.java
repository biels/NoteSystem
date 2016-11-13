package com.biel.notetree;

import com.biel.notetree.model.Account;
import com.biel.notetree.repository.AccountRepository;
import com.biel.notetree.repository.NoteRepository;
import com.biel.notetree.service.AccountService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NotetreeServerApplicationTests {
	private MockMvc mockMvc;
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	NoteRepository noteRepository;

	@Autowired AccountService accountService;

	@Test
	public void contextLoads() {
	}
	@Test
	public void loadAccounts() {
		Optional<Account> biel = accountRepository.findByUsername("biel");
		assert biel.isPresent();
		Optional<Account> sth = accountRepository.findByUsername("something_else");
		assert !sth.isPresent();
	}

}
