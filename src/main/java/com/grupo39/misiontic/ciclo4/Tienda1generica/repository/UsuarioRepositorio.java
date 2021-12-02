package com.grupo39.misiontic.ciclo4.Tienda1generica.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.grupo39.misiontic.ciclo4.Tienda1generica.modelo.Usuario;

public interface UsuarioRepositorio extends MongoRepository<Usuario, String>{
//enseñando como buscar algo en especifico, recordar la interfaz solo es una cascara
	
	List<Usuario>findByUsername(String username);
	
	List <Usuario>findByNombre(String nombre);
}
