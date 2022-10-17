package com.PI.API.repository;

import com.PI.API.model.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPictureRepository extends JpaRepository<Picture,Long> {
}
