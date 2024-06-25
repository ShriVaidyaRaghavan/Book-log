package com.questions.application.validator;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.questions.application.model.BookRequest;
import com.questions.application.service.BookService;

@Component
public class BookValidator {
	
	@Autowired
	private BookService bookService;
	
	
	public ResponseEntity<Map<String, String>> validateAddRequest(final BookRequest bookRequest) {

		Map<String, String> error = new HashMap<>();

		if (StringUtils.isEmpty(bookRequest.getName()) || Objects.isNull(bookRequest.getSummary())) {
			error.put("error", "Invalid request");
			return new ResponseEntity<Map<String, String>>(error, HttpStatus.BAD_REQUEST);
		}

		try {
			return new ResponseEntity<Map<String, String>>(bookService.addBook(bookRequest), HttpStatus.OK);
		} catch (Exception e) {
			error.put("error", e.getMessage());
			return new ResponseEntity<Map<String, String>>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<Map<String, String>> validateUpdateRequest(final Integer id, final BookRequest bookRequest) {

		Map<String, String> error = new HashMap<>();

		if (id == null || Objects.isNull(bookRequest.getSummary())) {
			error.put("error", "Invalid request");
			return new ResponseEntity<Map<String, String>>(error, HttpStatus.BAD_REQUEST);
		}

		try {
			return new ResponseEntity<Map<String, String>>(bookService.updateBook(id, bookRequest), HttpStatus.OK);
		} catch (Exception e) {
			error.put("error", e.getMessage());
			return new ResponseEntity<Map<String, String>>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
