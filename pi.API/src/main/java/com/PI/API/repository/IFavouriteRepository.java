package com.PI.API.repository;

import com.PI.API.model.Favourite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IFavouriteRepository extends JpaRepository<Favourite,Long> {


//    @Query("select f from Favourite f where f.usuario.username= :username")
//    Optional<List<Favourite>> findFavouriteByUsername(@Param("username") String username);
//
//    @Query("select f from Favourite f where f.product.id= :id")
//    Optional<Favourite> findFavouriteByProductId(@Param("id") Long id);

    @Query("select f from Favourite f where f.usuario.nombreUsuario= :userName")
     List<Favourite> findFavouriteByUserName(@Param("userName") String userName);

}
