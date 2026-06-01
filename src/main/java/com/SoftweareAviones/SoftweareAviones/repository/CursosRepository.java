package com.SoftweareAviones.SoftweareAviones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SoftweareAviones.SoftweareAviones.model.Cursos;

@Repository
public interface CursosRepository extends JpaRepository<Cursos, Integer> {

}