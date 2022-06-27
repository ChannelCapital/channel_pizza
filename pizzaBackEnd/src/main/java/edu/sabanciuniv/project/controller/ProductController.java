package edu.sabanciuniv.project.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.sabanciuniv.project.model.Product;
import edu.sabanciuniv.project.repository.ProductRepository;


@CrossOrigin(origins = "http://localhost:8082") 
@RestController
@RequestMapping("/api/test")
public class ProductController {

	@Autowired
	ProductRepository productRepository;
	
	@GetMapping("/all")
	public ResponseEntity<List<Product>> getAll() {

		try {
			List<Product> products = new ArrayList<Product>();
			
				productRepository.findAll().forEach(products::add);
			
			if (products.isEmpty()) {

				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(products, HttpStatus.OK);

		} catch (Exception e) {

			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/products")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<Product>> getAllProducts(@RequestParam(required = false) String name) {

		try {
			List<Product> products = new ArrayList<Product>();
			if (name == null) {
				productRepository.findAll().forEach(products::add);
			} else {
				productRepository.findByNameContaining(name).forEach(products::add);
			}
			if (products.isEmpty()) {

				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(products, HttpStatus.OK);

		} catch (Exception e) {

			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/details/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Product> getProductById(@PathVariable("id") String id) {

		Optional<Product> productData = productRepository.findById(id);

		if (productData.isPresent()) {

			return new ResponseEntity<>(productData.get(), HttpStatus.OK);

		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping("/details/published")
	public ResponseEntity<List<Product>> findByPublished() {
		try {
			List<Product> products = productRepository.findByPublished(true);

			if (products.isEmpty()) {

				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(products, HttpStatus.OK);

		} catch (Exception e) {

			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/add")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Product> createProduct(@RequestBody Product product) {

		try {
			Product _product = productRepository
					.save(new Product(product.getName(), product.getPrice(),product.getDescription(), product.getImageURL(), product.isPublished()));

			return new ResponseEntity<>(_product, HttpStatus.CREATED);

		} catch (Exception e) {

			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

	@PutMapping("/details/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Product> updateProduct(@PathVariable("id") String id, @RequestBody Product product) {

		Optional<Product> productData = productRepository.findById(id);

		if (productData.isPresent()) {

			Product _product = productData.get();

			_product.setName(product.getName());

			_product.setDescription(product.getDescription());

			_product.setPublished(product.isPublished());
			
			_product.setPrice(product.getPrice());
			
			_product.setImageURL(product.getImageURL());

			return new ResponseEntity<>(productRepository.save(_product), HttpStatus.OK);

		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@DeleteMapping("/details/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id") String id) {

		try {

			productRepository.deleteById(id);

			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

		} catch (Exception e) {

			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@DeleteMapping("/products")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<HttpStatus> deleteAllProducts() {

		try {

			productRepository.deleteAll();

			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

		} catch (Exception e) {

			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
