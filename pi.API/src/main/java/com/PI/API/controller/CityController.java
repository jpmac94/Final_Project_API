package com.PI.API.controller;


import com.PI.API.model.dto.CityDTO;
import com.PI.API.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/city")
public class CityController {

    @Autowired
    private ICityService cityService;
    @PostMapping("/save")
    public ResponseEntity<CityDTO> save(@Validated @Valid @RequestBody CityDTO cityDTO){
        return ResponseEntity.ok(cityService.save(cityDTO));
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<CityDTO> findById(@PathVariable("id") Long id){
        return ResponseEntity.ok(cityService.findById(id));
    }
    @GetMapping("/findAll")
    public ResponseEntity<Set<CityDTO>> findAll(){
        return ResponseEntity.ok(cityService.findAll());
    }
    @PutMapping("/updateById/{id}")
    public ResponseEntity<CityDTO> updateById(@Validated @Valid @RequestBody CityDTO cityDTO,@PathVariable("id") Long id){
        return ResponseEntity.ok(cityService.update(cityDTO,id));
    }
    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id){
        cityService.deleteById(id);
        return ResponseEntity.ok().body("City with id: "+id+" deleted");
    }
}
