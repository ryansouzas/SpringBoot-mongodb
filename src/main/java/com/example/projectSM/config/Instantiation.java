package com.example.projectSM.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.example.projectSM.domain.Post;
import com.example.projectSM.domain.User;
import com.example.projectSM.dto.AuthorDTO;
import com.example.projectSM.dto.CommentDTO;
import com.example.projectSM.repository.PostRepository;
import com.example.projectSM.repository.UserRepository;
import com.example.projectSM.resources.util.URL;

@Configuration
public class Instantiation implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex ,bob));
		
		Post post1 = new Post(null, URL.toInstant("21/03/2028"), "Partiu Viagem", "Vou viajar para São Paulo, Abraços", new AuthorDTO (maria));
		Post post2 = new Post(null, URL.toInstant("22/03/2028"), "Bom dia", "Acordei feliz Hoje!", new AuthorDTO(maria));
			
		CommentDTO comment1 = new CommentDTO("Boa viagem mano!", URL.toInstant("21/03/2028"), new AuthorDTO(alex));
		CommentDTO comment2 = new CommentDTO("Aproveite!", URL.toInstant("21/03/2028"), new AuthorDTO(bob));
		CommentDTO comment3 = new CommentDTO("Tenha um ótimo dia!", URL.toInstant("22/03/2028"), new AuthorDTO(alex));
		
		post1.getComments().addAll(Arrays.asList(comment1,comment2) );
		post2.getComments().addAll(Arrays.asList(comment3));
		
		postRepository.saveAll(Arrays.asList(post1,post2));
		
		maria.getPosts().addAll(Arrays.asList(post1,post2));
		userRepository.save(maria);
	}

}
