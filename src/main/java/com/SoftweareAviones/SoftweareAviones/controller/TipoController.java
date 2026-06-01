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

import com.SoftweareAviones.SoftweareAviones.DTO.TipoDTO;
import com.SoftweareAviones.SoftweareAviones.model.Tipo;
import com.SoftweareAviones.SoftweareAviones.services.TipoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/tipos")
public class TipoController {

    @Autowired
    private TipoService tipoService;

    @GetMapping
    public ResponseEntity<List<TipoDTO>> obtenerTodos() {
        List<TipoDTO> tipos = tipoService.obtenerTodos();
        if (tipos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tipos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoDTO> buscarPorId(@PathVariable Integer id) {
        try {
            TipoDTO tipo = tipoService.buscarPorId(id);
            return new ResponseEntity<>(tipo, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Tipo> agregarTipo(@Valid @RequestBody Tipo tipo) {
        try {
            Tipo nuevo = tipoService.guardarTipo(tipo);
            return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }    

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTipo(@PathVariable Integer id) {
        String resultado = tipoService.eliminar(id);
        if (resultado.contains("exitosamente") || resultado.contains("retirado")) {
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        }
        return new ResponseEntity<>(resultado, HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Tipo> editarTipo(@PathVariable Integer id, @Valid @RequestBody Tipo tipo) {
        try {
            tipo.setId_tipo(id);
            Tipo editado = tipoService.actualizarTipo(id, tipo);
            return new ResponseEntity<>(editado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tipo> actualizarTipo(@PathVariable Integer id, @Valid @RequestBody Tipo tipo) {
        try {
            Tipo actualizado = tipoService.actualizarTipo(id, tipo);
            return new ResponseEntity<>(actualizado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }    

}
