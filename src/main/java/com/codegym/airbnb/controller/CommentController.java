package com.codegym.airbnb.controller;

import com.codegym.airbnb.model.Comment;
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

    @GetMapping("/apartments/{id}")
    public ResponseEntity<Iterable<Comment>> getAllByApartmentID(@PathVariable Long id ){
        return new ResponseEntity<>(commentService.findByApartmentID(id), HttpStatus.OK);
    }

    @PostMapping ("/apartments")
    public ResponseEntity<Comment> getAllByApartmentID(@RequestBody Comment comment){
        return new ResponseEntity<>(commentService.save(comment), HttpStatus.CREATED);
    }
}
