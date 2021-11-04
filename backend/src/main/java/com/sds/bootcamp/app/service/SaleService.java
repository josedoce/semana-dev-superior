package com.sds.bootcamp.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sds.bootcamp.app.dto.SaleDTO;
import com.sds.bootcamp.app.dto.SaleSuccessDTO;
import com.sds.bootcamp.app.dto.SaleSumDTO;
import com.sds.bootcamp.app.model.Sale;
import com.sds.bootcamp.app.repo.SaleRepository;
import com.sds.bootcamp.app.repo.SellerRepository;

@Service
public class SaleService {
	
	@Autowired
	private SaleRepository saleRepo;
	
	@Autowired
	private SellerRepository sellerRepo;
	
	@Transactional(readOnly = true) //readOnly garante que não faça lock de escrita no bd
	public Page<SaleDTO> findAll(Pageable pageable){
		//ao fazer isso, to salvando na memória para evitar muitas consultar.
		sellerRepo.findAll();
		//com o parametro pageable, já é possivel paginar
		Page<Sale> sales = saleRepo.findAll(pageable);
		return sales.map(x->new SaleDTO(x));
	}
	
	@Transactional(readOnly = true)
	public List<SaleSumDTO> amountGroupedBySeller(){
		sellerRepo.findAll();
		return saleRepo.amountGroupedBySeller();
	}
	
	@Transactional(readOnly = true)
	public List<SaleSuccessDTO> successGroupedBySeller(){
		sellerRepo.findAll();
		return saleRepo.successGroupedBySeller();
	}
}
