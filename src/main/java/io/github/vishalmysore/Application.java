package io.github.vishalmysore;


import io.github.vishalmysore.tools4ai.EnableAgent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAgent
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
