package com.biel.notetree.repository;

import com.biel.notetree.model.Account;
import com.biel.notetree.model.Note;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.awt.*;
import java.util.List;
import java.util.Optional;

/**
 * Created by Biel on 11/11/2016.
 */
public interface NoteRepository extends PagingAndSortingRepository<Note, Long> {
    Optional<Note> findOneByAccountAndPath(Account account, String path);
    List<Event> findManyByAccount(Account account);
}
