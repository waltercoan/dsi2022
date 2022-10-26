package br.univille.dsi2022.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JWTRequestFilter 
        extends OncePerRequestFilter {
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
        HttpServletRequest request, 
        HttpServletResponse response, 
        FilterChain filterChain)
            throws ServletException, IOException {
        
        String authorizationHeader =
                request.getHeader("Authorization");
        String token = null;
        String username = null;

        if(authorizationHeader != null &&
            authorizationHeader.startsWith("Bearer ")){
            token = authorizationHeader.substring(7);
            username = jwtUtil.extractUserName(token);
        }

        if(username != null && 
            SecurityContextHolder.getContext().getAuthentication()
            == null){
            UserDetails userDetails =
                userDetailsService.loadUserByUsername(username);
            
            if(jwtUtil.validateToken(token, userDetails)){
                UsernamePasswordAuthenticationToken ut =
                 new UsernamePasswordAuthenticationToken
                    (userDetails, null,userDetails.getAuthorities());
                
                ut.setDetails(
                    new WebAuthenticationDetailsSource()
                    .buildDetails(request));
                SecurityContextHolder.getContext()
                    .setAuthentication(ut);
            }
        }
        filterChain.doFilter(request, response);
        
    }
    
}
