package com.bt.domain

class UserProfile {

  String username
  String password
  String password2
  String email
  
  String toString() { "${username}" }

   static belongsTo = User


  public static UserProfile findByCredentials(String username, String password) {
      return findWhere(username: username, password: password)
  }

  static transients = [ "password2" ]

  static def constraints =
  {
      username(blank: false, unique: true)
      email(blank: false, email: true)
      password(blank: false, password: true)
  }


  public int countVotedBattles() {

  }
    
}
