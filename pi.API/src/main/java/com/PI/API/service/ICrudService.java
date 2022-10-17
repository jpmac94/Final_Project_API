package com.PI.API.service;

import java.util.Set;

public interface ICrudService<T> {

    T save (T t);
    T findById (Long id); //cambio integer por Long
    Set<T> findAll();
    T update (T t,Long id); //Agrego el id del picture
    void delete (Long id);

}
