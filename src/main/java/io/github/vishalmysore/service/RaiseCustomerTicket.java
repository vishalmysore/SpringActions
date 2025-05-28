package io.github.vishalmysore.service;

import com.t4a.annotations.Action;
import com.t4a.annotations.Agent;

import com.t4a.api.JavaMethodAction;

import com.t4a.detect.ActionCallback;
import com.t4a.detect.ActionState;
import com.t4a.processor.AIProcessor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

@Service
@Log
@Agent(groupName ="raiseTicket", groupDescription = "Create a ticket for customer")
public class RaiseCustomerTicket {
    /**
     * Each action has access to AIProcessor and ActionCallback which are autowired by tools4ai
     * Use ThreadLocal to store the ActionCallback for the current thread if you are concerned
     * about concurrency issues.
     */
    private static final ThreadLocal<ActionCallback> callbackThreadLocal = new ThreadLocal<>();

    /**
     * Each action has access to AIProcessor and ActionCallback which are autowired by tools4ai
     */
    private AIProcessor processor;
    @Action(description = "Raise a ticket for customer")
    public String raiseTicket(String customerName, String reason) {
        if(callbackThreadLocal.get() != null) {
            ActionCallback callback = callbackThreadLocal.get();
            log.info("callback is set "+callback);
            // you can call the callback to get the AIProcessor
            if(callback!=null) {
                //the spelling mistake in the message is intentional to test the callback and the ai
                callback.sendtStatus("raiseed ticket for will be resoved soon , t"+customerName+  " reason "+reason, ActionState.COMPLETED);
            }
        } else {
            log.warning("callback is not set");
        }
        return "ticket 111 raised for "+customerName;
    }
}
