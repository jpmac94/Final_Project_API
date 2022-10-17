package com.PI.API.controller;


import com.PI.API.model.dto.FavouriteDTO;
import com.PI.API.service.IFavouriteService;
import com.PI.API.service.impl.FavouriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/favourite")
public class FavouriteController {

    @Autowired
    private FavouriteService favouriteService;


    @PostMapping("/save")
    public ResponseEntity<FavouriteDTO> save(@RequestBody FavouriteDTO favouriteDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(favouriteService.save(favouriteDTO));
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<FavouriteDTO> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(favouriteService.findById(id));
    }

    @GetMapping("/findAll")
    public ResponseEntity<Set<FavouriteDTO>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(favouriteService.findAll());
    }

    @PutMapping("/update")
    public ResponseEntity<FavouriteDTO> update(@RequestBody FavouriteDTO favouriteDTO, Long id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(favouriteService.update(favouriteDTO,id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        favouriteService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("eliminado");
    }

    @GetMapping("/findFavouriteByUserName")
    public ResponseEntity<List<FavouriteDTO>> findFavouriteByUserName(@RequestParam("userName") String userName) {
        return new ResponseEntity<>(favouriteService.findFavouriteByUserName(userName),HttpStatus.OK); }
}
