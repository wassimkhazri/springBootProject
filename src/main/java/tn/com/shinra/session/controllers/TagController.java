package tn.com.shinra.session.controllers;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.com.shinra.session.models.Author;
import tn.com.shinra.session.models.Tag;
import tn.com.shinra.session.repositories.AuthRepository;
import tn.com.shinra.session.repositories.TagRepository;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class TagController {
    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private TagRepository tagRepository;

    @GetMapping("/tags")
    public ResponseEntity<List<Tag>> getAllTags() {
        List<Tag> tags = new ArrayList<Tag>();

        tagRepository.findAll().forEach(tags::add);

        if (tags.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(tags, HttpStatus.OK);
    }

    @GetMapping("/authors/{authorId}/tags")
    public ResponseEntity<List<Tag>> getAllTagsByAuthorId(@PathVariable(value = "authorId") Long authorId) {
        if (!authRepository.existsById(authorId)) {
            throw new ResourceNotFoundException("Not found Author with id = " + authorId);
        }

        List<Tag> tags = tagRepository.findTagsByAuthorsId(authorId);
        return new ResponseEntity<>(tags, HttpStatus.OK);
    }

    @GetMapping("/tags/{id}")
    public ResponseEntity<Tag> getTagsById(@PathVariable(value = "id") Long id) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Tag with id = " + id));

        return new ResponseEntity<>(tag, HttpStatus.OK);
    }

    @GetMapping("/tags/{tagId}/authors")
    public ResponseEntity<List<Author>> getAllAuthorsByTagId(@PathVariable(value = "tagId") Long tagId) {
        if (!tagRepository.existsById(tagId)) {
            throw new ResourceNotFoundException("Not found Tag  with id = " + tagId);
        }

        List<Author> authors = authRepository.findAuthorsByTagsId(tagId);
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }

    @PostMapping("/authors/{authorId}/tags")
    public ResponseEntity<Tag> addTag(@PathVariable(value = "authorId") Long authorId, @RequestBody Tag tagRequest) {
        Tag tag = authRepository.findById(authorId).map(author -> {
            long tagId = tagRequest.getId();

            // tag is existed
            if (tagId != 0L) {
                Tag _tag = tagRepository.findById(tagId)
                        .orElseThrow(() -> new ResourceNotFoundException("Not found Tag with id = " + tagId));
                author.addTag(_tag);
                authRepository.save(author);
                return _tag;
            }

            // add and create new Tag
            author.addTag(tagRequest);
            return tagRepository.save(tagRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("Not found Author with id = " + authorId));

        return new ResponseEntity<>(tag, HttpStatus.CREATED);
    }

    @PutMapping("/tags/{id}")
    public ResponseEntity<Tag> updateTag(@PathVariable("id") long id, @RequestBody Tag tagRequest) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TagId " + id + "not found"));

        tag.setName(tagRequest.getName());

        return new ResponseEntity<>(tagRepository.save(tag), HttpStatus.OK);
    }

    @DeleteMapping("/authors/{authorId}/tags/{tagId}")
    public ResponseEntity<HttpStatus> deleteTagFromTutorial(@PathVariable(value = "authorId") Long authorId, @PathVariable(value = "tagId") Long tagId) {
        Author author = authRepository.findById(authorId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Author with id = " + authorId));

        author.removeTag(tagId);
        authRepository.save(author);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/tags/{id}")
    public ResponseEntity<HttpStatus> deleteTag(@PathVariable("id") long id) {
        tagRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
