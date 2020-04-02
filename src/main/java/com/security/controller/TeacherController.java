package com.security.controller;

import com.security.Models.Student;
import com.security.Models.Teacher;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Created by CAPTAN GHOURI on 31/03/2020.
 */


@RestController
@RequestMapping(value = "/api/teacher")
public class TeacherController {

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Teacher getTeacher() {
        return new Teacher("billal", "computer", 4);
    }

    @PostMapping
    @ResponseBody
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getMessage() {
        return "Post Call Successful in Teacher Controller";
    }
}

