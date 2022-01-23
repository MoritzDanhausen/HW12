package de.uni.koeln.se.bookstore.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.uni.koeln.se.bookstore.datamodel.Book;
import de.uni.koeln.se.bookstore.repository.BookRepo;

@Service
public class BookService {
	
	@Autowired
	private BookRepo bookRepo;
	
	public List<Book> findBooks(){
		
		return bookRepo.findAll();
	}
	
	public Optional<Book> fetchBook(int id){
		
		return bookRepo.findById(id);
	}
	
	public Book addBook(Book book) {
		
		return bookRepo.save(book);
	}
	
	public boolean deleteBook (int id) {
		
		boolean status;
		try {
			bookRepo.deleteById(id);
			status=true;
		}catch (Exception e) {
			status=false; 
			}
		return status;
	}

	public int getOldest(List<Book> books) {
		int oldest = books.get(0).getYear();
		int id = books.get(0).getId();
		for (int i = 1; books.size() > i; i++) {
			if (books.get(i).getYear() < oldest) {
				oldest = books.get(i).getYear();
				id = books.get(i).getId();		
			}		
		}
		return id;
	}

	public int getRecent(List<Book> books) {
		int recent = books.get(0).getYear();
		int id = books.get(0).getId();
		for (int i = 1; books.size() > i; i++) {
			if (books.get(i).getYear() > recent) {
				recent = books.get(i).getYear();
				id = books.get(i).getId();		
			}		
		}
		return id;
	}

}
