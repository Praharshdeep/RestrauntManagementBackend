package com.praharsh.CafeManagement.controllers;

import com.praharsh.CafeManagement.dtos.ReservationDto;
import com.praharsh.CafeManagement.services.customer.CustomerService;
import jdk.jfr.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
@CrossOrigin
public class CustomerController {

    private final CustomerService customerService;

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
