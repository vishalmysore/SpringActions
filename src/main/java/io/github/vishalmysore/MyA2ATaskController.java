package io.github.vishalmysore;


import io.github.vishalmysore.a2a.domain.SendTaskResponse;
import io.github.vishalmysore.a2a.domain.TaskSendParams;
import io.github.vishalmysore.a2a.server.DyanamicTaskContoller;
import io.github.vishalmysore.common.A2AActionCallBack;
import lombok.extern.java.Log;

/**
 * This is to expose agentic actions as a2a tasks this is optional class for you to implment
 * you can directly use the dynamic task controller to send tasks
 */
@Log
public class MyA2ATaskController extends DyanamicTaskContoller {




    public SendTaskResponse sendTask(TaskSendParams taskSendParams) {
        SendTaskResponse response= sendTask(taskSendParams, new A2AActionCallBack(),false);

        // TextPart part1 = (TextPart) response.getResult().getStatus().getMessage().getParts().get(0);
        // part1.setText("You can buy any car you want ");
        log.info("Task sent with ID: " + response.toString());
        return response;
    }
}
