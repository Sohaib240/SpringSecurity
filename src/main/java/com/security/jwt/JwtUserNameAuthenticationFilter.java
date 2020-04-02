package com.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.security.Models.UserNameAndPasswordModel;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Serializer;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

/**
 * Created by CAPTAN GHOURI on 01/04/2020.
 */
public class JwtUserNameAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    public JwtUserNameAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            UserNameAndPasswordModel model = new ObjectMapper().readValue(request.getInputStream(), UserNameAndPasswordModel.class);
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    model.getUsername(), model.getPassword()
            ));
            return authentication;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String token = Jwts.builder()
                .setSubject(authResult.getName())
                .claim("authorities", authResult.getAuthorities())
                .claim("email", "sohaibaslam4444@gmail.com")
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusWeeks(2)))
                .signWith(Keys.hmacShaKeyFor("ssecuressecuressecuressecuressecuressecuressecuressecuressecuressecure".getBytes()))
                .compact();
        response.addHeader("Authorization", "Bearer " + token);

    }
}





