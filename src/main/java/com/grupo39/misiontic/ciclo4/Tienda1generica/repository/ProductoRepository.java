package com.grupo39.misiontic.ciclo4.Tienda1generica.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.grupo39.misiontic.ciclo4.Tienda1generica.modelo.Producto;


public interface ProductoRepository extends MongoRepository<Producto, String>{
	
	List<Producto> findByCodigoproducto(Long codigoproducto);
	
	List<Producto> findByNombreproducto(String nombreproducto);

}
