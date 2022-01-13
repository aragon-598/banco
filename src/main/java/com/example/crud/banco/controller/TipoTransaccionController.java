package com.example.crud.banco.controller;

import com.example.crud.banco.entities.TipoTransaccion;
import com.example.crud.banco.repository.TipoTransaccionRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/tipoTransaccion")
public class TipoTransaccionController {
    
    private final static Logger logger = LoggerFactory.getLogger(TipoTransaccionController.class);

    @Autowired
    TipoTransaccionRepository repository;

    @GetMapping(path="/obtenertipos")
    public ResponseEntity<?> allTipos() {
        
        try {
            return ResponseEntity.ok(repository.findAll());
        } catch (Exception e) {
            logger.error("Error en obtener todos los tipos de transaccion", e);
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No hay registros");
    }

    @GetMapping(path = "/tipoById/{id}")
    public ResponseEntity<?> obtenerTipoById(@PathVariable("id") int id) {
        
        try {
            TipoTransaccion tt = repository.findById(id).get();

            return ResponseEntity.ok(tt);
        } catch (Exception e) {
            logger.error("Error al obtener un tipo transaccion", e);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
