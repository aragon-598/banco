package com.example.crud.banco.services;

import java.util.List;

import com.example.crud.banco.entities.Cliente;
import com.example.crud.banco.repository.Clienterepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ClienteService {
    
    private final static Logger logger = LoggerFactory.getLogger(ClienteService.class);

    @Autowired
    Clienterepository clienterepository;

    /**
     * Devuelve una lista completa de los clientes registrados
     */
    public List<Cliente> getAllClientes(){
        try {
            List<Cliente> clientes = clienterepository.findAll();
            if (!clientes.isEmpty()) {
                return clientes;
            }
        } catch (Exception e) {
            logger.error("Error en obtener todos los clientes", e);
        }

        return null;
    }

    public Cliente getClienteById(int codCliente) {
        
        try {
            Cliente cliente = clienterepository.findById(codCliente).get();
            if (cliente != null) {
                return cliente;
            }
        } catch (Exception e) {
            logger.error("Error en obtener un cliente por cod_cliente", e);
        }

        return null;
    }

    public void registrar(Cliente cliente) {

        boolean existeDui =false;

        try {
            existeDui = clienterepository.existsByDui(cliente.getDui());

            if (!existeDui) 
                clienterepository.save(cliente);
            
        } catch (Exception e) {
            logger.error("Error en guardar ", e);
        }
    }

    public void eliminar(int codCliente){
        boolean existeCliente=false;
        try {

            existeCliente = clienterepository.existsById(codCliente);

            if(existeCliente)
                clienterepository.deleteById(codCliente);

        } catch (Exception e) {
            logger.error("Error en eliminar cliente", e);
        }
    }
}
