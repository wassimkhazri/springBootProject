package tn.com.shinra.session.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.com.shinra.session.models.AuthorModel;
import tn.com.shinra.session.models.Book;
import tn.com.shinra.session.repositories.AuthorRepository;
import tn.com.shinra.session.services.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService{
	@Autowired
    private AuthorRepository agent;

	@Override
	public AuthorModel add_author(AuthorModel authorModel) {
		return agent.save(authorModel);

		// return "author added successfully";
	}

	@Override
	public Optional<AuthorModel> find_author(long d) {
		return Optional.empty();
	}

//	@Override
//	public Optional<AuthorModel> find_author(long d) {
//		return Optional.ofNullable(agent.findById(d));
//	}


	@Override
	public AuthorModel update_author(AuthorModel d) {
		return agent.save(d);
	}

	@Override
	public List<AuthorModel> getAuthors() {
		return agent.findAll();
	}

	@Override
	public AuthorModel getAuthorById(long id) {
		return null;
	}

//	@Override
//	public AuthorModel getAuthorById(long id) {
//		return agent.findById(id);
//	}

	@Override
	public String deleteAuthor(long id) {

			agent.deleteById(id);
			return "author removed !! " + id;
		}

	@Override
	public AuthorModel getAuthorByName(String name) {
		return agent.findByName(name);
	}
	@Override
	public AuthorModel updateAuthor(AuthorModel authorModel) {
		return agent.save(authorModel);
	}

//	public AuthorModel updateAuthor(AuthorModel authorModel) {
//		AuthorModel existingAuthor = agent.findById(authorModel.getId()).orElse(null);
//		existingAuthor.setName(authorModel.getName());
//		existingAuthor.setAdress(authorModel.getAdress());
//		existingAuthor.setPhonenumber(authorModel.getPhonenumber());
//		//existingAuthor.setBooks(authorModel.getBooks());
//		return agent.save(existingAuthor);
//	}

}
