package com.hotelproject.app.repo;

import com.hotelproject.app.model.Hotel;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends ReactiveCrudRepository<Hotel, Long> {

}
