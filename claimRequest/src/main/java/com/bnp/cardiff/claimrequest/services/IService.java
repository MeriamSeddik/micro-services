package com.bnp.cardiff.claimrequest.services;



import java.util.Collection;
import java.util.Optional;

public interface IService<T> {

    Collection<T> findAll();

    Optional<T> findById(Long id) ;

    T saveOrUpdate(T t);

    void deleteById(Long id);

}
