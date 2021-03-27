package com.konoha.konoha_server.controllers;

import com.konoha.konoha_server.models.User;
import com.konoha.konoha_server.services.DemoService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pro")
public class Products {
    private static Log log= LogFactory.getLog(Products.class);

    private DemoService demoService;

    public Products(DemoService demoService) {
        this.demoService = demoService;
    }

    @Autowired





    @GetMapping("/hello")
    public String hello(){
            log.info("Creating object inside Log");

            return "Welcome to the Site";
    }

    @PostMapping("/data/{name}")
        public String sendData(@PathVariable("name") String name){
            return name;
        }

    @GetMapping("/demo")
    public List<User> demoData(){
        return demoService.getAll();
    }





}
