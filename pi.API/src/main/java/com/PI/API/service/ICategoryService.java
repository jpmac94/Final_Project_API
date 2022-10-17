package com.PI.API.service;

import com.PI.API.model.Category;
import com.PI.API.model.dto.CategoryDTO;
import com.PI.API.model.dto.ProductDTO;

import java.util.Set;

public interface ICategoryService extends ICrudService<CategoryDTO> {

    CategoryDTO findByTitle(String title);
}
