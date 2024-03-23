package com.praharsh.CafeManagement.services.customer;

import com.praharsh.CafeManagement.dtos.ReservationDto;
import com.praharsh.CafeManagement.entities.Reservation;
import com.praharsh.CafeManagement.entities.User;
import com.praharsh.CafeManagement.enums.ReservationStatus;
import com.praharsh.CafeManagement.repositries.ReservationRepo;
import com.praharsh.CafeManagement.repositries.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{

    private final UserRepo userRepo;
    private final ReservationRepo reservationRepo;

    @Override
    public ReservationDto postReservation(ReservationDto reservationDto) {

        Optional<User> optionalUser = userRepo.findById(reservationDto.getCustomerId());
        if(optionalUser.isPresent()){
            Reservation reservation = new Reservation();
            reservation.setTableType(reservationDto.getTableType());
            reservation.setDateTime(reservationDto.getDateTime());
            reservation.setDescription(reservationDto.getDescription());
            reservation.setUser(optionalUser.get());
            reservation.setReservationStatus(ReservationStatus.PENDING);

            Reservation postedReservation = reservationRepo.save(reservation);

            ReservationDto postedReservationDto = new ReservationDto();
            postedReservationDto.setId(postedReservation.getId());
            return postedReservationDto;
        }

        return null;
    }

    @Override
    public List<ReservationDto> getReservationByUser(Long customerId) {
        return reservationRepo.findAllByUserId(customerId).stream().map(Reservation::getReservationDto).collect(Collectors.toList());
    }
}
