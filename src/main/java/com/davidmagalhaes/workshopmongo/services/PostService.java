package com.davidmagalhaes.workshopmongo.services;

import com.davidmagalhaes.workshopmongo.domain.Post;
import com.davidmagalhaes.workshopmongo.domain.User;
import com.davidmagalhaes.workshopmongo.dto.UserDTO;
import com.davidmagalhaes.workshopmongo.repository.PostRepository;
import com.davidmagalhaes.workshopmongo.repository.UserRepository;
import com.davidmagalhaes.workshopmongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository repo;

    public Post findById(String id){
        Optional<Post> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public List<Post> findByTitle(String text){
        return repo.searchTitle(text);
    }

    public List<Post> fullSearch(String text, Date minDate, Date maxDate) {
        maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
        return repo.fullSearch(text, minDate, maxDate);
    }

}
