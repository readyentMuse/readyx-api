package com.readyent.readyx;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReadyxApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReadyxApplication.class, args);
	}
}
