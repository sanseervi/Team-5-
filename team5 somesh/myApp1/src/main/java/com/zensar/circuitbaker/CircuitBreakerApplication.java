package com.zensar.circuitbaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@SpringBootApplication
@RestController
@EnableCircuitBreaker
@EnableHystrixDashboard
public class CircuitBreakerApplication {

	@Autowired
	RestTemplate restTemplate;
	
	@Bean
	public RestTemplate getRestTemplate()
	{
		return new RestTemplate();
		
	}
	
	public static void main(String[] args) {
		SpringApplication.run(CircuitBreakerApplication.class, args);
	}
	
	@RequestMapping(value="/my-circuit-breaker", method=RequestMethod.GET )
	@HystrixCommand(fallbackMethod="myfallBack")
	public String my_circuit_breaker()
	{
		return this.restTemplate.getForObject("http://localhost:8090/myapp", String.class);
	}
	
	public String myfallBack()
	{
		return "my fallBack is called";
	}
}