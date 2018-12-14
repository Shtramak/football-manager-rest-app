package com.shtramak.service;

import java.util.List;
import java.util.Optional;

public interface CrudService<T> {
    List<T> getAll();

    Optional<T> getById(Long id);

    void save(T entity);

    void removeById(Long id);
}
