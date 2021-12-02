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

import com.grupo39.misiontic.ciclo4.Tienda1generica.modelo.Usuario;
import com.grupo39.misiontic.ciclo4.Tienda1generica.repository.UsuarioRepositorio;

@CrossOrigin(origins ="*")//determina quiern puede consumir la api *= todos
//@CrossOrigin(origin= "http://localhost:8081" solo se podria usar este puerto
@RestController
@RequestMapping("/api")//8080/api/****
public class UsuarioController {
	@Autowired
	//inyeccion de dependencia
	UsuarioRepositorio usuarioRepositorio;
	@GetMapping("/usuario")
	//requestparam = puedo o no agregar un usernmae
	public ResponseEntity<List<Usuario>> getAllUsuarios(@RequestParam(required=false)String username){
		try {
			List<Usuario> usuarios = new ArrayList<Usuario>();
			if (username==null) {
				usuarioRepositorio.findAll().forEach(usuarios::add);
				
			}	else {
				//esta es la funcion que creamos
				usuarioRepositorio.findByUsername(username).forEach(usuarios::add);
			}
			if (usuarios.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				
			}
			return new ResponseEntity<>(usuarios,HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} @GetMapping("/usuarios/{id}")
	  public ResponseEntity<Usuario> getUsuariosById(@PathVariable("id") String id) {
	    Optional<Usuario> usuarioData = usuarioRepositorio.findById(id);

	    if (usuarioData.isPresent()) {
	      return new ResponseEntity<>(usuarioData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }

	  @PostMapping("/usuarios")
	  public ResponseEntity<Usuario> createUsuarios(@RequestBody Usuario user) {
	    try {
	    	Usuario _usuario = usuarioRepositorio.save(new Usuario(user.getUsername(), user.getPassword(),user.getNombre(), user.getEmail()));
	      return new ResponseEntity<>(_usuario, HttpStatus.CREATED);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }

	  @PutMapping("/usuarios/{id}")
	  public ResponseEntity<Usuario> updateUsuarios(@PathVariable("id") String id, @RequestBody Usuario user) {
	    Optional<Usuario> usuarioData = usuarioRepositorio.findById(id);

	    if (usuarioData.isPresent()) {
	      Usuario _usuario = usuarioData.get();
	      _usuario.setUsername(user.getUsername());
	      _usuario.setPassword(user.getPassword());
	      return new ResponseEntity<>(usuarioRepositorio.save(_usuario), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }

	  @DeleteMapping("/usuarios/{id}")
	  public ResponseEntity<HttpStatus> deleteUsuarios(@PathVariable("id") String id) {
	    try {
	      usuarioRepositorio.deleteById(id);
	      return new ResponseEntity<>(HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }

	  @DeleteMapping("/usuarios")
	  public ResponseEntity<HttpStatus> deleteAllUsuarioss() {
	    try {
	      usuarioRepositorio.deleteAll();
	      return new ResponseEntity<>(HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }

	  @GetMapping("/usuarios/{username}")
	  public ResponseEntity<List<Usuario>> findByUsername(@PathVariable("username") String username) {
	    try {
	      List<Usuario> usuarios = usuarioRepositorio.findByUsername(username);

	      if (usuarios.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }
	      return new ResponseEntity<>(usuarios, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	}
