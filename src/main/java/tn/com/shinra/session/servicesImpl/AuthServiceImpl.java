package tn.com.shinra.session.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.com.shinra.session.models.Author;

import tn.com.shinra.session.repositories.AuthRepository;
import tn.com.shinra.session.repositories.AuthorRepository;
import tn.com.shinra.session.services.AuthService;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AuthRepository agent;

    @Override
    public Author add_author(Author author) {
        return agent.save(author);

        // return "author added successfully";
    }
}
