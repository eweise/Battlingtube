package com.battlingtube.domain;

import com.bt.domain.Comment;
import com.bt.domain.User;

import java.util.Date;

public class Commenter {

    Commentable commentable;

    public Commenter(Commentable subject) {
        this.commentable = subject;
    }

    private Comment newComment(String text, User creator) {
        Comment comment = new Comment();
        comment.setText(text);
        comment.setCreator(creator);
        comment.setCreated(new Date());
        commentable.getComments().add(comment);
        return comment;
    }

    public Comment replyTo(Comment comment, String text, User creator) {
        Comment reply = newComment(text, creator); 
        comment.getReplies().add(reply);
        return reply;
    }

    public void createComment(String text, User creator) {
        newComment(text, creator);
    }
}
