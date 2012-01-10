package com.bt.domain

class Comment {

    String text;
    Date created;
    List<Comment> replies = new ArrayList<Comment>();
    User creator;

  static constraints = {
    text(nullable: false)
    creator(nullable:false)
  }

  static hasMany = [replies: Comment]


  public static Comment findById(Long id) {
    return this.get(id) 
  }

}
