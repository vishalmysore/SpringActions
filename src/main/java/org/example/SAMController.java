package org.example;

import com.t4a.predict.PredictionLoader;
import com.t4a.processor.AIProcessingException;
import com.t4a.processor.ActionProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@RestController
public class SAMController {
    @Autowired
    private ApplicationContext applicationContext;
    @GetMapping("/action")
    public String actOnPrompt(@RequestParam("prompt") String prompt) {

        PredictionLoader.getInstance(applicationContext);
        ActionProcessor processor = new ActionProcessor();
        try {
            return (String) processor.processSingleAction(prompt);
        } catch (AIProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    @PostMapping("/action")
    public String actOnPromptWithPost(  @RequestParam("car1") String car1,  @RequestParam("car2") String car2) {
        System.out.println(applicationContext);
        System.out.println(PredictionLoader.getInstance(applicationContext).getActionNameList());
        ActionProcessor processor = new ActionProcessor();
        try {
            return (String) processor.processSingleAction(" This is car1 "+car1+" this is car2 "+car2);
        } catch (AIProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
