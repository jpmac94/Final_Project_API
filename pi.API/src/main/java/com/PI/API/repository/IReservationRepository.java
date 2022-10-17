package com.PI.API.repository;

import com.PI.API.model.Favourite;
import com.PI.API.model.Product;
import com.PI.API.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Repository
public interface IReservationRepository extends JpaRepository<Reservation,Long> {

    @Query("select r from Product p inner join Reservation r on p.id=r.product.id  where r.product.id= :idProduct")
    List<Reservation> findReservationByIdProduct(@Param("idProduct") Long idProduct);

    @Query("select f from Reservation f where f.usuario.nombreUsuario= :userName")
    List<Reservation> findReservationByUserName(@Param("userName") String userName);
}
