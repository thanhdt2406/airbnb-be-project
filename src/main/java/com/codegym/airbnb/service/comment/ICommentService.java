package com.codegym.airbnb.service.comment;

import com.codegym.airbnb.model.Comment;
import com.codegym.airbnb.service.IGeneralService;

public interface ICommentService extends IGeneralService<Comment> {
    Iterable<Comment> findByApartmentID(Long id);
}
