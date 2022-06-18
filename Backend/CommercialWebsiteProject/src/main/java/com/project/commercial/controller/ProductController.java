package com.project.commercial.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.commercial.model.Product;
import com.project.commercial.repository.ProductDao;


/**
 * This controller list the product by connecting to productDao .8081 port is for frontend vue application port and we allowed this port
 * in here.  
 * @author fatih akguc
 * @return ok, 200 response when methods are executed.
 */

@RestController
@RequestMapping("")
@CrossOrigin("http://localhost:8081")
public class ProductController {
	
	
	@Autowired
	private ProductDao productDao;
	
	public ProductController(ProductDao productDao) {
		super();
		this.productDao = productDao;
	}
	
	@GetMapping("/products")
	public ResponseEntity<List<Product>> findAllProducts(){
		return ResponseEntity.ok(productDao.findAll());
	}

	

}
