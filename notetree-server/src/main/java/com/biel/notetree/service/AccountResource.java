package com.biel.notetree.service;

import com.biel.notetree.model.Account;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

/**
 * Created by Biel on 12/11/2016.
 */
public class AccountResource extends ResourceSupport {
    private final Account account;

    public AccountResource(Account account) {
        this.account = account;
        this.add(new Link(account.getEmail() + "@-@", "account-email"));
        //this.add(linkTo(methodOn(TaskService.class, account.getUsername()).get(null, "/life")).withSelfRel());
        this.add(new Link(account.getUsername(), Link.REL_SELF));
    }

    public Account getAccount() {
        return account;
    }
}
