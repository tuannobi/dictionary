package com.tuan.dictionary.base;

import com.tuan.dictionary.vocabulary.Vocabulary;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public abstract class BaseServiceImpl<T,ID> implements BaseService<T,ID> {

    protected abstract CrudRepository<T,ID> getRepository();

    @Override
    @Transactional(readOnly = true)
    public List<T> findAll() {
        return (List<T>) getRepository().findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<T> findById(ID id) {
        return getRepository().findById(id);
    }

    @Override
    public Vocabulary save(T t) {
        return (Vocabulary) getRepository().save(t);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(ID id) {
        return getRepository().existsById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        return getRepository().count();
    }

    @Override
    public void deleteById(ID id) {
        getRepository().deleteById(id);
    }

    @Override
    public void delete(T t) {
        getRepository().delete(t);
    }
}
