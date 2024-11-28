package com.exam.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtils {
    private SecretKey getSignInKey(){
        String SECRET = "Exam-Portal";
        byte[] keyBytes = Decoders.BASE64.decode ( SECRET);
        return Keys.hmacShaKeyFor ( keyBytes );

    }

    private Claims getClaimsFromToken(String token){
        return Jwts.parser ().setSigningKey ( getSignInKey () ).build ().parseClaimsJwt ( token ).getBody ();
    }

    private <T> T extractClaims(String token , Function<Claims, T> claimsResolver){
        final Claims claimsFromToken = getClaimsFromToken ( token );
        return claimsResolver.apply ( claimsFromToken );

    }

    public String getUsernameFromToken(String token) {
        Claims claims = getClaimsFromToken(token);  // Get claims from token
        return claims.getSubject();  // Return the username (subject)
    }

    // Method to check if the token has expired
    private boolean isTokenExpired(String token) {
        Date expirationDate = getExpirationDateFromToken(token);  // Extract expiration date
        return expirationDate.before(new Date());  // Check if the current time is after expiration
    }

    public boolean validateToken(String token, UserDetails username) {
        String tokenUsername = getUsernameFromToken(token);  // Extract username from the token
        return (username.equals(tokenUsername) && !isTokenExpired(token));  // Check if the username matches and token is not expired
    }

    private Date getExpirationDateFromToken(String token) {
        Claims claims = getClaimsFromToken(token);  // Get claims from token
        return claims.getExpiration();  // Return the expiration date
    }


    // Method to generate a token with specific expiration time (Optional, if you need custom expiration)
    private String generateToken(Map<String, Object> extractClaims , UserDetails userDetails) {
        return Jwts.builder ().setClaims ( extractClaims ).setSubject ( userDetails.getUsername ())
                .setIssuedAt ( new Date (System.currentTimeMillis ()) )
                .setExpiration ( new Date (System.currentTimeMillis ()+ 1000 * 60 * 60 * 24))
                .signWith ( getSignInKey (),SignatureAlgorithm.HS256 ).compact ();
    }
    public  String generateToken(UserDetails userDetails){
        return generateToken ( new HashMap<> (),userDetails );

    }



}


