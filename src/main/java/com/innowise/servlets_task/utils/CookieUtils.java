package com.innowise.servlets_task.utils;

import java.util.Arrays;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieUtils {

  public Cookie updateCookie(Cookie[] cookies, String cookieName, String newValue) {
    if(cookies == null){
      cookies = new Cookie[2];
      cookies[0] = new Cookie("userId", null);
      cookies[1] = new Cookie("userAccessLevel", null);
    }
    Cookie cookieToUpdate = Arrays.stream(cookies)
        .filter(cookie1 -> cookie1.getValue().equals(cookieName))
        .findFirst()
        .orElse(null);
    if (cookieToUpdate != null) {
      cookieToUpdate.setValue(newValue);
    }
    return cookieToUpdate;
  }

}
