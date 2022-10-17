package com.PI.API.repository;

import com.PI.API.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICityRepository extends JpaRepository<City,Long> {

    //agregar consulta hql de productos por ciudad cuando tenga mapeado con Poducto
}
