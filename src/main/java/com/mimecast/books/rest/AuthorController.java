package com.mimecast.books.rest;

import com.mimecast.books.AuthorBooks;
import com.mimecast.books.AuthorBooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@RestController
@RequestMapping("/author")
public class AuthorController {

  @Value("${books.api.url}")
  private String booksUrl;

  @Autowired
  private AuthorBooksRepository authorBooksRepository;

  @PostMapping("{author_name}")
  public ResponseEntity<Void> createAuthor(@PathVariable String author_name) {

    String actualUrl = String.format(booksUrl, author_name);
    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<AuthorBooks> response = restTemplate.getForEntity(actualUrl, AuthorBooks.class);

    if(response.getStatusCode().equals(HttpStatus.OK) && !response.getBody().getResults().isEmpty()){
      AuthorBooks authorBooks = response.getBody();
      authorBooks.setAuthorName(author_name);
      authorBooksRepository.save(authorBooks);
    }

    return ResponseEntity.noContent().build();
  }

  @GetMapping("{author_name}")
  public ResponseEntity<AuthorBooks> getBooks(@PathVariable String author_name) {
    Optional<AuthorBooks> authorBooks = authorBooksRepository.findById(author_name);
    if(authorBooks.isPresent()){
      return ResponseEntity.ok(authorBooks.get());
    }
    return ResponseEntity.noContent().build();
  }
}

