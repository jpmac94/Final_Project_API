package com.PI.API.service;

import com.PI.API.model.dto.CityDTO;

import java.util.Set;

public interface ICityService {

    CityDTO save(CityDTO cityDTO);
    CityDTO findById(Long id);
    Set<CityDTO> findAll();
    CityDTO update(CityDTO cityDTO, Long id);
    void deleteById(Long id);
}
