package com.PI.API.service.impl;

import com.PI.API.exception.ResourceNotFoundException;
import com.PI.API.model.City;
import com.PI.API.model.dto.CityDTO;
import com.PI.API.repository.ICityRepository;
import com.PI.API.service.ICityService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CityService implements ICityService {
    @Autowired
    private ICityRepository cityRepository;
    @Autowired
    private ObjectMapper mapper;


    @Override
    public CityDTO save(CityDTO cityDTO) {
        City city=mapperClass(cityDTO);
        City citySave=cityRepository.save(city);
        CityDTO cityDTOResponse=mapperDTO(citySave);
        return cityDTOResponse;
    }

    @Override
    public CityDTO findById(Long id) {
        City city=cityRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("City","id",id));
        return mapperDTO(city);
    }

    @Override
    public Set<CityDTO> findAll() {
        Set<CityDTO> cityDTOS=new HashSet<>();
        List<City> cities=cityRepository.findAll();
        cities.forEach(city -> {
            cityDTOS.add(mapperDTO(city));
        });
        return cityDTOS;
    }

    @Override
    public CityDTO update(CityDTO cityDTO, Long id) {
        City city=cityRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("City", "id",id));
        city.setName(cityDTO.getName());
        City cityUpdate=cityRepository.save(city);
        return mapperDTO(cityUpdate);
    }

    @Override
    public void deleteById(Long id) {
        City city=cityRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("City", "id",id));
        cityRepository.deleteById(city.getId());

    }

    //mappers

    private CityDTO mapperDTO(City city){
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.registerModule(new Jdk8Module()).registerModule(new JavaTimeModule());
        CityDTO cityDTO=mapper.convertValue(city,CityDTO.class);
        return cityDTO;
    }
    private City mapperClass(CityDTO cityDTO){
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.registerModule(new Jdk8Module()).registerModule(new JavaTimeModule());
        City city=mapper.convertValue(cityDTO,City.class);
        return city;
    }
}
