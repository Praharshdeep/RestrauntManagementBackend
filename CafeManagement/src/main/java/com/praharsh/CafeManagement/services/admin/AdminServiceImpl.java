package com.praharsh.CafeManagement.services.admin;

import com.praharsh.CafeManagement.dtos.CategoryDto;
import com.praharsh.CafeManagement.dtos.ProductDto;
import com.praharsh.CafeManagement.dtos.ReservationDto;
import com.praharsh.CafeManagement.entities.Category;
import com.praharsh.CafeManagement.entities.Product;
import com.praharsh.CafeManagement.entities.Reservation;
import com.praharsh.CafeManagement.enums.ReservationStatus;
import com.praharsh.CafeManagement.repositries.CategoryRepository;
import com.praharsh.CafeManagement.repositries.ProductRepo;
import com.praharsh.CafeManagement.repositries.ReservationRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final CategoryRepository categoryRepository;
    private final ProductRepo productRepo;
    private final ReservationRepo reservationRepo;

    @Override
    public CategoryDto postCategory(CategoryDto categoryDto) throws IOException {
        Category category = new Category();

        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        category.setImg(categoryDto.getImg().getBytes());

        Category createdCategory = categoryRepository.save(category);
        CategoryDto createdCategoryDto = new CategoryDto();
        createdCategoryDto.setId(createdCategory.getId());
        createdCategoryDto.setName(createdCategory.getName());
        createdCategoryDto.setDescription(createdCategory.getDescription());
        createdCategoryDto.setImg(createdCategoryDto.getImg());
        return createdCategoryDto;
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll().stream().map(Category::getCategoryDto).collect(Collectors.toList());
    }

    @Override
    public List<CategoryDto> getAllCategoriesByTitle(String title) {
        return categoryRepository.findAllByNameContaining(title).stream().map(Category::getCategoryDto).collect(Collectors.toList());
    }

    @Override
    public ProductDto postProduct(Long categoryID, ProductDto productDto) throws IOException {
        Optional<Category> optionalCategory= categoryRepository.findById(categoryID);
        if(optionalCategory.isPresent()){
            Product product = new Product();
            BeanUtils.copyProperties(productDto,product);
            //This method copies all the attributes of productDto in product
            product.setImg(productDto.getImg().getBytes()); //Same old Line
            product.setCategory(optionalCategory.get());
            Product createdProduct = productRepo.save(product);
            ProductDto createdProductDto = new ProductDto();
            createdProductDto.setId(createdProduct.getId());
            return createdProductDto;
        }
        return null;
    }

    @Override
    public List<ProductDto> getAllProductByCategory(Long categoryId) {
        return productRepo.findAllByCategoryId(categoryId).stream().map(Product :: getProductDto).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getProductsByCategoryAndTitle(String title, Long categoryId) {
        return productRepo.findAllByCategoryIdAndNameContaining(categoryId,title).stream().map(Product :: getProductDto).collect(Collectors.toList());
    }

    @Override
    public void deleteProduct(Long productId) {
        Optional<Product> optionalProduct = productRepo.findById(productId);
        if(optionalProduct.isPresent()){
            productRepo.deleteById(productId);
        }
        else throw new IllegalArgumentException("Product with id : "+ productId +" not found");

    }

    @Override
    public ProductDto getProductsById(Long productId) {
        Optional<Product> optionalProduct = productRepo.findById(productId);
        if(optionalProduct.isPresent()){
            ProductDto productDto = optionalProduct.get().getProductDto();
            return productDto;
        }
        return null;
    }

    @Override
    public ProductDto updateProduct(Long productId, ProductDto productDto) throws IOException {
        Optional<Product> optionalProduct = productRepo.findById(productId);
        if(optionalProduct.isPresent()){
            Product product = optionalProduct.get();
            product.setName(productDto.getName());
            product.setDescription(productDto.getDescription());
            product.setPrice(productDto.getPrice());
            if(productDto.getImg()!=null){product.setImg(productDto.getImg().getBytes());}
            Product updatedProduct = productRepo.save(product);
            ProductDto createdProductDto = new ProductDto();
            createdProductDto.setId(updatedProduct.getId());
            return createdProductDto;
        }
        return null;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return productRepo.findAll().stream().map(Product::getProductDto).collect(Collectors.toList());
    }



    @Override
    public ReservationDto changeReservationStatus(Long reservationId, String status) {
        Optional<Reservation> optionalReservation = reservationRepo.findById(reservationId);
        if(optionalReservation.isPresent()){
            Reservation reservation = optionalReservation.get();
            if(Objects.equals(status,"Approve")){
                reservation.setReservationStatus(ReservationStatus.APPROVED);
            }else{
                reservation.setReservationStatus(ReservationStatus.DISAPPROVED);
            }
            Reservation updatedReservation = reservationRepo.save(reservation);
            ReservationDto updatedreservationDto = new ReservationDto();
            updatedreservationDto.setId(updatedReservation.getId());
            return updatedreservationDto;
        }
        return null;
    }
    //Learn about stream(), map()
}
