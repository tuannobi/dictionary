package com.tuan.dictionary.vocabulary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<Vocabulary> findById(Integer id) {
        return vocabularyRepository.findById(id);
    }
}
