package com.konoha.konoha_server.controllers;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Collections;
import java.util.Map;


@Controller
public class DemoController {

    @RequestMapping(method= RequestMethod.GET,value="/")
    public String my(){
//        ModelAndView mv=new ModelAndView();
//        mv.setViewName("index");
        return "index.html";
    }

    @GetMapping("/user")
    @ResponseBody
    public String user(Principal principal) {
        System.out.println("inside user");
        System.out.println(principal.getClass());
        return principal.getName();
        //return Collections.singletonMap("name", principal.getAttribute("name"));
    }

    @PutMapping("/error")
    @ResponseBody
    public String logout(Principal principal) {
//        System.out.println("inside user");
//        System.out.println(principal.getName());
//        return principal.getName();
        //return Collections.singletonMap("name", principal.getAttribute("name"));
        return "Hello";
    }
}
