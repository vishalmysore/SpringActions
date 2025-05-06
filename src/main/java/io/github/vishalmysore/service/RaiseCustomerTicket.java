package io.github.vishalmysore.service;

import com.t4a.annotations.Action;
import com.t4a.annotations.Agent;

import com.t4a.api.JavaMethodAction;

import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

@Service
@Log
@Agent(groupName ="raiseTicket", groupDescription = "Create a ticket for customer")
public class RaiseCustomerTicket implements JavaMethodAction {
    @Action(description = "Raise a ticket for customer")
    public String raiseTicket(String customerName) {

        return "ticket 111 raised for "+customerName;
    }
}
