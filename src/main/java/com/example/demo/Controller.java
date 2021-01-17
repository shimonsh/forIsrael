package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class Controller {

    @GetMapping(path = "/print")
    public String getCallerAddress(HttpServletRequest request) {
        if(request.getHeader("X-Forwarded-For") != null){
            return request.getHeader("X-Forwarded-For");
        }else{
            return request.getRemoteAddr();
        }
    }
}
