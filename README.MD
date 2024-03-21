## Spring Action

Integrate your spring application with AI actions , uses Tools4AI platform 

Any Spring bean can be used as an action for matching prompt , you dont need to define it separately  , for example 
here we are creating a spring service called Compare car service, this will compare 2 cars and return the best car (in real world)

```
@Service
@Predict(actionName ="compareCar", description = "Provide persons name and then find out what does that person like")
public class CompareCarService implements JavaMethodAction {
    public String compareCar(String car1 , String car2) {
        System.out.println("this is car 1 "+car1);
        System.out.println("this is car 2"+car2);
        return car2;
    }
}

```

At runtime spring will create this service and call it with appropriate values if the prompt matches the action

```
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
```
As you can see the controller does not need to have the service instances , its mapped by Tools4AI automatically based on prompts

## set up
``` mvn clean install```

and then
```mvn spring-boot:run```

Once you visit http://localhost:8080/swagger-ui/index.html and provide any prompt appropriate action will be called 


<img src="swagger.PNG">

