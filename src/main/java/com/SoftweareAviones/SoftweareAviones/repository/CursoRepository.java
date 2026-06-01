package com.SoftweareAviones.SoftweareAviones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SoftweareAviones.SoftweareAviones.model.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer> {

}
