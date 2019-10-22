/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ostrue.app.restfulsample.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author kurakuraninja
 */
@RestController
public class TestContoller {
    @RequestMapping("/")
    public String home() {
        return "Hello World!";
    }    
}
