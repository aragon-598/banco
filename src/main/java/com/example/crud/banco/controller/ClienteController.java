package com.example.crud.banco.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import com.example.crud.banco.entities.Cliente;
import com.example.crud.banco.repository.Clienterepository;
import com.example.crud.banco.services.ClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping(path = "/cliente")
@CrossOrigin
public class ClienteController {

    @Autowired
    Clienterepository clienterepository;

    @Autowired
    ClienteService service;
    
    @GetMapping(value="/allclients")
    public ResponseEntity<Object> showAllClientes() {
        List<Cliente> clientes = new ArrayList<>();

        clientes = service.getAllClientes();

        if (clientes == null) {
            
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No hay clientes registrados");

        } else {
            
            return ResponseEntity.status(HttpStatus.OK).body(clientes);

        }
    }

    @GetMapping(value="/client/{codCliente}")
    public ResponseEntity<Object> getMethodName(@PathVariable("codCliente") int codCliente) {
        
        Cliente cliente = null;

        cliente = service.getClienteById(codCliente);

        if (cliente == null) {
            
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe el usuario");

        } else {
            
            return ResponseEntity.status(HttpStatus.OK).body(cliente);

        }

    }

    @GetMapping(value="/bydui/{dui}")
    public ResponseEntity<Object> getByDui(@PathVariable("dui") String dui) {
        
        Cliente cliente = null;

        boolean existeByDui = clienterepository.existsByDui(dui);

        if (!existeByDui) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message("No existe el usuario"));

        } else {
            cliente = clienterepository.findByDui(dui).get();
            return ResponseEntity.status(HttpStatus.OK).body(cliente);

        }

    }

    @PostMapping(value="/newclient")
    public ResponseEntity<Object> nuevoCliente(@RequestBody Cliente newCliente) {
        
        if (newCliente != null) {
            
            if(clienterepository.existsByDui(newCliente.getDui())){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El DUI ya está registrado");
            
            } else {
                service.registrar(newCliente);
                return ResponseEntity.status(HttpStatus.OK).body("Usuario registrado");

            }

        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Información incompleta");
        }

    }

    @PutMapping(path="/editar")
    public ResponseEntity<?> editarUsuario(@RequestBody Cliente clienteEditado) {
        if (clienterepository.existsByDui(clienteEditado.getDui())) {
            Cliente oldCliente = clienterepository.findByDui(clienteEditado.getDui()).get();

            oldCliente.setApellidos(clienteEditado.getApellidos());
            oldCliente.setNombre(clienteEditado.getNombre());
            clienterepository.save(oldCliente);
            return ResponseEntity.ok("Datos del cliente actualizados");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe usuario con dui"+clienteEditado.getDui());
    }

    @DeleteMapping(value = "/deleteclient/{codCliente}")
    public ResponseEntity<String> eliminarCliente(@PathVariable("codCliente") int codCliente) {
        
        if (clienterepository.existsById(codCliente)) {
            service.eliminar(codCliente);

            return ResponseEntity.status(HttpStatus.OK).body("Cliente eliminado con éxito");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No exite el usuario");

    }
    
    
    

}
