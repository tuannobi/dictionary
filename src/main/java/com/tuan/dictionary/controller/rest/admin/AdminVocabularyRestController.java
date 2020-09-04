package com.tuan.dictionary.controller.rest.admin;

import com.tuan.dictionary.exception.ServiceException;
import com.tuan.dictionary.vocabulary.Vocabulary;
import com.tuan.dictionary.vocabulary.VocabularyRepository;
import com.tuan.dictionary.vocabulary.VocabularyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest/admin/vocabularies")
public class AdminVocabularyRestController {

    private VocabularyService vocabularyService;

    @Autowired
    public AdminVocabularyRestController(VocabularyService vocabularyService, VocabularyRepository vocabularyRepository){
        this.vocabularyService=vocabularyService;
    }

    @GetMapping
    public List<Vocabulary> getAll(){
        return vocabularyService.findAll();
    }


    @GetMapping("/{id}")
    public Optional<Vocabulary> get(@PathVariable("id") Long id){
        return vocabularyService.findById(id);
    }

    @PostMapping
    public Object add(@RequestBody Vocabulary vocabulary){
        if(vocabulary.getId()==null){
            vocabularyService.save(vocabulary);
            return "thanh cong";
        }else{
            throw new ServiceException("Không được nhập id");
        }
    }

    @PutMapping
    public Object update(HttpServletResponse response,@RequestBody Vocabulary vocabulary){
        if(vocabulary.getId()!=null){
            vocabularyService.save(vocabulary);
            return "cap nhat thanh cong";
        }else{
//            throw new ServiceException("Phải nhập id");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return "fail";
        }
    }

    @DeleteMapping("/{id}")
    public Object delete(HttpServletResponse response,@PathVariable("id") Long id){
        try{
            vocabularyService.deleteById(id);
            return "delete thanh cong";
        }catch (EmptyResultDataAccessException emptyResultDataAccessException){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return emptyResultDataAccessException.getMessage();
        }catch (Exception exception){
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return exception.getMessage();
        }
    }
}
