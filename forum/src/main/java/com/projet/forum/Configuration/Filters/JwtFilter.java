package com.projet.forum.Configuration.Filters;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.projet.forum.Entities.UserEntity;
import com.projet.forum.Services.UserInfoServices.UserInfoService;
import com.projet.forum.Services.UserServices.UserService;
import com.projet.forum.Utils.JwtHelper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtFilter extends OncePerRequestFilter {
    
    private final JwtHelper jwtHelper;

    private final UserService detailsService;

    @Autowired
    public JwtFilter(JwtHelper h, UserService s){ this.jwtHelper = h; this.detailsService = s; }

    @Override protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
    throws ServletException, java.io.IOException {

        // if (request.getHeader("Authorization") == null){
        //      filterChain.doFilter(request, response); 
        //      return;    
        // }

        Optional<String> requestTokenHandler = Optional.ofNullable(request.getHeader("Authorization"));
        //AtomicReference<Optional<String>> optUsername = new AtomicReference<>(Optional.empty());
        //atomic reference needed ^ for not taking an in-between state

        //Bearer efzqrgqrsgsqrgfzqelfnzqlenfluzqief (Bearer type of token, exclude)
        requestTokenHandler.filter(it -> it.startsWith("Bearer"))
                            .map(it -> it.substring(0, 7)) 
                            .ifPresent(token -> {
                                Optional<String> optUsername = Optional.of(jwtHelper.getUsernameFromToken(token));
                                optUsername.ifPresent(username -> {
                                    if(SecurityContextHolder.getContext().getAuthentication() == null){

                                        UserEntity userDetails = detailsService.findUserByUsername(username).get();
                                        if(jwtHelper.IsTokenValid(token, userDetails)){

                                            UsernamePasswordAuthenticationToken auth = new
                                            UsernamePasswordAuthenticationToken(userDetails, 
                                                                                null, 
                                                                                userDetails.getAuthorities());

                                            auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                                            SecurityContextHolder.getContext().setAuthentication(auth);
                                        }
                                    }
                                });
                            });
    
        filterChain.doFilter(request, response); 
    }
}
