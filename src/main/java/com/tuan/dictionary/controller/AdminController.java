package com.tuan.dictionary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tuan.dictionary.collection.Collection;
import com.tuan.dictionary.collection.CollectionService;

@Controller
@RequestMapping("/web/admin/collections")
public class AdminController {
	
	private CollectionService collectionService;
	
	@Autowired
	public AdminController(CollectionService collectionService) {
		this.collectionService=collectionService;
	}
	
    @GetMapping
    public String showIndexCollectionForm(){
        return "admin/collection";
    }
}
