package org.example;

import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

@Log
@Service
public class RestaurantBookingService {
    public String bookReservation(RestaurantPojo restaurantPojo){
        log.info(restaurantPojo.toString());
        return "This has been booked "+restaurantPojo.toString();
    }
}
