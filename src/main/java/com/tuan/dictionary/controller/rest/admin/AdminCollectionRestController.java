package com.tuan.dictionary.controller.rest.admin;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import com.tuan.dictionary.exception.ControllerException;
import com.tuan.dictionary.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
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
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/rest/admin/collections")
public class AdminCollectionRestController {
	private CollectionService collectionService;

    @Autowired
    public AdminCollectionRestController(CollectionService collectionService){
        this.collectionService=collectionService;
    }

    @GetMapping
    public List<Collection> getAll(){
        return collectionService.findAll();
    }


    @GetMapping("/{id}")
    public Optional<Collection> get(@PathVariable("id") Long id){
        return collectionService.findById(id);
    }

    @PostMapping
    public void add(@RequestBody Collection collection, HttpServletResponse httpServletResponse){
        if(collection.getId()!=null){
            throw new ControllerException("Collection Id must be null");
        }
        collectionService.addCollection(collection);
    }

    @PutMapping
    public void update(@RequestBody Collection collection){
        try{
            collectionService.updateCollection(collection);
        }catch (ServiceException ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,ex.getMessage(),ex);
        }catch (Exception ex){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,ex.getMessage(),ex);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(HttpServletResponse response,@PathVariable("id") Long id){
        collectionService.deleteById(id);
    }
}
