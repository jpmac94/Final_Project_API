package com.PI.API.service;

import com.PI.API.model.Favourite;
import com.PI.API.model.dto.FavouriteDTO;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IFavouriteService extends ICrudService<FavouriteDTO> {

    List<FavouriteDTO> findFavouriteByUserName(String userName);
}
