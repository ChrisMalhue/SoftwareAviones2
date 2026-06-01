package com.SoftweareAviones.SoftweareAviones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SoftweareAviones.SoftweareAviones.model.Origen;

@Repository
public interface OrigenRepository extends JpaRepository<Origen, Integer> {

}