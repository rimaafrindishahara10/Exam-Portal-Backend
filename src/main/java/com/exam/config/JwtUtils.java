package com.exam.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtils {

    private   String SECRET = "";

    public JwtUtils() {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance ( "HmacSHA256" );
            SecretKey secretKey = keyGen.generateKey ();
            SECRET = Base64.getEncoder ().encodeToString ( secretKey.getEncoded () );
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace ();
        }


    }

    public String generateToken(UserDetails userDetails) {
        final String userName = userDetails.getUsername ();
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .claims()
                .add (claims)
                .subject(userName)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+ 60 * 60 * 30))
                .and()
                .signWith(getKey())
                .compact ();
    }

    private SecretKey getKey() {
        byte[] bytes = Decoders.BASE64.decode (SECRET);
        return  Keys.hmacShaKeyFor (bytes);
    }


    public String extractUsername(String jwtToken) {

      return extractClaim(jwtToken, Claims::getSubject );
    }

    private <T> T extractClaim(String jwtToken, Function<Claims, T> claimsResolver) {
       final Claims claims= extractAllClaim(jwtToken);
       return claimsResolver.apply (claims);
    }

    private Claims extractAllClaim(String jwtToken) {
        return Jwts.parser()
                .verifyWith (getKey())
                .build ()
                .parseSignedClaims ( jwtToken )
                .getPayload ();
    }

    public boolean validateToken(String jwtToken, UserDetails userDetails) {
        final String  userName = extractUsername(jwtToken);
        return  (userName.equals ( userDetails.getUsername ()) && !isTokenExpired(jwtToken));
    }

    private boolean isTokenExpired(String jwtToken) {
        return  extractExpiration(jwtToken).before(new Date ());
    }

    private Date extractExpiration(String jwtToken) {
        return extractClaim (jwtToken,Claims::getExpiration);
    }
}
