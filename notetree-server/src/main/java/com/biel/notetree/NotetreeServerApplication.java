package com.biel.notetree;

import com.biel.notetree.model.Account;
import com.biel.notetree.model.Note;
import com.biel.notetree.repository.AccountRepository;
import com.biel.notetree.repository.NoteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class NotetreeServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotetreeServerApplication.class, args);
	}

	@Bean
	CommandLineRunner init(AccountRepository accountRepository, NoteRepository noteRepository){
		return args -> {
			Account accountBiel = new Account("biel", "1234");
			accountRepository.save(accountBiel);
			Stream.of(new Note("life/study/subj1", "", "Problems 3.13 to 3.18"),
					new Note("life/study/subj1", "", "Review presentation"),
					new Note("life/sport/running", "Interval training","")
			).forEach(t -> noteRepository.save(t));
		};

	}
}
