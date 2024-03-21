package org.example;

import com.t4a.api.JavaMethodAction;
import com.t4a.predict.Predict;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

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
