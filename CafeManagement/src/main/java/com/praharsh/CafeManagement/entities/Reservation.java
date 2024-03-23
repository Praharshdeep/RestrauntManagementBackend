package com.praharsh.CafeManagement.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.praharsh.CafeManagement.dtos.ReservationDto;
import com.praharsh.CafeManagement.enums.ReservationStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Entity
@Data
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tableType;
    private String description;
    private Date dateTime;
    private ReservationStatus reservationStatus;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "user_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    public ReservationDto getReservationDto(){
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setId(id);
        reservationDto.setReservationStatus(reservationStatus);
        reservationDto.setDateTime(dateTime);
        reservationDto.setDescription(description);
        reservationDto.setTableType(tableType);
        reservationDto.setCustomerId(user.getId());
        reservationDto.setCustomerName(user.getName());

        return reservationDto;
    }
}
