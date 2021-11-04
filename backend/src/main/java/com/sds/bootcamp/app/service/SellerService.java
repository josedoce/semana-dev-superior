package com.sds.bootcamp.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sds.bootcamp.app.dto.SellerDTO;
import com.sds.bootcamp.app.model.Seller;
import com.sds.bootcamp.app.repo.SellerRepository;

@Service
public class SellerService {
	@Autowired
	private SellerRepository sellerRepo;
	
	public List<SellerDTO> findAll(){
		List<Seller> sellers = sellerRepo.findAll();
		return sellers.stream()
				.map(x -> new SellerDTO(x))
				.collect(Collectors.toList());
	}
}
