package com.wipro.sfh.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.sfh.dto.ProductDTO;
import com.wipro.sfh.entity.Product;
import com.wipro.sfh.service.IProductService;

/**
 * @author Umasankar
 * Modified Date: 26-08-2022
 * Description: product controller 
 * 
 */

@RestController
@RequestMapping("/api/v1/product")
@CrossOrigin
public class ProductController {

	@Autowired
	private IProductService service;
	
	@GetMapping("/getall")
	public List<Product> getAllProduct() {
		return service.getAllProduct();
	}
	
	@GetMapping("/getall/{page}")
	public Page<Product> getProductPage(@PathVariable int page) {
		return service.getProductPage(page-1);
	}
	
	@GetMapping("/getall/category/{category}")
	public List<Product> getProductByCategory(@PathVariable String category) {
		return service.getProductbyCategory(category);
	}
	
	@GetMapping("/getall/sort/asc/{field}/{page}")
	public Page<Product> getProductSortAsc(@PathVariable String field, @PathVariable int page) {
		return service.getProductSortByInAsc(field,page-1);
	}
	
	@GetMapping("/getall/sort/desc/{field}/{page}")
	public Page<Product> getProductSortDesc(@PathVariable String field, @PathVariable int page) {
		return service.getProductSortByInDesc(field,page-1);
	}

	@PostMapping("/add")
	public Product saveProduct(@RequestBody ProductDTO dto) {
		return service.addProduct(dto);
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable long id) {
		try {
			Product product = service.getProductById(id);
			return new ResponseEntity<Product>(product, HttpStatus.OK);

		} catch (NoSuchElementException e) {
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@PutMapping("/update")
	public ResponseEntity<Product> updateProduct(@RequestBody ProductDTO product) {
		try {
			Product updateProduct = service.updateProduct(product);
			return new ResponseEntity<Product>(updateProduct, HttpStatus.OK);

		} catch (NoSuchElementException e) {
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable long id) {
		try {
			service.deleteProduct(id);
			return new ResponseEntity<>(HttpStatus.OK);

		} catch (NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	

}
