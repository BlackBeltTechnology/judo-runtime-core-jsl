package hu.blackbelt.judo.runtime.core.jsl.spring;

import hu.blackbelt.judo.dispatcher.api.Dispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JudoRuntimeCoreSpringApplication {

	@Autowired
	Dispatcher dispatcher;

	public static void main(String[] args) {
		SpringApplication.run(JudoRuntimeCoreSpringApplication.class, args);
	}

}
