package com.jamal.ecommerce_project.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jamal.ecommerce_project.dto.ProductDTO;
import com.jamal.ecommerce_project.model.Product;
import com.jamal.ecommerce_project.service.ProductService;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class ProductController {
@Autowired
private ProductService service;

//@GetMapping("/products")
//public ResponseEntity< List<Product>> getAllProducts(){
//	
//	return new ResponseEntity<>(service.getAllProducts(),HttpStatus.OK);}

//🟢 Upgraded endpoint to serve the lightweight DTO collection instead of the full entity objects
@GetMapping("/products")
public ResponseEntity<List<ProductDTO>> getAllProducts() {
	try {
		// 🟢 Fixed: Updated method name call to match what you wrote in ProductService
		List<ProductDTO> products = service.getAllProductsWithoutImages();
		return new ResponseEntity<>(products, HttpStatus.OK);
	} catch (Exception e) {
		System.err.println("Error pulling optimized catalog collection: " + e.getMessage());
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
@GetMapping("/product/{id}")
public  ResponseEntity< Product> getProductByName(@PathVariable int id) {
	Product product= service.getProductById( id);
	if(product!=null) {
	return new ResponseEntity<>( product,HttpStatus.OK);}
	else {
		return new ResponseEntity<>( product,HttpStatus.NOT_FOUND);
	}
}
@PostMapping("/product")
 public ResponseEntity<?> addProduct(@RequestPart Product product,@RequestPart MultipartFile imageFile) {
	 try {
		 Product product1=service.addProduct(product,imageFile);
		 return new ResponseEntity<>(product1,HttpStatus.CREATED);
 
	 }
	 catch(Exception e) {
		 return  new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	 }}
	 @GetMapping("/product/{productId}/image")
	 public ResponseEntity<byte[]> getImageByProductId(@PathVariable int productId) {
	     
	     // 1. Fetch the product using your service layer
	     Product product = service.getProductById(productId);
	     
	     // 2. Extract the primitive byte array from your model
	     byte[] imageFile = product.getImageData();
	     
	     // 3. Return the byte array with  an OK status, setting the headers so the browser knows it's an image
	     return ResponseEntity.ok()  //ResponseEntity.ok() — The "Green Light" What it means: It sends a code (200 OK) back to the web browser.
	             .contentType(MediaType.valueOf(product.getImageType())) .body(imageFile);
	     			//contentType(...) — Telling the Browser What it's Looking At What it means: It tells the browser exactly what kind of file it is sending (e.g., a .jpg, .png, or .gif).
	     			//MediaType.valueOf(...): Java cannot just pass a raw text string as a media type; it needs a strongly-typed MediaType object. valueOf() parses your string (e.g., "image/jpeg") and converts it into a formal Spring MediaType object
	     			//.body(imageFile) — Dropping the Actual Item What it means: It takes the raw, binary data of the image (the actual zeros and ones stored in your database) and throws it into the delivery box.
	 
	 }
	 
	 @PutMapping("/product/{id}")
	 public ResponseEntity<String> updateProduct(@PathVariable int id, @RequestPart Product product, @RequestPart MultipartFile imageFile){
	     Product product1;
		try {
			product1 = service.updateProduct(id, product, imageFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<String>("Failed to Update",HttpStatus.BAD_REQUEST);
		}
	     
	     if(product1!=null) {
	    		return new ResponseEntity<>( "Updated",HttpStatus.OK);}
	    		else {
	    			return new ResponseEntity<>( "Failed To Update",HttpStatus.NOT_FOUND);
	    		}
	     
	 }
	 
	 @DeleteMapping("/product/{id}")
	 public ResponseEntity<String> deleteProduct(@PathVariable int id) {
	     Product product = service.getProductById(id);
	     
	     if (product != null) {
	         service.deleteProduct(id);
	         return new ResponseEntity<>("Product deleted successfully", HttpStatus.OK);
	     } else {
	         return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
	     }
	 }
	 
	 @GetMapping("/products/search")
	 public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword) {
		 List<Product> products = service.searchProducts( keyword) ;
	     return new ResponseEntity<>(products, HttpStatus.OK);
	    
		
		
	 }

 
	 


}


 

