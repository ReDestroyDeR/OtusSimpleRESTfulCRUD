package ru.red.otussimplerestfulcrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@EnableR2dbcRepositories
@SpringBootApplication
@EnableAspectJAutoProxy
public class OtusSimpleRESTfulCRUD {

    public static void main(String[] args) {
        SpringApplication.run(OtusSimpleRESTfulCRUD.class, args);
    }

}


