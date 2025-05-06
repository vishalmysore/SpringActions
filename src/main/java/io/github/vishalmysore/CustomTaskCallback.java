package io.github.vishalmysore;

import com.t4a.detect.ActionCallback;
import com.t4a.detect.ActionState;
import io.github.vishalmysore.common.A2AActionCallBack;

/**
 * This shows how to create a custom call back which can be used by actions
 */
public class CustomTaskCallback implements A2AActionCallBack {
    private String status;
    private Object context;

    @Override
    public void setContext(Object obj) {
       this.context = obj;
    }

    @Override
    public Object getContext() {
        return context;
    }



    @Override
    public void sendtStatus(String status, ActionState state) {

    }
}
