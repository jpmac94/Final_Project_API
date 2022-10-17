package com.PI.API.controller;

import com.PI.API.model.dto.CityDTO;
import com.PI.API.model.dto.PictureDTO;
import com.PI.API.service.IPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/picture")
public class PictureController {

    @Autowired
    private IPictureService pictureService;

    @PostMapping("/save")
    public ResponseEntity<PictureDTO> save(@Valid @RequestBody PictureDTO pictureDTO){
        return ResponseEntity.ok(pictureService.save(pictureDTO));
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<PictureDTO> findById(@PathVariable("id") Long id){
        return ResponseEntity.ok(pictureService.findById(id));
    }
    @GetMapping("/findAll")
    public ResponseEntity<Set<PictureDTO>> findAll(){
        return ResponseEntity.ok(pictureService.findAll());
    }
    @PutMapping("/updateById/{id}")
    public ResponseEntity<PictureDTO> updateById(@Valid @RequestBody PictureDTO pictureDTO,@PathVariable("id") Long id){
        return ResponseEntity.ok(pictureService.update(pictureDTO,id));
    }
    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id){
        return ResponseEntity.ok().body("Picture with id: "+id+" deleted");
    }
}
