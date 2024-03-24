package com.praharsh.CafeManagement.services.customer;

import com.praharsh.CafeManagement.dtos.CategoryDto;
import com.praharsh.CafeManagement.dtos.ReservationDto;

import java.util.List;

public interface CustomerService {
    ReservationDto postReservation(ReservationDto reservationDto);

    List<ReservationDto> getReservationByUser(Long customerId);

    List<CategoryDto> getAllCategories();

    List<CategoryDto> getCategoriesByName(String title);
}
