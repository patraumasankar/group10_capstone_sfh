package com.wipro.sfh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.wipro.sfh.dto.ProductDTO;
import com.wipro.sfh.entity.Product;
import com.wipro.sfh.repository.ProductRepository;
import com.wipro.sfh.service.IProductService;

/**
 * @author Jagadeeshwara
 * Modified Date: 27-08-2022
 * Description: product implementation class 
 * 
 */
@Service
public class ProductServiceImpl implements IProductService {

	
	@Autowired
	private ProductRepository productRepository;
	
	/**
	 * @author Jagadeeshwara
	 * Modified Date: 27-08-2022
	 * Description: add product  
	 * Param: ProductDTO
	 * Return Type: Product
	 */
	@Override
	public Product addProduct(ProductDTO product) {
		Product newProduct = new Product();
		
		newProduct.setId(product.getId());
		newProduct.setProductName(product.getProductName());
		newProduct.setProductPrice(product.getProductPrice());
		newProduct.setProductStock(product.getProductStock());
		newProduct.setProductDescription(product.getProductDescription());
		newProduct.setCategory(product.getCategory());
		newProduct.setUrl(product.getUrl());
		
		return productRepository.save(newProduct);

	}

	/**
	 * @author Jagadeeshwara
	 * Modified Date: 27-08-2022
	 * Description: get product data
	 * Return Type: list of Product
	 */
	@Override
	public List<Product> getAllProduct() {
		return productRepository.findAll();
	}

	/**
	 * @author Umasankar
	 * Modified Date: 27-08-2022
	 * Description: get product in terms of page
	 *  Param: int page
	 * Return Type: Page of Product
	 */
	@Override
	public Page<Product> getProductPage(int page) {
		return productRepository.findAll(PageRequest.of(page, 8));
	}
	
	/**
	 * @author Umasankar
	 * Modified Date: 27-08-2022
	 * Description: category wise product 
	 *  Param: category
	 * Return Type: list of product
	 */
	@Override
	public List<Product> getProductbyCategory(String category) {
		return productRepository.findAllByCategory(category);
	}
	
	/**
	 * @author Umasankar
	 * Modified Date: 27-08-2022
	 * Description: get product by id
	 *  Param: int id
	 * Return Type: Product
	 */
	@Override
	public Product getProductById(long id) {
		return productRepository.findById(id).orElse(new Product());
	}

	/**
	 * @author Jagadeeshwara
	 * Modified Date: 27-08-2022
	 * Description: update product
	 *  Param: ProductDTO
	 * Return Type: Product
	 */
	@Override
	public Product updateProduct(ProductDTO product) {
		
		Product updateProduct = getProductById(product.getId());
		
		updateProduct.setId(product.getId());
		updateProduct.setProductName(product.getProductName());
		updateProduct.setProductPrice(product.getProductPrice());
		updateProduct.setProductStock(product.getProductStock());
		updateProduct.setProductDescription(product.getProductDescription());
		updateProduct.setCategory(product.getCategory());
		updateProduct.setUrl(product.getUrl());
		
		return productRepository.save(updateProduct);
		
	}

	/**
	 * @author Jagadeeshwara
	 * Modified Date: 27-08-2022
	 * Description: delete product
	 *  Param: id
	 * Return Type: void
	 */
	@Override
	public void deleteProduct(long id) {
		productRepository.deleteById(id);
	}
	
	/**
	 * @author Umasankar
	 * Modified Date: 27-08-2022
	 * Description: sorting in ascending order 
	 *  Param: field,page
	 * Return Type: Page of Product
	 */
	@Override
	public Page<Product> getProductSortByInAsc(String field, int page){
		return productRepository.findAll(PageRequest.of(page, 8).withSort(Sort.by(Direction.ASC,field)));
	}
	
	/**
	 * @author Umasankar
	 * Modified Date: 27-08-2022
	 * Description: sorting in descending order 
	 *  Param: field,page
	 * Return Type: Page of Product
	 */
	@Override
	public Page<Product> getProductSortByInDesc(String field, int page){
		return productRepository.findAll(PageRequest.of(page, 8).withSort(Sort.by(Direction.DESC,field)));
	}
	
}
