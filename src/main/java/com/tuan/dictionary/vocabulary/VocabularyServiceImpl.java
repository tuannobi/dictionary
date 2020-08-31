package com.tuan.dictionary.vocabulary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VocabularyServiceImpl implements VocabularyService {

    private VocabularyRepository vocabularyRepository;

    @Autowired
    public VocabularyServiceImpl(VocabularyRepository vocabularyRepository){
        this.vocabularyRepository=vocabularyRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Vocabulary> findAll() {
        return (List<Vocabulary>) vocabularyRepository.findAll();
    }
}
