package com.springbootacademy.point_of_sale.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("api/v1/test")
public class TestController {

    @GetMapping(path="/1")
    public String getMyText(){
        String myText="this is test out";
        System.out.println(myText);
        return myText;
    }
    @GetMapping(path="/2")
    public String getMyText2(){
        String myText="this is test out 2";
        System.out.println(myText);
        return myText;
    }
}
