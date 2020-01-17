package com.example.om.employeeasyncjpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@SpringBootApplication
@EnableAsync
public class EmployeeAsyncJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeAsyncJpaApplication.class, args);
	}

	@Bean
	public Executor taskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(Runtime.getRuntime().availableProcessors());
		executor.setMaxPoolSize(Runtime.getRuntime().availableProcessors()*4);
		executor.setQueueCapacity(10);
		executor.setWaitForTasksToCompleteOnShutdown(true);
		executor.setAwaitTerminationSeconds(30);
		executor.setThreadNamePrefix("employeeAsyncJPA-");
		executor.initialize();
		return executor;
	}
}
