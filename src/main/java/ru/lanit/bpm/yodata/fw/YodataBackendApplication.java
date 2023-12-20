package ru.lanit.bpm.yodata.fw;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "ru.lanit.bpm.yodata")
@EntityScan(basePackages = "ru.lanit.bpm.yodata.domain")
@EnableJpaRepositories(basePackages = {
    "ru.lanit.bpm.yodata.app.repo", "ru.lanit.bpm.yodata.adapter.hibernate"
})
@EnableProcessApplication
public class YodataBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(YodataBackendApplication.class, args);
    }
}
