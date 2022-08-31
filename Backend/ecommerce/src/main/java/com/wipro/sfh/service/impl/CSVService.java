package com.wipro.sfh.service.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.wipro.sfh.entity.Product;
import com.wipro.sfh.helper.CSVHelper;
import com.wipro.sfh.repository.ProductRepository;

/**
 * @author Akshat
 * Modified Date: 27-08-2022
 * Description: csv service 
 * 
 */
@Service
public class CSVService {
  @Autowired
  ProductRepository repository;

  /**
   * @author Akshat
   * Modified Date: 27-08-2022
   * Description: save file 
   *Param: file
   * Return Type: void 
   */
  public void save(MultipartFile file) {
    try {
      List<Product> products = CSVHelper.csvToProduct(file.getInputStream());
      repository.saveAll(products);
    } catch (IOException e) {
      throw new RuntimeException("fail to store csv data: " + e.getMessage());
    }
  }

  /**
   * @author Akshat
   * Modified Date: 27-08-2022
   * Description: download file
   * Return Type: input stream
   */
  public ByteArrayInputStream load() {
    List<Product> products = repository.findAll();

    ByteArrayInputStream in = CSVHelper.productToCSV(products);
    return in;
  }

  /**
   * @author Akshat
   * Modified Date: 27-08-2022
   * Description: get all product  
	 * Return Type: list of product
   */
  public List<Product> getAllProducts() {
    return repository.findAll();
  }
}
