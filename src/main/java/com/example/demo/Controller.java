package com.example.demo;

import org.springframework.http.MediaType;
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
    private final String israelPass = "israel";

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

    @GetMapping(path="/getFile/{pass}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public String getFile(@PathVariable String pass) {
        StringBuilder builder = new StringBuilder();
        if(pass.equals(israelPass))
        {
            for(String ip : ips) {
                builder.append(ip).append("\n");
            }
            return builder.toString();
        }
        return "Not authorized";
    }

    @GetMapping(path="/delete/{pass}")
    public String deleteFile(@PathVariable String pass) {
        if(pass.equals(israelPass))
        {
            ips = new ArrayList<>();
            return "Done";
        }
        return "Not authorized";
    }
}
