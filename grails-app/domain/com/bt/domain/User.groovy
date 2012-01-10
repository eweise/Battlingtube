package com.bt.domain

import javax.persistence.Transient
import com.battlingtube.util.AppUtils

class User {

  boolean guest = false
  boolean admin = false
  Date createTime = AppUtils.currentDate

  UserProfile profile

  static def constraints = {
      profile(nullable: true)
  }

  static transients = ['username']


  public String getUsername(){
    return getProfile() == null ? null : profile.username
  }

  public static User findUserByCredentials(def username, def password) {
    String query = "from com.bt.domain.User u where u.profile.username = :username and u.profile.password = :password"
    def results = User.executeQuery(query, [username: username, password: password])
    return results.isEmpty() ? null : results.get(0)
  }
}
