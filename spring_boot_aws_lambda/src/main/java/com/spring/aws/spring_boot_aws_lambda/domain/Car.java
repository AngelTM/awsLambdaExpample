package com.spring.aws.spring_boot_aws_lambda.domain;

import lombok.Data;

@Data
public class Car {

    private Long id;
    private String name;
    private String brand;
    private int year;
    private Double price;

}
