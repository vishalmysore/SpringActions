## Spring Action

Integrate your spring application with AI actions , uses Tools4AI (https://github.com/vishalmysore/Tools4AI/) platform 

Any Spring bean can be used as an action for matching prompt , you dont need to define it separately  , for example 
here we are creating a spring service called Compare car service, this will compare 2 cars and return the best car (in real world)

```
@Service
@Log
@Predict(actionName ="compareCar", description = "Provide 2 cars and compare them")
public class CompareCarService implements JavaMethodAction {
    public String compareCar(String car1 , String car2) {
        log.info(car2);
        log.info(car1);
        // implement the comparison logic here
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
        SpringAwareActionProcessor processor = new SpringAwareActionProcessor(applicationContext);
        try {
            return (String) processor.processSingleAction(prompt);
        } catch (AIProcessingException e) {
            throw new RuntimeException(e);
        }

    }
```
As you can see the controller does not need to have the service instances , its mapped by Tools4AI automatically based on prompts
At runtime ```SpringAwareActionProcessor``` takes care of mapping prompts to Spring Beans , they can either be spring bean service
or a component

Also you can convert any prompt to Java Pojo

``` 
String promptText ="can you book maharaja restaurant for 4 people on Indian Independence day in Toronto , my name is Vishal ";
PojoBuilder processor = new PojoBuilder();
RestaurantPojo pojo = (RestaurantPojo)processor.buildPojo(promptText,RestaurantPojo.class.getName(),"RestaurantPojo","Build the pojo for restaurant")
```

## set up
``` mvn clean install```

and then
```mvn spring-boot:run```

Once you visit http://localhost:8080/swagger-ui/index.html and provide any prompt appropriate action will be called 

For example in below case car compare service will be called automatically based on the prompt

<img src="swagger.PNG">

Sending Multiple Prompts and converting them into Pojo directly before calling service 

<img src="swagger2.PNG">

