package com.appcent.todoist.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;


import java.util.Date;

@Component
public class JwtTokenGenerator {

    @Value("${appcent.jwt.security.app.key}")
    private String APP_KEY;

    @Value("${appcent.jwt.security.expire.time}")
    private Long EXPIRE_TIME;

    public String generateJwtToken(Authentication authentication){

        JwtUserDetails jwtUserDetails = (JwtUserDetails) authentication.getPrincipal();
        Date expireDate = new Date(new Date().getTime() + EXPIRE_TIME);

        return Jwts.builder()
                .setSubject(Long.toString(jwtUserDetails.getId()))
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, APP_KEY)
                .compact();
    }

    public Long findUserIdByToken(String token){

        Jws<Claims> claimsJws = parseToken(token);

        String userIdStr = claimsJws
                .getBody()
                .getSubject();

        return Long.parseLong(userIdStr);
    }

    private Jws<Claims> parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(APP_KEY)
                .parseClaimsJws(token);
    }

    public boolean validateToken(String token){

        boolean isValid;

        try {
            Jws<Claims> claimsJws = parseToken(token);

            isValid = !isTokenExpired(claimsJws);
        } catch (Exception e){
            isValid = false;
        }

        return isValid;
    }

    private boolean isTokenExpired(Jws<Claims> claimsJws) {

        Date expirationDate = claimsJws.getBody().getExpiration();

        return expirationDate.before(new Date());
    }
}
