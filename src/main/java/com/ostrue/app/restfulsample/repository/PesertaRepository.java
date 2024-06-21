/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ostrue.app.restfulsample.repository;

import com.ostrue.app.restfulsample.model.Peserta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author kurakuraninja
 */
@Repository
public interface PesertaRepository extends CrudRepository<Peserta, String>{    
}
