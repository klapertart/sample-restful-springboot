/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ostrue.app.restfulsample.controller;

import com.ostrue.app.restfulsample.model.Peserta;
import com.ostrue.app.restfulsample.model.ResponseMessage;
import com.ostrue.app.restfulsample.repository.PesertaRepository;
import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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


    @PostMapping(path = "/employees")
    public ResponseEntity<ResponseMessage> create(@RequestBody Peserta peserta){
        Peserta pesertaSaved = pesertaRepository.save(peserta);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseMessage<>(HttpStatus.CREATED.value(), null, pesertaSaved
        ));
    }

    @GetMapping(path ="/employees")
    public ResponseEntity<ResponseMessage> all() {
        Iterable<Peserta> all = pesertaRepository.findAll();

        int count = 0;
        for (Peserta peserta : all) {
            count++;
        }

        if (count != 0){
            return ResponseEntity
                    .status(HttpStatus.OK).body(new ResponseMessage(HttpStatus.OK.value(), null, all));
        }else {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(new ResponseMessage<>(HttpStatus.NO_CONTENT.value(), "data tidak ada", all));
        }
    }
    
    @RequestMapping(value = "/employees/{id}", method = RequestMethod.GET)
    public ResponseEntity<ResponseMessage> findById(@PathVariable String id){
        Optional<Peserta> byId = pesertaRepository.findById(id);

        if (!byId.isPresent()){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage(HttpStatus.NOT_FOUND.value(), "data tidak ada", null));
        }else{
            Peserta tPerserta = new Peserta();
            byId.ifPresent(peserta -> {
                tPerserta.setId(peserta.getId());
                tPerserta.setNama(peserta.getNama());
                tPerserta.setEmail(peserta.getEmail());
            });

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseMessage(HttpStatus.OK.value(), null, tPerserta));
        }
    }

    @DeleteMapping(path = "/employees/{id}")
    public ResponseEntity<ResponseMessage> delete(@PathVariable String id){
        Optional<Peserta> byId = pesertaRepository.findById(id);

        if (byId.isPresent()){
            pesertaRepository.deleteById(id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseMessage(HttpStatus.OK.value(), "data telah dihapus", null));
        }else{
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage(HttpStatus.NOT_FOUND.value(), "data tidak ada", null));
        }
    }

    @PutMapping(path = "/employees/{id}")
    public ResponseEntity<ResponseMessage> update(@PathVariable String id, @RequestBody Peserta peserta){
        Optional<Peserta> byId = pesertaRepository.findById(id);

        if (byId.isPresent()){
            peserta.setId(id);
            Peserta savePeserta = pesertaRepository.save(peserta);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseMessage(HttpStatus.OK.value(), "data telah diupdate", savePeserta));
        }else{
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage(HttpStatus.NOT_FOUND.value(), "data tidak ada", null));
        }
    }


/*
    @RequestMapping(value = "/peserta/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable String id, @RequestBody Peserta peserta){
        Optional<Peserta> tPeserta = pesertaRepository.findById(id);
        if(tPeserta == null){
            throw new IllegalStateException("No Peserta with id : " + id);
        }
        peserta.setId(id);
        pesertaRepository.save(peserta);
    }
*/

}

