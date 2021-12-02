package com.grupo39.misiontic.ciclo4.Tienda1generica.repository;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.grupo39.misiontic.ciclo4.Tienda1generica.modelo.Venta;



public interface VentaRepository extends MongoRepository<Venta, String> {

	List<Venta> findByCodigoventa(Long codigoventa);
	List<Venta> findByCedulacliente(Long cedulacliente);
	
	
	void deleteByCodigoventa(Long codigoventa);
	void deleteByCedulacliente(Long cedulacliente);
}
