package com.tuan.dictionary.controller.rest.admin;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tuan.dictionary.collection.Collection;
import com.tuan.dictionary.collection.CollectionService;
import com.tuan.dictionary.vocabulary.Vocabulary;
import com.tuan.dictionary.vocabulary.VocabularyRepository;
import com.tuan.dictionary.vocabulary.VocabularyService;

@RestController
@RequestMapping("/rest/admin/collections")
public class AdminCollectionVocabularyRestController {
	private CollectionService collectionService;

    @Autowired
    public AdminCollectionVocabularyRestController(CollectionService collectionService){
        this.collectionService=collectionService;
    }

    @GetMapping
    public List<Collection> getAll(){
        return collectionService.findAll();
    }


    @GetMapping("/{id}")
    public Optional<Collection> get(@PathVariable("id") String id){
        return collectionService.findById(id);
    }

    @PostMapping
    public Object add(@RequestBody Collection collection, HttpServletResponse httpServletResponse){
//        if(vocabulary.getId()==null){
//            vocabularyService.save(vocabulary);
//            return "thanh cong";
//        }else{
//            throw new ServiceException("Không được nhập id");
//        }
        collectionService.addCollection(collection);
        return "thanh cong";
    }

    @PutMapping
    public Object update(HttpServletResponse response,@RequestBody Collection collection){
        if(collection.getName()!=null){
        	collectionService.updateCollection(collection);
            return "cap nhat thanh cong";
        }else{
//            throw new ServiceException("Phải nhập id");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return "fail";
        }
    }

    @DeleteMapping("/{id}")
    public Object delete(HttpServletResponse response,@PathVariable("id") String id){
        try{
        	collectionService.deleteById(id);
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
