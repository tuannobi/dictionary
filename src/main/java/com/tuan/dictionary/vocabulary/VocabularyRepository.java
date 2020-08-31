package com.tuan.dictionary.vocabulary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface VocabularyRepository extends CrudRepository<Vocabulary,Integer> {

}
