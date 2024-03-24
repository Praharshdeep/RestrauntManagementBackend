package com.praharsh.CafeManagement.controllers;

import com.praharsh.CafeManagement.dtos.CategoryDto;
import com.praharsh.CafeManagement.dtos.ProductDto;
import com.praharsh.CafeManagement.dtos.ReservationDto;
import com.praharsh.CafeManagement.services.admin.AdminServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@CrossOrigin
public class AdminController {
    private final AdminServiceImpl adminService;

//    @PostMapping("/category")
//    public ResponseEntity<?> postCategory(@ModelAttribute CategoryDto categoryDto) throws IOException {
//    CategoryDto createdDto = adminService.postCategory(categoryDto);
//        if (createdDto == null) {
//            return new ResponseEntity<>("Category not Created. Come again later", HttpStatus.BAD_REQUEST);
//        }
//    return new ResponseEntity<>(createdDto, HttpStatus.CREATED);
//    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDto>> getAllCategories(){
        List<CategoryDto> categoryDtoList = adminService.getAllCategories();
        if(categoryDtoList == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(categoryDtoList);
    }

    @GetMapping("/categories/{title}")
    public ResponseEntity<List<CategoryDto>> getAllCategoriesByTitle(@PathVariable String title){
        List<CategoryDto> categoryDtoList = adminService.getAllCategoriesByTitle(title);
        if(categoryDtoList == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(categoryDtoList);
    }


    //Product Operations
    @PostMapping("/{categoryId}/product")
    public ResponseEntity<?> postProduct(@PathVariable Long categoryId, @ModelAttribute ProductDto productDto) throws IOException {
        ProductDto createdDto = adminService.postProduct(categoryId,productDto);
        if (createdDto == null) {
            return new ResponseEntity<>("Category not Created. Come again later", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(createdDto, HttpStatus.CREATED);
    }

//    @GetMapping("/{categoryId}/products")
//    public ResponseEntity<List<ProductDto>> getAllProductByCategory(@PathVariable Long categoryId){
//        List<ProductDto> productDtoList = adminService.getAllProductByCategory(categoryId);
//        if(productDtoList == null) return ResponseEntity.notFound().build();
//        return ResponseEntity.ok(productDtoList);
//    }

    @GetMapping("/{categoryId}/product/{title}")
    public ResponseEntity<List<ProductDto>> getProductsByCategoryAndTitle(@PathVariable String title,@PathVariable Long categoryId){
        List<ProductDto> productDtoList = adminService.getProductsByCategoryAndTitle(title,categoryId);
        if(productDtoList == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(productDtoList);
    }

//    @DeleteMapping("/product/{productId}")
//    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId){
//        adminService.deleteProduct(productId);
//        return ResponseEntity.noContent().build();
//    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<ProductDto> getProductsById(@PathVariable Long productId){
        ProductDto productDto = adminService.getProductsById(productId);
        if(productDto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(productDto);
    }

//    @PutMapping("/product/{productId}")
//    public ResponseEntity<?> updateProduct(@PathVariable Long productId,@ModelAttribute ProductDto productDto) throws IOException {
//        ProductDto updatedProduct = adminService.updateProduct(productId,productDto);
//        if (updatedProduct == null) {
//            return new ResponseEntity<>("Category not Created. Come again later", HttpStatus.BAD_REQUEST);
//        }
//        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
//    }

//    @GetMapping("/products")
//    public ResponseEntity<List<ProductDto>> getAllProducts(){
//        List<ProductDto> productDtoList = adminService.getAllProducts();
//        if(productDtoList == null) return ResponseEntity.notFound().build();
//        return ResponseEntity.ok(productDtoList);
//    }

    @GetMapping("/reservations")
    public ResponseEntity<List<ReservationDto>> getReservations(){
        List<ReservationDto> reservationDtoList = adminService.getReservations();
        if(reservationDtoList == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(reservationDtoList);
    }

    @GetMapping("/reservations/{reservationId}/{status}")
    public ResponseEntity<ReservationDto> changeReservationStatus(@PathVariable Long reservationId, @PathVariable String status){
        ReservationDto reservationDto = adminService.changeReservationStatus(reservationId,status);
        if(reservationDto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(reservationDto);
    }







}

//Learn about responseEntity and ModelAttribute
