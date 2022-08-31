package com.wipro.sfh.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.wipro.sfh.dto.ProductDTO;
import com.wipro.sfh.entity.Product;

/**
 * @author Akshat
 * Modified Date: 25-08-2022
 * Description: product service interface 
 * 
 */

public interface IProductService {
	
	public Product addProduct(ProductDTO productDTO);

	public List<Product> getAllProduct();
	
	public Page<Product> getProductPage(int page);
	
	public List<Product> getProductbyCategory(String category);
	
	public Page<Product> getProductSortByInAsc(String field, int page);
	
	public Page<Product> getProductSortByInDesc(String field, int page);
	
	public Product getProductById(long id);

	public Product updateProduct(ProductDTO productDTO);

	public void deleteProduct(long id);
}
