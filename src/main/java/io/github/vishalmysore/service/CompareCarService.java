package io.github.vishalmysore.service;

import com.t4a.annotations.Action;
import com.t4a.annotations.Agent;

import com.t4a.api.JavaMethodAction;

import com.t4a.detect.ActionCallback;
import com.t4a.detect.ActionState;
import io.github.vishalmysore.a2a.domain.Task;
import io.github.vishalmysore.a2a.domain.TaskState;
import io.github.vishalmysore.common.CallBackType;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

/**
 * This is your A2A task or MCP tool which will be used to compare 2 cars
 * It will get automatically exposed as a tool in the A2A framework
 * This is the service class which will be used to compare 2 cars
 *
 */
@Service
@Log
@Agent(groupName ="compareCar", groupDescription = "Provide 2 cars and compare them")
public class CompareCarService{
    public CompareCarService() {
        log.info("created compare car service");
    }
    private ActionCallback callback;

    @Action(description = "compare 2 cars")
    public String compareCar(String car1 , String car2) {

        log.info(car2);
        log.info(car1);
        // implement the comparison logic here
        if(callback!=null) {
            callback.sendtStatus(car2+" is better than "+car1, ActionState.COMPLETED);
        }
        return " this is better - "+car2;
    }
}
