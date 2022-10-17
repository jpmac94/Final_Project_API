package com.PI.API.service.impl;

import com.PI.API.exception.ResourceNotFoundException;
import com.PI.API.model.City;
import com.PI.API.model.State;
import com.PI.API.model.dto.CityDTO;
import com.PI.API.model.dto.StateDTO;
import com.PI.API.repository.IStateRepository;
import com.PI.API.service.IStateService;
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
public class StateService implements IStateService {
    @Autowired
    private IStateRepository stateRepository;
    @Autowired
    private ObjectMapper mapper;


    @Override
    public StateDTO save(StateDTO stateDTO) {
        State state=mapperClass(stateDTO);
        State stateSave=stateRepository.save(state);
        StateDTO stateDTOResponse=mapperDTO(stateSave);
        return stateDTOResponse;

    }

    @Override
    public StateDTO findById(Long id) {
        State state=stateRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("State","id",id));
        return mapperDTO(state);
    }

    @Override
    public Set<StateDTO> findAll() {
        Set<StateDTO> stateDTOS=new HashSet<>();
        List<State> states=stateRepository.findAll();
        states.forEach(state -> {
            stateDTOS.add(mapperDTO(state));
        });
        return stateDTOS;
    }

    @Override
    public StateDTO update(StateDTO stateDTO, Long id) {
        State state=stateRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("State", "id",id));
        state.setName(stateDTO.getName());
        state.setCountry(stateDTO.getCountry());
        State stateUpdate=stateRepository.save(state);
        return mapperDTO(stateUpdate);
    }

    @Override
    public void delete(Long id) {
        State state=stateRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("State", "id",id));
        stateRepository.deleteById(state.getId());
    }


    //mappers

    private StateDTO mapperDTO(State state){
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.registerModule(new Jdk8Module()).registerModule(new JavaTimeModule());
        StateDTO stateDTO=mapper.convertValue(state,StateDTO.class);
        return stateDTO;
    }
    private State mapperClass(StateDTO stateDTO){
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.registerModule(new Jdk8Module()).registerModule(new JavaTimeModule());
        State state=mapper.convertValue(stateDTO,State.class);
        return state;
    }
}
