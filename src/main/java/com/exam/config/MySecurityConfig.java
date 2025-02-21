package com.exam.config;

import com.exam.services.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class MySecurityConfig {
    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;
    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder ();
    }


    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = httpSecurity.getSharedObject ( AuthenticationManagerBuilder.class );

        authenticationManagerBuilder.userDetailsService ( userDetailsServiceImpl ).passwordEncoder ( passwordEncoder () );

        return authenticationManagerBuilder.build ();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .csrf ( csrf -> csrf.disable () )

                .authorizeHttpRequests ( auth ->
                        auth.requestMatchers ( "/generate-token", "/user/" ).permitAll ()
                                .requestMatchers ( HttpMethod.OPTIONS ).permitAll ()
                                .anyRequest ().authenticated ()

                )

                .exceptionHandling ( exceptionHandling -> exceptionHandling.authenticationEntryPoint ( unauthorizedHandler ) )
                .sessionManagement ( session -> session.sessionCreationPolicy ( SessionCreationPolicy.STATELESS ) )
                .addFilterBefore ( jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class );

        return httpSecurity.build ();
    }



}
