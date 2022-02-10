package com.springsecurityjwt.jwt;

import com.springsecurityjwt.jwt.models.AuthenticationRequest;
import com.springsecurityjwt.jwt.models.AuthenticationResponse;
import com.springsecurityjwt.jwt.services.MyUserDetailsService;
import com.springsecurityjwt.jwt.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @GetMapping("/user")
    public String user(){
        return ("<h1>User</h1>");
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
        try {
            //authentication Manager authenticates the provided username and password
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException ex){
            throw new Exception("Incorrect username or password",ex);
        }
        //passes userdetail information after checking the provided username
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        //generates jwt from the userdetails information
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

}
