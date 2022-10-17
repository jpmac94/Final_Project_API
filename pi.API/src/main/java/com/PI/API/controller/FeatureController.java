package com.PI.API.controller;

import com.PI.API.model.dto.CityDTO;
import com.PI.API.model.dto.FeatureDTO;
import com.PI.API.service.IFeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/feature")
public class FeatureController {
    @Autowired
    private IFeatureService featureService;


    @PostMapping("/save")
    public ResponseEntity<FeatureDTO> save(@Validated @Valid @RequestBody FeatureDTO featureDTO){
        return new ResponseEntity<>(featureService.save(featureDTO), HttpStatus.OK);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<FeatureDTO> findById(@PathVariable("id") Long id){
        return ResponseEntity.ok(featureService.findById(id));
    }
    @GetMapping("/findAll")
    public ResponseEntity<Set<FeatureDTO>> findAll(){
        return ResponseEntity.ok(featureService.findAll());
    }
    @PutMapping("/updateById/{id}")
    public ResponseEntity<FeatureDTO> updateById(@Validated @Valid @RequestBody FeatureDTO featureDTO,@PathVariable("id") Long id){
        return ResponseEntity.ok(featureService.update(featureDTO,id));
    }
    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id){
        featureService.delete(id);
        return ResponseEntity.ok().body("Feature with id: "+id+" deleted");
    }
}
