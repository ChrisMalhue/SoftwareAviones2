package com.SoftwareAviones.SoftwareAviones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SoftwareAviones.SoftwareAviones.model.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region, Integer> {

}