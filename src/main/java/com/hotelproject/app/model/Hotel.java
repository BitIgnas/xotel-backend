package com.hotelproject.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "hotel")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String hotelCode;
    private Integer hotelStars;
    private Long hotelRooms;
    private String hotelCountry;
    private String hotelCity;
    private String hotelAddress;
    private String hotelPostalCode;
    private Boolean isVerified;
    @Enumerated(EnumType.STRING)
    private HotelType hotelType;
    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Reservation> hotelReservations;
    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Review> reviews;
    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL,
                fetch = FetchType.LAZY)
    private List<Staff> staff;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}
