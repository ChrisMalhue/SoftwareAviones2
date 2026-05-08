package com.SoftwareAviones.SoftwareAviones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SoftwareAviones.SoftwareAviones.model.Aviones;

@Repository
public interface AvionesRepository extends JpaRepository<Aviones, Integer> {

}
