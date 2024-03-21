package org.example;

import com.t4a.api.JavaMethodAction;
import com.t4a.predict.Predict;
import org.springframework.stereotype.Service;

@Service
@Predict(actionName ="whatFoodDoesThisPersonLike", description = "Provide persons name and then find out what does that person like")
public class SimpleService implements JavaMethodAction {


    public String whatFoodDoesThisPersonLike(String name) {
        if("vishal".equalsIgnoreCase(name))
        return "Paneer Butter Masala";
        else if ("vinod".equalsIgnoreCase(name)) {
            return "aloo kofta";
        }else
            return "something yummy";
    }

}
