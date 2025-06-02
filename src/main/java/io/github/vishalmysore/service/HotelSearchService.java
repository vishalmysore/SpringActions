package io.github.vishalmysore.service;

import com.t4a.annotations.Action;
import com.t4a.annotations.Agent;
import com.t4a.detect.ActionCallback;
import com.t4a.detect.ActionState;
import com.t4a.processor.AIProcessor;
import io.github.vishalmysore.dao.IhotelsDAO;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log
@Agent(groupName = "hotels", groupDescription = "Search hotels based on hotel name")
public class HotelSearchService {

    private IhotelsDAO hotelsDAO;

    private ActionCallback actionCallback;
    private AIProcessor aiProcessor;

    public HotelSearchService() {
        log.info(" Created Hotel Search Service");
    }

    @Autowired
    public HotelSearchService(IhotelsDAO hotelsDAO) {
        log.info(" Created Hotel Search Service");
        this.hotelsDAO = hotelsDAO;
    }

    @Action(description = "Find hotels based on given country and city")
    public String findHotels(String country, String city) {
        log.info(" Searching for hotels in the city "+city);
        String hotels = hotelsDAO.findHotels(country, city);
        if (actionCallback != null ) {
            actionCallback.sendtStatus(" HOTELS LIST : "+hotels, ActionState.COMPLETED);
        }
        return " HOTELS LIST : "+hotels;
    }
}
