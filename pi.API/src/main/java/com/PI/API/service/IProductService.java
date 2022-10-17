package com.PI.API.service;

import com.PI.API.model.Product;
import com.PI.API.model.dto.ProductDTO;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface IProductService extends ICrudService<ProductDTO> {

    Set<ProductDTO> findProductByCity(String city);
    Set<ProductDTO> findProductByCategory(Long id);

    Set<ProductDTO> findProductByNameCityAndDate(String nameCity, String startDate, String endDate);
    Set<ProductDTO> findProductByDate( String String, String endDate);
    List<ProductDTO> findProductByIdCategoryAndNameCityAndDates(Long idCategory, String nameCity, String startDate, String endDate);

    List<ProductDTO> findProductByIdCategoryAndCity(Long idCategory,String nameCity);
    List<ProductDTO> findProductByIdCategoryAndDates(Long idCategory,String startDate,String endDate);
}
