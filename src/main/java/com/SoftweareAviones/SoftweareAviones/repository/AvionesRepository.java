package com.SoftweareAviones.SoftweareAviones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SoftweareAviones.SoftweareAviones.model.Aviones;

@Repository
public interface AvionesRepository extends JpaRepository<Aviones, Integer> {

}
