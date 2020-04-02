package com.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by CAPTAN GHOURI on 02/04/2020.
 */
public class JwtTokenVerifier extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String tokenHeader = request.getHeader("Authorization").replace("Bearer ", "");
        // filterChain.doFilter(request,response); in case token is empty or not start with something defined
        Jws<Claims> claims = Jwts.parser()
                .setSigningKey(Keys.hmacShaKeyFor("ssecuressecuressecuressecuressecuressecuressecuressecuressecuressecure".getBytes()))
                .parseClaimsJws(tokenHeader);
        Claims body = claims.getBody();
        String userName = body.getSubject();
        List<Map<String, String>> authorities = (List<Map<String, String>>) body.get("authorities");
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(authorities.get(0).get("authority"));
        Authentication authentication = new UsernamePasswordAuthenticationToken(userName, null, Arrays.asList(authority));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }
}