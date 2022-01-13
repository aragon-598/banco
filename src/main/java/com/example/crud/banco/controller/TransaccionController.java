package com.example.crud.banco.controller;

import java.util.List;

import com.example.crud.banco.entities.Cuenta;
import com.example.crud.banco.entities.Transaccion;
import com.example.crud.banco.repository.CuentaRepository;
import com.example.crud.banco.repository.TransaccionRepository;
import com.example.crud.banco.services.TransaccionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/transaccion")
public class TransaccionController {

    @Autowired
    TransaccionService service;

    @Autowired
    TransaccionRepository repository;

    @Autowired
    CuentaRepository cuentaRepository;

    @GetMapping(path = "/transaccionesByNumerocuenta/{numeroCuenta}")
    public ResponseEntity<Object> transaccionesByCuenta(@PathVariable("numeroCuenta") String numeroCuenta) {
        
        if (service.getTransaccionesByNumeroCuenta(numeroCuenta)!=null) {
            List<Transaccion> listaTransacciones = service.getTransaccionesByNumeroCuenta(numeroCuenta);

            return ResponseEntity.status(HttpStatus.OK).body(listaTransacciones);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No hay transacciones con este numero de cuenta");
    }

    @GetMapping(path = "/transaccionById/{idTransaccion}")
    public ResponseEntity<Object> transaccionById(@PathVariable("idTransaccion") int idTransaccion) {
        
        if (service.getTransaccionById(idTransaccion)!=null) {
            return ResponseEntity.ok(service.getTransaccionById(idTransaccion));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe la transaccion");
    }

    @PostMapping(path="/nuevaTransaccion")
    public ResponseEntity<Object> nuevaTransaccion(@RequestBody Transaccion nuevaTransaccion) {
        
        if(nuevaTransaccion!=null){
            // service.guardarTransaccion(nuevaTransaccion);
            // return ResponseEntity.status(HttpStatus.OK).body("Transaccion exitosa");
            if (cuentaRepository.existsById(nuevaTransaccion.getNumeroCuenta().getNumeroCuenta())) {
                Cuenta cuenta = cuentaRepository.getById(nuevaTransaccion.getNumeroCuenta().getNumeroCuenta());
                if (cuenta.getEstadoCuenta()=='A') {
                    if (nuevaTransaccion.getIdTipoTransaccion().getIdTipoTransaccion() == 1) {
                        //DEPOSITO
                        cuenta.setSaldo(cuenta.getSaldo()+nuevaTransaccion.getValorMonetario());
    
                        cuentaRepository.save(cuenta);
                        service.guardarTransaccion(nuevaTransaccion);
    
                        return ResponseEntity.status(HttpStatus.OK).body("Deposito exitoso");
                    }
                    else {
                        //Retiro
                        if(nuevaTransaccion.getValorMonetario()>cuenta.getSaldo())
                            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Estás tratando de retirar una cantidad más grande de tu saldo actual");
                        
                        cuenta.setSaldo(cuenta.getSaldo()-nuevaTransaccion.getValorMonetario());

                        return ResponseEntity.ok("Retiro exitoso");
                    }
                }
                
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La cuenta esta inactiva");
            }

        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }

    @PutMapping(path = "/editTransaccion")
    public ResponseEntity<?> editarTransaccion(@RequestBody Transaccion transaccion) {
        boolean existe = repository.existsById(transaccion.getIdTransaccion());

        if (existe) {
            Transaccion oldTransaccion = service.getTransaccionById(transaccion.getIdTransaccion());

            oldTransaccion.setIdTipoTransaccion(transaccion.getIdTipoTransaccion());
            oldTransaccion.setValorMonetario(transaccion.getValorMonetario());
            oldTransaccion.setFechaTransaccion(transaccion.getFechaTransaccion());
            
            service.guardarTransaccion(oldTransaccion);

            return ResponseEntity.ok().build();
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    
}
