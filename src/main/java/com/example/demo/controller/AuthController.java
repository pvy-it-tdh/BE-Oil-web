package com.example.demo.controller;


import com.example.demo.DTO.JwtRequest;
import com.example.demo.DTO.JwtResponse;
import com.example.demo.JWT.JwtTokenUtil;
import com.example.demo.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserService userService;
    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception
    {
        authenticate(authenticationRequest.getUsername(),authenticationRequest.getPassword());
        final UserDetails userDetails=userService.loadUserByUsername(authenticationRequest.getUsername());
        final String token =jwtTokenUtil.generateToken(userDetails.getUsername());
        return ResponseEntity.ok(new JwtResponse(token));
    }
    private void authenticate(String username,String password) throws Exception{
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        }catch (BadCredentialsException e)
        {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

}
