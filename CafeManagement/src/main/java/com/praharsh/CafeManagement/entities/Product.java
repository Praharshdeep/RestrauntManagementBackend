package com.praharsh.CafeManagement.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.praharsh.CafeManagement.dtos.ProductDto;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

//@Entity
//@Data
//public class Product {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String name;
//    private String price;
//    private String description;
//
//    @Lob
//    @Column(columnDefinition = "longblob")
//    private byte[] img;
//
//    @ManyToOne(fetch = FetchType.LAZY,optional = false)
//    @JoinColumn(name = "category_id",nullable = false)
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonIgnore
//    private Category category;
//
//    public ProductDto getProductDto(){
//        ProductDto productDto = new ProductDto();
//        productDto.setId(id);
//        productDto.setName(name);
//        productDto.setPrice(price);
//        productDto.setDescription(description);
//        productDto.setCategoryId(category.getId());
//        productDto.setReturnedImg(img);
//        productDto.setCategoryName(category.getName());
//        return productDto;
//    }
//
//    //Whenever this method calls it will set all the details to dto
//
//}
