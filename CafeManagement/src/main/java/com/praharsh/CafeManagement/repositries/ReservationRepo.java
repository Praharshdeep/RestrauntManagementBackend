package com.praharsh.CafeManagement.repositries;

import com.praharsh.CafeManagement.entities.Reservation;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepo extends JpaRepository<Reservation,Long> {


    List<Reservation> findAllByUserId(Long customerId);

}
