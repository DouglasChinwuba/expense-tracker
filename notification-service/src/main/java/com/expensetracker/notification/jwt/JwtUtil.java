package com.expensetracker.notification.jwt;

import com.expensetracker.notification.model.Account;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.function.Function;
import java.util.stream.Collectors;

public class JwtUtil {
    private static Logger logger = LoggerFactory.getLogger(JwtUtil.class);


    @Value("${expenseTracker.jwt.secretKey}")
    private String secretKey;

    public String extractUsername(String jwtToken){
        return extractClaim(jwtToken, Claims::getSubject);
    }

    private Date extractExpirationDate(String jwtToken){
        return extractClaim(jwtToken, Claims::getExpiration);
    }

    public int getId(String jwtToken){
        return (int) getAllClaims(jwtToken).get("id");
    }

    public Collection<? extends GrantedAuthority> getAuthorities(String jwtToken){
        return Arrays.asList(getAllClaims(jwtToken).get("role").toString().split(",")).stream()
                .map(authority -> new SimpleGrantedAuthority(authority))
                .collect(Collectors.toList());
    }

    private boolean isTokenExpired(String jwtToken){
        return extractExpirationDate(jwtToken).before(new Date());
    }
    private <T> T extractClaim(String jwtToken, Function<Claims, T> claimResolver) {
        Claims claims = getAllClaims(jwtToken);
        return claimResolver.apply(claims);
    }

    public Claims getAllClaims(String jwtToken){
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken).getBody();
    }

    public boolean validateJwt(String token, Account account){
        logger.info("Validating Jwt");
        String username = extractUsername(token);
        return (username.equals(account.getName()) && !isTokenExpired(token));
    }
}
