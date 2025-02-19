package com.exam.controllers;

import com.exam.config.JwtUtils;
import com.exam.models.JwtRequest;
import com.exam.models.JwtResponse;
import com.exam.models.User;
import com.exam.services.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin
public class AuthenticateController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/generate-token")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {

        try {

            authenticate ( jwtRequest.getUserName (),jwtRequest.getPassword () );
        }catch (UsernameNotFoundException e){
            e.printStackTrace ();
            throw  new Exception ("User Not Found Exception");
        }

        ///Authenticated///
        UserDetails userDetails = this.userDetailsServiceImpl.loadUserByUsername ( jwtRequest.getUserName ());

        String token = this.jwtUtils.generateToken (userDetails);
        System.out.println (token);

        return ResponseEntity.ok (new JwtResponse (token));

    }


    public  void authenticate(String userName , String password) throws Exception {

        try{

            authenticationManager.authenticate ( new UsernamePasswordAuthenticationToken ( userName, password ) );
        }catch (DisabledException e){

            throw  new Exception ("USER DISABLED" + e.getMessage ());
        }catch (BadCredentialsException e){
            throw  new Exception ("INVALID CREDENTIALS"+ e.getMessage ());
        }
    }

    //Create -> Current User
    @GetMapping ("/currentUser")
    public User currentUser(Principal principal){
       return  (User) this.userDetailsServiceImpl.loadUserByUsername ( principal.getName () );

    }


}
