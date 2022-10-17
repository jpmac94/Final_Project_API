package com.PI.API.controller;

import com.PI.API.model.dto.CityDTO;
import com.PI.API.model.dto.CountryDTO;
import com.PI.API.service.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/country")
public class CountryController {

    @Autowired
    private ICountryService countryService;

    @PostMapping("/save")
    public ResponseEntity<CountryDTO> save(@Validated @Valid @RequestBody CountryDTO countryDTO){
        return ResponseEntity.ok(countryService.save(countryDTO));
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<CountryDTO> findById(@PathVariable("id") Long id){
        return ResponseEntity.ok(countryService.findById(id));
    }
    @GetMapping("/findAll")
    public ResponseEntity<Set<CountryDTO>> findAll(){
        return ResponseEntity.ok(countryService.findAll());
    }
    @PutMapping("/updateById/{id}")
    public ResponseEntity<CountryDTO> updateById(@Validated @Valid @RequestBody CountryDTO countryDTO,@PathVariable("id") Long id){
        return ResponseEntity.ok(countryService.update(countryDTO,id));
    }
    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id){
        countryService.delete(id);
        return ResponseEntity.ok().body("Country with id: "+id+" deleted");
    }
}
