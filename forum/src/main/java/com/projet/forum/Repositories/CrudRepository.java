package com.projet.forum.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrudRepository<T,TKey> extends JpaRepository<T,TKey> {

    T create();

    T saveEntity(T entity);

    Optional<T> findById(TKey id);

    List<T> findAll();
    
    void deleteById(TKey id);
}
