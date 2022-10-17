package com.PI.API.controller;

import com.PI.API.model.dto.RatingDTO;
import com.PI.API.service.IRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/rating")
public class RatingController {

    @Autowired
    private IRatingService ratingService;
//    @PreAuthorize("hasRole('USER')")
    @PostMapping("/save")
    public ResponseEntity<RatingDTO> saveRating(@Valid @RequestBody RatingDTO ratingDTO){

        return new ResponseEntity<>( ratingService.save(ratingDTO), HttpStatus.OK);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<RatingDTO> findRatingById(@PathVariable Long id) {
        RatingDTO productDTO = ratingService.findById(id);
        return new ResponseEntity<>(productDTO,HttpStatus.OK);
    }
    @GetMapping("/findAll")
    public ResponseEntity<Set<RatingDTO>> findAll(){
        Set<RatingDTO> ratingDTOS = ratingService.findAll();
        return new ResponseEntity<>(ratingDTOS, HttpStatus.OK);
    }

//    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity deleteRating (@PathVariable Long id){
        ratingService.delete(id);
        return new ResponseEntity<>("Rating with id " + id + " has been deleted",HttpStatus.OK);
    }
    @GetMapping("/findRatingByIdProduct/{id}")
    public ResponseEntity<Set<RatingDTO>> findRatingByIdProduct(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(ratingService.findRatingByProduct(id));
    }

}
