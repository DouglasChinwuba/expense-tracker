package com.expensetracker.auth.jwt;

import com.expensetracker.auth.service.UserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private static Logger logger = LoggerFactory.getLogger(JwtRequestFilter.class);

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        try {
            String jwtToken = getJwtFromAuthHeader(request);

            if(Objects.nonNull(jwtToken)){
                String username = jwtUtil.extractUsername(jwtToken);
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                if (jwtUtil.validateJwtToken(jwtToken, userDetails) &&
                        SecurityContextHolder.getContext().getAuthentication() == null){

                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
        } catch (Exception e) {
            logger.error("Error setting authentication: {}", e);
        }

        filterChain.doFilter(request, response);
    }


    private String getJwtFromAuthHeader(HttpServletRequest request){
        String authorizationHeader = request.getHeader("Authorization");

        if(Objects.nonNull(authorizationHeader) && authorizationHeader.startsWith("Bearer ")){
            return authorizationHeader.substring(7);
        }

        return null;
    }
}
