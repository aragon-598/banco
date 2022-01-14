package com.example.crud.banco.controller;

import java.util.ArrayList;
import java.util.List;

import com.example.crud.banco.entities.Cuenta;
import com.example.crud.banco.repository.CuentaRepository;
import com.example.crud.banco.services.CuentaService;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/cuenta")
@CrossOrigin
public class Cuentacontroller {
    
    @Autowired
    CuentaService service;

    @Autowired
    CuentaRepository repository;

    @GetMapping(path = "/cuentasbycliente/{codCliente}")
    public ResponseEntity<Object> getCuentasByCodCliente(@PathVariable("codCliente") Integer codCliente) {

        List<Cuenta> cuentasByClientes = new ArrayList<>();

        cuentasByClientes = service.getAllCuentasByCliente(codCliente);

        if(cuentasByClientes==null)
            return ResponseEntity.status(HttpStatus.OK).body("No existen cuentas para el usuario");
        
        return ResponseEntity.status(HttpStatus.OK).body(cuentasByClientes);

    }
    
    @GetMapping(path="/cuentabynumerocuenta/{numeroCuenta}")
    public ResponseEntity<Object> getCuentaByNumeroCuenta(@PathVariable("numeroCuenta") String numeroCuenta) {
        
        Cuenta cuenta = service.getCuentaById(numeroCuenta);

        if(cuenta ==null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No existe la cuenta con número de cuenta "+numeroCuenta);
        
        return ResponseEntity.status(HttpStatus.OK).body(cuenta);

    }

    @PostMapping(path="/newcuenta")
    public ResponseEntity<String> crearCuenta(@RequestBody Cuenta newCuenta) {
        
        if(newCuenta != null){
            newCuenta.setSaldo(newCuenta.getMontoApertura());
            String idCuenta = RandomStringUtils.randomAlphanumeric(10);

            while (repository.existsByNumeroCuenta(idCuenta)) {
                idCuenta = RandomStringUtils.randomAlphanumeric(10);
            }

            newCuenta.setNumeroCuenta(idCuenta);

            service.guardarCuenta(newCuenta);
            return ResponseEntity.status(HttpStatus.OK).body("Cuenta creada con exito");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Campos incompletos");

    }

    @PutMapping(path = "/editcuenta")
    public ResponseEntity<Object> editarSaldoCuenta(@RequestBody Cuenta cuenta) {
        
        boolean cuentaExiste = false;
        cuentaExiste = repository.existsById(cuenta.getNumeroCuenta());
        Cuenta oldCuenta = repository.getById(cuenta.getNumeroCuenta());

        if (!cuentaExiste) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La cuenta no existe");
        }
        else if (cuentaExiste && oldCuenta.getEstadoCuenta()!='A') {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La cuenta no está activa, no podemos trabajar con ella");
        }
        
        oldCuenta.setSaldo(cuenta.getSaldo());
        repository.save(oldCuenta);
        
        return ResponseEntity.status(HttpStatus.OK).body("Cuenta actualizada");
        
    }

    @PutMapping(path="/activarCuenta/{numeroCuenta}")
    public ResponseEntity<Object> activarCuenta(@PathVariable("numeroCuenta") String numeroCuenta) {
        
        boolean existeCuenta = repository.existsById(numeroCuenta);

        if(existeCuenta){
            Cuenta oldCuenta = service.getCuentaById(numeroCuenta);

            oldCuenta.setEstadoCuenta('A');

            repository.save(oldCuenta);

            return ResponseEntity.status(HttpStatus.OK).body("Cuenta activada");
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe la cuenta");
        }

    }

    @PutMapping(path="/desactivarCuenta/{numeroCuenta}")
    public ResponseEntity<Object> desactivarCuenta(@PathVariable("numeroCuenta") String numeroCuenta) {
        
        boolean existeCuenta = repository.existsById(numeroCuenta);

        if(existeCuenta){
            Cuenta oldCuenta = service.getCuentaById(numeroCuenta);

            oldCuenta.setEstadoCuenta(' ');

            repository.save(oldCuenta);

            return ResponseEntity.status(HttpStatus.OK).body("Cuenta desactivada");
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe la cuenta");
        }

    }

    @DeleteMapping(path="/deletecuenta/{numeroCuenta}")
    public ResponseEntity<Object> eliminarCuenta(@PathVariable("numeroCuenta") String numerocuenta) {
        boolean existeCuenta = repository.existsById(numerocuenta);

        if (existeCuenta) {
            service.eliminar(numerocuenta);

            ResponseEntity.status(HttpStatus.OK).body("Cuenta eliminada");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
