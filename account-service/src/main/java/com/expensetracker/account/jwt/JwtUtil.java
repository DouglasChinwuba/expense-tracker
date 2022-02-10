package com.expensetracker.account.jwt;

import com.expensetracker.account.model.Account;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.function.Function;

@Service
public class JwtUtil {

    @Value("${expenseTracker.jwt.secretKey}")
    private String secretKey;

    private String extractUsername(String jwtToken){
        return extractClaim(jwtToken, Claims::getSubject);
    }

    private Date extractExpirationDate(String jwtToken){
        return extractClaim(jwtToken, Claims::getExpiration);
    }

    private boolean isTokenExpired(String jwtToken){
       return extractExpirationDate(jwtToken).before(new Date());
    }

    private <T> T extractClaim(String jwtToken, Function<Claims, T> claimResolver) {
        Claims claims = getAllClaims(jwtToken);
        return claimResolver.apply(claims);
    }

    private Claims getAllClaims(String jwtToken){
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken).getBody();
    }

    public boolean validateJwt(String token, Account account){
        String username = extractUsername(token);
        return (username.equals(account.getName()) && !isTokenExpired(token));
    }
}
