package com.example;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Lenovo
 */
@SpringBootApplication(scanBasePackages = "com.example.*")
public class Application {

  public static void main(String... args) {
    SpringApplication.run(Application.class, args);
  }

}