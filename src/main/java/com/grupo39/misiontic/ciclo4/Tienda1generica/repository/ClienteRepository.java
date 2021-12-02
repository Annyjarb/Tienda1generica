package com.grupo39.misiontic.ciclo4.Tienda1generica.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.grupo39.misiontic.ciclo4.Tienda1generica.modelo.Cliente;

public interface ClienteRepository extends MongoRepository<Cliente, String>{
List<Cliente>  findByCedula(Long cedula);
List<Cliente> deleteByCedula(Long cedula);
List<Cliente>  findByNombre(String nombre);


}
