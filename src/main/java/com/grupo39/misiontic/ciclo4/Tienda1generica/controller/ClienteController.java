package com.grupo39.misiontic.ciclo4.Tienda1generica.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.grupo39.misiontic.ciclo4.Tienda1generica.modelo.Cliente;

import com.grupo39.misiontic.ciclo4.Tienda1generica.repository.ClienteRepository;
import com.mongodb.DuplicateKeyException;

@CrossOrigin(origins = "*")
//@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")

public class ClienteController {

	@Autowired
	ClienteRepository clienteRepository;
	
	@GetMapping("/clientes")
	public ResponseEntity<List<Cliente>> getAllClientes(@RequestParam(required = false) Long cedula) {
		try {
			List<Cliente> clientes = new ArrayList<Cliente>();

			if (cedula == null) {
				clienteRepository.findAll().forEach(clientes::add);
			} else {
				clienteRepository.findByCedula(cedula).forEach(clientes::add);
			}

			if (clientes.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(clientes, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	

	@GetMapping("/clientes/{cedula}")
	public ResponseEntity<List<Cliente>> getClient(@RequestParam Long cedula) {
		try {
			List<Cliente> clientes = new ArrayList<Cliente>();

			if (cedula == null) {
				clienteRepository.findAll().forEach(clientes::add);
			} else {
				clienteRepository.findByCedula(cedula).forEach(clientes::add);
			}

			if (clientes.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(clientes, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@GetMapping("/clientes/{id}")
	public ResponseEntity<Cliente> getClienteById(@PathVariable("id") String id) {
		Optional<Cliente> clienteData = clienteRepository.findById(id);

		if (clienteData.isPresent()) {
			return new ResponseEntity<>(clienteData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/clientes")
	public ResponseEntity<Cliente> createCliente(@RequestBody Cliente client) {
		try {
			Cliente _cliente = clienteRepository.save(
					new Cliente(client.getCedula(), 
							client.getNombre(),
							client.getDireccion(), 
							client.getTelefono(),
							client.getEmail()));
			return new ResponseEntity<>(_cliente, HttpStatus.CREATED);
		}catch (DuplicateKeyException e) {return new ResponseEntity<>(null, HttpStatus.CONFLICT);} 
		catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/clientes/{id}")
	public ResponseEntity<Cliente> updateCliente(@PathVariable("id") String id, @RequestBody Cliente cedula) {
		Optional<Cliente> clienteData = clienteRepository.findById(id);

		if (clienteData.isPresent()) {
			Cliente _client = clienteData.get();
			_client.setCedula(cedula.getCedula());
			_client.setNombre(cedula.getNombre());
			_client.setDireccion(cedula.getDireccion());
			_client.setTelefono(cedula.getTelefono());
			_client.setEmail(cedula.getEmail());
			
			
			return new ResponseEntity<>(clienteRepository.save(_client), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	
	@DeleteMapping("/clientes/{cedula}")
	public ResponseEntity<HttpStatus> deleteClientes(@PathVariable("cedula") Long cedula) {
		try {
			clienteRepository.deleteByCedula(cedula);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/clientes")
	public ResponseEntity<HttpStatus> deleteAllClientes() {
		try {
			clienteRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/clientes/{nombre}")
	public ResponseEntity<List<Cliente>> findByNombre(@PathVariable("nombre") String nombre) {
		try {
			List<Cliente> clientes = clienteRepository.findByNombre(nombre);

			if (clientes.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(clientes, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
