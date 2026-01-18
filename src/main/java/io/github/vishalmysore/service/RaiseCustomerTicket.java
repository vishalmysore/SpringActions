package io.github.vishalmysore.service;

import com.t4a.annotations.Action;
import com.t4a.annotations.Agent;

import com.t4a.api.JavaMethodAction;

import com.t4a.detect.ActionCallback;
import com.t4a.detect.ActionState;
import com.t4a.processor.AIProcessor;
import com.t4a.processor.ProcessorAware;
import io.github.vishalmysore.a2ui.A2UIAware;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

@Service
@Log
@Agent(groupName ="raiseTicket", groupDescription = "Create a ticket for customer")
public class RaiseCustomerTicket implements A2UIAware, ProcessorAware {
    /**
     * Each action has access to AIProcessor and ActionCallback which are autowired by tools4ai
     * Use ThreadLocal to store the ActionCallback for the current thread if you are concerned
     * about concurrency issues.
     */

    /**
     * Each action has access to AIProcessor and ActionCallback which are autowired by tools4ai
     */

    @Action(description = "Raise a ticket for customer")
    public String raiseTicket(String customerName, String reason) {
        if(getCallback() != null) {
            ActionCallback callback = getCallback();
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
