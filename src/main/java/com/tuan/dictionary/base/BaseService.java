package com.tuan.dictionary.base;

import com.tuan.dictionary.vocabulary.Vocabulary;

import java.util.List;
import java.util.Optional;

public interface BaseService<T,ID> {
    List<T> findAll();
    Optional<T> findById(ID id);
    Vocabulary save(T t);
    boolean existsById(ID id);
    long count();
    void deleteById(ID id);
    void delete(T t);
}
