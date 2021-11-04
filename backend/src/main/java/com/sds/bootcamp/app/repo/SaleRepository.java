package com.sds.bootcamp.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sds.bootcamp.app.model.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {

}
