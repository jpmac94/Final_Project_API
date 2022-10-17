package com.PI.API.service;

import com.PI.API.model.dto.RatingDTO;

import java.util.Set;

public interface IRatingService extends ICrudService<RatingDTO>{

    Set<RatingDTO> findRatingByProduct(Long id);

}
