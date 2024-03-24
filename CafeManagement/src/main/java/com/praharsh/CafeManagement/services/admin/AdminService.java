package com.praharsh.CafeManagement.services.admin;

import com.praharsh.CafeManagement.dtos.CategoryDto;
import com.praharsh.CafeManagement.dtos.ProductDto;
import com.praharsh.CafeManagement.dtos.ReservationDto;

import java.io.IOException;
import java.util.List;

public interface AdminService {
//    CategoryDto postCategory(CategoryDto categoryDto) throws IOException;
//
//    List<CategoryDto> getAllCategories();
//
//    List<CategoryDto> getAllCategoriesByTitle(String title);

    ProductDto postProduct(Long categoryID, ProductDto productDto) throws IOException;

    List<ProductDto> getAllProductByCategory(Long categoryId);

    List<ProductDto> getProductsByCategoryAndTitle(String title, Long categoryId);

//    void deleteProduct(Long productId);
//
//    ProductDto getProductsById(Long productId);
//
//    ProductDto updateProduct(Long productId, ProductDto productDto) throws IOException;

    List<ProductDto> getAllProducts();

    List<ReservationDto> getReservations();

    ReservationDto changeReservationStatus(Long reservationId, String status);
}
