package com.biel.notetree.repository;


import com.biel.notetree.model.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Created by Biel on 11/11/2016.
 */
public interface AccountRepository extends CrudRepository<Account, Long> {
    Optional<Account> findByUsername(String username);
}
