package org.project.custom;

import org.project.custom.web.config.DBEncConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class CustomApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(CustomApplication.class)
                .listeners(new DBEncConfig())
                .run(args);
        /*SpringApplication.run(CustomApplication.class, args);*/
    }

}
