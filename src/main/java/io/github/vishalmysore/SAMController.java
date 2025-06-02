package io.github.vishalmysore;


import com.t4a.api.ActionGroup;
import com.t4a.api.GroupInfo;

import com.t4a.predict.PredictionLoader;

import com.t4a.processor.*;

import com.t4a.processor.spring.SpringGeminiProcessor;
import com.t4a.processor.spring.SpringOpenAIProcessor;
import com.t4a.transform.GeminiV2PromptTransformer;
import com.t4a.transform.PromptTransformer;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.java.Log;
import io.github.vishalmysore.pojo.Customer;
import io.github.vishalmysore.pojo.RestaurantPojo;
import io.github.vishalmysore.service.RestaurantBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * This is the expose the agentic actions without any protocol
 * There are no services instance variables defined here, Spring + Tools4AI will dynamically figure out what to call
 * based on the prompt
 */
@Log
@RestController
public class SAMController {

    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private RestaurantBookingService restaurantBookingService;
    @Operation(summary = "Execute any action based on prompt", description = " Try out with any of these prompts <br>" +
            " 1) My Customer name is Vishal , his computer needs repair <br>" +
            " 2) Can you compare Honda City to Toyota Corolla <br>" +
            " 3) Can i Go out without Jacket in Toronto today <br> " +
            " 4) what would vishal want to eat today? "
    )
    @ApiResponses(value = {


    })
    @GetMapping("/action")
    public String actOnPrompt(@RequestParam("prompt") String prompt) {
        AIProcessor processor = new SpringGeminiProcessor(applicationContext);
        try {
            return (String) processor.processSingleAction(prompt);
        } catch (AIProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    @Operation(summary = "Execute any action based on prompt", description = " Try out with any of these prompts <br>" +
            " 1) My Customer name is Vishal , his computer needs repair <br>" +
            " 2) Can you compare Honda City to Toyota Corolla <br>" +
            " 3) Can i Go out without Jacket in Toronto today <br> " +
            " 4) what would vishal want to eat today? "
    )
    @ApiResponses(value = {


    })
    @GetMapping("/actionOpenAI")
    public String actOnPromptWithOpenAI(@RequestParam("prompt") String prompt) {
        AIProcessor processor = new SpringOpenAIProcessor(applicationContext);
        try {

            Object object = processor.processSingleAction(prompt);
            String answer =  processor.query(prompt,(String)object);
            return answer;
        } catch (AIProcessingException e) {
            throw new RuntimeException(e);
        }

    }

  //  @PostMapping("/action")
    public String actOnPromptWithPost(  @RequestParam("car1") String car1,  @RequestParam("car2") String car2) {
        AIProcessor processor = new SpringGeminiProcessor(applicationContext);
        try {
            return (String) processor.processSingleAction(" This is car1 "+car1+" this is car2 "+car2);
        } catch (AIProcessingException e) {
            throw new RuntimeException(e);
        }
    }

  //  @GetMapping("/bookRestaurant")
    public String bookRestaurant(@RequestParam("prompt") String prompt) {
        PromptTransformer processor = new GeminiV2PromptTransformer();
        try {
            return restaurantBookingService.bookReservation((RestaurantPojo) processor.transformIntoPojo(prompt,RestaurantPojo.class.getName(),"RestaurantPojo","Build the pojo for restaurant") );
        } catch (AIProcessingException e) {
            throw new RuntimeException(e);
        }

    }

  //  @GetMapping("/bookRestaurantWithDetails")
    public String findCustomerDetails(@RequestParam("restaurantDetails") String restaurantDetails,@RequestParam("customerDetails") String customerDetails) {
        PromptTransformer processor = new GeminiV2PromptTransformer();
        try {
            Customer customer = (Customer)processor.transformIntoPojo(customerDetails,Customer.class.getName(),"Customer","Get the customer details");
            log.info(customer.toString());
            return restaurantBookingService.bookReservation((RestaurantPojo)processor.transformIntoPojo(restaurantDetails,RestaurantPojo.class.getName(),"RestaurantPojo","Build the pojo for restaurant") );
        } catch (AIProcessingException e) {
            throw new RuntimeException(e);
        }

    }


    @Operation(summary = "Get All the actions available for a particular group")
    @GetMapping("/getAllActionsForGroup")
    public String getAllActions(String groupName) {
        return PredictionLoader.getInstance().getActionGroupList().getGroupActions().get((new ActionGroup(groupName)).getGroupInfo());
    }
    @Operation(summary = "Provide all the group names - this could be your enterprise applications expsosing rest apis")
    @GetMapping("/getAllGroupNames")
    public List<GroupInfo> getAllActionGroups() {
        return PredictionLoader.getInstance().getActionGroupList().getGroupInfo();
    }

   // @GetMapping("/actionWithName")
    public String actOnPrompt(@RequestParam("prompt") String prompt, @RequestParam("actionName") String actionName) {
        AIProcessor processor = new SpringGeminiProcessor(applicationContext);
        try {
            return (String) processor.processSingleAction(prompt,actionName);
        } catch (AIProcessingException e) {
            throw new RuntimeException(e);
        }

    }


}
