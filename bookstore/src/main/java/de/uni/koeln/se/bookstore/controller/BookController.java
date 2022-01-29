package de.uni.koeln.se.bookstore.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.uni.koeln.se.bookstore.datamodel.Book;
import de.uni.koeln.se.bookstore.service.BookService;

@RequestMapping("/bookStore")
@RestController
@CrossOrigin(origins="http:localhost:4200")
public class BookController {
	
	@Autowired
	BookService BookServ;
	
	@GetMapping
	public ResponseEntity<List<Book>> getAllbooks(){
		
		List<Book> books = new ArrayList<Book> ();
		books=BookServ.findBooks();
		
		return new ResponseEntity<>(books,HttpStatus.OK);
	}
	
	@GetMapping("/oldest")
	public ResponseEntity<Book> getOldestBook(){
		
		List<Book> books = new ArrayList<Book> ();
		books=BookServ.findBooks();
		
		int oldest = BookServ.getOldest(books);
		
		return new ResponseEntity<>(BookServ.fetchBook(oldest).get(),HttpStatus.OK);
	}
	
	@GetMapping("/recent")
	public ResponseEntity<Book> getRecentBook(){
		
		List<Book> books = new ArrayList<Book> ();
		books=BookServ.findBooks();
		
		int recent = BookServ.getRecent(books);
		
		return new ResponseEntity<>(BookServ.fetchBook(recent).get(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable int id){
		
		return new ResponseEntity<>(BookServ.fetchBook(id).get(),HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Book> addBook(@RequestBody Book book){
		
		BookServ.addBook(book);
		
		return new ResponseEntity<>(book,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Book> removeBookById(@PathVariable int id){
		
		Book book = BookServ.fetchBook(id).get();
		
		if (BookServ.deleteBook(id)) {
			return new ResponseEntity<>(book,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(book,HttpStatus.BAD_REQUEST);			
		}
	}
	

}
