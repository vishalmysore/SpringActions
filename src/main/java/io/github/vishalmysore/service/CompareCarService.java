package io.github.vishalmysore.service;

import com.t4a.annotations.Action;
import com.t4a.annotations.Agent;

import com.t4a.annotations.Prompt;
import com.t4a.api.JavaMethodAction;

import com.t4a.detect.ActionCallback;
import com.t4a.detect.ActionState;
import com.t4a.processor.AIProcessingException;
import com.t4a.processor.AIProcessor;
import com.t4a.processor.OpenAiActionProcessor;
import com.t4a.processor.ProcessorAware;
import io.github.vishalmysore.a2a.domain.Task;
import io.github.vishalmysore.a2a.domain.TaskState;
import io.github.vishalmysore.a2ui.A2UIAware;
import io.github.vishalmysore.common.CallBackType;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

/**
 * This is your A2A task or MCP tool which will be used to compare 2 cars
 * It will get automatically exposed as a tool in the A2A framework
 * This is the service class which will be used to compare 2 cars
 *
 * Each action has access to AIProcessor and ActionCallback which are autowired by tools4ai
 */
@Service
@Log
@Agent(groupName ="compareCar", groupDescription = "Provide 2 cars and compare them" ,prompt = "make sure you only provide non ev vehcile")
public class CompareCarService implements A2UIAware, ProcessorAware {
    public CompareCarService() {
        log.info("created compare car service");
    }



    @Action(description = "compare 2 cars",prompt = "dont provide colors")
    public String compareCar(@Prompt(describe = "use only car and not truck") String car1 , String car2) throws AIProcessingException {

        getProcessor().query("get me latest price of the toyota car after researching on the web");;
        log.info(car2);
        log.info(car1);
        // implement the comparison logic here
        if(getCallback()!=null) {
            getCallback().sendtStatus(car2+" is better than "+car1, ActionState.COMPLETED);
        }
        return " this is better - "+car2;
    }
}
