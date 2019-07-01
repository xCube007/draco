package com.universe.draco;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DracoApplication {

    private static final Logger logger = LoggerFactory.getLogger(DracoApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DracoApplication.class, args);
        logger.info("========================启动完毕========================");
    }

}
