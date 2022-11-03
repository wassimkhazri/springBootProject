package tn.com.shinra.session.services;

import java.util.List;
import java.util.Optional;


import org.springframework.stereotype.Repository;
import tn.com.shinra.session.models.AuthorModel;
import tn.com.shinra.session.models.Book;
import tn.com.shinra.session.models.Product;

public interface AuthorService {

	AuthorModel add_author(AuthorModel authorModel);
	Optional <AuthorModel> find_author(long d);
	AuthorModel  update_author(AuthorModel d);

	AuthorModel updateAuthor(AuthorModel authorModel);


	List<AuthorModel> getAuthors();

	public AuthorModel getAuthorById(long id);

	String deleteAuthor(long id);

	public AuthorModel getAuthorByName(String name);


}
