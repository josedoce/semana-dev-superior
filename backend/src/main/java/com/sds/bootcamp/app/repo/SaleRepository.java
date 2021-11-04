package com.sds.bootcamp.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sds.bootcamp.app.dto.SaleSuccessDTO;
import com.sds.bootcamp.app.dto.SaleSumDTO;
import com.sds.bootcamp.app.model.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {
	//essa query trará a soma de vendas por vendedor.
	@Query("SELECT new com.sds.bootcamp.app.dto.SaleSumDTO(obj.seller, SUM(obj.amount))"
			+ " FROM Sale AS obj GROUP BY obj.seller")
	List<SaleSumDTO> amountGroupedBySeller();
	
	
	//essa query trará a taxa de sucesso por vendedor.
		@Query("SELECT new com.sds.bootcamp.app.dto.SaleSuccessDTO(obj.seller, SUM(obj.visited), SUM(obj.deals))"
				+ " FROM Sale AS obj GROUP BY obj.seller")
		List<SaleSuccessDTO> successGroupedBySeller();
}
