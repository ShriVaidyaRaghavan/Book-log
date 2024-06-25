package com.questions.application.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.questions.application.model.BookEntity;
import com.questions.application.model.BookRequest;
import com.questions.application.repository.BookRepository;

@Service
public class BookService {
	
	@Autowired
	BookRepository bookRepo;
	
	public ResponseEntity<Map<String,Object>> getBooks(Integer id) {
		try {
			return new ResponseEntity<Map<String,Object>>(getBookFromRepo(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Map<String,Object>>(Map.of("error", e),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public Map<String, Object> getBookFromRepo(Integer id) {
		if(id != null && !bookRepo.findById(id).isEmpty()) {
			return Map.of("books", bookRepo.findById(id).get());
		}
		return Map.of("books", bookRepo.findAll());
	}

	public ResponseEntity<Map<String, String>> deleteBook(Integer id) {
		Map<String, String> error = new HashMap<>();
		error.put("error", "Invalid request");
		if(id != null) {
			if(bookRepo.findById(id).isEmpty()) {
				return new ResponseEntity<Map<String, String>>(error, HttpStatus.BAD_REQUEST);
			}
			bookRepo.deleteById(id);
			return new ResponseEntity<Map<String, String>>(Map.of("book", "successfully deleted"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Map<String, String>>(error, HttpStatus.BAD_REQUEST);
	}

	public Map<String, String> addBook(BookRequest bookRequest) {
		BookEntity book = new BookEntity();
		book.setName(bookRequest.getName());
		book.setSummary(bookRequest.getSummary());
		return Map.of("added book",bookRepo.save(book).toString());
	}

	public Map<String, String> updateBook(Integer id, BookRequest bookRequest) {
		BookEntity book = bookRepo.findById(id).get();
		book.setSummary(bookRequest.getSummary());
		return Map.of("updated book",bookRepo.save(book).toString());
	}

}
