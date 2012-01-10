package com.battlingtube.util

import com.bt.domain.User
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpSession

class ControllerHelper {

  public static final int SECONDS_PER_YEAR = 60*60*24*365;

  static def findUser(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
    User user = session.user
    if (!user) {

      // look for user in cookie
      Cookie[] cookies = request.getCookies()
      cookies.each {Cookie cookie ->
        if (cookie.getName() == "key") {
          String idString = cookie.getValue()
          user = User.get(idString)
          user?.getProfile()?.getUsername()
        }
      }

      if (!user) {
        user = new User("guest": true)
        user.save(flush: true)
        def errors = user.errors;
        def cookie = new Cookie("key", user.id.toString())
        cookie.setPath "/"
        cookie.setMaxAge SECONDS_PER_YEAR 
        response.addCookie(cookie)
      }
      session.user = user;
    }
    return user
  }
}

