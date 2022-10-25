package br.univille.dsi2022.security;

import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JWTUtil {
    /*não fazer isso no mundo real */
    private String SECRET_KEY = "78-UTGE9JD342AESasf";

    public boolean validateToken(String token,
                                UserDetails userDetails){
        String userName = extractUserName(token);
        return ((userName.equals(userDetails.getUsername())) 
                && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    private String extractUserName(String token){
        return extractClaim(token, Claims::getSubject);
    }
    
    private <T> T extractClaim(String token, 
                Function<Claims,T> claimsResolver){
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }


    private Claims extractAllClaims(String token){
        return Jwts.parser()
            .setSigningKey(SECRET_KEY)
            .parseClaimsJws(token).getBody();
    }

    public String 
        createToken(Map<String,Object> claims,
                    String subject){
        return Jwts.builder().setClaims(claims)
                    .setSubject(subject)
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(
                        new Date(System.currentTimeMillis() 
                            + 1000 * 60 * 60 * 10))
                    .signWith(SignatureAlgorithm.HS256
                    , SECRET_KEY).compact();
    }
}
