package com.mimecast.books;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthorBooksRepository extends MongoRepository<AuthorBooks, String> {
}
