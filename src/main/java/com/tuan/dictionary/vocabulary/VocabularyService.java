package com.tuan.dictionary.vocabulary;

import java.util.List;
import java.util.Optional;

public interface VocabularyService {
    List<Vocabulary> findAll();
    Optional<Vocabulary> findById(Integer id);
}
