package com.PI.API.service.impl;


import com.PI.API.exception.ResourceNotFoundException;
import com.PI.API.model.Picture;
import com.PI.API.model.dto.PictureDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.PI.API.model.Category;

import com.PI.API.model.dto.CategoryDTO;
import com.PI.API.repository.ICategoryRepository;
import com.PI.API.service.ICategoryService;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private ICategoryRepository categoryRepository;

    @Autowired
    ObjectMapper mapper;



    public CategoryDTO saveCategory(CategoryDTO categoryDTO){

        Category category = mapperClass(categoryDTO);
        Category category1 = categoryRepository.save(category);
        //categoryRepository.save(category);

        return mapperDTO(category1);
    }



    @Override
    public CategoryDTO save(CategoryDTO categoryDTO) {
        CategoryDTO categoryDTOSave=saveCategory(categoryDTO);
        return categoryDTOSave;
    }



    @Override
    public CategoryDTO findById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Category","id",id));
        CategoryDTO categoryDTO = mapperDTO(category);
        return categoryDTO;
    }


    @Override
    public CategoryDTO update(CategoryDTO categoryDTO, Long id) {
        Category category=categoryRepository.findById(id)
                        .orElseThrow(()->new ResourceNotFoundException("Category", "id",id));
       CategoryDTO categoryDTOSave=saveCategory(categoryDTO);
       return categoryDTOSave;
    }

    @Override
    public Set<CategoryDTO> findAll() {
        List<Category> categoriesList = categoryRepository.findAll();
        Set<CategoryDTO> categoriesDTOS = new HashSet<CategoryDTO>();

        for(Category category: categoriesList){
            categoriesDTOS.add(mapperDTO(category));
        }

        return categoriesDTOS;
    }




    @Override
    public void delete(Long id) {
        categoryRepository.findById(id)
                        .orElseThrow(()->new ResourceNotFoundException("Category","id",id));
        categoryRepository.deleteById(id);
    }
    @Override
    public CategoryDTO findByTitle(String title) {
        Category category=categoryRepository.findByTitle(title)
                .orElseThrow();
        return mapperDTO(category);
    }

    //mappers

    private CategoryDTO mapperDTO(Category category){
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.registerModule(new Jdk8Module()).registerModule(new JavaTimeModule());
        CategoryDTO categoryDTO=mapper.convertValue(category,CategoryDTO.class);
        return categoryDTO;
    }
    private Category mapperClass(CategoryDTO categoryDTO){
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.registerModule(new Jdk8Module()).registerModule(new JavaTimeModule());
        Category category=mapper.convertValue(categoryDTO,Category.class);
        return category;
    }


}
