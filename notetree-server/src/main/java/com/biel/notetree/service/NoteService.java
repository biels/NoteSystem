package com.biel.notetree.service;

import com.biel.notetree.model.Note;
import com.biel.notetree.repository.NoteRepository;
import com.biel.notetree.service.exceptions.TaskNotFoundException;
import com.biel.notetree.service.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Biel on 11/11/2016.
 */
@RestController
@RequestMapping("/accounts/{userId}/note")
public class NoteService {
    AccountService accountService;
    NoteRepository noteRepository;

    @Autowired
    public NoteService(AccountService accountService, NoteRepository noteRepository) {
        this.accountService = accountService;
        this.noteRepository = noteRepository;
    }

    @RequestMapping(name = "/", method = RequestMethod.GET)
    public Note get(@PathVariable String userId, @RequestParam String path) throws UserNotFoundException, TaskNotFoundException {
        //return new Note(path, "Sample title", "Sample content by " + userId);
        return noteRepository.findOneByAccountAndPath(accountService.getAccount(userId), path).orElseThrow(() -> new TaskNotFoundException(path));
    }
    @RequestMapping(name = "", method = RequestMethod.POST)
    public ResponseEntity create(@PathVariable String userId, @RequestParam String path){
        return ResponseEntity.ok(HttpEntity.EMPTY);
    }



}
