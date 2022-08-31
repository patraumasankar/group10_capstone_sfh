package com.wipro.sfh.helper;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.QuoteMode;
import org.springframework.web.multipart.MultipartFile;

import com.wipro.sfh.entity.Product;

/**
 * @author Partha
 * Modified Date: 27-08-2022
 * Description: csv helper class
 * 
 */

public class CSVHelper {
	  public static String TYPE = "text/csv";
	  static String[] HEADERs = { "id", "productName", "productDescription", "productPrice","productStock","category","url" };

	  public static boolean hasCSVFormat(MultipartFile file) {
	    if (TYPE.equals(file.getContentType())
	    		|| file.getContentType().equals("application/vnd.ms-excel")) {
	      return true;
	    }

	    return false;
	  }

	  public static List<Product> csvToProduct(InputStream is) {
	    try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
	        CSVParser csvParser = new CSVParser(fileReader,
	            CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

	      List<Product> productlist = new ArrayList<>();

	      Iterable<CSVRecord> csvRecords = csvParser.getRecords();

	      for (CSVRecord csvRecord : csvRecords) {
	    	  Product product = new Product();
	    	  	product.setId(Long.parseLong(csvRecord.get("id")));
	    	  	product.setProductName(csvRecord.get("productName"));
	    	  	product.setProductPrice(Long.parseLong(csvRecord.get("productPrice")));
	    	  	product.setProductStock(Integer.parseInt(csvRecord.get("productStock")));
	    	  	product.setProductDescription(csvRecord.get("productDescription"));
	    	  	product.setCategory(csvRecord.get("category"));
	    	  	product.setUrl(csvRecord.get("url"));	           

	    	  productlist.add(product);
	    	 
	      }

	      return productlist;
	    } catch (IOException e) {
	      throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
	    }
	  }

	  public static ByteArrayInputStream productToCSV(List<Product> productlist) {
	    final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

	    try (ByteArrayOutputStream out = new ByteArrayOutputStream();
	        CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
	      for (Product product : productlist) {
	        List<String> data = Arrays.asList(
	        		String.valueOf(product.getId()),
	        		product.getProductName(),
	        		product.getProductDescription(),
	        		String.valueOf(product.getProductStock()) ,
	        		String.valueOf(product.getProductPrice()) 
	            );

	        csvPrinter.printRecord(data);
	      }

	      csvPrinter.flush();
	      return new ByteArrayInputStream(out.toByteArray());
	    } catch (IOException e) {
	      throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
	    }
	  }
	  
	}
