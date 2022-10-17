package com.PI.API.service.impl;

import com.PI.API.exception.BookingException;
import com.PI.API.exception.ResourceNotFoundException;
import com.PI.API.model.Favourite;
import com.PI.API.model.dto.FavouriteDTO;
import com.PI.API.repository.IFavouriteRepository;
import com.PI.API.service.IFavouriteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FavouriteService implements IFavouriteService {

    @Autowired
    private IFavouriteRepository iFavouriteRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public FavouriteDTO mapEntity(Favourite favourite){
        objectMapper.registerModule(new Jdk8Module()).registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);
        return objectMapper.convertValue(favourite,FavouriteDTO.class);
    }

    public Favourite mapDto( FavouriteDTO favouriteDTO){
        objectMapper.registerModule(new Jdk8Module()).registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);
        return objectMapper.convertValue(favouriteDTO,Favourite.class);
    }

    @Override
    public FavouriteDTO save(FavouriteDTO favouriteDTO) {
        return mapEntity(iFavouriteRepository.save(mapDto(favouriteDTO)));
    }

    @Override
    public FavouriteDTO findById(Long id) {
        return mapEntity(iFavouriteRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Favourite","id",id)));
    }

    @Override
    public Set<FavouriteDTO> findAll() {
        return iFavouriteRepository.findAll().stream().map(item->mapEntity(item)).collect(Collectors.toSet());
    }

    @Override
    public FavouriteDTO update(FavouriteDTO favouriteDTO, Long id) {
        iFavouriteRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Favourite","id",id));
        return mapEntity(iFavouriteRepository.save(mapDto(favouriteDTO)));
    }

    @Override
    public void delete(Long id) {
        iFavouriteRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Favourite","id",id));
        iFavouriteRepository.deleteById(id);
    }

    @Override
    public List<FavouriteDTO> findFavouriteByUserName(String userName) {
        List<FavouriteDTO> favouriteDTOList=new ArrayList<>();
        List<Favourite> favouriteList=iFavouriteRepository.findFavouriteByUserName(userName);
        if (favouriteList.isEmpty()) throw new BookingException(HttpStatus.NOT_FOUND,"No favourites have been found for the indicated userName.");
        for (Favourite favourite : favouriteList) { favouriteDTOList.add(mapEntity(favourite)); }
        return favouriteDTOList;
    }
}
