package com.PI.API.repository;

import com.PI.API.model.State;
import com.PI.API.model.dto.StateDTO;
import com.PI.API.service.ICrudService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStateRepository extends JpaRepository<State, Long> {
}
