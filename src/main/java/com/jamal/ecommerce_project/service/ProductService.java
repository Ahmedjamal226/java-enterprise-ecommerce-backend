package com.jamal.ecommerce_project.service;

import java.io.IOException;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.jamal.ecommerce_project.dto.ProductDTO;
import com.jamal.ecommerce_project.model.Product;
import com.jamal.ecommerce_project.repo.ProductRepo;
@Service
public class ProductService {
	@Autowired
	private ProductRepo repo;
	
//	public List<Product> getAllProducts() {
//		// TODO Auto-generated method stub
//		 return repo.findAll();
//	}
	public List<ProductDTO> getAllProductsWithoutImages() {
		return repo.findAllWithoutImageData();
	}
	
	
	public Product getProductById(int id) {
		// TODO Auto-generated method stub
		return repo.findById(id).orElse(null);
	}

	public Product addProduct(Product product, MultipartFile imageFile) throws IOException {
		// TODO Auto-generated method stub
		product.setImageName(imageFile.getOriginalFilename());
		product.setImageType(imageFile.getContentType());
		product.setImageData(imageFile.getBytes());
		
		return repo.save(product);
	}

	public Product updateProduct(@PathVariable int id, @RequestPart Product product, @RequestPart MultipartFile imageFile) throws IOException {
		
		product.setImageData(imageFile.getBytes());
		product.setImageName(imageFile.getOriginalFilename());
		product.setImageType(imageFile.getContentType());
		
		return repo.save(product);
	}

	public void deleteProduct(int id) {
		
		    repo.deleteById(id);
		}

	public List<Product> searchProducts(String keyword) {
		return repo.searchProducts(keyword);
	}}

	

	
	
	
