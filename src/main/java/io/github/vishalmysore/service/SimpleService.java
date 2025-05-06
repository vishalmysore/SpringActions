package io.github.vishalmysore.service;

import com.t4a.annotations.Action;
import com.t4a.annotations.Agent;

import com.t4a.api.JavaMethodAction;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Agent(groupName ="whatThisPersonFavFood", groupDescription = "Provide persons name and then find out what does that person like")
@Slf4j
public class SimpleService implements JavaMethodAction {

    public SimpleService(){
      log.info(" Created Simple Service");
    }

    @Action(description = "Get the favourite food of a person")
    public String whatThisPersonFavFood(String name) {
        if("vishal".equalsIgnoreCase(name))
        return "Paneer Butter Masala";
        else if ("vinod".equalsIgnoreCase(name)) {
            return "aloo kofta";
        }else
            return "something yummy";
    }

}
