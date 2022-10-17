package com.PI.API.repository;

import com.PI.API.model.Product;
import com.PI.API.model.dto.ProductDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface IProductRepository extends JpaRepository<Product,Long> {
    @Query("FROM Product p WHERE p.city.name = ?1")
    Set<Product> findProductByCity(String city);

    @Query("FROM Product p WHERE p.category.id = ?1")
    Set<Product> findProductByCategory(Long id);


    @Query("select p from City c left join  Product p on c.id=p.city.id  left join Reservation r on p.id=r.product.id left join Category ca on p.category.id=ca.id where ca.id= :idCategory and c.name= :nameCity and (not (r.startDate= :startDate  and r.endDate= :endDate) or r.product.id is null)")
    List<Product> findProductByIdCategoryAndNameCityAndDates(@Param("idCategory") Long idCategory, @Param("nameCity") String nameCity, @Param("startDate") String startDate, @Param("endDate") String endDate);

    @Query("SELECT p FROM City c LEFT JOIN Product p on c.id=p.city.id  LEFT JOIN Reservation r on p.id=r.product.id WHERE c.name= :nameCity AND (not (r.startDate= :startDate  and r.endDate= :endDate) OR r.product.id IS NULL)")
    Set<Product> findProductByNameCityAndDate(@Param("nameCity") String nameCity, @Param("startDate") String startDate,@Param("endDate") String endDate);

    @Query("select p from  Product p  left join Reservation r on p.id=r.product.id where  not (r.startDate= :startDate  and r.endDate= :endDate) or r.product.id is null")
    Set<Product> findProductByDate(@Param("startDate") String startDate,@Param("endDate") String endDate);


    @Query("select p from City c inner join  Product p on c.id=p.city.id  inner join Category ca on p.category.id=ca.id where ca.id= :idCategory and c.name= :nameCity")
    List<Product> findProductByIdCategoryAndCity(@Param("idCategory") Long idCategory,@Param("nameCity") String nameCity);

    @Query("select p from Category ca inner join Product p on p.category.id=ca.id  left join Reservation r on p.id=r.product.id  where ca.id= :idCategory and (not (r.startDate= :startDate  and r.endDate= :endDate) or r.product.id is null)")
    List<Product> findProductByIdCategoryAndDates(@Param("idCategory") Long idCategory,@Param("startDate") String startDate,@Param("endDate") String endDate);


}
