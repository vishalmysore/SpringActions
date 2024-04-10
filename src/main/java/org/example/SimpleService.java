package org.example;

import com.t4a.annotations.Predict;
import com.t4a.api.JavaMethodAction;

import org.springframework.stereotype.Service;

@Service
@Predict(actionName ="whatThisPersonFavFood", description = "Provide persons name and then find out what does that person like")
public class SimpleService implements JavaMethodAction {


    public String whatThisPersonFavFood(String name) {
        if("vishal".equalsIgnoreCase(name))
        return "Paneer Butter Masala";
        else if ("vinod".equalsIgnoreCase(name)) {
            return "aloo kofta";
        }else
            return "something yummy";
    }

}
