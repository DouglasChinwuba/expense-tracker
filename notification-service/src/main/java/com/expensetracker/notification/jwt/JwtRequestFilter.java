package com.expensetracker.notification.jwt;

import com.expensetracker.notification.model.Account;
import com.expensetracker.notification.model.CustomUserDetail;
import com.expensetracker.notification.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

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

            if(Objects.nonNull(jwtToken)){

                String username = jwtUtil.extractUsername(jwtToken);

                CustomUserDetail customUserDetail = new CustomUserDetail(
                        jwtUtil.getId(jwtToken), username, jwtUtil.getAuthorities(jwtToken));

                logger.info("Getting account by name");
                Account account = accountService.findByName(username);


                if (jwtUtil.validateJwt(jwtToken, account) &&
                        SecurityContextHolder.getContext().getAuthentication() == null){

                    Optional<UsernamePasswordAuthenticationToken> optional = createAuthentication(jwtToken, customUserDetail);

                    if(optional.isPresent()){
                        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = optional.get();
                        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                        logger.info("Setting SecurityContextHolder");
                    }else{
                        logger.info("Error setting SecurityContextHolder");
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Error setting authentication",e);
            return;
        }

        filterChain.doFilter(request, response);
    }

    public Optional<UsernamePasswordAuthenticationToken> createAuthentication(String token, CustomUserDetail customUserDetail){

        logger.info("Creating UsernamePasswordAuthenticationToken");
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(customUserDetail, token, customUserDetail.getAuthorities());

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
