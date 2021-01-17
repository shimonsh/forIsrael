package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@RestController
public class Controller {

    List<String> ips = new ArrayList<>();

    @GetMapping(path = "/print")
    public String getCallerAddress(HttpServletRequest request) {
        String returnValue;
        if(request.getHeader("X-Forwarded-For") != null){
            returnValue = request.getHeader("X-Forwarded-For");
        }else{
            returnValue = request.getRemoteAddr();
        }
        ips.add(returnValue);
        return "Hello world!";
    }

    @GetMapping(path="/show/{pass}")
    public String getShow(@PathVariable String pass) {
        if(pass.equals("israel"))
        {
            return Arrays.toString(ips.toArray());
        }
        return "Not authorized";
    }
}
