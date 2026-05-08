package com.SoftwareAviones.SoftwareAviones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SoftwareAviones.SoftwareAviones.model.Avion;

@Repository
public interface AvionRepository extends JpaRepository<Avion, Integer> {

}
