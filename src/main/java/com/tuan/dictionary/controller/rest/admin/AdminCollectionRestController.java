package com.tuan.dictionary.controller.rest.admin;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.websocket.server.PathParam;

import com.tuan.dictionary.exception.ErrorResponse;
import com.tuan.dictionary.exception.ServiceException;
import com.tuan.dictionary.exception.SuccessResponse;
import com.tuan.dictionary.exception.ValidateException;
import com.tuan.validate.group.CollectionUpdate;
import com.tuan.validate.group.CollectionUpdateExcludeImage;

import javassist.expr.NewArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
    public ResponseEntity<?> add(@RequestBody @Valid Collection collection,BindingResult bindingResult){
        if(collection.getId()!=null){
            throw new ValidateException("Collection Id must be null");
        }
        if(bindingResult.hasErrors()) {
        	throw new ValidateException(bindingResult.getFieldError().toString());
        }
    	collectionService.addCollection(collection);	
        return new ResponseEntity<SuccessResponse>(new SuccessResponse("Add successfully!",HttpStatus.OK.value()),HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody @Validated(CollectionUpdate.class) Collection collection, 
    		BindingResult bindingResult){
    	if(bindingResult.hasErrors()) {
    		throw new ValidateException(bindingResult.getFieldError().toString());
    	}
        collectionService.updateCollection(collection);
        return new ResponseEntity<SuccessResponse>(new SuccessResponse("Update Successfully!",HttpStatus.OK.value()),HttpStatus.OK);
    }
    
    @PutMapping("/excludeImage")
    public ResponseEntity<?> updateExcludeImage(@RequestBody @Validated(CollectionUpdateExcludeImage.class) Collection collection, 
    		BindingResult bindingResult){
    	if(bindingResult.hasErrors()) {
    		throw new ValidateException(bindingResult.getFieldError().toString());
    	}
    	collectionService.updateExcludeImage(collection);
    	return new ResponseEntity<SuccessResponse>(new SuccessResponse("Update Successfully!",HttpStatus.OK.value()),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        collectionService.deleteById(id);
        return new ResponseEntity<SuccessResponse>(new SuccessResponse("Delete Successfully!",HttpStatus.OK.value()),HttpStatus.OK);
    }
    
    @GetMapping("/getUrlImage/{id}")
    public String getUrlImage(@PathVariable("id") Long id) {
    	return collectionService.getUrlImageById(id);
    }
}
