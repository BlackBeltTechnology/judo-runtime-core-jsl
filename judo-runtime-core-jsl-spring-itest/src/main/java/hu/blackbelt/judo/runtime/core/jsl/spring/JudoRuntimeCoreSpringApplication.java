package hu.blackbelt.judo.runtime.core.jsl.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@Slf4j
public class JudoRuntimeCoreSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(JudoRuntimeCoreSpringApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void ready() {
		log.info("Application ready");

	}
}
