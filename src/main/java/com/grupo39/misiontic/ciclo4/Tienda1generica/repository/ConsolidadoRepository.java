package com.grupo39.misiontic.ciclo4.Tienda1generica.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.grupo39.misiontic.ciclo4.Tienda1generica.modelo.Consolidado;



public interface ConsolidadoRepository  extends MongoRepository<Consolidado, String>{
	
	List<Consolidado> findByCiudad(String ciudad);
}
