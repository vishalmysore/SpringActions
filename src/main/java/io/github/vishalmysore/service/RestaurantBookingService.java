package io.github.vishalmysore.service;

import lombok.extern.java.Log;
import io.github.vishalmysore.pojo.RestaurantPojo;
import org.springframework.stereotype.Service;

@Log
@Service
public class RestaurantBookingService {

    public RestaurantBookingService(){
        log.info("created RestaurantBookingService");
    }
    public String bookReservation(RestaurantPojo restaurantPojo){
        log.info(restaurantPojo.toString());
        return "This has been booked "+restaurantPojo.toString();
    }
}
