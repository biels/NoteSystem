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
@RequestMapping("/account/{userId}/task")
public class TaskService {
    AccountService accountService;
    NoteRepository noteRepository;

    @Autowired
    public TaskService(AccountService accountService, NoteRepository noteRepository) {
        this.accountService = accountService;
        this.noteRepository = noteRepository;
    }

    @RequestMapping(name = "", method = RequestMethod.GET)
    public Note get(@PathVariable String userId, @RequestParam String path) throws UserNotFoundException, TaskNotFoundException {
        return noteRepository.findOneByAccountAndPath(accountService.getAccount(userId), path).orElseThrow(() -> new TaskNotFoundException(path));
    }
    @RequestMapping(name = "", method = RequestMethod.POST)
    ResponseEntity create(@PathVariable String userId, @RequestParam String path){
        return ResponseEntity.ok(HttpEntity.EMPTY);
    }



}
