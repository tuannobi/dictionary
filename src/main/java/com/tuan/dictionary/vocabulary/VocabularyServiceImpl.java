package com.tuan.dictionary.vocabulary;

import com.tuan.dictionary.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class VocabularyServiceImpl extends BaseServiceImpl<Vocabulary,Long> implements VocabularyService {

    private VocabularyRepository vocabularyRepository;

    @Autowired
    public VocabularyServiceImpl(VocabularyRepository vocabularyRepository) {
        this.vocabularyRepository = vocabularyRepository;
    }

    @Override
    protected CrudRepository<Vocabulary, Long> getRepository() {
        return vocabularyRepository;
    }

}
