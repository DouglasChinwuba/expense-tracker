package com.expensetracker.account.jwt;

import com.expensetracker.account.model.Account;
import com.expensetracker.account.model.User;
import com.expensetracker.account.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private static Logger logger = LoggerFactory.getLogger(JwtRequestFilter.class);

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AccountService accountService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        try {
            String jwtToken = getJwtFromAuthHeader(request);
            ObjectMapper mapper = new ObjectMapper();
            User user = mapper.readValue(request.getInputStream(), User.class);

            if(Objects.nonNull(jwtToken)){
                String username = jwtUtil.extractUsername(jwtToken);
                Account account = accountService.findByName(username);

                if (jwtUtil.validateJwt(jwtToken, account) &&
                        SecurityContextHolder.getContext().getAuthentication() == null){

                    createAuthentication(jwtToken, user).ifPresent( authentication -> {
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    });
                }
            }
        } catch (Exception e) {
            logger.error("Error setting authentication: {}", e);
        }

        filterChain.doFilter(request, response);
    }

    public Optional<Authentication> createAuthentication(String token, User user){

        Collection<? extends GrantedAuthority> authorities = user.getRoles().stream()
                                                                    .map(SimpleGrantedAuthority::new)
                                                                    .collect(Collectors.toList());

        return Optional.of(new UsernamePasswordAuthenticationToken(user,null, authorities));
    }

    private String getJwtFromAuthHeader(HttpServletRequest request){
        String authorizationHeader = request.getHeader("Authorization");

        if(Objects.nonNull(authorizationHeader) && authorizationHeader.startsWith("Bearer ")){
            return authorizationHeader.substring(7);
        }

        return null;
    }
}
