package com.battlingtube.domain;

import com.bt.domain.Comment;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: eweise
 * Date: Nov 9, 2009
 * Time: 7:32:21 AM
 * To change this template use File | Settings | File Templates.
 */
public interface Commentable {
    
    Commenter commenter();

    List<Comment> getComments();
}
