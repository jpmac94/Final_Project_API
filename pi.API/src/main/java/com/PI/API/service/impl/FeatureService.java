package com.PI.API.service.impl;

import com.PI.API.exception.ResourceNotFoundException;
import com.PI.API.model.Feature;
import com.PI.API.model.dto.FeatureDTO;
import com.PI.API.repository.IFeatureRepository;
import com.PI.API.service.IFeatureService;
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
public class FeatureService implements IFeatureService {
    @Autowired
    private IFeatureRepository featureRepository;
    @Autowired
    private ObjectMapper mapper;

    @Override
    public FeatureDTO save(FeatureDTO featureDTO) {
        Feature feature=mapperClass(featureDTO);
        Feature featureSave=featureRepository.save(feature);
        return mapperDTO(featureSave);
    }

    @Override
    public FeatureDTO findById(Long id) {
        Feature feature=featureRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Feature","id",id));
        return mapperDTO(feature);
    }

    @Override
    public Set<FeatureDTO> findAll() {
        List<Feature>features=featureRepository.findAll();
        Set<FeatureDTO>featureDTOS=new HashSet<>();
        for (Feature feature : features) {
            featureDTOS.add(mapperDTO(feature));
        }
        return featureDTOS;
    }

    @Override
    public FeatureDTO update(FeatureDTO featureDTO, Long id) {
        Feature feature=featureRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Feature","id",id));
        feature.setName(featureDTO.getName());
        Feature featureSave=featureRepository.save(feature);
        return mapperDTO(featureSave);
    }

    @Override
    public void delete(Long id) {
        featureRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Feature","id",id));
        featureRepository.deleteById(id);
    }

    //mappers

    private FeatureDTO mapperDTO(Feature feature){
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.registerModule(new Jdk8Module()).registerModule(new JavaTimeModule());
        FeatureDTO featureDTO=mapper.convertValue(feature,FeatureDTO.class);
        return featureDTO;
    }
    private Feature mapperClass(FeatureDTO featureDTO){
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.registerModule(new Jdk8Module()).registerModule(new JavaTimeModule());
        Feature feature=mapper.convertValue(featureDTO,Feature.class);
        return feature;
    }


}
