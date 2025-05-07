##  Google A2A Integration with Spring Boot
Integrate your Spring Boot applications with Google's A2A Protocol using a2ajava. This integration allows any Spring bean to be exposed as an AI-actionable service—no need for extra annotations or custom wiring. 

✅ Key Features
- Auto-discovery of Spring Beans as A2A Actions

- Built-in support for prompt-based execution

- Zero boilerplate integration using @Agent and @Action annotations

- Ideal for building intelligent microservices and AI-enhanced APIs

### Example: Compare Two Cars with AI-Driven Spring Bean

```
@Service
@Log
@Agent(groupName ="compareCar", groupDescription = "Provide 2 cars and compare them")
public class CompareCarService implements JavaMethodAction {
    public CompareCarService() {
        log.info("created compare car service");
    }
    private ActionCallback callback;

    @Action(description = "compare 2 cars")
    public String compareCar(String car1 , String car2) {

        log.info(car2);
        log.info(car1);
        // implement the comparison logic here
        if((callback!= null) && (callback.getType().equals(CallBackType.A2A.name()))) {
            log.info("callback is not null");
            ((Task) callback.getContext()).setDetailedAndMessage(TaskState.COMPLETED, "I compared the car and this is better "+car2 );
        }
        return " this is better - "+car2;
    }
}


```

-  A2A card will be available here http://localhost:7860/.well-known/agent.json
-  MCP Tools will be available here http://localhost:7860/mcp/list-tools


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


## 📝 set up
``` mvn clean install```

and then
```mvn spring-boot:run```
