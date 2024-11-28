package com.exam.config;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

       final String requestTokenHeader = request.getHeader ( "Authorization" );
        System.out.println (requestTokenHeader);
        String userName = null;
        String jwtToken = null;

        if (requestTokenHeader != null && requestTokenHeader.startsWith ( "Bearer " )) {
            
            jwtToken=requestTokenHeader.substring ( 7 );
            try {
                userName = this.jwtUtils.getUsernameFromToken ( jwtToken );

            }catch (ExpiredJwtException e) {
                e.printStackTrace ();
                System.out.println ( "JWT Token has expired" );
            }catch (Exception e){
                e.printStackTrace ();
                System.out.println ("Error");
            }

        }
        else {
            System.out.println ("Invalid Token. Not Start with Bearer");
        }

        //Validated
        if(userName != null && SecurityContextHolder.getContext ().getAuthentication ()==null){
           final UserDetails userDetails = this.userDetailsService.loadUserByUsername ( userName );
           if (this.jwtUtils.validateToken ( jwtToken,userDetails )){
                //token is validated
               UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken (userDetails,null,userDetails.getAuthorities ());

               usernamePasswordAuthenticationToken.setDetails ( new WebAuthenticationDetailsSource ().buildDetails ( request ) );

               SecurityContextHolder.getContext().setAuthentication ( usernamePasswordAuthenticationToken );


           }

        }else {
            System.out.println ("Token is not valid");
        }

        filterChain.doFilter ( request,response );



    }
}
