package com.codegym.airbnb.controller;

import com.codegym.airbnb.model.Comment;
import com.codegym.airbnb.model.User;
import com.codegym.airbnb.service.comment.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private ICommentService commentService;

    @GetMapping("apartment/{id}")
    public ResponseEntity<Iterable<Comment>> getAllByApartmentID(@PathVariable Long id ){
        return new ResponseEntity<>(commentService.findByApartmentID(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Comment> deleteComment(@PathVariable Long id){
        Optional<Comment> productOptional = commentService.findById(id);
        return productOptional.map(product -> {
            commentService.delete(id);
            return new ResponseEntity<Comment>(HttpStatus.NO_CONTENT);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
