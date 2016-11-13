package com.biel.notetree.repository;

import com.biel.notetree.model.Account;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

/**
 * Created by Biel on 13/11/2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountRepositoryTest {
    @Autowired
    AccountRepository accountRepository;

    Account testAccount;
    @Before
    public void setup() throws Exception {
       testAccount = accountRepository.save(new Account(String.format("test_username_%s", Math.random() * 100), "123456"));
    }
    @Test
    public void findByUsername(){
        accountRepository.findByUsername(testAccount.getUsername());
    }
}
