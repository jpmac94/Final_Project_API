package com.PI.API.service.impl;

import com.PI.API.exception.BookingException;
import com.PI.API.exception.ResourceNotFoundException;
import com.PI.API.model.Product;
import com.PI.API.model.Rating;
import com.PI.API.model.dto.ProductDTO;
import com.PI.API.model.dto.RatingDTO;
import com.PI.API.repository.IRatingRepository;
import com.PI.API.service.IRatingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RatingService implements IRatingService {

    @Autowired
    private IRatingRepository ratingRepository;

    @Autowired
    private ObjectMapper mapper;


    @Override
    public RatingDTO save(RatingDTO ratingDTO) {
        Rating rating=mapperClass(ratingDTO);
        Rating ratingSave=ratingRepository.save(rating);
        return mapperDTO(ratingSave);
    }

    @Override
    public RatingDTO findById(Long id) {
        Rating rating=ratingRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Rating","id",id));
        return mapperDTO(rating);
    }

    @Override
    public Set<RatingDTO> findAll() {
        List<Rating>ratingList=ratingRepository.findAll();
        Set<RatingDTO>ratingDTOSet=new HashSet<>();
        for (Rating rating : ratingList) {
            ratingDTOSet.add(mapperDTO(rating));
        }
        return ratingDTOSet;
    }

    @Override
    public RatingDTO update(RatingDTO ratingDTO, Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {
        Rating rating=ratingRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Rating","id",id));
        ratingRepository.deleteById(rating.getId());
    }
    @Override
    public Set<RatingDTO> findRatingByProduct(Long id){
        Set<Rating> ratingSet=ratingRepository.findRatingByProduct(id);
        Set<RatingDTO>ratingDTOSet=new HashSet<>();
        if (ratingSet.isEmpty())
            throw new BookingException(HttpStatus.NOT_FOUND,"No ratings have been found for the indicated id product.");
        for (Rating rating : ratingSet) {
            ratingDTOSet.add(mapperDTO(rating));
        }
        return ratingDTOSet;
    }

    //mappers

    private RatingDTO mapperDTO(Rating rating){
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.registerModule(new Jdk8Module()).registerModule(new JavaTimeModule());
        RatingDTO ratingDTO=mapper.convertValue(rating,RatingDTO.class);
        return ratingDTO;
    }

    private Rating mapperClass(RatingDTO ratingDTO){
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.registerModule(new Jdk8Module()).registerModule(new JavaTimeModule());
        Rating rating=mapper.convertValue(ratingDTO,Rating.class);
        return rating;
    }
}
