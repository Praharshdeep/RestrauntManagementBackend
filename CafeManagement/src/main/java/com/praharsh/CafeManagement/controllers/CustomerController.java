package com.praharsh.CafeManagement.controllers;

import com.praharsh.CafeManagement.dtos.CategoryDto;
import com.praharsh.CafeManagement.dtos.ProductDto;
import com.praharsh.CafeManagement.dtos.ReservationDto;
import com.praharsh.CafeManagement.services.customer.CustomerService;
import jdk.jfr.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
@CrossOrigin
public class CustomerController{

    private final CustomerService customerService ;

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDto>> getAllCategories(){
        List<CategoryDto> categoryDtoList = customerService.getAllCategories();
        if(categoryDtoList == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(categoryDtoList);
    }

    @GetMapping("/categories/{title}")
    public ResponseEntity<List<CategoryDto>> getAllCategories(@PathVariable String title){
        List<CategoryDto> categoryDtoList = customerService.getCategoriesByName(title);
        if(categoryDtoList == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(categoryDtoList);
    }

    @GetMapping("/{categoryId}/products")
    public ResponseEntity<List<ProductDto>> getProductsByCategory(@PathVariable Long categoryId){
        List<ProductDto> productDtoList = customerService.getProductsByCategory(categoryId);
        if(productDtoList == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(productDtoList);
    }


    @PostMapping("/reservation")
    public ResponseEntity<?> postReservation(@RequestBody ReservationDto reservationDto){
        ReservationDto postedReservation = customerService.postReservation(reservationDto);
        if(postedReservation == null){
            return new ResponseEntity<>("Something is Wrong!", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(postedReservation);
    }

    @GetMapping("/reservations/{customerId}")
    public ResponseEntity<List<ReservationDto>> getReservationsByUser(@PathVariable Long customerId){
        List<ReservationDto> reservationDtoList = customerService.getReservationByUser(customerId);
        if(reservationDtoList==null) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(reservationDtoList);
    }
}
