package com.biel.notetree.service;

import com.biel.notetree.model.Account;
import com.biel.notetree.repository.AccountRepository;
import com.biel.notetree.service.exceptions.UserNotFoundException;
import com.biel.notetree.service.requests.RegisterRequest;
import com.biel.notetree.service.resources.AccountResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.text.MessageFormat;

/**
 * Created by Biel on 11/11/2016.
 */
@RestController
@RequestMapping("/accounts")
public class AccountService {
    AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    String list() {
        return MessageFormat.format("{0} accounts registered on the server.", accountRepository.count());
    }

    //Register, login, ...
    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    AccountResource summary(@PathVariable String userId) throws UserNotFoundException {
        return new AccountResource(getAccount(userId));
    }

    //Register
    @RequestMapping(value = "", method = RequestMethod.POST)
    ResponseEntity register(@RequestBody RegisterRequest registerRequest){ // Still throws exception
        String userId = registerRequest.getUsername();
        String password = registerRequest.getPassword();
        if (accountRepository.findByUsername(userId).isPresent()) return ResponseEntity.status(HttpStatus.CONFLICT).body("Username " + userId + " already exists.");
        Account account = accountRepository.save(new Account(userId, password));
        Link link = new AccountResource(account).getLink(Link.REL_SELF);
        return ResponseEntity.created(URI.create(link.getHref())).build();
    }

    protected Account getAccount(String userId) throws UserNotFoundException {
        //Authenticate user here
        return accountRepository.findByUsername(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }
}
