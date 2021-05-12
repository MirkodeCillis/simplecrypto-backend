package com.simplecrypto.server.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.simplecrypto.server.domains.User;
import com.simplecrypto.server.repository.UserRepository;
import com.simplecrypto.server.security.JwtAuthenticationRequest;
import com.simplecrypto.server.security.JwtAuthenticationResponse;
import com.simplecrypto.server.security.JwtTokenUtil;
import com.simplecrypto.server.security.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(path = "/api/auth")
public class AuthCtrl {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest, HttpServletResponse response) throws AuthenticationException, JsonProcessingException {

        User user = userRepository.findByEmail(authenticationRequest.getEmail());

        if (user != null) {
            if (!passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword())){
                return new ResponseEntity("Bad Credentials", HttpStatus.BAD_REQUEST);
            }
        }

        // Genero Token
        final JwtUser userDetails = (JwtUser) userDetailsService.loadUserByUsername(user.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);

        MultiValueMap<String, String> headers = new HttpHeaders();
        // Sets our custom response header
        headers.add(tokenHeader, token);
        headers.add("Access-Control-Expose-Headers", tokenHeader);
        // Ritorno il token
        return new ResponseEntity(new JwtAuthenticationResponse(userDetails.getUsername(), userDetails.getEmail()), headers, HttpStatus.OK);
    }

}