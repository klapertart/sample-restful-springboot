/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ostrue.app.restfulsample.controller;

import com.ostrue.app.restfulsample.model.Peserta;
import com.ostrue.app.restfulsample.service.PesertaRepository;
import java.net.URI;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriTemplate;

/**
 *
 * @author kurakuraninja
 */
@RestController
@RequestMapping("/api")
public class PesertaContoller {
    @Autowired private PesertaRepository pesertaRepository;
    
    @RequestMapping("/")
    public String home() {
        return "Hello World!";
    }    
    
    @RequestMapping(value="/peserta", method = RequestMethod.GET)
    public Iterable<Peserta> all(){
        return pesertaRepository.findAll();
    }    
    
    @RequestMapping(value = "/peserta/{id}", method = RequestMethod.GET)
    public Optional<Peserta> findById(@PathVariable String id){
        return pesertaRepository.findById(id);
    }
    
    @RequestMapping(value = "/peserta", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Peserta peserta, HttpServletRequest request, HttpServletResponse response){
        pesertaRepository.save(peserta);
        String id = peserta.getId();
        String requestUrl = request.getRequestURL().toString();
        URI uri = new UriTemplate("{requestUrl}/{id}").expand(requestUrl,id);
        response.setHeader("Location",uri.toASCIIString());
    }

}

