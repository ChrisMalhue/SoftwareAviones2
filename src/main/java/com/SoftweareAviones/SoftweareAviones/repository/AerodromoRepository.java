package com.SoftweareAviones.SoftweareAviones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SoftweareAviones.SoftweareAviones.model.Aerodromo;

@Repository
public interface AerodromoRepository extends JpaRepository<Aerodromo, Integer> {

    

}

