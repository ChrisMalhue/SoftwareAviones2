package com.SoftwareAviones.SoftwareAviones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SoftwareAviones.SoftwareAviones.model.Tipo;

@Repository
public interface TipoRepository extends JpaRepository<Tipo, Integer> {

}

