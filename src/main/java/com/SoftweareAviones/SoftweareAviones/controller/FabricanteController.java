package com.SoftweareAviones.SoftweareAviones.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SoftweareAviones.SoftweareAviones.DTO.FabricanteDTO;
import com.SoftweareAviones.SoftweareAviones.model.Fabricante;
import com.SoftweareAviones.SoftweareAviones.services.FabricanteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/fabricantes")
public class FabricanteController {

    @Autowired
    private FabricanteService fabricanteService;

    @GetMapping
    public ResponseEntity<List<FabricanteDTO>> obtenerTodos() {

        List<FabricanteDTO> fabricantes = fabricanteService.obtenerTodos();

        if (fabricantes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(fabricantes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FabricanteDTO> buscarPorId(@PathVariable Integer id) {
        try {
            FabricanteDTO fabricante = fabricanteService.buscarPorId(id);
            return new ResponseEntity<>(fabricante, HttpStatus.OK);

        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PostMapping
    public ResponseEntity<Fabricante> agregarFabricante(@Valid @RequestBody Fabricante fabricante) {
        try {
            Fabricante nuevo = fabricanteService.guardarFabricante(fabricante);
            return new ResponseEntity<>(nuevo, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarFabricante(@PathVariable Integer id) {
        String resultado = fabricanteService.eliminar(id);
        if (resultado.contains("correctamente") || resultado.contains("eliminado")) {
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        }
        return new ResponseEntity<>(resultado, HttpStatus.NOT_FOUND);
    }    

    @PatchMapping("/{id}")
    public ResponseEntity<Fabricante> editarFabricante(@PathVariable Integer id, @Valid @RequestBody Fabricante fabricante) {
        try {
            fabricante.setId_fabricante(id);
            Fabricante editado = fabricanteService.guardarFabricante(fabricante);
            return new ResponseEntity<>(editado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Fabricante> actualizarFabricante(@PathVariable Integer id,
                                                            @Valid @RequestBody Fabricante fabricante) {
        try {
            Fabricante actualizado = fabricanteService.actualizarFabricante(id, fabricante);
            return new ResponseEntity<>(actualizado, HttpStatus.OK);

        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }    
    

}
