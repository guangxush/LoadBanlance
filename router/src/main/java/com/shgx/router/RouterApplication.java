package com.shgx.router;

import com.shgx.router.listener.MyListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RouterApplication {

    public static void main(String[] args) {
        SpringApplication.run(RouterApplication.class, args);
        SpringApplication springApplication = new SpringApplication(RouterApplication.class);
        springApplication.addListeners(new MyListener(), new MyListener());
        //springApplication.run(args);
    }

}
