package tn.com.shinra.session.models;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//@Data
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
@Entity
@Table(name="AUTHOR_TBL")
public class AuthorModel  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;
	@Column(name = "adress")
	private String adress;
	@Column(name = "phonenumber")
	private Integer phonenumber;


	@ManyToMany(fetch = FetchType.LAZY,
			cascade = {
					CascadeType.PERSIST,
					CascadeType.MERGE
			})
	@JoinTable(name = "author_books",
			joinColumns = { @JoinColumn(name = "author_id") },
			inverseJoinColumns = { @JoinColumn(name = "book_id") })
	private Set<Book> books = new HashSet<>();

	public AuthorModel() {
	}

	public AuthorModel(Long id, String name, String adress, Integer phonenumber, Set<Book> books) {
		this.id = id;
		this.name = name;
		this.adress = adress;
		this.phonenumber = phonenumber;
		this.books = books;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public Integer getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(Integer phonenumber) {
		this.phonenumber = phonenumber;
	}

	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}

	public void addBook(Book book) {
		this.books.add(book);
		book.getAuthors().add(this);
	}

	public void removeBook(long bookId) {
		Book tag = this.books.stream().filter(t -> t.getId() == bookId).findFirst().orElse(null);
		if (tag != null) {
			this.books.remove(tag);
			tag.getAuthors().remove(this);
		}
	}
	@Override
	public String toString() {
		return "AuthorModel [id=" + id + ", name=" + name + ", adress=" + adress + ", phonenumber=" + phonenumber + "]";
	}
//	@ManyToMany(fetch = FetchType.EAGER)
//	@JoinTable(name = "author_book",
//	joinColumns = @JoinColumn(name = "author_id"),inverseJoinColumns = @JoinColumn(name = "book_id"))
//	Set<Book> books = new HashSet<>();

//	@ManyToMany(cascade = CascadeType.ALL)
//	@JoinTable( name = "Author_Book",
//			joinColumns = @JoinColumn( name = "idAuthor",referencedColumnName = "id" ),
//			inverseJoinColumns = @JoinColumn( name = "idBook",referencedColumnName = "id" ) )
//	@JsonManagedReference
//	private Set<Book> books;

}
