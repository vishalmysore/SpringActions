package org.example;

import com.t4a.api.JavaMethodAction;
import com.t4a.predict.Predict;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

@Service
@Log
@Predict(actionName ="raiseTicket", description = "Create a ticket for customer")
public class RaiseCustomerTicket implements JavaMethodAction {
    public String raiseTicket(String customerName) {

        return customerName;
    }
}
