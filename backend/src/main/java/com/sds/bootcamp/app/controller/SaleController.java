package com.sds.bootcamp.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sds.bootcamp.app.dto.SaleDTO;
import com.sds.bootcamp.app.dto.SaleSuccessDTO;
import com.sds.bootcamp.app.dto.SaleSumDTO;
import com.sds.bootcamp.app.service.SaleService;

@RestController
@RequestMapping(value="/sales")
public class SaleController {
	@Autowired
	private SaleService saleService;
	
	@GetMapping
	public ResponseEntity<Page<SaleDTO>> findAll(Pageable pageable){
		/*em pageable, por padrão ele aceita esses queryParams:: 
		 * ?page=<pagina> -> número da página
		 * ?size=<itens> -> número de itens por página.
		 * ?sort=<pelo: nome|id|data>,<ordem: desc> -> ordena pelo atributo x de objeto.
		*/
		Page<SaleDTO> list = saleService.findAll(pageable);
		return ResponseEntity.ok(list);
	}
	
	@GetMapping(value="/amount-by-seller")
	public ResponseEntity<List<SaleSumDTO>> amountGroupedBySeller(){
		List<SaleSumDTO> list = saleService.amountGroupedBySeller();
		return ResponseEntity.ok(list);
	}
	
	@GetMapping(value="/success-by-seller")
	public ResponseEntity<List<SaleSuccessDTO>> successGroupedBySeller(){
		List<SaleSuccessDTO> list = saleService.successGroupedBySeller();
		return ResponseEntity.ok(list);
	}
}
