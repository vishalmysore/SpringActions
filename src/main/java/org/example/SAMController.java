package org.example;

import com.t4a.predict.PredictionLoader;
import com.t4a.processor.AIProcessingException;
import com.t4a.processor.ActionProcessor;
import com.t4a.processor.SpringAwareActionProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * There are no services instance variables defined here, Spring + Tools4AI will dynamically figure out what to call
 * based on the prompt
 */

@RestController
public class SAMController {
    @Autowired
    private ApplicationContext applicationContext;
    @GetMapping("/action")
    public String actOnPrompt(@RequestParam("prompt") String prompt) {
        SpringAwareActionProcessor processor = new SpringAwareActionProcessor(applicationContext);
        try {
            return (String) processor.processSingleAction(prompt);
        } catch (AIProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    @PostMapping("/action")
    public String actOnPromptWithPost(  @RequestParam("car1") String car1,  @RequestParam("car2") String car2) {
        SpringAwareActionProcessor processor = new SpringAwareActionProcessor(applicationContext);
        try {
            return (String) processor.processSingleAction(" This is car1 "+car1+" this is car2 "+car2);
        } catch (AIProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
