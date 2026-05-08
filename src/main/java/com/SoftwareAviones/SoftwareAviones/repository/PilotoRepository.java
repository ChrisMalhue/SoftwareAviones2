package com.SoftwareAviones.SoftwareAviones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SoftwareAviones.SoftwareAviones.model.Piloto;

@Repository
public interface PilotoRepository extends JpaRepository<Piloto, Integer> {

}