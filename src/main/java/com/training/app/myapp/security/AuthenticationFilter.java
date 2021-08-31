package com.training.app.myapp.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.training.app.myapp.MyappApplication;
import com.training.app.myapp.SpringApplicationContext;
import com.training.app.myapp.entities.UserEntity;
import com.training.app.myapp.repositories.UserRepository;
import com.training.app.myapp.requests.UserLoginRequest;
import com.training.app.myapp.services.UserService;
import com.training.app.myapp.shared.dto.UserDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {


    private  AuthenticationManager authenticationManager;

    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager=authenticationManager;

    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException {
        try {

            UserLoginRequest creds = new ObjectMapper().readValue(req.getInputStream(), UserLoginRequest.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getPassword(), new ArrayList<>()));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {

        String userName = ((User) auth.getPrincipal()).getUsername();
        UserService userService = (UserService) SpringApplicationContext.getBean("userServiceImpl");
        UserDto user = userService.getUserByEmail(auth.getName());
        String token = Jwts.builder().claim("id",user.getUserId())
                .setSubject(userName)
                .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.Token_Expiration_Time))
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.TOKEN_SECRET )
                .compact();


      //res.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.Token_Prefix + token);



        res.getWriter().write("{\"token\": \"" + token + "\", \"id\": \""+ user.getUserId() + "\"}");


    }
}
