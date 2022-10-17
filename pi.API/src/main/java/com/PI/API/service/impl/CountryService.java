package com.PI.API.service.impl;

import com.PI.API.exception.ResourceNotFoundException;
import com.PI.API.model.Country;
import com.PI.API.model.Product;
import com.PI.API.model.dto.CountryDTO;
import com.PI.API.model.dto.ProductDTO;
import com.PI.API.repository.ICountryRepository;
import com.PI.API.service.ICountryService;
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
public class CountryService implements ICountryService {
    @Autowired
    private ICountryRepository countryRepository;
    @Autowired
    private ObjectMapper mapper;
    @Override
    public CountryDTO save(CountryDTO countryDTO) {
        Country country=mapperClass(countryDTO);
        Country countrySave=countryRepository.save(country);
        return mapperDTO(countrySave);
    }

    @Override
    public CountryDTO findById(Long id) {
        Country country=countryRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Country","id",id));
        return mapperDTO(country);
    }

    @Override
    public Set<CountryDTO> findAll() {
        Set<CountryDTO>countryDTOSet=new HashSet<>();
        List<Country>countryList=countryRepository.findAll();
        for (Country country : countryList) {
            countryDTOSet.add(mapperDTO(country));
        }
        return countryDTOSet;
    }

    @Override
    public CountryDTO update(CountryDTO countryDTO, Long id) {
        Country country=countryRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Country","id",id));
        country.setName(countryDTO.getName());
        Country countrySave=countryRepository.save(country);
        return mapperDTO(countrySave);
    }

    @Override
    public void delete(Long id) {
        Country country=countryRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Country","id",id));
        countryRepository.deleteById(id);
    }
    //mappers

    private CountryDTO mapperDTO(Country country){
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.registerModule(new Jdk8Module()).registerModule(new JavaTimeModule());
        CountryDTO countryDTO=mapper.convertValue(country,CountryDTO.class);
        return countryDTO;
    }

    private Country mapperClass(CountryDTO countryDTO){
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.registerModule(new Jdk8Module()).registerModule(new JavaTimeModule());
        Country country=mapper.convertValue(countryDTO,Country.class);
        return country;
    }
}
