package com.security.controller;

import com.security.Models.Student;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by CAPTAN GHOURI on 31/03/2020.
 */

@RestController
@RequestMapping(value = "/api/student")
public class StudentController {

    CookieCsrfTokenRepository repository = new CookieCsrfTokenRepository();


    @GetMapping
    @PreAuthorize("hasAuthority('STUDENT')")
    public Student getStudent() {
        return new Student(24, "Sohaib");
    }

    @GetMapping("/get")
    @ResponseBody
    @PreAuthorize("hasAuthority('STUDENT')")
    public String getMessage(HttpServletRequest httpServletRequest) {
        return "Post Call Successfull";
    }
}
