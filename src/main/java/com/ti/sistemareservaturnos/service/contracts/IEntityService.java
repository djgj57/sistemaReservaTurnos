package com.ti.sistemareservaturnos.service.contracts;

import java.util.List;
import java.util.Optional;

public interface IEntityService<T> {
    /*Métodos*/
    T save(T t);
    Optional<T> findById(Long id);
    List<T> findAll();
    T update(T t);
    void delete(Long id);
}

