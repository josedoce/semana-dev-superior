package com.sds.bootcamp.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sds.bootcamp.app.model.Seller;

public interface SellerRepository extends JpaRepository<Seller, Long>{

}
