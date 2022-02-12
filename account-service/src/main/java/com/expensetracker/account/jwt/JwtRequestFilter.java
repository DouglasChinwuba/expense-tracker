package com.expensetracker.account.jwt;

import com.expensetracker.account.model.Account;
import com.expensetracker.account.model.User;
import com.expensetracker.account.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
            logger.info(request.toString());
            ObjectMapper mapper = new ObjectMapper();
//            mapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
            User user = mapper.readValue(request.toString(), User.class);

//            User user = new User();
//            user.setId(1);
//            user.setUsername("douglas_jnr");
//            user.setRoles(Arrays.asList("ROLE_USER"));
            logger.info("Unmarshall user {}", user.toString());

            if(Objects.nonNull(jwtToken)){
                String username = jwtUtil.extractUsername(jwtToken);

                logger.info("Getting account by name");
                Account account = accountService.findByName(username);

                Object b = SecurityContextHolder.getContext().getAuthentication();
                logger.info(b.toString());

                if (jwtUtil.validateJwt(jwtToken, account) &&
                        SecurityContextHolder.getContext().getAuthentication() == null){

//                    createAuthentication(jwtToken, user).ifPresent( authentication -> {
//                        SecurityContextHolder.getContext().setAuthentication(authentication);
//                    });

                    Optional<UsernamePasswordAuthenticationToken> optional = createAuthentication(jwtToken, user);

                    if(optional.isPresent()){
                        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = optional.get();
//                        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                        logger.info("Setting SecurityContextHolder");
                    }else{
                        logger.info("Error setting SecurityContextHolder");
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Error setting authentication",e);
        }

        filterChain.doFilter(request, response);
    }

    public Optional<UsernamePasswordAuthenticationToken> createAuthentication(String token, User user){

        Collection<? extends GrantedAuthority> authorities = user.getRoles().stream()
//                                                                    .map(SimpleGrantedAuthority::new)
                                                                    .map(role -> new SimpleGrantedAuthority(role.getName().toString()))
                                                                    .collect(Collectors.toList());
        logger.info("Creating UsernamePasswordAuthenticationToken");
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(user, token, authorities);

        if(usernamePasswordAuthenticationToken != null){
            logger.info( usernamePasswordAuthenticationToken.toString());
        }

        return Optional.of(usernamePasswordAuthenticationToken);
    }

    private String getJwtFromAuthHeader(HttpServletRequest request){
        String authorizationHeader = request.getHeader("Authorization");

        if(Objects.nonNull(authorizationHeader) && authorizationHeader.startsWith("Bearer ")){
            return authorizationHeader.substring(7);
        }

        return null;
    }
}
