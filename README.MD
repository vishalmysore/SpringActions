## Spring Action

Integrate your spring application with AI actions , uses Tools4AI platform 

Any Spring bean will be used as aciton for matching prompt , you dont need to define it separately 

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

## set up
``` mvn clean install```

and then
```mvn spring-boot:run```

<img src="swagger.png">

