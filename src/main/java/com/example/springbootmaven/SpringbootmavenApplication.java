package com.example.springbootmaven;

import com.jam2in.arcus.app.common.config.ArcusConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigurationExcludeFilter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.TypeExcludeFilter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@SpringBootApplication
@ImportResource("classpath:applicationContext.xml")
public class SpringbootmavenApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootmavenApplication.class, args);
	}

}
