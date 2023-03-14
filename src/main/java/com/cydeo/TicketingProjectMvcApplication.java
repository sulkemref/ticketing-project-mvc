package com.cydeo;

import com.cydeo.service.ProjectService;
import com.cydeo.service.impl.ProjectServiceImpl;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class TicketingProjectMvcApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(TicketingProjectMvcApplication.class, args);

//        ProductService productService = context.getBean(ProductServiceImpl.class);

        ProjectService projectService = context.getBean(ProjectServiceImpl.class);

        System.out.println( projectService.findAll());
    }

}
