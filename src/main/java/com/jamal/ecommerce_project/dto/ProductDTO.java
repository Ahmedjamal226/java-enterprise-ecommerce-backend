package com.jamal.ecommerce_project.dto;



import java.math.BigDecimal;
import java.util.Date;

public interface ProductDTO {
    Integer getId();
    String getName();
    String getBrand();
    String getDescription();
    BigDecimal getPrice();
    String getCategory();
    Integer getStockQuantity();
    Date getReleaseDate();
    Boolean getProductAvailable();
}