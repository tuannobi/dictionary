package com.tuan.dictionary.controller.rest.admin;

import com.tuan.dictionary.vocabulary.Vocabulary;
import com.tuan.dictionary.vocabulary.VocabularyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/admin/vocabularies")
public class VocabularyRestController {

    private VocabularyService vocabularyService;

    @Autowired
    public VocabularyRestController(VocabularyService vocabularyService){
        this.vocabularyService=vocabularyService;
    }

    @GetMapping
    public List<Vocabulary> getAll(){
        return vocabularyService.findAll();
    }
}
