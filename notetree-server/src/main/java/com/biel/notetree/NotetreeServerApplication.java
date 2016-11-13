package com.biel.notetree;

import com.biel.notetree.model.Account;
import com.biel.notetree.model.Note;
import com.biel.notetree.repository.AccountRepository;
import com.biel.notetree.repository.NoteRepository;
import org.jooq.lambda.Seq;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

import static org.jooq.lambda.tuple.Tuple.tuple;

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
            Seq.of(
                    tuple("/Projects/NoteTree", "Finish NoteTree tests"),
                    tuple("/Projects", "General note about projects"),
                    tuple("/College/Science", "Watch documentary about cells"),
                    tuple("/College/Maths", "Problems 3.13 to 3.18"),
                    tuple("/College/Maths", "Review unit 3"),
                    tuple("/Sport/Running", "Interval training"),
                    tuple("/", "Top level note. This note might be unclassified.")
            ).forEach(t -> noteRepository.save(new Note(accountBiel, t.v1, t.v2)));
		};

	}
}
