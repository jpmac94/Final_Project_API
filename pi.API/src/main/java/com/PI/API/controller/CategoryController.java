package com.PI.API.controller;

import com.PI.API.service.ICategoryService;
import com.PI.API.model.dto.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    ICategoryService categoryService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@Valid @RequestBody CategoryDTO categoryDTO){

        return new ResponseEntity<>(categoryService.save(categoryDTO),HttpStatus.OK);

    }

    @GetMapping("/findById/{id}")
    public CategoryDTO findById(@PathVariable("id") Long id) {
        CategoryDTO cDTO= categoryService.findById(id);
        return cDTO;

    }

    @GetMapping("/findAll")
    public ResponseEntity<Set<CategoryDTO>> findCategories(){
        return ResponseEntity.ok(categoryService.findAll());

    }

    @PutMapping("/updateById/{id}")
    public ResponseEntity<CategoryDTO> categoryUpdate(@Valid @RequestBody CategoryDTO categoryDTO,@PathVariable("id") Long id) {
        CategoryDTO cDTO = categoryService.update(categoryDTO,id);
        return new ResponseEntity<>(cDTO, HttpStatus.OK);
        //return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<?> categoryDelete(@PathVariable("id") Long id) {
        categoryService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/findByTitle/{title}")
    public ResponseEntity<CategoryDTO> findByTitle(@PathVariable("title") String title){
        return new ResponseEntity<>(categoryService.findByTitle(title),HttpStatus.OK);
    }

}
