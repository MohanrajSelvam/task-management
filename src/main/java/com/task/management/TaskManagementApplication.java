package com.task.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


/**
 * <p>
 * TaskManagementApplication Spring-Boot Application class.
 * </p>
 *
 * @author Mohanraj Created on March 21, 2018.
 */
@SpringBootApplication
@ComponentScan(value = "com.task.management")
public class TaskManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskManagementApplication.class, args);
	}
}
