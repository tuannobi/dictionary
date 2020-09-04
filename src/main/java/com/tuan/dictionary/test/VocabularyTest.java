package com.tuan.dictionary.test;

import com.tuan.dictionary.partofspeech.PartOfSpeech;
import com.tuan.dictionary.partofspeech.PartOfSpeechService;
import com.tuan.dictionary.vocabulary.Vocabulary;
import com.tuan.dictionary.vocabulary.VocabularyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.swing.text.html.Option;
import java.util.Optional;

@Controller
@RequestMapping("/test")
public class VocabularyTest {
    @Autowired
    private VocabularyService vocabularyService;

    @Autowired
    private PartOfSpeechService partOfSpeechService;

    @GetMapping("/addVocabulary")
    @ResponseBody
    public String addVocabulary(){
        Vocabulary vocabulary=new Vocabulary();
        vocabulary.setEnglishWord("dffdsdfsdf");
//        Optional<PartOfSpeech> partOfSpeechOption=partOfSpeechService.findById("Noun");
//        vocabulary.setPartOfSpeech(partOfSpeechOption.get());
        PartOfSpeech partOfSpeech=new PartOfSpeech();
        partOfSpeech.setName("Lonely");
        vocabulary.setPartOfSpeech(partOfSpeech);
        vocabularyService.save(vocabulary);
        return "Thanh cong";
    }

    @GetMapping("/deleteVocabulary")
    @ResponseBody
    public void deleteVocabulary(){
        vocabularyService.deleteById(11L);
    }
}
