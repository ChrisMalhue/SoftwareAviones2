package com.SoftwareAviones.SoftwareAviones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SoftwareAviones.SoftwareAviones.model.Aerodromo;

@Repository
public interface AerodromoRepository extends JpaRepository<Aerodromo, Integer> {

    

}

//jpa sirve para conectar la base de dtaos con la clase 
// //guarda datos- busca datos - elimilna dstos - actualiza 
// interface genera el codigo del repositoyy auq este vacio