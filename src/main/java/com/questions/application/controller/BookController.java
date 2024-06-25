package com.questions.application.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.questions.application.model.BookRequest;
import com.questions.application.service.BookService;
import com.questions.application.validator.BookValidator;


@RestController
@RequestMapping("/api/v1")
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private BookValidator bookValidator;
	
	@GetMapping("/book/{id}")
	public ResponseEntity<Map<String,Object>> getBooks(@PathVariable Integer id){
		return bookService.getBooks(id);
	}
	
	@PostMapping("/book")
	public ResponseEntity<Map<String,String>> addBooks(@RequestBody BookRequest bookRequest){
		return bookValidator.validateAddRequest(bookRequest);
	}
	
	@PutMapping("/book/{id}")
	public ResponseEntity<Map<String,String>> updateBook(@PathVariable Integer id, @RequestBody BookRequest bookRequest){
		return bookValidator.validateUpdateRequest(id, bookRequest);
	}
	
	@DeleteMapping("/book/{id}")
	public ResponseEntity<Map<String,String>> deleteBooks(@PathVariable Integer id){
		return bookService.deleteBook(id);
	}
}
