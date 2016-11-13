package com.biel.notetree.repository;

import com.biel.notetree.model.Account;
import com.biel.notetree.model.Note;
import org.jooq.lambda.Seq;
import org.jooq.lambda.Unchecked;
import org.jooq.lambda.tuple.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Stream;

import static org.jooq.lambda.tuple.Tuple.tuple;

/**
 * Created by Biel on 13/11/2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class NoteRepositoryTest {
    @Autowired
    NoteRepository noteRepository;
    @Autowired
    AccountRepository accountRepository;
    Account account;

    @Before
    public void setup() throws Exception {
        account = accountRepository.save(new Account("sample_username", "sample_password"));
        noteRepository.deleteAll();
        Seq.of(
                tuple("/Projects/NoteTree", "Finish NoteTree tests"),
                tuple("/Projects", "General note about projects"),
                tuple("/College/Science", "Watch documentary about cells"),
                tuple("/College/Maths", "Problems 3.13 to 3.18"),
                tuple("/College/Maths", "Review unit 3"),
                tuple("/Sport/Running", "Interval training"),
                tuple("/", "Top level note. This note might be unclassified.")
        ).forEach(t -> noteRepository.save(new Note(account, t.v1, t.v2)));
    }

    @Test
    public void findByAccountAndPath() throws Exception{
        noteRepository.findOneByAccountAndPath(account, "/Projects").orElseThrow(() -> new Exception("Not found!"));
        if (noteRepository.findOneByAccountAndPath(account, "/Proj").isPresent())throw new Exception("Found where it shouldn't");
    }
}
