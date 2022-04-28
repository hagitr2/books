package com.mimecast.books;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthorBooks {

  @Id
  private String authorName;
  private List<Book> results;

  public String getAuthorName() {
    return authorName;
  }

  public void setAuthorName(String authorName) {
    this.authorName = authorName;
  }

  public List<Book> getResults() {
    return results;
  }

  public void setResults(List<Book> results) {
    this.results = results;
  }
}
