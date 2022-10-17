package com.PI.API.controller;

import com.PI.API.model.dto.CountryDTO;
import com.PI.API.model.dto.StateDTO;
import com.PI.API.service.IStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/state")
public class StateController {
    @Autowired
    private IStateService stateService;

    @PostMapping("/save")
    public ResponseEntity<StateDTO> save(@Validated @Valid @RequestBody StateDTO stateDTO){
        return ResponseEntity.ok(stateService.save(stateDTO));
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<StateDTO> findById(@PathVariable("id") Long id){
        return ResponseEntity.ok(stateService.findById(id));
    }
    @GetMapping("/findAll")
    public ResponseEntity<Set<StateDTO>> findAll(){
        return ResponseEntity.ok(stateService.findAll());
    }
    @PutMapping("/updateById/{id}")
    public ResponseEntity<StateDTO> updateById(@Validated @Valid @RequestBody StateDTO stateDTO,@PathVariable("id") Long id){
        return ResponseEntity.ok(stateService.update(stateDTO,id));
    }
    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id){
        stateService.delete(id);
        return ResponseEntity.ok().body("State with id: "+id+" deleted");
    }
}
