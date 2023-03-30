package com.innowise.servlets_task.utils;

import com.innowise.servlets_task.dto.LoginDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

public class JWTSecurityUtils {

  private static JWTSecurityUtils securityInstance;
  private final String SECRET = "7b230Hfm36Be4Nf78fb0ml83Nfi4823hIUf764NFb8g93fgb318Gfjw0yf72";
  private final long EXP_TIME_MILLIS = 1800000L;

  private JWTSecurityUtils() {
  }

  public static JWTSecurityUtils getInstance() {
    JWTSecurityUtils instance = securityInstance;
    if (instance == null) {
      synchronized (JWTSecurityUtils.class) {
        instance = securityInstance;
        if (instance == null) {
          securityInstance = new JWTSecurityUtils();
          instance = securityInstance;
        }
      }
    }
    return instance;
  }

  public String generateUserToken(LoginDTO userLogin) {
    Date dateOfIssue = new Date();
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(dateOfIssue);
    calendar.add(Calendar.MINUTE, 30);

    return Jwts.builder()
        .setIssuer("A-Corporation")
        .claim("userId", userLogin.getUserId())
        .claim("userAccessLevel", userLogin.getRank().ordinal() + 1)
        .setIssuedAt(Date.from(Instant.ofEpochSecond(System.currentTimeMillis())))
        .setExpiration(Date.from(Instant.ofEpochSecond(System.currentTimeMillis() + EXP_TIME_MILLIS)))
        .signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET)))
        .compact();
  }

  public Jws<Claims> verifyUserToken(String jwsString) throws JwtException {
    return Jwts.parserBuilder()
        .setSigningKey((Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET))))
        .build()
        .parseClaimsJws(jwsString);
  }


}
