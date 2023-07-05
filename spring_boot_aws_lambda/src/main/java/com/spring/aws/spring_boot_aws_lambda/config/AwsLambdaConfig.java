package com.spring.aws.spring_boot_aws_lambda.config;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import javax.servlet.Filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.spring.aws.spring_boot_aws_lambda.domain.Car;

@Configuration
public class AwsLambdaConfig {

    @Bean
    public Filter getfilter(){
        return new SecurityFilter();
    }
    
    @Bean("greetingWorld")
    public Supplier<String> greeting(){
        return ()->"Hello world";
    }

    @Bean
    public Consumer<String> printParam(){
        return (name)->{
            System.out.println("your name is: ".concat(name));
        };
    }

    @Bean
    public Function<String,String> receiveNameAndReturn(){
        return (name)->{
            StringBuilder response = new StringBuilder();
            response.append("your name in upper case is: ").append(name);
            return response.toString();
        };
    }


    //generate JSON
    @Bean
    public Supplier<Map<String,Object>> createCar(){
        return ()->{
            Map<String,Object> car = new HashMap<>();
            
            car.put("id",123456);
            car.put("name", "Civic");
            car.put("brand", "Honda");
            car.put("year", 2023);
            car.put("price", 540000);
            return car;
        };
    }

    // Receive a JSON and return String
    @Bean
    public Function<Map<String, Object>, String> receiveCar(){
        return (param) -> {
            param.forEach( (key, value) -> System.out.println(key + " - " + value) );
            return "Car received";
        };
    }

    // Receive an OBJECT and return an OBJECT
    @Bean
    public Function<Car, Car> receiveAnObject(){
        return (param) -> {
          return param;
        };
    }

    // Receive a JSON and return a JSON
    @Bean
    public Function<Map<String, Object>, Map<String, Object>> processCars(){
        return (param) -> {
            Map<String, Object> mapCar = param;

            mapCar.forEach( (key, value) -> System.out.println(key + " - " + value) );

            Map<String, Object> newCar = new HashMap<>();
            newCar.put("id", 2);
            newCar.put("name", "Accord");
            newCar.put("brand", "Honda");
            newCar.put("price", 640000);
            newCar.put("year", 2023);
            newCar.put("price", 540000);

            return newCar;
        };
    }
}
