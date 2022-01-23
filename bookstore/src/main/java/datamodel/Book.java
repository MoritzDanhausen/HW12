package datamodel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String name;
	private String author;
	private Integer year;

	public Book() {}
	
	public Book(String name, String author, Integer year) {
		this.name= name;
		this.author = author;
		this.year = year;
	}
}
