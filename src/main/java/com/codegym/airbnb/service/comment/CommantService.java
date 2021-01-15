package com.codegym.airbnb.service.comment;

import com.codegym.airbnb.model.Comment;
import com.codegym.airbnb.repository.ICommandRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommantService implements ICommentService{
    @Autowired
    private ICommandRepo commandRepo;

    @Override
    public Iterable<Comment> findAll() {
        return commandRepo.findAll();
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return commandRepo.findById(id);
    }

    @Override
    public Comment save(Comment comment) {
        return commandRepo.save(comment);
    }

    @Override
    public void delete(Long id) {
        commandRepo.deleteById(id);
    }

    @Override
    public Iterable<Comment> findByApartmentID(Long id) {
        return commandRepo.findAllByApartment_Id(id);
    }
}
