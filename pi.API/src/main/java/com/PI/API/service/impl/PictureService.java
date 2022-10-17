package com.PI.API.service.impl;

import com.PI.API.exception.ResourceNotFoundException;
import com.PI.API.model.Picture;
import com.PI.API.model.dto.CityDTO;
import com.PI.API.model.dto.PictureDTO;
import com.PI.API.repository.IPictureRepository;
import com.PI.API.service.IPictureService;
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
public class PictureService implements IPictureService {
    @Autowired
    private IPictureRepository pictureRepository;
    @Autowired
    private ObjectMapper mapper;
    @Override
    public PictureDTO save(PictureDTO pictureDTO) {
        Picture picture=mapperClass(pictureDTO);
        Picture pictureSave=pictureRepository.save(picture);
        PictureDTO pictureDTOResponse=mapperDTO(pictureSave);
        return pictureDTOResponse;
    }

    @Override
    public PictureDTO findById(Long id) {
        Picture picture=pictureRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Picture","id",id));
        PictureDTO pictureDTO=mapperDTO(picture);
        return pictureDTO;
    }

    @Override
    public Set<PictureDTO> findAll() {
        List<Picture> pictures=pictureRepository.findAll();
        Set<PictureDTO>pictureDTOS=new HashSet<>();
        for (Picture picture : pictures) {
            pictureDTOS.add(mapperDTO(picture));
        }
        return pictureDTOS;
    }

    @Override
    public PictureDTO update(PictureDTO pictureDTO, Long id) {
        Picture picture=pictureRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Picture","id",id));
        picture.setTitle(pictureDTO.getTitle());
        picture.setUrl(pictureDTO.getUrl());
        Picture pictureUpdate=pictureRepository.save(picture);
        PictureDTO pictureDTOResponse=mapperDTO(pictureUpdate);
        return pictureDTOResponse;
    }

    @Override
    public void delete(Long id) {
        pictureRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Picture","id",id));
        pictureRepository.deleteById(id);

    }

    //mappers

    private PictureDTO mapperDTO(Picture picture){
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.registerModule(new Jdk8Module()).registerModule(new JavaTimeModule());
        PictureDTO pictureDTO=mapper.convertValue(picture,PictureDTO.class);
        return pictureDTO;
    }
    private Picture mapperClass(PictureDTO pictureDTO){
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.registerModule(new Jdk8Module()).registerModule(new JavaTimeModule());
        Picture picture=mapper.convertValue(pictureDTO,Picture.class);
        return picture;
    }
}
