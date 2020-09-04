package com.tuan.dictionary.partofspeech;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PartOfSpeechRepository extends JpaRepository<PartOfSpeech,String> {
}
