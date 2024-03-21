package org.example;

import com.t4a.api.JavaMethodAction;
import com.t4a.predict.Predict;
import org.springframework.stereotype.Service;

@Service
@Predict(actionName ="compareCar", description = "Provide persons name and then find out what does that person like")
public class CompareCarService implements JavaMethodAction {
    public String compareCar(String car1 , String car2) {
        System.out.println("this is car 1 "+car1);
        System.out.println("this is car 2"+car2);
        return car2;
    }
}
